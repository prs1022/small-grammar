package com.grammar.core;

import java.util.Objects;

/**
 * 表示实体匹配结果
 */
public class EntityMatch {
    private String entityType;
    private String value;
    private String normalized;
    private int startIndex;
    private int endIndex;
    private double confidence;
    
    public EntityMatch() {}
    
    public EntityMatch(String entityType, String value, int startIndex, int endIndex) {
        this.entityType = entityType;
        this.value = value;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.confidence = 1.0;
    }
    
    public String getEntityType() {
        return entityType;
    }
    
    public void setEntityType(String entityType) {
        this.entityType = entityType;
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
    
    public double getConfidence() {
        return confidence;
    }
    
    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntityMatch that = (EntityMatch) o;
        return startIndex == that.startIndex &&
                endIndex == that.endIndex &&
                Double.compare(that.confidence, confidence) == 0 &&
                Objects.equals(entityType, that.entityType) &&
                Objects.equals(value, that.value) &&
                Objects.equals(normalized, that.normalized);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(entityType, value, normalized, startIndex, endIndex, confidence);
    }
    
    @Override
    public String toString() {
        return "EntityMatch{" +
                "entityType='" + entityType + '\'' +
                ", value='" + value + '\'' +
                ", normalized='" + normalized + '\'' +
                ", startIndex=" + startIndex +
                ", endIndex=" + endIndex +
                ", confidence=" + confidence +
                '}';
    }
}