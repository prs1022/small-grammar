package com.grammar.grammar;

import org.antlr.v4.runtime.tree.*;
import com.grammar.antlr.*;
import java.util.*;

/**
 * Utility class for analyzing and traversing Sense parse trees
 */
public class ParseTreeAnalyzer {
    
    /**
     * Extract all production rules from a grammar parse tree
     */
    public static List<ProductionInfo> extractProductions(ParseTree tree) {
        List<ProductionInfo> productions = new ArrayList<>();
        
        if (tree instanceof SenseParser.SenseContext) {
            SenseParser.SenseContext senseContext = (SenseParser.SenseContext) tree;
            
            for (SenseParser.ProductionSentenceContext prodCtx : senseContext.productionSentence()) {
                ProductionInfo info = new ProductionInfo();
                info.name = prodCtx.name.getText();
                info.expression = prodCtx.expr().getText();
                
                // Extract annotations if present
                if (prodCtx.annotation() != null) {
                    for (SenseParser.AnnotationContext annCtx : prodCtx.annotation()) {
                        info.annotations.add(annCtx.getText());
                    }
                }
                
                // Extract exclusion if present
                if (prodCtx.exclusion() != null) {
                    info.exclusion = prodCtx.exclusion().getText();
                }
                
                productions.add(info);
            }
        }
        
        return productions;
    }
    
    /**
     * Extract all prime rules from a grammar parse tree
     */
    public static List<PrimeInfo> extractPrimes(ParseTree tree) {
        List<PrimeInfo> primes = new ArrayList<>();
        
        if (tree instanceof SenseParser.SenseContext) {
            SenseParser.SenseContext senseContext = (SenseParser.SenseContext) tree;
            
            for (SenseParser.PrimeSentenceContext primeCtx : senseContext.primeSentence()) {
                PrimeInfo info = new PrimeInfo();
                info.name = primeCtx.name.getText();
                info.expression = primeCtx.expr().getText();
                
                // Extract criteria if present
                if (primeCtx.criteria() != null) {
                    info.criteria = primeCtx.criteria().getText();
                }
                
                // Extract annotations if present
                if (primeCtx.annotation() != null) {
                    for (SenseParser.AnnotationContext annCtx : primeCtx.annotation()) {
                        info.annotations.add(annCtx.getText());
                    }
                }
                
                // Extract exclusion if present
                if (primeCtx.exclusion() != null) {
                    info.exclusion = primeCtx.exclusion().getText();
                }
                
                primes.add(info);
            }
        }
        
        return primes;
    }
    
    /**
     * Extract all macro definitions from a grammar parse tree
     */
    public static List<MacroInfo> extractMacros(ParseTree tree) {
        List<MacroInfo> macros = new ArrayList<>();
        
        if (tree instanceof SenseParser.SenseContext) {
            SenseParser.SenseContext senseContext = (SenseParser.SenseContext) tree;
            
            for (SenseParser.MacroSentenceContext macroCtx : senseContext.macroSentence()) {
                MacroInfo info = new MacroInfo();
                info.name = macroCtx.name.getText();
                info.expression = macroCtx.expr().getText();
                
                // Extract parameters if present
                if (macroCtx.idList() != null) {
                    for (TerminalNode id : macroCtx.idList().ID()) {
                        info.parameters.add(id.getText());
                    }
                }
                
                // Extract annotations if present
                if (macroCtx.annotation() != null) {
                    for (SenseParser.AnnotationContext annCtx : macroCtx.annotation()) {
                        info.annotations.add(annCtx.getText());
                    }
                }
                
                macros.add(info);
            }
        }
        
        return macros;
    }
    
    /**
     * Extract namespace from a grammar parse tree
     */
    public static String extractNamespace(ParseTree tree) {
        if (tree instanceof SenseParser.SenseContext) {
            SenseParser.SenseContext senseContext = (SenseParser.SenseContext) tree;
            if (senseContext.nsSentence() != null) {
                return senseContext.nsSentence().namespace().getText();
            }
        }
        return null;
    }
    
    /**
     * Extract all imports from a grammar parse tree
     */
    public static List<String> extractImports(ParseTree tree) {
        List<String> imports = new ArrayList<>();
        
        if (tree instanceof SenseParser.SenseContext) {
            SenseParser.SenseContext senseContext = (SenseParser.SenseContext) tree;
            
            for (SenseParser.ImportSentenceContext importCtx : senseContext.importSentence()) {
                imports.add(importCtx.getText());
            }
        }
        
        return imports;
    }
    
    /**
     * Pretty print a parse tree for debugging
     */
    public static String prettyPrint(ParseTree tree, SenseParser parser) {
        return tree.toStringTree(parser);
    }
    
    /**
     * Information about a production rule
     */
    public static class ProductionInfo {
        public String name;
        public String expression;
        public String exclusion;
        public List<String> annotations = new ArrayList<>();
        
        @Override
        public String toString() {
            return String.format("Production{name='%s', expr='%s', annotations=%s, exclusion='%s'}", 
                name, expression, annotations, exclusion);
        }
    }
    
    /**
     * Information about a prime rule
     */
    public static class PrimeInfo {
        public String name;
        public String expression;
        public String criteria;
        public String exclusion;
        public List<String> annotations = new ArrayList<>();
        
        @Override
        public String toString() {
            return String.format("Prime{name='%s', expr='%s', criteria='%s', annotations=%s, exclusion='%s'}", 
                name, expression, criteria, annotations, exclusion);
        }
    }
    
    /**
     * Information about a macro definition
     */
    public static class MacroInfo {
        public String name;
        public String expression;
        public List<String> parameters = new ArrayList<>();
        public List<String> annotations = new ArrayList<>();
        
        @Override
        public String toString() {
            return String.format("Macro{name='%s', params=%s, expr='%s', annotations=%s}", 
                name, parameters, expression, annotations);
        }
    }
}