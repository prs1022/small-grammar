package com.grammar.nlu;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WorkingNLUTest {
    
    @Test
    void testWeatherIntentMatching() {
        WorkingNLUDemo.WeatherMatcher matcher = new WorkingNLUDemo.WeatherMatcher();
        
        // Test case 1: "Help me find out what the weather is like today"
        NLUResult result1 = matcher.match("Help me find out what the weather is like today");
        assertEquals("WeatherSearch", result1.getIntent());
        assertEquals(1, result1.getSlots().size());
        assertEquals("time", result1.getSlots().get(0).getEntity());
        assertEquals("today", result1.getSlots().get(0).getValue());
        
        // Test case 2: "help me today weather"
        NLUResult result2 = matcher.match("help me today weather");
        assertEquals("WeatherSearch", result2.getIntent());
        assertEquals("today", result2.getSlots().get(0).getValue());
        
        // Test case 3: "tomorrow weather conditions"
        NLUResult result3 = matcher.match("tomorrow weather conditions");
        assertEquals("WeatherSearch", result3.getIntent());
        assertEquals("tomorrow", result3.getSlots().get(0).getValue());
    }
    
    @Test
    void testNavigationIntentMatching() {
        WorkingNLUDemo.NavigationMatcher matcher = new WorkingNLUDemo.NavigationMatcher();
        
        // Test case: "Navigate to People's Square"
        NLUResult result = matcher.match("Navigate to People's Square");
        assertEquals("Navigate", result.getIntent());
        assertEquals(1, result.getSlots().size());
        assertEquals("poi", result.getSlots().get(0).getEntity());
        assertEquals("People's Square", result.getSlots().get(0).getValue());
    }
    
    @Test
    void testJSONOutput() {
        WorkingNLUDemo.WeatherMatcher weatherMatcher = new WorkingNLUDemo.WeatherMatcher();
        NLUResult weatherResult = weatherMatcher.match("today weather");
        
        String json = weatherResult.toJsonString();
        assertEquals("{\"intent\": \"WeatherSearch\", \"slot\": [{\"entity\": \"time\", \"value\": \"today\"}]}", json);
        
        WorkingNLUDemo.NavigationMatcher navMatcher = new WorkingNLUDemo.NavigationMatcher();
        NLUResult navResult = navMatcher.match("Navigate to People's Square");
        
        String navJson = navResult.toJsonString();
        assertEquals("{\"intent\": \"Navigate\", \"slot\": [{\"entity\": \"poi\", \"value\": \"People's Square\"}]}", navJson);
    }
    
    @Test
    void testPatternMatcher() {
        WorkingNLUDemo.PatternMatcher matcher = new WorkingNLUDemo.PatternMatcher();
        
        // Add weather pattern
        matcher.addPattern("weather", "WeatherSearch", 
            new String[]{"help me", "let me look"}, 
            new String[]{"today", "tomorrow"}, 
            new String[]{"weather", "weather conditions"}
        );
        
        // Add navigation pattern
        matcher.addPattern("navigation", "Navigate",
            new String[]{"navigate to", "go to"}, 
            new String[]{"People's Square", "Central Park"}, 
            null
        );
        
        // Test weather
        NLUResult weatherResult = matcher.match("help me today weather");
        assertEquals("WeatherSearch", weatherResult.getIntent());
        assertEquals("time", weatherResult.getSlots().get(0).getEntity());
        assertEquals("today", weatherResult.getSlots().get(0).getValue());
        
        // Test navigation
        NLUResult navResult = matcher.match("navigate to People's Square");
        assertEquals("Navigate", navResult.getIntent());
        assertEquals("poi", navResult.getSlots().get(0).getEntity());
        assertEquals("People's Square", navResult.getSlots().get(0).getValue());
    }
    
    @Test
    void testUnknownIntent() {
        WorkingNLUDemo.WeatherMatcher matcher = new WorkingNLUDemo.WeatherMatcher();
        
        NLUResult result = matcher.match("play music");
        assertEquals("unknown", result.getIntent());
        assertEquals(0, result.getSlots().size());
    }
}