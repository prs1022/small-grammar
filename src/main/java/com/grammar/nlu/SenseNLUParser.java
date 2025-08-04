package com.grammar.nlu;

import com.grammar.grammar.GrammarParser;
import com.grammar.grammar.ParseTreeAnalyzer;
import com.grammar.antlr.*;
import org.antlr.v4.runtime.tree.*;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Enhanced NLU parser specifically designed for Sense grammar
 */
public class SenseNLUParser {
    
    private final GrammarParser grammarParser;
    private ParseTree grammarTree;
    private Map<String, IntentRule> intentRules;
    private Map<String, List<String>> primeDefinitions;
    private Map<String, List<String>> productionDefinitions;
    
    public SenseNLUParser() {
        this.grammarParser = new GrammarParser();
        this.intentRules = new HashMap<>();
        this.primeDefinitions = new HashMap<>();
        this.productionDefinitions = new HashMap<>();
    }
    
    /**
     * Load grammar rules from string
     */
    public boolean loadGrammar(String grammarText) {
        grammarTree = grammarParser.parseGrammar(grammarText);
        
        if (grammarParser.hasErrors()) {
            return false;
        }
        
        extractGrammarInfo();
        return true;
    }
    
    /**
     * Parse natural language text and extract intent and slots
     */
    public NLUResult parseText(String text) {
        if (grammarTree == null) {
            throw new IllegalStateException("Grammar not loaded");
        }
        
        String normalizedText = text.toLowerCase().trim();
        
        // Try to match against all intent rules
        for (IntentRule rule : intentRules.values()) {
            NLUResult result = tryMatchIntentRule(normalizedText, rule);
            if (result != null) {
                return result;
            }
        }
        
        return new NLUResult("unknown", new ArrayList<>());
    }
    
    /**
     * Extract grammar information from parse tree
     */
    private void extractGrammarInfo() {
        // Extract productions
        List<ParseTreeAnalyzer.ProductionInfo> productions = 
            ParseTreeAnalyzer.extractProductions(grammarTree);
        
        for (ParseTreeAnalyzer.ProductionInfo prod : productions) {
            String intentValue = extractIntentFromAnnotations(prod.annotations);
            boolean isStatement = hasStatementAnnotation(prod.annotations);
            
            if (intentValue != null && isStatement) {
                IntentRule rule = new IntentRule(prod.name, intentValue, prod.expression);
                intentRules.put(prod.name, rule);
            }
            
            // Store all productions for reference resolution
            productionDefinitions.put(prod.name, parseChoices(prod.expression));
        }
        
        // Extract primes
        List<ParseTreeAnalyzer.PrimeInfo> primes = 
            ParseTreeAnalyzer.extractPrimes(grammarTree);
        
        for (ParseTreeAnalyzer.PrimeInfo prime : primes) {
            primeDefinitions.put(prime.name, parseChoices(prime.expression));
        }
    }
    
    /**
     * Extract intent value from annotations
     */
    private String extractIntentFromAnnotations(List<String> annotations) {
        for (String annotation : annotations) {
            if (annotation.startsWith("@intent")) {
                // Handle @intent(value="WeatherSearch") format
                Pattern pattern = Pattern.compile("@intent\\s*\\(\\s*value\\s*=\\s*\"([^\"]+)\"\\s*\\)");
                Matcher matcher = pattern.matcher(annotation);
                if (matcher.find()) {
                    return matcher.group(1);
                }
            }
        }
        return null;
    }
    
    /**
     * Check if annotations contain @statement
     */
    private boolean hasStatementAnnotation(List<String> annotations) {
        return annotations.stream().anyMatch(ann -> ann.startsWith("@statement"));
    }
    
    /**
     * Try to match text against an intent rule
     */
    private NLUResult tryMatchIntentRule(String text, IntentRule rule) {
        List<Slot> slots = new ArrayList<>();
        
        if (matchExpression(text, rule.expression, slots)) {
            return new NLUResult(rule.intentValue, slots);
        }
        
        return null;
    }
    
    /**
     * Match text against an expression pattern
     */
    private boolean matchExpression(String text, String expression, List<Slot> slots) {
        // Parse the expression into components
        List<ExpressionComponent> components = parseExpression(expression);
        
        String[] words = text.split("\\s+");
        int wordIndex = 0;
        
        for (ExpressionComponent component : components) {
            int matchedWords = matchComponent(words, wordIndex, component, slots);
            if (matchedWords == -1) {
                if (!component.isOptional) {
                    return false;
                }
                // Optional component didn't match, continue
            } else {
                wordIndex += matchedWords;
            }
        }
        
        // Check if we consumed most of the input
        return wordIndex >= words.length - 1; // Allow for some flexibility
    }
    
