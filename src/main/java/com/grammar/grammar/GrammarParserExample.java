package com.grammar.grammar;

import org.antlr.v4.runtime.tree.ParseTree;
import com.grammar.grammar.ParseTreeAnalyzer.*;
import java.util.List;

/**
 * Example demonstrating how to use the GrammarParser
 */
public class GrammarParserExample {

    public static void main(String[] args) {
        GrammarParser parser = new GrammarParser();

        // Example Sense grammar
        String sampleGrammar = "namespace example;\n\n" +
                "import common::words;\n\n" +
                "@description\n" +
                "greeting: \"hello\" <person> | \"hi\" <person>;\n\n" +
                "@category\n" +
                "<person>: \"world\" | \"there\" | \"friend\";\n\n" +
                "@template\n" +
                "repeat(x): x x;\n\n" +
                "~processGreeting: <(\n" +
                "    return \"processed\";\n" +
                ")>;";

        System.out.println("=== Parsing Complete Grammar ===");
        ParseTree tree = parser.parseGrammar(sampleGrammar);

        if (parser.hasErrors()) {
            System.out.println("Parsing errors:");
            parser.getErrors().forEach(System.out::println);
            return;
        }

        System.out.println("Grammar parsed successfully!");

        // Extract information from the parse tree
        System.out.println("\n=== Grammar Analysis ===");

        String namespace = ParseTreeAnalyzer.extractNamespace(tree);
        System.out.println("Namespace: " + namespace);

        List<String> imports = ParseTreeAnalyzer.extractImports(tree);
        System.out.println("Imports: " + imports);

        List<ProductionInfo> productions = ParseTreeAnalyzer.extractProductions(tree);
        System.out.println("\nProductions:");
        productions.forEach(System.out::println);

        List<PrimeInfo> primes = ParseTreeAnalyzer.extractPrimes(tree);
        System.out.println("\nPrimes:");
        primes.forEach(System.out::println);

        List<MacroInfo> macros = ParseTreeAnalyzer.extractMacros(tree);
        System.out.println("\nMacros:");
        macros.forEach(System.out::println);

        // Test individual parsing methods
        System.out.println("\n=== Individual Parsing Tests ===");

        testExpressionParsing(parser);
        testProductionParsing(parser);
        testPrimeParsing(parser);
        testMacroParsing(parser);
        testCriteriaParsing(parser);

        // Test error handling
        System.out.println("\n=== Error Handling Test ===");
        testErrorHandling(parser);

        // Test token analysis
        System.out.println("\n=== Token Analysis ===");
        testTokenAnalysis(parser);
    }

    private static void testExpressionParsing(GrammarParser parser) {
        System.out.println("\nTesting expression parsing:");

        String[] expressions = {
                "\"hello\" \"world\"",
                "\"hi\" | \"hello\"",
                "<person> \"+\"? <greeting>",
                "[repeat(\"test\")]",
                "\"start\" . \"end\"",
                "${tag1|tag2}"
        };

        for (String expr : expressions) {
            ParseTree tree = parser.parseExpression(expr);
            System.out.printf("  '%s' -> %s\n", expr,
                    tree != null && !parser.hasErrors() ? "OK" : "ERROR");
        }
    }

    private static void testProductionParsing(GrammarParser parser) {
        System.out.println("\nTesting production parsing:");

        String[] productions = {
                "simple: \"test\";",
                "@annotation production: \"hello\" \"world\";",
                "complex: \"start\" <middle> \"end\" ! \"exclude\";"
        };

        for (String prod : productions) {
            ParseTree tree = parser.parseProduction(prod);
            System.out.printf("  '%s' -> %s\n", prod,
                    tree != null && !parser.hasErrors() ? "OK" : "ERROR");
        }
    }

    private static void testPrimeParsing(GrammarParser parser) {
        System.out.println("\nTesting prime parsing:");

        String[] primes = {
                "<word>: \"test\";",
                "@[noun] <person>: \"john\" | \"jane\";",
                "<greeting>: \"hello\" ! \"goodbye\";"
        };

        for (String prime : primes) {
            ParseTree tree = parser.parsePrime(prime);
            System.out.printf("  '%s' -> %s\n", prime,
                    tree != null && !parser.hasErrors() ? "OK" : "ERROR");
        }
    }

    private static void testMacroParsing(GrammarParser parser) {
        System.out.println("\nTesting macro parsing:");

        String[] macros = {
                "simple(): \"test\";",
                "repeat(x): x x;",
                "@template optional(x): x?;"
        };

        for (String macro : macros) {
            ParseTree tree = parser.parseMacro(macro);
            System.out.printf("  '%s' -> %s\n", macro,
                    tree != null && !parser.hasErrors() ? "OK" : "ERROR");
        }
    }

    private static void testCriteriaParsing(GrammarParser parser) {
        System.out.println("\nTesting criteria parsing:");

        String[] criteria = {
                "@[noun]",
                "@[verb & !past]",
                "@[noun | adjective]",
                "@[word{pos=noun}]"
        };

        for (String criterion : criteria) {
            ParseTree tree = parser.parseCriteria(criterion);
            System.out.printf("  '%s' -> %s\n", criterion,
                    tree != null && !parser.hasErrors() ? "OK" : "ERROR");
        }
    }

    private static void testErrorHandling(GrammarParser parser) {
        String invalidGrammar = "namespace test production invalid syntax here";

        ParseTree tree = parser.parseGrammar(invalidGrammar);

        if (parser.hasErrors()) {
            System.out.println("Successfully caught parsing errors:");
            parser.getErrors().forEach(error -> System.out.println("  " + error));
        } else {
            System.out.println("ERROR: Should have detected parsing errors!");
        }
    }

    private static void testTokenAnalysis(GrammarParser parser) {
        String input = "namespace test; production: \"hello\" <world>;";

        var tokens = parser.getTokens(input);
        System.out.println("Tokens for: " + input);

        tokens.forEach(token -> {
            if (token.getType() != -1) { // Skip EOF token details
                System.out.printf("  %s: '%s'\n",
                        parser.getLexer() != null ? parser.getLexer().getVocabulary().getDisplayName(token.getType())
                                : "Token" + token.getType(),
                        token.getText());
            }
        });
    }
}