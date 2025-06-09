package com.grammar.matcher;

import java.util.List;
import java.util.ArrayList;

public class FSMEngine {
    private State[] states;
    private int initialState;
    
    public List<MatchResult> match(String input) {
        // 实现FSM匹配逻辑
        // 返回匹配结果，包括意图和词槽
        return new ArrayList<>();
    }
}

// 临时添加 State 类
class State {
    private int id;
    private boolean isFinal;
    private String label;
    
    public State(int id, boolean isFinal, String label) {
        this.id = id;
        this.isFinal = isFinal;
        this.label = label;
    }
    
    // getters and setters
    public int getId() { return id; }
    public boolean isFinal() { return isFinal; }
    public String getLabel() { return label; }
}

// 临时添加 MatchResult 类
class MatchResult {
    private String text;
    private int start;
    private int end;
    private double confidence;
    
    // getters and setters
    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
    public int getStart() { return start; }
    public void setStart(int start) { this.start = start; }
    public int getEnd() { return end; }
    public void setEnd(int end) { this.end = end; }
    public double getConfidence() { return confidence; }
    public void setConfidence(double confidence) { this.confidence = confidence; }
}