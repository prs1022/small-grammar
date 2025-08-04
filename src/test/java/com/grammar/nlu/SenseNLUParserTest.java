package com.grammar.nlu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class SenseNLUParserTest {
    
    private SenseNLUParser parser;
    
    @BeforeEach
    void setUp() {
        parser = new SenseNLUParser();
    }
    
    @Test
    void testWeatherGrammarParsing() {
        String weatherGrammar = 
            "namespace weather;\n" +
            "commonPrefix: \"help me\" | \"let me look\";\n" +
            "weatherInfo: \"weather\" | \"weather conditions\";\n" +
            "<time>: \"today\" | \"tomorrow\" | \"the day after tomorrow\";\n" +
            "@intent(value=\"WeatherSearch\")\n" +
            "@statement\n" +
            "askWeather: commonPrefix? <time> weatherInfo;";
        
        assertTrue(parser.loadGrammar(weatherGrammar));
        assertFalse(parser.hasErrors());
    }
    
    @Test
    void testWeatherIntentExtraction() {
        String weatherGrammar = 
            "namespace weather;\n" +
            "commonPrefix: \"help me\" | \"let me look\";\n" +
            "weatherInfo: \"weather\" | \"weather conditions\";\n" +
            "<time>: \"today\" | \"tomorrow\" | \"the day after tomorrow\";\n" +
            "@intent(value=\"WeatherSearch\")\n" +
            "@statement\n" +
            "askWeather: commonPrefix? <time> weatherInfo;";
        
        parser.loadGrammar(weatherGrammar);
        
        // Test case 1: "Help me find out what the weather is like today"
        NLUResult result1 = parser.parseText("help me today weather");
        assertEquals("WeatherSearch", result1.getIntent());
        assertEquals(1, result1.getSlots().size());
        assertEquals("time", result1.getSlots().get(0).getEntity());
        assertEquals("today", result1.getSlots().get(0).getValue());
        
        // Test case 2: "let me look tomorrow weather conditions"
        NLUResult result2 = parser.parseText("let me look tomorrow weather conditions");
        assertEquals("WeatherSearch", result2.getIntent());
        assertEquals(1, result2.getSlots().size());
        assertEquals("time", result2.getSlots().get(0).getEntity());
        assertEquals("tomorrow", result2.getSlots().get(0).getValue());
        
        // Test case 3: "today weather" (without optional prefix)
        NLUResult result3 = parser.parseText("today weather");
        assertEquals("WeatherSearch", result3.getIntent());
        assertEquals(1, result3.getSlots().size());
        assertEquals("time", result3.getSlots().get(0).getEntity());
        assertEquals("today", result3.getSlots().get(0).getValue());
    }
    
    @Test
    void testNavigationGrammar() {
        String navigationGrammar = 
            "namespace navigation;\n" +
            "navAction: \"navigate to\" | \"go to\" | \"take me to\";\n" +
            "<poi>: \"People's Square\" | \"Central Park\" | \"Times Square\";\n" +
            "@intent(value=\"Navigate\")\n" +
            "@statement\n" +
            "navigate: navAction <poi>;";
        
        assertTrue(parser.loadGrammar(navigationGrammar));
        
        // Test navigation intent
        NLUResult result = parser.parseText("navigate to People's Square");
        assertEquals("Navigate", result.getIntent());
        assertEquals(1, result.getSlots().size());
        assertEquals("poi", result.getSlots().get(0).getEntity());
        assertEquals("People's Square", result.getSlots().get(0).getValue());
    }
    
    @Test
    void testComplexGrammarWithMultipleIntents() {
        String complexGrammar = 
            "namespace assistant;\n" +
            "\n" +
            "commonPrefix: \"help me\" | \"please\" | \"can you\";\n" +
            "weatherInfo: \"weather\" | \"weather conditions\" | \"temperature\";\n" +
            "navAction: \"navigate to\" | \"go to\" | \"directions to\";\n" +
            "\n" +
            "<time>: \"today\" | \"tomorrow\" | \"this week\";\n" +
            "<location>: \"Beijing\" | \"Shanghai\" | \"New York\";\n" +
            "<poi>: \"airport\" | \"station\" | \"mall\";\n" +
            "\n" +
            "@intent(value=\"WeatherQuery\")\n" +
            "@statement\n" +
            "weatherQuery: commonPrefix? weatherInfo \"in\" <location> <time>?;\n" +
            "\n" +
            "@intent(value=\"Navigation\")\n" +
            "@statement\n" +
            "navigation: navAction <poi> \"in\" <location>?;";
        
        assertTrue(parser.loadGrammar(complexGrammar));
        
        // Test weather query
        NLUResult weatherResult = parser.parseText("help me weather in Beijing today");
        assertEquals("WeatherQuery", weatherResult.getIntent());
        assertEquals(2, weatherResult.getSlots().size());
        
        // Find location and time slots
        Slot locationSlot = weatherResult.getSlots().stream()
            .filter(s -> s.getEntity().equals("location"))
            .findFirst().orElse(null);
        assertNotNull(locationSlot);
        assertEquals("Beijing", locationSlot.getValue());
        
        Slot timeSlot = weatherResult.getSlots().stream()
            .filter(s -> s.getEntity().equals("time"))
            .findFirst().orElse(null);
        assertNotNull(timeSlot);
        assertEquals("today", timeSlot.getValue());
        
        // Test navigation
        NLUResult navResult = parser.parseText("go to airport in Shanghai");
        assertEquals("Navigation", navResult.getIntent());
        assertEquals(2, navResult.getSlots().size());
    }
    
    @Test
    void testUnknownIntent() {
        String simpleGrammar = 
            "namespace test;\n" +
            "<item>: \"book\" | \"pen\";\n" +
            "@intent(value=\"FindItem\")\n" +
            "@statement\n" +
            "findItem: \"find\" <item>;";
        
        parser.loadGrammar(simpleGrammar);
        
        // Test unrecognized input
        NLUResult result = parser.parseText("play music");
        assertEquals("unknown", result.getIntent());
        assertEquals(0, result.getSlots().size());
    }
    
    @Test
    void testJSONOutput() {
        String grammar = 
            "namespace test;\n" +
            "<time>: \"today\" | \"tomorrow\";\n" +
            "@intent(value=\"WeatherSearch\")\n" +
            "@statement\n" +
            "weather: \"weather\" <time>;";
        
        parser.loadGrammar(grammar);
        
        NLUResult result = parser.parseText("weather today");
        String json = result.toJsonString();
        
        assertTrue(json.contains("\"intent\": \"WeatherSearch\""));
        assertTrue(json.contains("\"entity\": \"time\""));
        assertTrue(json.contains("\"value\": \"today\""));
    }
    
    @Test
    void testErrorHandling() {
        String invalidGrammar = "invalid grammar syntax here";
        
        assertFalse(parser.loadGrammar(invalidGrammar));
        assertTrue(parser.hasErrors());
        assertFalse(parser.getErrors().isEmpty());
    }
    
    @Test
    void testCaseInsensitiveMatching() {
        String grammar = 
            "namespace test;\n" +
            "<item>: \"Book\" | \"Pen\";\n" +
            "@intent(value=\"FindItem\")\n" +
            "@statement\n" +
            "findItem: \"Find\" <item>;";
        
        parser.loadGrammar(grammar);
        
        // Test different cases
        NLUResult result1 = parser.parseText("find book");
        assertEquals("FindItem", result1.getIntent());
        
        NLUResult result2 = parser.parseText("FIND BOOK");
        assertEquals("FindItem", result2.getIntent());
        
        NLUResult result3 = parser.parseText("Find Book");
        assertEquals("FindItem", result3.getIntent());
    }
}