    /**
     * Match a single expression component
     */
    private int matchComponent(String[] words, int startIndex, ExpressionComponent component, List<Slot> slots) {
        if (component.type == ComponentType.PRIME) {
            return matchPrime(words, startIndex, component.value, slots);
        } else if (component.type == ComponentType.REFERENCE) {
            return matchReference(words, startIndex, component.value);
        } else if (component.type == ComponentType.LITERAL) {
            return matchLiteral(words, startIndex, component.value);
        } else if (component.type == ComponentType.CHOICE) {
            return matchChoice(words, startIndex, component.choices);
        }
        
        return -1;
    }
    
    /**
     * Match a prime (slot)
     */
    private int matchPrime(String[] words, int startIndex, String primeName, List<Slot> slots) {
        List<String> primeValues = primeDefinitions.get(primeName);
        if (primeValues == null) {
            return -1;
        }
        
        for (String value : primeValues) {
            String cleanValue = value.replaceAll("\"", "").trim();
            String[] valueWords = cleanValue.split("\\s+");
            
            if (matchSequence(words, startIndex, valueWords)) {
                slots.add(new Slot(primeName, cleanValue));
                return valueWords.length;
            }
        }
        
        return -1;
    }
    
    /**
     * Match a production reference
     */
    private int matchReference(String[] words, int startIndex, String refName) {
        List<String> refValues = productionDefinitions.get(refName);
        if (refValues == null) {
            return -1;
        }
        
        for (String value : refValues) {
            String cleanValue = value.replaceAll("\"", "").trim();
            String[] valueWords = cleanValue.split("\\s+");
            
            if (matchSequence(words, startIndex, valueWords)) {
                return valueWords.length;
            }
        }
        
        return -1;
    }
    
    /**
     * Match a literal string
     */
    private int matchLiteral(String[] words, int startIndex, String literal) {
        String cleanLiteral = literal.replaceAll("\"", "").trim();
        String[] literalWords = cleanLiteral.split("\\s+");
        
        if (matchSequence(words, startIndex, literalWords)) {
            return literalWords.length;
        }
        
        return -1;
    }
    
    /**
     * Match a choice expression
     */
    private int matchChoice(String[] words, int startIndex, List<String> choices) {
        for (String choice : choices) {
            String cleanChoice = choice.replaceAll("\"", "").trim();
            String[] choiceWords = cleanChoice.split("\\s+");
            
            if (matchSequence(words, startIndex, choiceWords)) {
                return choiceWords.length;
            }
        }
        
        return -1;
    }
    
    /**
     * Check if a sequence of words matches at a given position
     */
    private boolean matchSequence(String[] words, int startIndex, String[] sequence) {
        if (startIndex + sequence.length > words.length) {
            return false;
        }
        
        for (int i = 0; i < sequence.length; i++) {
            if (!words[startIndex + i].equalsIgnoreCase(sequence[i])) {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * Parse expression into components
     */
    private List<ExpressionComponent> parseExpression(String expression) {
        List<ExpressionComponent> components = new ArrayList<>();
        
        // Simple parsing - split by spaces and identify components
        String[] tokens = expression.split("\\s+");
        
        for (String token : tokens) {
            token = token.trim();
            if (token.isEmpty()) continue;
            
            boolean isOptional = token.endsWith("?");
            if (isOptional) {
                token = token.substring(0, token.length() - 1);
            }
            
            ExpressionComponent component = new ExpressionComponent();
            component.isOptional = isOptional;
            
            if (token.startsWith("<") && token.endsWith(">")) {
                // Prime
                component.type = ComponentType.PRIME;
                component.value = token.substring(1, token.length() - 1);
            } else if (token.startsWith("\"") && token.endsWith("\"")) {
                // Literal
                component.type = ComponentType.LITERAL;
                component.value = token;
            } else if (token.contains("|")) {
                // Choice
                component.type = ComponentType.CHOICE;
                component.choices = Arrays.asList(token.split("\\|"));
            } else {
                // Reference
                component.type = ComponentType.REFERENCE;
                component.value = token;
            }
            
            components.add(component);
        }
        
        return components;
    }
    
    /**
     * Parse choices from expression (for simple cases)
     */
    private List<String> parseChoices(String expression) {
        if (expression.contains("|")) {
            return Arrays.asList(expression.split("\\|"));
        } else {
            return Arrays.asList(expression);
        }
    }
    
    public List<String> getErrors() {
        return grammarParser.getErrors();
    }
    
    public boolean hasErrors() {
        return grammarParser.hasErrors();
    }
    
    // Helper classes
    private static class IntentRule {
        final String name;
        final String intentValue;
        final String expression;
        
        IntentRule(String name, String intentValue, String expression) {
            this.name = name;
            this.intentValue = intentValue;
            this.expression = expression;
        }
    }
    
    private static class ExpressionComponent {
        ComponentType type;
        String value;
        List<String> choices;
        boolean isOptional = false;
    }
    
    private enum ComponentType {
        PRIME, REFERENCE, LITERAL, CHOICE
    }
}