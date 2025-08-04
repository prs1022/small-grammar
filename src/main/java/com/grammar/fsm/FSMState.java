package com.grammar.fsm;

import java.util.*;

/**
 * Represents a state in the finite state machine
 */
public class FSMState {
    private final String id;
    private final boolean isFinal;
    private final Map<String, FSMTransition> transitions;
    private final Map<String, String> attributes;
    
    public FSMState(String id, boolean isFinal) {
        this.id = id;
        this.isFinal = isFinal;
        this.transitions = new HashMap<>();
        this.attributes = new HashMap<>();
    }
    
    public String getId() {
        return id;
    }
    
    public boolean isFinal() {
        return isFinal;
    }
    
    public void addTransition(String input, FSMTransition transition) {
        transitions.put(input, transition);
    }
    
    public FSMTransition getTransition(String input) {
        return transitions.get(input);
    }
    
    public Map<String, FSMTransition> getTransitions() {
        return new HashMap<>(transitions);
    }
    
    public void setAttribute(String key, String value) {
        attributes.put(key, value);
    }
    
    public String getAttribute(String key) {
        return attributes.get(key);
    }
    
    public Map<String, String> getAttributes() {
        return new HashMap<>(attributes);
    }
    
    @Override
    public String toString() {
        return String.format("FSMState{id='%s', final=%s, transitions=%d}", 
            id, isFinal, transitions.size());
    }
}