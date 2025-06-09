package com.grammar.core;

import java.util.Objects;

/**
 * 表示提取的词槽信息
 */
public class Slot {
    private String name;
    private String value;
    private String normalized;
    private double confidence;
    private int startIndex;
    private int endIndex;
    
    public Slot() {}
    
    public Slot(String name, String value) {
        this.name = name;
        this.value = value;
        this.confidence = 1.0;
    }
    
    public Slot(String name, String value, String normalized, double confidence) {
        this.name = name;
        this.value = value;
        this.normalized = normalized;
        this.confidence = confidence;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getValue() {
        return value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }
    
    public String getNormalized() {
        return normalized;
    }
    
    public void setNormalized(String normalized) {
        this.normalized = normalized;
    }
    
    public double getConfidence() {
        return confidence;
    }
    
    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }
    
    public int getStartIndex() {
        return startIndex;
    }
    
    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }
    
    public int getEndIndex() {
        return endIndex;
    }
    
    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Slot slot = (Slot) o;
        return Double.compare(slot.confidence, confidence) == 0 &&
                startIndex == slot.startIndex &&
                endIndex == slot.endIndex &&
                Objects.equals(name, slot.name) &&
                Objects.equals(value, slot.value) &&
                Objects.equals(normalized, slot.normalized);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(name, value, normalized, confidence, startIndex, endIndex);
    }
    
    @Override
    public String toString() {
        return "Slot{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", normalized='" + normalized + '\'' +
                ", confidence=" + confidence +
                ", startIndex=" + startIndex +
                ", endIndex=" + endIndex +
                '}';
    }
}