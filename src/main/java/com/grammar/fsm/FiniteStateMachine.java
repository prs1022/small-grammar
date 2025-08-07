package com.grammar.fsm;

import com.grammar.nlu.NLUResult;
import com.grammar.nlu.Slot;
import java.util.*;

/**
 * Finite State Machine for NLU pattern matching
 */
public class FiniteStateMachine {
    private final String name;
    private final FSMState startState;
    private final String intentName;
    private final Map<String, FSMState> states;
    
    public FiniteStateMachine(String name, String intentName) {
        this.name = name;
        this.intentName = intentName;
        this.states = new HashMap<>();
        this.startState = new FSMState("start", false);
        this.states.put("start", startState);
    }
    
    public String getName() {
        return name;
    }
    
    public String getIntentName() {
        return intentName;
    }
    
    public FSMState getStartState() {
        return startState;
    }
    
    public void addState(FSMState state) {
        states.put(state.getId(), state);
    }
    
    public FSMState getState(String id) {
        return states.get(id);
    }
    
    public Collection<FSMState> getAllStates() {
        return states.values();
    }
    
    /**
     * Match input text against this FSM
     */
    public NLUResult match(String input) {
        return match(input, detectLanguage(input));
    }
    
    /**
     * Match input text against this FSM with specified language
     */
    public NLUResult match(String input, String language) {
        String[] tokens = tokenize(input, language);
        List<MatchResult> results = new ArrayList<>();
        
        // Try matching from start state
        matchRecursive(startState, tokens, 0, new ArrayList<>(), results);
        
        // Find the best match (longest match with most slots)
        MatchResult bestMatch = findBestMatch(results);
        
        if (bestMatch != null) {
            return new NLUResult(intentName, bestMatch.slots);
        }
        
        return new NLUResult("unknown", new ArrayList<>());
    }
    
    /**
     * Recursive matching algorithm
     */
    private void matchRecursive(FSMState currentState, String[] tokens, int tokenIndex, 
                               List<Slot> currentSlots, List<MatchResult> results) {
        
        // If we've consumed all tokens and reached a final state, record the match
        if (tokenIndex >= tokens.length) {
            if (currentState.isFinal()) {
                results.add(new MatchResult(new ArrayList<>(currentSlots), tokenIndex));
            }
            return;
        }
        
        // If current state is final, record partial match
        if (currentState.isFinal()) {
            results.add(new MatchResult(new ArrayList<>(currentSlots), tokenIndex));
        }
        
        // Try all transitions from current state
        for (Map.Entry<String, FSMTransition> entry : currentState.getTransitions().entrySet()) {
            String transitionInput = entry.getKey();
            FSMTransition transition = entry.getValue();
            
            boolean canTransition = false;
            List<Slot> newSlots = new ArrayList<>(currentSlots);
            int tokensConsumed = 0;
            
            switch (transition.getType()) {
                case LITERAL:
                    // Try to match the literal string against consecutive tokens
                    tokensConsumed = matchLiteral(tokens, tokenIndex, transitionInput);
                    canTransition = tokensConsumed > 0;
                    break;
                    
                case SLOT:
                    // For slot transitions, try to match against slot values
                    if (tokenIndex < tokens.length) {
                        String currentToken = tokens[tokenIndex].toLowerCase();
                        if (transitionInput.equals(currentToken) || transitionInput.equals("*")) {
                            canTransition = true;
                            tokensConsumed = 1;
                            if (transition.getSlotName() != null) {
                                newSlots.add(new Slot(transition.getSlotName(), tokens[tokenIndex]));
                            }
                        }
                    }
                    break;
                    
                case EPSILON:
                    // Empty transition, don't consume token
                    matchRecursive(transition.getTargetState(), tokens, tokenIndex, newSlots, results);
                    continue;
                    
                case WILDCARD:
                    canTransition = tokenIndex < tokens.length;
                    tokensConsumed = 1;
                    break;
            }
            
            if (canTransition) {
                matchRecursive(transition.getTargetState(), tokens, tokenIndex + tokensConsumed, newSlots, results);
            } else if (transition.isOptional()) {
                // Try skipping optional transition
                matchRecursive(transition.getTargetState(), tokens, tokenIndex, newSlots, results);
            }
        }
    }
    
    /**
     * Try to match a literal string against consecutive tokens
     */
    private int matchLiteral(String[] tokens, int startIndex, String literal) {
        if (startIndex >= tokens.length) {
            return 0;
        }
        
        // For Chinese (character-based tokenization), reconstruct the literal from tokens
        StringBuilder reconstructed = new StringBuilder();
        int tokensUsed = 0;
        
        for (int i = startIndex; i < tokens.length && tokensUsed < literal.length(); i++) {
            reconstructed.append(tokens[i]);
            tokensUsed++;
            
            if (reconstructed.toString().equals(literal)) {
                return tokensUsed;
            }
        }
        
        // If we couldn't reconstruct the exact literal, try direct match for single token
        if (startIndex < tokens.length && tokens[startIndex].equals(literal)) {
            return 1;
        }
        
        return 0;
    }
    
    /**
     * Find the best match from results
     */
    private MatchResult findBestMatch(List<MatchResult> results) {
        if (results.isEmpty()) {
            return null;
        }
        
        // Sort by tokens consumed (descending) then by slots found (descending)
        results.sort((a, b) -> {
            int tokenCompare = Integer.compare(b.tokensConsumed, a.tokensConsumed);
            if (tokenCompare != 0) {
                return tokenCompare;
            }
            return Integer.compare(b.slots.size(), a.slots.size());
        });
        
        return results.get(0);
    }
    
    /**
     * Tokenize input text based on language
     */
    private String[] tokenize(String input, String language) {
        input = input.trim();
        
        if ("zh".equals(language) || "chinese".equals(language)) {
            // Chinese: split by characters
            return input.split("");
        } else {
            // English and others: split by whitespace
            return input.split("\\s+");
        }
    }
    
    /**
     * Detect language of input text
     */
    private String detectLanguage(String input) {
        // Simple heuristic: if contains Chinese characters, treat as Chinese
        for (char c : input.toCharArray()) {
            if (c >= 0x4E00 && c <= 0x9FFF) { // CJK Unified Ideographs range
                return "zh";
            }
        }
        return "en";
    }
    
    @Override
    public String toString() {
        return String.format("FSM{name='%s', intent='%s', states=%d}", 
            name, intentName, states.size());
    }
    
    // Helper class for match results
    private static class MatchResult {
        final List<Slot> slots;
        final int tokensConsumed;
        
        MatchResult(List<Slot> slots, int tokensConsumed) {
            this.slots = slots;
            this.tokensConsumed = tokensConsumed;
        }
    }
}