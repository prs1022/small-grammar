package com.grammar.nlu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FSMNLUEngineTest {
    
    private FSMNLUEngine engine;
    
    @BeforeEach
    void setUp() {
        engine = new FSMNLUEngine();
    }
    
    @Test
    void testWeatherGrammarLoading() {
        String weatherGrammar = 
            "namespace weather;\n" +
            "\n" +
            "@type(name=\"location\")\n" +
            "<location>: ${city|district|poi};\n" +
            "\n" +
            "@type(name=\"date_time\")\n" +
            "<date_time>: ${date|time|datetime};\n" +
            "\n" +
            "@attr(intention=\"weatherSearch\")\n" +
            "@statement\n" +
            "query_weather: (\"查\" \"询\"? | \"看\" \"看\"?)? <date_time>? <location>? \"的\"? \"天气\" \"怎么样\"?;";
        
        engine.loadGrammarString(weatherGrammar, "weather");
        
        assertEquals(1, engine.getFSMs().size());
        assertNotNull(engine.getFSM("query_weather"));
    }
    
    @Test
    void testNavigationGrammarLoading() {
        String navigationGrammar = 
            "namespace navigation;\n" +
            "\n" +
            "@type(name=\"poi\")\n" +
            "<poi>: ${\"People's Square\"|\"Central Park\"|\"Times Square\"};\n" +
            "\n" +
            "@attr(intention=\"Navigate\")\n" +
            "@statement\n" +
            "navigate: (\"导航\" \"到\" | \"去\" | \"前往\") <poi>;";
        
        engine.loadGrammarString(navigationGrammar, "navigation");
        
        assertEquals(1, engine.getFSMs().size());
        assertEquals("Navigate", engine.getFSM("navigate").getIntentName());
    }
    
    @Test
    void testSimpleWeatherMatching() {
        String weatherGrammar = 
            "namespace weather;\n" +
            "\n" +
            "@type(name=\"date_time\")\n" +
            "<date_time>: \"今天\" | \"明天\" | \"后天\";\n" +
            "\n" +
            "@attr(intention=\"weatherSearch\")\n" +
            "@statement\n" +
            "query_weather: <date_time> \"天气\";";
        
        engine.loadGrammarString(weatherGrammar, "weather");
        
        NLUResult result = engine.parseText("今天 天气");
        assertEquals("weatherSearch", result.getIntent());
        assertEquals(1, result.getSlots().size());
        assertEquals("date_time", result.getSlots().get(0).getEntity());
        assertEquals("今天", result.getSlots().get(0).getValue());
    }
    
    @Test
    void testOptionalElements() {
        String grammar = 
            "namespace test;\n" +
            "\n" +
            "@type(name=\"time\")\n" +
            "<time>: \"today\" | \"tomorrow\";\n" +
            "\n" +
            "@attr(intention=\"WeatherQuery\")\n" +
            "@statement\n" +
            "weather: \"help\"? <time> \"weather\";";
        
        engine.loadGrammarString(grammar, "test");
        
        // Test with optional element
        NLUResult result1 = engine.parseText("help today weather");
        assertEquals("WeatherQuery", result1.getIntent());
        assertEquals("today", result1.getSlots().get(0).getValue());
        
        // Test without optional element
        NLUResult result2 = engine.parseText("today weather");
        assertEquals("WeatherQuery", result2.getIntent());
        assertEquals("today", result2.getSlots().get(0).getValue());
    }
    
    @Test
    void testMultipleFSMs() {
        // Load weather grammar
        String weatherGrammar = 
            "namespace weather;\n" +
            "@type(name=\"time\")\n" +
            "<time>: \"today\" | \"tomorrow\";\n" +
            "@attr(intention=\"WeatherQuery\")\n" +
            "@statement\n" +
            "weather: <time> \"weather\";";
        
        // Load navigation grammar
        String navGrammar = 
            "namespace navigation;\n" +
            "@type(name=\"poi\")\n" +
            "<poi>: \"park\" | \"mall\";\n" +
            "@attr(intention=\"Navigate\")\n" +
            "@statement\n" +
            "navigate: \"go\" <poi>;";
        
        engine.loadGrammarString(weatherGrammar, "weather");
        engine.loadGrammarString(navGrammar, "navigation");
        
        assertEquals(2, engine.getFSMs().size());
        
        // Test weather query
        NLUResult weatherResult = engine.parseText("today weather");
        assertEquals("WeatherQuery", weatherResult.getIntent());
        
        // Test navigation query
        NLUResult navResult = engine.parseText("go park");
        assertEquals("Navigate", navResult.getIntent());
    }
    
    @Test
    void testUnknownInput() {
        String grammar = 
            "namespace test;\n" +
            "@type(name=\"item\")\n" +
            "<item>: \"book\" | \"pen\";\n" +
            "@attr(intention=\"FindItem\")\n" +
            "@statement\n" +
            "find: \"find\" <item>;";
        
        engine.loadGrammarString(grammar, "test");
        
        NLUResult result = engine.parseText("play music");
        assertEquals("unknown", result.getIntent());
        assertEquals(0, result.getSlots().size());
    }
    
    @Test
    void testJSONOutput() {
        String grammar = 
            "namespace test;\n" +
            "@type(name=\"time\")\n" +
            "<time>: \"today\";\n" +
            "@attr(intention=\"WeatherSearch\")\n" +
            "@statement\n" +
            "weather: <time> \"weather\";";
        
        engine.loadGrammarString(grammar, "test");
        
        NLUResult result = engine.parseText("today weather");
        String json = result.toJsonString();
        
        assertTrue(json.contains("\"intent\": \"WeatherSearch\""));
        assertTrue(json.contains("\"entity\": \"time\""));
        assertTrue(json.contains("\"value\": \"today\""));
    }
}