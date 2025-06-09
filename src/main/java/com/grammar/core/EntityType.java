package com.grammar.core;

import java.util.List;
import java.util.Objects;

/**
 * 表示实体类型定义
 */
public class EntityType {
    private String name;
    private String description;
    private List<String> values;
    private boolean isBuiltIn;
    
    public EntityType() {}
    
    public EntityType(String name, List<String> values) {
        this.name = name;
        this.values = values;
        this.isBuiltIn = false;
    }
    
    public EntityType(String name, List<String> values, boolean isBuiltIn) {
        this.name = name;
        this.values = values;
        this.isBuiltIn = isBuiltIn;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public List<String> getValues() {
        return values;
    }
    
    public void setValues(List<String> values) {
        this.values = values;
    }
    
    public boolean isBuiltIn() {
        return isBuiltIn;
    }
    
    public void setBuiltIn(boolean builtIn) {
        isBuiltIn = builtIn;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntityType that = (EntityType) o;
        return isBuiltIn == that.isBuiltIn &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(values, that.values);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(name, description, values, isBuiltIn);
    }
    
    @Override
    public String toString() {
        return "EntityType{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", values=" + values +
                ", isBuiltIn=" + isBuiltIn +
                '}';
    }
}