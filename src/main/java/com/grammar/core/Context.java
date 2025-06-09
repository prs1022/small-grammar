package com.grammar.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 表示处理上下文
 */
public class Context {
    private String sessionId;
    private Map<String, Object> attributes;
    private List<String> previousIntents;
    private long timestamp;
    
    public Context() {
        this.attributes = new HashMap<>();
        this.timestamp = System.currentTimeMillis();
    }
    
    public Context(String sessionId) {
        this.sessionId = sessionId;
        this.attributes = new HashMap<>();
        this.timestamp = System.currentTimeMillis();
    }
    
    public String getSessionId() {
        return sessionId;
    }
    
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
    
    public Map<String, Object> getAttributes() {
        return attributes;
    }
    
    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }
    
    public void setAttribute(String key, Object value) {
        this.attributes.put(key, value);
    }
    
    public Object getAttribute(String key) {
        return this.attributes.get(key);
    }
    
    public List<String> getPreviousIntents() {
        return previousIntents;
    }
    
    public void setPreviousIntents(List<String> previousIntents) {
        this.previousIntents = previousIntents;
    }
    
    public long getTimestamp() {
        return timestamp;
    }
    
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Context context = (Context) o;
        return timestamp == context.timestamp &&
                Objects.equals(sessionId, context.sessionId) &&
                Objects.equals(attributes, context.attributes) &&
                Objects.equals(previousIntents, context.previousIntents);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(sessionId, attributes, previousIntents, timestamp);
    }
    
    @Override
    public String toString() {
        return "Context{" +
                "sessionId='" + sessionId + '\'' +
                ", attributes=" + attributes +
                ", previousIntents=" + previousIntents +
                ", timestamp=" + timestamp +
                '}';
    }
}