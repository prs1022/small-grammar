package com.grammar.grammar;

import com.grammar.grammar.GrammarParser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

class GrammarParserTest {

    private GrammarParser grammarParser;

    @BeforeEach
    void setUp() {
        grammarParser = new GrammarParser();
    }

    @Test
    @DisplayName("解析简单规则文本")
    void testParseRule_SimpleRule_ReturnsParseTree() {
        // Given
        String ruleText = "namespace test; <greeting>: 'hello' | 'hi';";

        // When
        ParseTree result = grammarParser.parseRule(ruleText);

        // Then
        assertNotNull(result, "解析结果不应为null");
        assertTrue(result.getChildCount() > 0, "解析树应包含子节点");
    }

    @Test
    @DisplayName("解析空字符串")
    void testParseRule_EmptyString_ReturnsParseTree() {
        // Given
        String ruleText = "";

        // When
        ParseTree result = grammarParser.parseRule(ruleText);

        // Then
        assertNotNull(result, "即使是空字符串也应返回解析树");
    }

    @Test
    @DisplayName("解析复杂规则")
    void testParseRule_ComplexRule_ReturnsParseTree() {
        // Given
        String ruleText = "namespace booking;\n" +
                "import common::time;\n" +
                "<book_flight>: '我想' ('订' | '预订') '机票' time;";

        // When
        ParseTree result = grammarParser.parseRule(ruleText);

        // Then
        assertNotNull(result);
        assertTrue(result.getChildCount() > 0);
    }

    @Test
    @DisplayName("解析包含注解的规则")
    void testParseRule_RuleWithAnnotations_ReturnsParseTree() {
        // Given
        String ruleText = "namespace test;\n" +
                "@intent(name=\"greeting\")\n" +
                "<hello>: 'hello' | 'hi' | '你好';";

        // When
        ParseTree result = grammarParser.parseRule(ruleText);

        // Then
        assertNotNull(result);
        assertTrue(result.getChildCount() > 0);
    }
}