package com.grammar.nlu;

import java.util.Objects;

/**
 * Represents a slot (entity-value pair) extracted from natural language
 */
public class Slot {
    private final String entity;
    private final String value;
    
    public Slot(String entity, String value) {
        this.entity = entity;
        this.value = value;
    }
    
    public String getEntity() {
        return entity;
    }
    
    public String getValue() {
        return value;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Slot slot = (Slot) o;
        return Objects.equals(entity, slot.entity) && 
               Objects.equals(value, slot.value);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(entity, value);
    }
    
    @Override
    public String toString() {
        return String.format("Slot{entity='%s', value='%s'}", entity, value);
    }
}