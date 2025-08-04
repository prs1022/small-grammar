package com.grammar.nlu;

import com.grammar.grammar.GrammarParser;
import com.grammar.grammar.ParseTreeAnalyzer;
import org.antlr.v4.runtime.tree.*;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Simplified NLU parser that focuses on the core requirements
 */
public class SimpleNLUParser {
    
    private final GrammarParser grammarParser;
    private Map<String, IntentRule> intentRules;
    private Map<String, List<String>> slotDefinitions;
    private Map<String, List<String>> productionDefinitions;
    
    public SimpleNLUParser() {
        this.grammarParser = new GrammarParser();
        this.intentRules = new HashMap<>();
        this.slotDefinitions = new HashMap<>();
        this.productionDefinitions = new HashMap<>();
    }
    
    /**
     * Load grammar rules from string
     */
    public boolean loadGrammar(String grammarText) {
        ParseTree grammarTree = grammarParser.parseGrammar(grammarText);
        
        if (grammarParser.hasErrors()) {
            return false;
        }
        
        extractGrammarInfo(grammarTree);
        return true;
    }
    
    /**
     * Parse natural language text and extract intent and slots
     */
    public NLUResult parseText(String text) {
        String normalizedText = text.toLowerCase().trim();
        
        // Try to match against all intent rules
        for (IntentRule rule : intentRules.values()) {
            NLUResult result = tryMatchRule(normalizedText, rule);
            if (result != null) {
                return result;
            }
        }
        
        return new NLUResult("unknown", new ArrayList<>());
    }
    
    /**
     * Extract grammar information from parse tree
     */
    private void extractGrammarInfo(ParseTree grammarTree) {
        // Extract productions
        List<ParseTreeAnalyzer.ProductionInfo> productions = 
            ParseTreeAnalyzer.extractProductions(grammarTree);
        
        for (ParseTreeAnalyzer.ProductionInfo prod : productions) {
            String intentValue = extractIntentValue(prod.annotations);
            boolean isStatement = hasStatementAnnotation(prod.annotations);
            
            if (intentValue != null && isStatement) {
                IntentRule rule = new IntentRule(prod.name, intentValue, prod.expression);
                intentRules.put(prod.name, rule);
            }
            
            // Store production definitions
            productionDefinitions.put(prod.name, parseChoices(prod.expression));
        }
        
        // Extract primes (slots)
        List<ParseTreeAnalyzer.PrimeInfo> primes = 
            ParseTreeAnalyzer.extractPrimes(grammarTree);
        
        for (ParseTreeAnalyzer.PrimeInfo prime : primes) {
            slotDefinitions.put(prime.name, parseChoices(prime.expression));
        }
    }
    
    /**
     * Extract intent value from annotations
     */
    private String extractIntentValue(List<String> annotations) {
        for (String annotation : annotations) {
            if (annotation.startsWith("@intent")) {
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
     * Try to match text against a rule
     */
    private NLUResult tryMatchRule(String text, IntentRule rule) {
        List<Slot> slots = new ArrayList<>();
        
        // Simple pattern matching approach
        String pattern = rule.expression;
        
        // Replace slot references with regex patterns
        for (String slotName : slotDefinitions.keySet()) {
            String slotPattern = "<" + slotName + ">";
            if (pattern.contains(slotPattern)) {
                // Create a capturing group for this slot
                List<String> slotValues = slotDefinitions.get(slotName);
                String valuePattern = String.join("|", slotValues)
                    .replaceAll("\"", "")
                    .toLowerCase();
                pattern = pattern.replace(slotPattern, "(" + valuePattern + ")");
            }
        }
        
        // Replace production references
        for (String prodName : productionDefinitions.keySet()) {
            if (pattern.contains(prodName)) {
                List<String> prodValues = productionDefinitions.get(prodName);
                String valuePattern = String.join("|", prodValues)
                    .replaceAll("\"", "")
                    .toLowerCase();
                pattern = pattern.replace(prodName, "(?:" + valuePattern + ")");
            }
        }
        
        // Handle optional elements (simplified)
        pattern = pattern.replaceAll("\\s*\\?", "?");
        
        // Clean up the pattern
        pattern = pattern.replaceAll("\"", "")
                        .replaceAll("\\s+", "\\\\s+")
                        .toLowerCase();
        
        // Try to match
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(text);
        
        if (matcher.find()) {
            // Extract slots from captured groups
            int groupIndex = 1;
            for (String slotName : slotDefinitions.keySet()) {
                String slotPattern = "<" + slotName + ">";
                if (rule.expression.contains(slotPattern)) {
                    try {
                        String slotValue = matcher.group(groupIndex);
                        if (slotValue != null) {
                            slots.add(new Slot(slotName, slotValue.trim()));
                        }
                        groupIndex++;
                    } catch (IndexOutOfBoundsException e) {
                        // Group not found, continue
                    }
                }
            }
            
            return new NLUResult(rule.intentValue, slots);
        }
        
        return null;
    }
    
    /**
     * Parse choices from expression
     */
    private List<String> parseChoices(String expression) {
        if (expression.contains("|")) {
            List<String> choices = new ArrayList<>();
            for (String choice : expression.split("\\|")) {
                choices.add(choice.trim());
            }
            return choices;
        } else {
            return Arrays.asList(expression.trim());
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
}