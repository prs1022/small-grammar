package com.grammar.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 表示语义分析结果
 */
public class Semantic {
    private String text;
    private List<Intent> intents;
    private long processingTime;
    
    public Semantic() {
        this.intents = new ArrayList<>();
    }
    
    public Semantic(String text) {
        this.text = text;
        this.intents = new ArrayList<>();
    }
    
    public String getText() {
        return text;
    }
    
    public void setText(String text) {
        this.text = text;
    }
    
    public List<Intent> getIntents() {
        return intents;
    }
    
    public void setIntents(List<Intent> intents) {
        this.intents = intents;
    }
    
    public void addIntent(Intent intent) {
        this.intents.add(intent);
    }
    
    public long getProcessingTime() {
        return processingTime;
    }
    
    public void setProcessingTime(long processingTime) {
        this.processingTime = processingTime;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Semantic semantic = (Semantic) o;
        return processingTime == semantic.processingTime &&
                Objects.equals(text, semantic.text) &&
                Objects.equals(intents, semantic.intents);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(text, intents, processingTime);
    }
    
    @Override
    public String toString() {
        return "Semantic{" +
                "text='" + text + '\'' +
                ", intents=" + intents +
                ", processingTime=" + processingTime +
                '}';
    }
}