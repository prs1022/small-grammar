package com.grammar.core;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

class IntentTest {

    private Intent intent;

    @BeforeEach
    void setUp() {
        intent = new Intent();
    }

    @Test
    @DisplayName("创建空意图")
    void testCreateEmptyIntent() {
        // Then
        assertNotNull(intent.getSlots());
        assertTrue(intent.getSlots().isEmpty());
        assertEquals(0.0, intent.getConfidence());
    }

    @Test
    @DisplayName("创建带名称和置信度的意图")
    void testCreateIntentWithNameAndConfidence() {
        // Given
        String name = "greeting";
        double confidence = 0.95;
        
        // When
        Intent intentWithData = new Intent(name, confidence);
        
        // Then
        assertEquals(name, intentWithData.getName());
        assertEquals(confidence, intentWithData.getConfidence());
        assertNotNull(intentWithData.getSlots());
        assertTrue(intentWithData.getSlots().isEmpty());
    }

    @Test
    @DisplayName("添加词槽到意图")
    void testAddSlot() {
        // Given
        Slot slot = new Slot("time", "明天");
        
        // When
        intent.addSlot("time", slot);
        
        // Then
        assertEquals(1, intent.getSlots().size());
        assertTrue(intent.getSlots().containsKey("time"));
        assertEquals(slot, intent.getSlots().get("time"));
    }

    @Test
    @DisplayName("添加多个词槽")
    void testAddMultipleSlots() {
        // Given
        Slot timeSlot = new Slot("time", "明天");
        Slot locationSlot = new Slot("location", "北京");
        
        // When
        intent.addSlot("time", timeSlot);
        intent.addSlot("location", locationSlot);
        
        // Then
        assertEquals(2, intent.getSlots().size());
        assertEquals(timeSlot, intent.getSlots().get("time"));
        assertEquals(locationSlot, intent.getSlots().get("location"));
    }

    @Test
    @DisplayName("设置意图名称")
    void testSetName() {
        // Given
        String name = "booking";
        
        // When
        intent.setName(name);
        
        // Then
        assertEquals(name, intent.getName());
    }

    @Test
    @DisplayName("设置置信度")
    void testSetConfidence() {
        // Given
        double confidence = 0.85;
        
        // When
        intent.setConfidence(confidence);
        
        // Then
        assertEquals(confidence, intent.getConfidence());
    }
}