package com.grammar.nlu;

/**
 * Demonstration of NLU parsing with the exact examples from requirements
 */
public class NLUDemo {
    
    public static void main(String[] args) {
        SenseNLUParser parser = new SenseNLUParser();
        
        // Load the weather grammar as specified in requirements
        String weatherGrammar = 
            "namespace weather;\n" +
            "\n" +
            "commonPrefix: \"help me\" | \"let me look\";\n" +
            "weatherInfo: \"weather\" | \"weather conditions\";\n" +
            "\n" +
            "<time>: \"today\" | \"tomorrow\" | \"the day after tomorrow\";\n" +
            "\n" +
            "@intent(value=\"WeatherSearch\")\n" +
            "@statement\n" +
            "askWeather: commonPrefix? <time> weatherInfo;";
        
        // Load navigation grammar
        String navigationGrammar = 
            "namespace navigation;\n" +
            "\n" +
            "navAction: \"navigate to\" | \"go to\";\n" +
            "\n" +
            "<poi>: \"People's Square\" | \"Central Park\" | \"Times Square\";\n" +
            "\n" +
            "@intent(value=\"Navigate\")\n" +
            "@statement\n" +
            "navigate: navAction <poi>;";
        
        System.out.println("=== NLU Parsing Demo ===\n");
        
        // Demo 1: Weather parsing
        System.out.println("1. Weather Grammar Demo:");
        if (parser.loadGrammar(weatherGrammar)) {
            System.out.println("✓ Weather grammar loaded successfully\n");
            
            // Test the exact example from requirements
            String[] weatherTests = {
                "Help me find out what the weather is like today",
                "help me today weather",
                "let me look tomorrow weather conditions",
                "today weather",
                "tomorrow weather conditions"
            };
            
            for (String test : weatherTests) {
                NLUResult result = parser.parseText(test);
                System.out.println("Input: \"" + test + "\"");
                System.out.println("Output: " + result.toJsonString());
                System.out.println();
            }
        } else {
            System.out.println("✗ Failed to load weather grammar");
            parser.getErrors().forEach(System.out::println);
        }
        
        // Demo 2: Navigation parsing
        System.out.println("2. Navigation Grammar Demo:");
        if (parser.loadGrammar(navigationGrammar)) {
            System.out.println("✓ Navigation grammar loaded successfully\n");
            
            String[] navigationTests = {
                "Navigate to People's Square",
                "go to Central Park",
                "navigate to Times Square"
            };
            
            for (String test : navigationTests) {
                NLUResult result = parser.parseText(test);
                System.out.println("Input: \"" + test + "\"");
                System.out.println("Output: " + result.toJsonString());
                System.out.println();
            }
        } else {
            System.out.println("✗ Failed to load navigation grammar");
            parser.getErrors().forEach(System.out::println);
        }
        
        // Demo 3: Combined grammar with multiple intents
        System.out.println("3. Combined Grammar Demo:");
        String combinedGrammar = 
            "namespace assistant;\n" +
            "\n" +
            "// Weather related\n" +
            "commonPrefix: \"help me\" | \"let me look\" | \"please\";\n" +
            "weatherInfo: \"weather\" | \"weather conditions\" | \"temperature\";\n" +
            "\n" +
            "// Navigation related\n" +
            "navAction: \"navigate to\" | \"go to\" | \"take me to\";\n" +
            "\n" +
            "// Slots\n" +
            "<time>: \"today\" | \"tomorrow\" | \"this week\";\n" +
            "<poi>: \"People's Square\" | \"Central Park\" | \"airport\" | \"station\";\n" +
            "\n" +
            "// Intent rules\n" +
            "@intent(value=\"WeatherSearch\")\n" +
            "@statement\n" +
            "askWeather: commonPrefix? <time> weatherInfo;\n" +
            "\n" +
            "@intent(value=\"Navigate\")\n" +
            "@statement\n" +
            "navigate: navAction <poi>;";
        
        if (parser.loadGrammar(combinedGrammar)) {
            System.out.println("✓ Combined grammar loaded successfully\n");
            
            String[] combinedTests = {
                "help me today weather",
                "navigate to People's Square",
                "please tomorrow weather conditions",
                "take me to airport",
                "this week temperature"
            };
            
            for (String test : combinedTests) {
                NLUResult result = parser.parseText(test);
                System.out.println("Input: \"" + test + "\"");
                System.out.println("Output: " + result.toJsonString());
                System.out.println("Intent: " + result.getIntent());
                System.out.println("Slots: " + result.getSlots());
                System.out.println();
            }
        } else {
            System.out.println("✗ Failed to load combined grammar");
            parser.getErrors().forEach(System.out::println);
        }
        
        // Demo 4: Show expected output format
        System.out.println("4. Expected Output Format Examples:");
        System.out.println("For \"Help me find out what the weather is like today\":");
        System.out.println("{\"intent\": \"WeatherSearch\", \"slot\": [{\"entity\": \"time\", \"value\": \"today\"}]}");
        System.out.println();
        System.out.println("For \"Navigate to People's Square\":");
        System.out.println("{\"intent\": \"Navigate\", \"slot\": [{\"entity\": \"poi\", \"value\": \"People's Square\"}]}");
    }
}