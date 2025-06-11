package com.grammar.core;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

class SemanticTest {

    private Semantic semantic;

    @BeforeEach
    void setUp() {
        semantic = new Semantic();
    }

    @Test
    @DisplayName("创建空的语义分析结果")
    void testCreateEmptySemantic() {
        // Then
        assertNotNull(semantic.getIntents());
        assertTrue(semantic.getIntents().isEmpty());
        assertEquals(0, semantic.getProcessingTime());
    }

    @Test
    @DisplayName("创建带文本的语义分析结果")
    void testCreateSemanticWithText() {
        // Given
        String text = "测试文本";
        
        // When
        Semantic semanticWithText = new Semantic(text);
        
        // Then
        assertEquals(text, semanticWithText.getText());
        assertNotNull(semanticWithText.getIntents());
        assertTrue(semanticWithText.getIntents().isEmpty());
    }

    @Test
    @DisplayName("添加意图到语义分析结果")
    void testAddIntent() {
        // Given
        Intent intent = new Intent("greeting", 0.95);
        
        // When
        semantic.addIntent(intent);
        
        // Then
        assertEquals(1, semantic.getIntents().size());
        assertEquals(intent, semantic.getIntents().get(0));
    }

    @Test
    @DisplayName("设置处理时间")
    void testSetProcessingTime() {
        // Given
        long processingTime = 150L;
        
        // When
        semantic.setProcessingTime(processingTime);
        
        // Then
        assertEquals(processingTime, semantic.getProcessingTime());
    }

    @Test
    @DisplayName("获取最高置信度意图")
    void testGetHighestConfidenceIntent() {
        // Given
        Intent intent1 = new Intent("greeting", 0.8);
        Intent intent2 = new Intent("booking", 0.95);
        Intent intent3 = new Intent("query", 0.7);
        
        semantic.addIntent(intent1);
        semantic.addIntent(intent2);
        semantic.addIntent(intent3);
        
        // When
        Intent topIntent = semantic.getIntents().stream()
                .max((i1, i2) -> Double.compare(i1.getConfidence(), i2.getConfidence()))
                .orElse(null);
        
        // Then
        assertNotNull(topIntent);
        assertEquals("booking", topIntent.getName());
        assertEquals(0.95, topIntent.getConfidence());
    }

    @Test
    @DisplayName("测试语义分析结果的字符串表示")
    void testToString() {
        // Given
        semantic.setText("测试");
        semantic.setProcessingTime(100L);
        
        // When
        String result = semantic.toString();
        
        // Then
        assertNotNull(result);
        assertTrue(result.contains("测试"));
        assertTrue(result.contains("100"));
    }
}