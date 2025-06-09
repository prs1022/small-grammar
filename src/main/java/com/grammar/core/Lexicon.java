package com.grammar.core;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 词汇表管理器
 */
public class Lexicon {
    private Map<String, EntityType> entityTypes;
    private Map<String, Set<String>> entityValues;
    
    public Lexicon() {
        this.entityTypes = new ConcurrentHashMap<>();
        this.entityValues = new ConcurrentHashMap<>();
        initializeBuiltInEntities();
    }
    
    /**
     * 初始化内置实体类型
     */
    private void initializeBuiltInEntities() {
        // 城市实体
        Set<String> cities = new HashSet<>(Arrays.asList(
            "北京", "上海", "广州", "深圳", "杭州", "南京", "武汉", "成都", "西安", "重庆"
        ));
        addEntityType("city", cities, true);
        
        // 时间实体
        Set<String> timeExpressions = new HashSet<>(Arrays.asList(
            "今天", "明天", "后天", "昨天", "前天", "现在", "今晚", "明早", "下午", "晚上"
        ));
        addEntityType("time", timeExpressions, true);
        
        // 日期实体
        Set<String> dateExpressions = new HashSet<>(Arrays.asList(
            "今日", "明日", "本周", "下周", "本月", "下月", "今年", "明年"
        ));
        addEntityType("date", dateExpressions, true);
        
        // 日期时间实体
        Set<String> datetimeExpressions = new HashSet<>();
        datetimeExpressions.addAll(timeExpressions);
        datetimeExpressions.addAll(dateExpressions);
        addEntityType("datetime", datetimeExpressions, true);
        
        // 地区实体
        Set<String> districts = new HashSet<>(Arrays.asList(
            "朝阳区", "海淀区", "西城区", "东城区", "丰台区", "石景山区", "浦东新区", "黄浦区"
        ));
        addEntityType("district", districts, true);
        
        // POI实体
        Set<String> pois = new HashSet<>(Arrays.asList(
            "天安门", "故宫", "长城", "颐和园", "天坛", "外滩", "东方明珠", "西湖"
        ));
        addEntityType("poi", pois, true);
    }
    
    /**
     * 添加实体类型
     */
    public void addEntityType(String name, Set<String> values, boolean isBuiltIn) {
        EntityType entityType = new EntityType(name, new ArrayList<>(values), isBuiltIn);
        entityTypes.put(name, entityType);
        entityValues.put(name, new HashSet<>(values));
    }
    
    /**
     * 获取实体类型
     */
    public EntityType getEntityType(String name) {
        return entityTypes.get(name);
    }
    
    /**
     * 获取实体值集合
     */
    public Set<String> getEntityValues(String entityType) {
        return entityValues.getOrDefault(entityType, new HashSet<>());
    }
    
    /**
     * 检查文本是否匹配某个实体类型
     */
    public boolean matchesEntity(String text, String entityType) {
        Set<String> values = getEntityValues(entityType);
        return values.contains(text);
    }
    
    /**
     * 查找文本中的实体
     */
    public List<EntityMatch> findEntities(String text) {
        List<EntityMatch> matches = new ArrayList<>();
        
        for (Map.Entry<String, Set<String>> entry : entityValues.entrySet()) {
            String entityType = entry.getKey();
            Set<String> values = entry.getValue();
            
            for (String value : values) {
                int index = text.indexOf(value);
                while (index != -1) {
                    EntityMatch match = new EntityMatch();
                    match.setEntityType(entityType);
                    match.setValue(value);
                    match.setStartIndex(index);
                    match.setEndIndex(index + value.length());
                    match.setConfidence(1.0);
                    matches.add(match);
                    
                    index = text.indexOf(value, index + 1);
                }
            }
        }
        
        return matches;
    }
    
    /**
     * 获取所有实体类型名称
     */
    public Set<String> getAllEntityTypeNames() {
        return entityTypes.keySet();
    }
}