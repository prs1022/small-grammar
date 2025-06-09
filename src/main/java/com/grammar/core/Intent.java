package com.grammar.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 表示识别出的意图
 */
public class Intent {
    private String name;
    private double confidence;
    private Map<String, Slot> slots;
    
    public Intent() {
        this.slots = new HashMap<>();
    }
    
    public Intent(String name, double confidence) {
        this.name = name;
        this.confidence = confidence;
        this.slots = new HashMap<>();
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public double getConfidence() {
        return confidence;
    }
    
    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }
    
    public Map<String, Slot> getSlots() {
        return slots;
    }
    
    public void setSlots(Map<String, Slot> slots) {
        this.slots = slots;
    }
    
    public void addSlot(String name, Slot slot) {
        this.slots.put(name, slot);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Intent intent = (Intent) o;
        return Double.compare(intent.confidence, confidence) == 0 &&
                Objects.equals(name, intent.name) &&
                Objects.equals(slots, intent.slots);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(name, confidence, slots);
    }
    
    @Override
    public String toString() {
        return "Intent{" +
                "name='" + name + '\'' +
                ", confidence=" + confidence +
                ", slots=" + slots +
                '}';
    }
}