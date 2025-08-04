package com.grammar.grammar;

import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GrammarParserTest {
    
    private GrammarParser grammarParser;
    
    @BeforeEach
    void setUp() {
        grammarParser = new GrammarParser();
    }
    
    @Test
    void testParseSimpleGrammar() {
        String grammar = "namespace test;\n\nproduction: \"hello\" \"world\";";
        
        ParseTree tree = grammarParser.parseGrammar(grammar);
        
        assertNotNull(tree);
        assertFalse(grammarParser.hasErrors(), 
            "Parsing should succeed without errors: " + grammarParser.getErrors());
    }
    
    @Test
    void testParseProduction() {
        String production = "greeting: \"hello\" \"world\";";
        
        ParseTree tree = grammarParser.parseProduction(production);
        
        assertNotNull(tree);
        assertFalse(grammarParser.hasErrors());
    }
    
    @Test
    void testParsePrime() {
        String prime = "<word>: \"test\";";
        
        ParseTree tree = grammarParser.parsePrime(prime);
        
        assertNotNull(tree);
        assertFalse(grammarParser.hasErrors());
    }
    
    @Test
    void testParseMacro() {
        String macro = "repeat(x): x x;";
        
        ParseTree tree = grammarParser.parseMacro(macro);
        
        assertNotNull(tree);
        assertFalse(grammarParser.hasErrors());
    }
    
    @Test
    void testParseExpression() {
        String expression = "\"hello\" \"world\" | \"hi\" \"there\"";
        
        ParseTree tree = grammarParser.parseExpression(expression);
        
        assertNotNull(tree);
        assertFalse(grammarParser.hasErrors());
    }
    
    @Test
    void testParseCriteria() {
        String criteria = "@[noun & !plural]";
        
        ParseTree tree = grammarParser.parseCriteria(criteria);
        
        assertNotNull(tree);
        assertFalse(grammarParser.hasErrors());
    }
    
    @Test
    void testErrorHandling() {
        String invalidGrammar = "invalid syntax here";
        
        ParseTree tree = grammarParser.parseGrammar(invalidGrammar);
        
        assertTrue(grammarParser.hasErrors());
        assertFalse(grammarParser.getErrors().isEmpty());
    }
    
    @Test
    void testValidateSyntax() {
        String validGrammar = "namespace test;\nproduction: \"valid\" \"syntax\";";
        
        String invalidGrammar = "namespace test production invalid";
        
        assertTrue(grammarParser.validateSyntax(validGrammar));
        assertFalse(grammarParser.validateSyntax(invalidGrammar));
    }
    
    @Test
    void testGetTokens() {
        String input = "namespace test;";
        
        var tokens = grammarParser.getTokens(input);
        
        assertNotNull(tokens);
        assertTrue(tokens.size() > 0);
    }
}