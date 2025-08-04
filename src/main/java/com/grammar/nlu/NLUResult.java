package com.grammar.nlu;

import java.util.List;
import java.util.Objects;

/**
 * Result of NLU parsing containing intent and slots
 */
public class NLUResult {
    private final String intent;
    private final List<Slot> slots;
    
    public NLUResult(String intent, List<Slot> slots) {
        this.intent = intent;
        this.slots = slots;
    }
    
    public String getIntent() {
        return intent;
    }
    
    public List<Slot> getSlots() {
        return slots;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NLUResult nluResult = (NLUResult) o;
        return Objects.equals(intent, nluResult.intent) && 
               Objects.equals(slots, nluResult.slots);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(intent, slots);
    }
    
    @Override
    public String toString() {
        return String.format("NLUResult{intent='%s', slots=%s}", intent, slots);
    }
    
    /**
     * Convert to JSON-like string format
     */
    public String toJsonString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"intent\": \"").append(intent).append("\", \"slot\": [");
        
        for (int i = 0; i < slots.size(); i++) {
            if (i > 0) sb.append(", ");
            Slot slot = slots.get(i);
            sb.append("{\"entity\": \"").append(slot.getEntity())
              .append("\", \"value\": \"").append(slot.getValue()).append("\"}");
        }
        
        sb.append("]}");
        return sb.toString();
    }
}