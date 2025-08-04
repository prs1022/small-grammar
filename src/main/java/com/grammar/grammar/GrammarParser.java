package com.grammar.grammar;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import com.grammar.antlr.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.ArrayList;

public class GrammarParser {
    private SenseLexer lexer;
    private SenseParser parser;
    private List<String> errors;
    
    public GrammarParser() {
        this.errors = new ArrayList<>();
    }
    
    /**
     * Parse a complete Sense grammar from string input
     */
    public ParseTree parseGrammar(String grammarText) {
        return parseWithErrorHandling(grammarText, parser -> parser.sense());
    }
    
    /**
     * Parse a Sense grammar from file
     */
    public ParseTree parseGrammarFromFile(String filePath) throws IOException {
        String content = Files.readString(Path.of(filePath));
        return parseGrammar(content);
    }
    
    /**
     * Parse a specific rule expression
     */
    public ParseTree parseExpression(String expressionText) {
        return parseWithErrorHandling(expressionText, parser -> parser.expr());
    }
    
    /**
     * Parse a production sentence
     */
    public ParseTree parseProduction(String productionText) {
        return parseWithErrorHandling(productionText, parser -> parser.productionSentence());
    }
    
    /**
     * Parse a prime sentence
     */
    public ParseTree parsePrime(String primeText) {
        return parseWithErrorHandling(primeText, parser -> parser.primeSentence());
    }
    
    /**
     * Parse a macro sentence
     */
    public ParseTree parseMacro(String macroText) {
        return parseWithErrorHandling(macroText, parser -> parser.macroSentence());
    }
    
    /**
     * Parse criteria expression
     */
    public ParseTree parseCriteria(String criteriaText) {
        return parseWithErrorHandling(criteriaText, parser -> parser.criteria());
    }
    
    /**
     * Generic parsing method with error handling
     */
    private ParseTree parseWithErrorHandling(String input, ParserFunction parserFunction) {
        errors.clear();
        
        try {
            CharStream charStream = CharStreams.fromString(input);
            lexer = new SenseLexer(charStream);
            
            // Add error listener to lexer
            lexer.removeErrorListeners();
            lexer.addErrorListener(new CustomErrorListener());
            
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            parser = new SenseParser(tokens);
            
            // Add error listener to parser
            parser.removeErrorListeners();
            parser.addErrorListener(new CustomErrorListener());
            
            return parserFunction.apply(parser);
            
        } catch (Exception e) {
            errors.add("Parsing failed: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Get parsing errors
     */
    public List<String> getErrors() {
        return new ArrayList<>(errors);
    }
    
    /**
     * Check if parsing had errors
     */
    public boolean hasErrors() {
        return !errors.isEmpty();
    }
    
    /**
     * Get the current lexer instance
     */
    public SenseLexer getLexer() {
        return lexer;
    }
    
    /**
     * Get the current parser instance
     */
    public SenseParser getParser() {
        return parser;
    }
    
    /**
     * Validate grammar syntax without full parsing
     */
    public boolean validateSyntax(String grammarText) {
        parseGrammar(grammarText);
        return !hasErrors();
    }
    
    /**
     * Get token stream for debugging
     */
    public List<Token> getTokens(String input) {
        CharStream charStream = CharStreams.fromString(input);
        SenseLexer tokenLexer = new SenseLexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(tokenLexer);
        tokens.fill();
        return tokens.getTokens();
    }
    
    /**
     * Functional interface for parser methods
     */
    @FunctionalInterface
    private interface ParserFunction {
        ParseTree apply(SenseParser parser) throws RecognitionException;
    }
    
    /**
     * Custom error listener to collect parsing errors
     */
    private class CustomErrorListener extends BaseErrorListener {
        @Override
        public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
                              int line, int charPositionInLine, String msg, RecognitionException e) {
            String error = String.format("Line %d:%d - %s", line, charPositionInLine, msg);
            errors.add(error);
        }
    }
}