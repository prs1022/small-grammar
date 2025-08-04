package com.grammar.nlu;

import com.grammar.grammar.GrammarParser;
import com.grammar.grammar.ParseTreeAnalyzer;
import com.grammar.antlr.*;
import org.antlr.v4.runtime.tree.*;
import java.util.*;

/**
 * Natural Language Understanding parser that extracts intents and slots
 * from natural language text using Sense grammar rules
 */
public class NLUParser {
    
    private final GrammarParser grammarParser;
    private ParseTree grammarTree;
    private Map<String, String> intentMappings;
    private Map<String, List<String>> primeDefinitions;
    private Map<String, List<String>> productionDefinitions;
    
    public NLUParser() {
        this.grammarParser = new GrammarParser();
        this.intentMappings = new HashMap<>();
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
        
        // Extract grammar information
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
        
        // Try to match against all statement rules
        for (var entry : intentMappings.entrySet()) {
            String ruleName = entry.getKey();
            String intentValue = entry.getValue();
            
            NLUResult result = tryMatchRule(text, ruleName, intentValue);
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
        // Extract productions and their intent annotations
        List<ParseTreeAnalyzer.ProductionInfo> productions = 
            ParseTreeAnalyzer.extractProductions(grammarTree);
        
        for (ParseTreeAnalyzer.ProductionInfo prod : productions) {
            // Extract intent annotation
            String intentValue = extractIntentFromAnnotations(prod.annotations);
            if (intentValue != null) {
                intentMappings.put(prod.name, intentValue);
            }
            
            // Store production definition for matching
            List<String> tokens = parseExpression(prod.expression);
            productionDefinitions.put(prod.name, tokens);
        }
        
        // Extract primes
        List<ParseTreeAnalyzer.PrimeInfo> primes = 
            ParseTreeAnalyzer.extractPrimes(grammarTree);
        
        for (ParseTreeAnalyzer.PrimeInfo prime : primes) {
            List<String> tokens = parseExpression(prime.expression);
            primeDefinitions.put(prime.name, tokens);
        }
    }
    
    /**
     * Extract intent value from annotations
     */
    private String extractIntentFromAnnotations(List<String> annotations) {
        for (String annotation : annotations) {
            if (annotation.contains("@intent")) {
                // Parse @intent(value="WeatherSearch") format
                int start = annotation.indexOf("value=\"");
                if (start != -1) {
                    start += 7; // length of "value=\""
                    int end = annotation.indexOf("\"", start);
                    if (end != -1) {
                        return annotation.substring(start, end);
                    }
                }
                // Parse @intent format (use rule name as intent)
                return null;
            }
        }
        return null;
    }
    
    /**
     * Try to match text against a specific rule
     */
    private NLUResult tryMatchRule(String text, String ruleName, String intentValue) {
        List<String> ruleTokens = productionDefinitions.get(ruleName);
        if (ruleTokens == null) {
            return null;
        }
        
        // Simple pattern matching implementation
        List<Slot> slots = new ArrayList<>();
        String[] words = text.toLowerCase().split("\\s+");
        
        // Try to match the pattern
        if (matchPattern(words, ruleTokens, slots)) {
            return new NLUResult(intentValue, slots);
        }
        
        return null;
    }
    
    /**
     * Match words against rule pattern and extract slots
     */
    private boolean matchPattern(String[] words, List<String> pattern, List<Slot> slots) {
        int wordIndex = 0;
        
        for (String token : pattern) {
            if (token.startsWith("<") && token.endsWith(">")) {
                // This is a prime (slot)
                String primeName = token.substring(1, token.length() - 1);
                List<String> primeValues = primeDefinitions.get(primeName);
                
                if (primeValues != null) {
                    boolean matched = false;
                    for (String value : primeValues) {
                        String[] valueWords = value.replaceAll("\"", "").split("\\s+");
                        if (matchSequence(words, wordIndex, valueWords)) {
                            slots.add(new Slot(primeName, value.replaceAll("\"", "")));
                            wordIndex += valueWords.length;
                            matched = true;
                            break;
                        }
                    }
                    if (!matched) {
                        return false;
                    }
                }
            } else if (token.contains("|")) {
                // Choice expression
                String[] choices = token.split("\\|");
                boolean matched = false;
                for (String choice : choices) {
                    String cleanChoice = choice.trim().replaceAll("\"", "");
                    String[] choiceWords = cleanChoice.split("\\s+");
                    if (matchSequence(words, wordIndex, choiceWords)) {
                        wordIndex += choiceWords.length;
                        matched = true;
                        break;
                    }
                }
                if (!matched && !token.contains("?")) {
                    return false;
                }
            } else if (token.endsWith("?")) {
                // Optional token
                String optionalToken = token.substring(0, token.length() - 1);
                String cleanToken = optionalToken.replaceAll("\"", "");
                String[] tokenWords = cleanToken.split("\\s+");
                if (matchSequence(words, wordIndex, tokenWords)) {
                    wordIndex += tokenWords.length;
                }
                // If optional token doesn't match, continue
            } else {
                // Literal token
                String cleanToken = token.replaceAll("\"", "");
                String[] tokenWords = cleanToken.split("\\s+");
                if (!matchSequence(words, wordIndex, tokenWords)) {
                    return false;
                }
                wordIndex += tokenWords.length;
            }
        }
        
        return wordIndex <= words.length;
    }
    
    /**
     * Check if a sequence of words matches at a given position
     */
    private boolean matchSequence(String[] words, int startIndex, String[] sequence) {
        if (startIndex + sequence.length > words.length) {
            return false;
        }
        
        for (int i = 0; i < sequence.length; i++) {
            if (!words[startIndex + i].equals(sequence[i].toLowerCase())) {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * Parse expression into tokens (simplified)
     */
    private List<String> parseExpression(String expression) {
        List<String> tokens = new ArrayList<>();
        
        // Simple tokenization - in a real implementation, 
        // you'd use the ANTLR parse tree
        String[] parts = expression.split("\\s+");
        for (String part : parts) {
            if (!part.trim().isEmpty()) {
                tokens.add(part.trim());
            }
        }
        
        return tokens;
    }
    
    /**
     * Get parsing errors
     */
    public List<String> getErrors() {
        return grammarParser.getErrors();
    }
    
    /**
     * Check if there are parsing errors
     */
    public boolean hasErrors() {
        return grammarParser.hasErrors();
    }
}