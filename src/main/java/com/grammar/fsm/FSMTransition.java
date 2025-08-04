package com.grammar.fsm;

/**
 * Represents a transition in the finite state machine
 */
public class FSMTransition {
    private final FSMState targetState;
    private final String slotName;
    private final String slotValue;
    private final boolean isOptional;
    private final TransitionType type;
    
    public enum TransitionType {
        LITERAL,    // Exact text match
        SLOT,       // Slot capture
        EPSILON,    // Empty transition
        WILDCARD    // Any token
    }
    
    public FSMTransition(FSMState targetState, TransitionType type) {
        this(targetState, type, null, null, false);
    }
    
    public FSMTransition(FSMState targetState, TransitionType type, String slotName, String slotValue, boolean isOptional) {
        this.targetState = targetState;
        this.type = type;
        this.slotName = slotName;
        this.slotValue = slotValue;
        this.isOptional = isOptional;
    }
    
    public FSMState getTargetState() {
        return targetState;
    }
    
    public String getSlotName() {
        return slotName;
    }
    
    public String getSlotValue() {
        return slotValue;
    }
    
    public boolean isOptional() {
        return isOptional;
    }
    
    public TransitionType getType() {
        return type;
    }
    
    @Override
    public String toString() {
        return String.format("FSMTransition{target='%s', type=%s, slot='%s', value='%s', optional=%s}", 
            targetState.getId(), type, slotName, slotValue, isOptional);
    }
}