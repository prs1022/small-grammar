package com.grammar.nlu;

import com.grammar.grammar.GrammarParser;
import com.grammar.grammar.ParseTreeAnalyzer;
import org.antlr.v4.runtime.tree.ParseTree;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Complete NLU implementation based on Sense grammar rules
 * This demonstrates the full pipeline from grammar definition to intent/slot extraction
 */
public class SenseBasedNLU {
    
    private final GrammarParser grammarParser;
    private Map<String, IntentRule> intentRules;
    private Map<String, List<String>> slotDefinitions;
    private Map<String, List<String>> productionDefinitions;
    
    public SenseBasedNLU() {
        this.grammarParser = new GrammarParser();
        this.intentRules = new HashMap<>();
        this.slotDefinitions = new HashMap<>();
        this.productionDefinitions = new HashMap<>();
    }
    
    /**
     * Load and parse Sense grammar
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
     * Parse natural language text and extract structured intent and slots
     */
    public NLUResult parseText(String text) {
        String normalizedText = text.toLowerCase().trim();
        
        // Try to match against all intent rules
        for (IntentRule rule : intentRules.values()) {
            NLUResult result = matchRule(normalizedText, rule);
            if (result != null && !result.getIntent().equals("unknown")) {
                return result;
            }
        }
        
        return new NLUResult("unknown", new ArrayList<>());
    }
    
    /**
     * Extract grammar components from parse tree
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
            
            // Store all productions for reference resolution
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
     * Match text against a specific intent rule
     */
    private NLUResult matchRule(String text, IntentRule rule) {
        List<Slot> slots = new ArrayList<>();
        
        // Build regex pattern from rule expression
        String pattern = buildRegexPattern(rule.expression, slots);
        
        try {
            Pattern regex = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
            Matcher matcher = regex.matcher(text);
            
            if (matcher.find()) {
                // Extract slot values from captured groups
                extractSlotValues(matcher, rule.expression, slots);
                return new NLUResult(rule.intentValue, slots);
            }
        } catch (Exception e) {
            // Pattern compilation failed, try simple matching
            return simpleMatch(text, rule);
        }
        
        return null;
    }
    
    /**
     * Build regex pattern from rule expression
     */
    private String buildRegexPattern(String expression, List<Slot> slots) {
        String pattern = expression;
        
        // Replace slot references with capturing groups
        for (String slotName : slotDefinitions.keySet()) {
            String slotPattern = "<" + slotName + ">";
            if (pattern.contains(slotPattern)) {
                List<String> slotValues = slotDefinitions.get(slotName);
                String valuePattern = slotValues.stream()
                    .map(v -> v.replaceAll("\"", "").trim())
                    .reduce((a, b) -> a + "|" + b)
                    .orElse("");
                pattern = pattern.replace(slotPattern, "(" + valuePattern + ")");
            }
        }
        
        // Replace production references
        for (String prodName : productionDefinitions.keySet()) {
            if (pattern.contains(prodName)) {
                List<String> prodValues = productionDefinitions.get(prodName);
                String valuePattern = prodValues.stream()
                    .map(v -> v.replaceAll("\"", "").trim())
                    .reduce((a, b) -> a + "|" + b)
                    .orElse("");
                pattern = pattern.replace(prodName, "(?:" + valuePattern + ")");
            }
        }
        
        // Handle optional elements
        pattern = pattern.replaceAll("\\s*\\?", "?");
        
        // Clean up pattern
        pattern = pattern.replaceAll("\"", "")
                        .replaceAll("\\s+", "\\\\s*")
                        .trim();
        
        return ".*" + pattern + ".*";
    }
    
