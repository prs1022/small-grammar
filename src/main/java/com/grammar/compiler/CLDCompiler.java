package com.grammar.compiler;

import com.grammar.fsm.*;
import com.grammar.grammar.GrammarParser;
import com.grammar.grammar.ParseTreeAnalyzer;
import org.antlr.v4.runtime.tree.ParseTree;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Compiler that converts CLD grammar files into Finite State Machines
 */
public class CLDCompiler {
    
    private final GrammarParser grammarParser;
    private Map<String, List<String>> slotDefinitions;
    private Map<String, List<String>> productionDefinitions;
    private Map<String, String> slotTypes;
    
    public CLDCompiler() {
        this.grammarParser = new GrammarParser();
        this.slotDefinitions = new HashMap<>();
        this.productionDefinitions = new HashMap<>();
        this.slotTypes = new HashMap<>();
    }
    
    /**
     * Compile CLD grammar text into FSM
     */
    public FiniteStateMachine compile(String grammarText) {
        ParseTree grammarTree = grammarParser.parseGrammar(grammarText);
        
        if (grammarParser.hasErrors()) {
            throw new RuntimeException("Grammar parsing failed: " + grammarParser.getErrors());
        }
        
        return buildFSM(grammarTree);
    }
    
    /**
     * Build FSM from parsed grammar tree
     */
    private FiniteStateMachine buildFSM(ParseTree grammarTree) {
        // Extract grammar information
        extractGrammarInfo(grammarTree);
        
        // Find the statement rule (intent rule)
        List<ParseTreeAnalyzer.ProductionInfo> productions = 
            ParseTreeAnalyzer.extractProductions(grammarTree);
        
        ParseTreeAnalyzer.ProductionInfo statementRule = null;
        String intentName = "unknown";
        
        for (ParseTreeAnalyzer.ProductionInfo prod : productions) {
            if (hasStatementAnnotation(prod.annotations)) {
                statementRule = prod;
                intentName = extractIntentName(prod.annotations);
                break;
            }
        }
        
        if (statementRule == null) {
            throw new RuntimeException("No statement rule found in grammar");
        }
        
        // Create FSM
        FiniteStateMachine fsm = new FiniteStateMachine(statementRule.name, intentName);
        
        // Build FSM states and transitions from the statement rule
        buildFSMFromExpression(fsm, statementRule.expression);
        
        return fsm;
    }
    
    /**
     * Extract grammar information from parse tree
     */
    private void extractGrammarInfo(ParseTree grammarTree) {
        // Extract primes (slots)
        List<ParseTreeAnalyzer.PrimeInfo> primes = 
            ParseTreeAnalyzer.extractPrimes(grammarTree);
        
        for (ParseTreeAnalyzer.PrimeInfo prime : primes) {
            slotDefinitions.put(prime.name, parseSlotValues(prime.expression));
            
            // Extract type information
            String slotType = extractSlotType(prime.annotations);
            if (slotType != null) {
                slotTypes.put(prime.name, slotType);
            }
        }
        
        // Extract productions
        List<ParseTreeAnalyzer.ProductionInfo> productions = 
            ParseTreeAnalyzer.extractProductions(grammarTree);
        
        for (ParseTreeAnalyzer.ProductionInfo prod : productions) {
            if (!hasStatementAnnotation(prod.annotations)) {
                productionDefinitions.put(prod.name, parseChoices(prod.expression));
            }
        }
    }
    
    /**
     * Build FSM states and transitions from expression
     */
    private void buildFSMFromExpression(FiniteStateMachine fsm, String expression) {
        List<ExpressionElement> elements = parseExpression(expression);
        
        FSMState currentState = fsm.getStartState();
        int stateCounter = 1;
        
        for (int i = 0; i < elements.size(); i++) {
            ExpressionElement element = elements.get(i);
            boolean isLast = (i == elements.size() - 1);
            
            FSMState nextState;
            if (isLast) {
                nextState = new FSMState("final", true);
            } else {
                nextState = new FSMState("state_" + stateCounter++, false);
            }
            fsm.addState(nextState);
            
            buildTransitionsForElement(currentState, nextState, element);
            currentState = nextState;
        }
    }
    
