package com.grammar.grammar;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import com.grammar.antlr.SenseLexer;
import com.grammar.antlr.SenseParser;

public class GrammarParser {
    private SenseLexer lexer;
    private SenseParser parser;
    
    public ParseTree parseRule(String ruleText) {
        CharStream input = CharStreams.fromString(ruleText);
        lexer = new SenseLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        parser = new SenseParser(tokens);
        return parser.sense();
    }
}