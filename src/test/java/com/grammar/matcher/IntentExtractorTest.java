package com.grammar.matcher;

import com.grammar.core.Intent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import java.util.List;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class IntentExtractorTest {

    private IntentExtractor extractor;

    @BeforeEach
    void setUp() {
        extractor = new IntentExtractor();
    }

    @Test
    @DisplayName("从空匹配结果提取意图")
    void testExtractIntents_EmptyMatches_ReturnsEmptyList() {
        // Given
        String text = "测试文本";
        List<MatchResult> emptyMatches = new ArrayList<>();

        // When
        List<Intent> intents = extractor.extractIntents(text, emptyMatches);

        // Then
        assertNotNull(intents);
        assertTrue(intents.isEmpty());
    }

    @Test
    @DisplayName("从null匹配结果提取意图")
    void testExtractIntents_NullMatches_ReturnsEmptyList() {
        // Given
        String text = "测试文本";

        // When
        List<Intent> intents = extractor.extractIntents(text, null);

        // Then
        assertNotNull(intents);
        assertTrue(intents.isEmpty());
    }

    @Test
    @DisplayName("从空文本提取意图")
    void testExtractIntents_EmptyText_ReturnsEmptyList() {
        // Given
        String emptyText = "";
        List<MatchResult> matches = new ArrayList<>();

        // When
        List<Intent> intents = extractor.extractIntents(emptyText, matches);

        // Then
        assertNotNull(intents);
        assertTrue(intents.isEmpty());
    }
}