    /**
     * Build transitions for a single expression element
     */
    private void buildTransitionsForElement(FSMState fromState, FSMState toState, ExpressionElement element) {
        switch (element.type) {
            case LITERAL:
                FSMTransition literalTransition = new FSMTransition(toState, FSMTransition.TransitionType.LITERAL);
                fromState.addTransition(element.value, literalTransition);
                break;
                
            case SLOT:
                String slotName = element.value;
                List<String> slotValues = slotDefinitions.get(slotName);
                
                if (slotValues != null) {
                    for (String slotValue : slotValues) {
                        String cleanValue = cleanSlotValue(slotValue);
                        FSMTransition slotTransition = new FSMTransition(
                            toState, FSMTransition.TransitionType.SLOT, slotName, cleanValue, element.isOptional);
                        fromState.addTransition(cleanValue, slotTransition);
                    }
                } else {
                    // Wildcard slot
                    FSMTransition wildcardTransition = new FSMTransition(
                        toState, FSMTransition.TransitionType.SLOT, slotName, "*", element.isOptional);
                    fromState.addTransition("*", wildcardTransition);
                }
                break;
                
            case CHOICE:
                for (String choice : element.choices) {
                    String cleanChoice = choice.replaceAll("\"", "").trim();
                    FSMTransition choiceTransition = new FSMTransition(toState, FSMTransition.TransitionType.LITERAL);
                    fromState.addTransition(cleanChoice, choiceTransition);
                }
                break;
                
            case REFERENCE:
                List<String> refValues = productionDefinitions.get(element.value);
                if (refValues != null) {
                    for (String refValue : refValues) {
                        String cleanValue = refValue.replaceAll("\"", "").trim();
                        FSMTransition refTransition = new FSMTransition(toState, FSMTransition.TransitionType.LITERAL);
                        fromState.addTransition(cleanValue, refTransition);
                    }
                }
                break;
        }
        
        // Handle optional elements
        if (element.isOptional) {
            FSMTransition epsilonTransition = new FSMTransition(toState, FSMTransition.TransitionType.EPSILON);
            fromState.addTransition("", epsilonTransition);
        }
    }
    
    /**
     * Parse expression into elements
     */
    private List<ExpressionElement> parseExpression(String expression) {
        List<ExpressionElement> elements = new ArrayList<>();
        
        // Simple tokenization - in a real implementation, use proper parsing
        String[] tokens = expression.split("\\s+");
        
        for (String token : tokens) {
            token = token.trim();
            if (token.isEmpty()) continue;
            
            boolean isOptional = token.endsWith("?");
            if (isOptional) {
                token = token.substring(0, token.length() - 1);
            }
            
            ExpressionElement element = new ExpressionElement();
            element.isOptional = isOptional;
            
            if (token.startsWith("<") && token.endsWith(">")) {
                // Slot
                element.type = ElementType.SLOT;
                element.value = token.substring(1, token.length() - 1);
            } else if (token.startsWith("\"") && token.endsWith("\"")) {
                // Literal
                element.type = ElementType.LITERAL;
                element.value = token.substring(1, token.length() - 1);
            } else if (token.contains("|")) {
                // Choice
                element.type = ElementType.CHOICE;
                element.choices = Arrays.asList(token.split("\\|"));
            } else {
                // Reference
                element.type = ElementType.REFERENCE;
                element.value = token;
            }
            
            elements.add(element);
        }
        
        return elements;
    }
    
    /**
     * Parse slot values, handling special syntax like ${...}
     */
    private List<String> parseSlotValues(String expression) {
        if (expression.contains("${") && expression.contains("}")) {
            // Handle ${city|district|poi} syntax
            Pattern pattern = Pattern.compile("\\$\\{([^}]+)\\}");
            Matcher matcher = pattern.matcher(expression);
            
            if (matcher.find()) {
                String content = matcher.group(1);
                return Arrays.asList(content.split("\\|"));
            }
        }
        
        return parseChoices(expression);
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
     * Extract slot type from annotations
     */
    private String extractSlotType(List<String> annotations) {
        for (String annotation : annotations) {
            if (annotation.startsWith("@type")) {
                Pattern pattern = Pattern.compile("@type\\s*\\(\\s*name\\s*=\\s*\"([^\"]+)\"\\s*\\)");
                Matcher matcher = pattern.matcher(annotation);
                if (matcher.find()) {
                    return matcher.group(1);
                }
            }
        }
        return null;
    }
    
    /**
     * Extract intent name from annotations
     */
    private String extractIntentName(List<String> annotations) {
        for (String annotation : annotations) {
            if (annotation.startsWith("@attr")) {
                Pattern pattern = Pattern.compile("@attr\\s*\\(\\s*intention\\s*=\\s*\"([^\"]+)\"\\s*\\)");
                Matcher matcher = pattern.matcher(annotation);
                if (matcher.find()) {
                    return matcher.group(1);
                }
            }
        }
        return "unknown";
    }
    
    /**
     * Check if annotations contain @statement
     */
    private boolean hasStatementAnnotation(List<String> annotations) {
        return annotations.stream().anyMatch(ann -> ann.startsWith("@statement"));
    }
    
    /**
     * Clean slot value by removing quotes and tags
     */
    private String cleanSlotValue(String value) {
        return value.replaceAll("\"", "").replaceAll("\\$\\{[^}]*\\}", "").trim();
    }
    
    public List<String> getErrors() {
        return grammarParser.getErrors();
    }
    
    // Helper classes
    private static class ExpressionElement {
        ElementType type;
        String value;
        List<String> choices;
        boolean isOptional = false;
    }
    
    private enum ElementType {
        LITERAL, SLOT, CHOICE, REFERENCE
    }
}