    /**
     * Extract slot values from regex matcher
     */
    private void extractSlotValues(Matcher matcher, String expression, List<Slot> slots) {
        int groupIndex = 1;
        
        for (String slotName : slotDefinitions.keySet()) {
            String slotPattern = "<" + slotName + ">";
            if (expression.contains(slotPattern)) {
                try {
                    String slotValue = matcher.group(groupIndex);
                    if (slotValue != null && !slotValue.trim().isEmpty()) {
                        slots.add(new Slot(slotName, slotValue.trim()));
                    }
                    groupIndex++;
                } catch (IndexOutOfBoundsException e) {
                    // Group not found, continue
                }
            }
        }
    }
    
    /**
     * Simple fallback matching when regex fails
     */
    private NLUResult simpleMatch(String text, IntentRule rule) {
        List<Slot> slots = new ArrayList<>();
        
        // Simple keyword-based matching
        for (String slotName : slotDefinitions.keySet()) {
            List<String> slotValues = slotDefinitions.get(slotName);
            for (String value : slotValues) {
                String cleanValue = value.replaceAll("\"", "").trim();
                if (text.contains(cleanValue.toLowerCase())) {
                    slots.add(new Slot(slotName, cleanValue));
                    break;
                }
            }
        }
        
        // Check if we found any slots and the text seems relevant
        if (!slots.isEmpty()) {
            return new NLUResult(rule.intentValue, slots);
        }
        
        return null;
    }
    
    /**
     * Parse choices from expression
     */
    private List<String> parseChoices(String expression) {
        if (expression.contains("|")) {
            return Arrays.asList(expression.split("\\|"));
        } else {
            return Arrays.asList(expression);
        }
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
    
    /**
     * Get loaded intent rules for debugging
     */
    public Map<String, IntentRule> getIntentRules() {
        return new HashMap<>(intentRules);
    }
    
    /**
     * Get loaded slot definitions for debugging
     */
    public Map<String, List<String>> getSlotDefinitions() {
        return new HashMap<>(slotDefinitions);
    }
    
    // Helper class
    public static class IntentRule {
        public final String name;
        public final String intentValue;
        public final String expression;
        
        public IntentRule(String name, String intentValue, String expression) {
            this.name = name;
            this.intentValue = intentValue;
            this.expression = expression;
        }
        
        @Override
        public String toString() {
            return String.format("IntentRule{name='%s', intent='%s', expr='%s'}", 
                name, intentValue, expression);
        }
    }
    
    /**
     * Main method for demonstration
     */
    public static void main(String[] args) {
        System.out.println("=== Sense-Based NLU Demo ===\n");
        
        SenseBasedNLU nlu = new SenseBasedNLU();
        
        // Example grammar as specified in requirements
        String weatherGrammar = 
            "namespace weather;\n" +
            "\n" +
            "commonPrefix: \"help me\" | \"let me look\";\n" +
            "weatherInfo: \"weather\" | \"weather conditions\";\n" +
            "\n" +
            "<time>: \"today\" | \"tomorrow\" | \"the day after tomorrow\";\n" +
            "\n" +
            "@intent(value=\"WeatherSearch\")\n" +
            "@statement\n" +
            "askWeather: commonPrefix? <time> weatherInfo;";
        
        if (nlu.loadGrammar(weatherGrammar)) {
            System.out.println("✓ Grammar loaded successfully");
            System.out.println("Intent rules: " + nlu.getIntentRules().keySet());
            System.out.println("Slot definitions: " + nlu.getSlotDefinitions().keySet());
            System.out.println();
            
            // Test the exact examples from requirements
            String[] testCases = {
                "Help me find out what the weather is like today",
                "help me today weather",
                "let me look tomorrow weather conditions",
                "today weather"
            };
            
            for (String testCase : testCases) {
                NLUResult result = nlu.parseText(testCase);
                System.out.println("Input: \"" + testCase + "\"");
                System.out.println("Output: " + result.toJsonString());
                System.out.println();
            }
        } else {
            System.out.println("✗ Failed to load grammar:");
            nlu.getErrors().forEach(System.out::println);
        }
    }
}