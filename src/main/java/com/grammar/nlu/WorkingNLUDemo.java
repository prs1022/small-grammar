package com.grammar.nlu;

import java.util.*;

/**
 * Working demonstration of NLU parsing with exact requirements
 */
public class WorkingNLUDemo {
    
    public static void main(String[] args) {
        System.out.println("=== NLU Parser Demo ===\n");
        
        // Demo 1: Weather Intent
        demonstrateWeatherIntent();
        
        // Demo 2: Navigation Intent  
        demonstrateNavigationIntent();
        
        // Demo 3: Manual pattern matching (simplified approach)
        demonstrateManualMatching();
    }
    
    private static void demonstrateWeatherIntent() {
        System.out.println("1. Weather Intent Demo:");
        
        // Create a simple rule-based matcher for weather queries
        WeatherMatcher weatherMatcher = new WeatherMatcher();
        
        String[] testInputs = {
            "Help me find out what the weather is like today",
            "help me today weather",
            "let me look tomorrow weather conditions",
            "today weather",
            "weather today"
        };
        
        for (String input : testInputs) {
            NLUResult result = weatherMatcher.match(input);
            System.out.println("Input: \"" + input + "\"");
            System.out.println("Output: " + result.toJsonString());
            System.out.println();
        }
    }
    
    private static void demonstrateNavigationIntent() {
        System.out.println("2. Navigation Intent Demo:");
        
        NavigationMatcher navMatcher = new NavigationMatcher();
        
        String[] testInputs = {
            "Navigate to People's Square",
            "go to People's Square",
            "take me to Central Park"
        };
        
        for (String input : testInputs) {
            NLUResult result = navMatcher.match(input);
            System.out.println("Input: \"" + input + "\"");
            System.out.println("Output: " + result.toJsonString());
            System.out.println();
        }
    }
    
    private static void demonstrateManualMatching() {
        System.out.println("3. Manual Pattern Matching Demo:");
        
        // This shows how the grammar rules would work in practice
        PatternMatcher matcher = new PatternMatcher();
        
        // Add weather patterns
        matcher.addPattern("weather", "WeatherSearch", 
            new String[]{"help me", "let me look"}, // optional prefixes
            new String[]{"today", "tomorrow", "the day after tomorrow"}, // time slot
            new String[]{"weather", "weather conditions"} // weather info
        );
        
        // Add navigation patterns
        matcher.addPattern("navigation", "Navigate",
            new String[]{"navigate to", "go to", "take me to"}, // nav actions
            new String[]{"People's Square", "Central Park", "Times Square"}, // poi slot
            null // no third component
        );
        
        String[] allTests = {
            "help me today weather",
            "Navigate to People's Square",
            "let me look tomorrow weather conditions",
            "go to Central Park"
        };
        
        for (String test : allTests) {
            NLUResult result = matcher.match(test);
            System.out.println("Input: \"" + test + "\"");
            System.out.println("Output: " + result.toJsonString());
            System.out.println();
        }
    }
    
    // Simple weather matcher
    static class WeatherMatcher {
        private final String[] prefixes = {"help me", "let me look"};
        private final String[] timeSlots = {"today", "tomorrow", "the day after tomorrow"};
        private final String[] weatherWords = {"weather", "weather conditions", "temperature"};
        
        public NLUResult match(String input) {
            String text = input.toLowerCase();
            
            // Find time slot
            String timeValue = null;
            for (String time : timeSlots) {
                if (text.contains(time)) {
                    timeValue = time;
                    break;
                }
            }
            
            // Check if it contains weather words
            boolean hasWeatherWord = false;
            for (String weather : weatherWords) {
                if (text.contains(weather)) {
                    hasWeatherWord = true;
                    break;
                }
            }
            
            if (timeValue != null && hasWeatherWord) {
                return new NLUResult("WeatherSearch", 
                    Arrays.asList(new Slot("time", timeValue)));
            }
            
            return new NLUResult("unknown", new ArrayList<>());
        }
    }
    
    // Simple navigation matcher
    static class NavigationMatcher {
        private final String[] navActions = {"navigate to", "go to", "take me to"};
        private final String[] pois = {"People's Square", "Central Park", "Times Square", "airport", "station"};
        
        public NLUResult match(String input) {
            String text = input.toLowerCase();
            
            // Check for navigation action
            boolean hasNavAction = false;
            for (String action : navActions) {
                if (text.contains(action.toLowerCase())) {
                    hasNavAction = true;
                    break;
                }
            }
            
            // Find POI
            String poiValue = null;
            for (String poi : pois) {
                if (text.toLowerCase().contains(poi.toLowerCase())) {
                    poiValue = poi;
                    break;
                }
            }
            
            if (hasNavAction && poiValue != null) {
                return new NLUResult("Navigate", 
                    Arrays.asList(new Slot("poi", poiValue)));
            }
            
            return new NLUResult("unknown", new ArrayList<>());
        }
    }
    
    // Generic pattern matcher
    static class PatternMatcher {
        private final List<Pattern> patterns = new ArrayList<>();
        
        public void addPattern(String name, String intent, String[] component1, String[] component2, String[] component3) {
            patterns.add(new Pattern(name, intent, component1, component2, component3));
        }
        
        public NLUResult match(String input) {
            String text = input.toLowerCase();
            
            for (Pattern pattern : patterns) {
                NLUResult result = pattern.match(text);
                if (!result.getIntent().equals("unknown")) {
                    return result;
                }
            }
            
            return new NLUResult("unknown", new ArrayList<>());
        }
        
        static class Pattern {
            final String name;
            final String intent;
            final String[] component1;
            final String[] component2;
            final String[] component3;
            
            Pattern(String name, String intent, String[] component1, String[] component2, String[] component3) {
                this.name = name;
                this.intent = intent;
                this.component1 = component1;
                this.component2 = component2;
                this.component3 = component3;
            }
            
            NLUResult match(String text) {
                List<Slot> slots = new ArrayList<>();
                
                if (name.equals("weather")) {
                    // Find time slot
                    String timeValue = null;
                    for (String time : component2) {
                        if (text.contains(time)) {
                            timeValue = time;
                            break;
                        }
                    }
                    
                    // Check weather words
                    boolean hasWeatherWord = false;
                    for (String weather : component3) {
                        if (text.contains(weather)) {
                            hasWeatherWord = true;
                            break;
                        }
                    }
                    
                    if (timeValue != null && hasWeatherWord) {
                        slots.add(new Slot("time", timeValue));
                        return new NLUResult(intent, slots);
                    }
                } else if (name.equals("navigation")) {
                    // Check nav action
                    boolean hasNavAction = false;
                    for (String action : component1) {
                        if (text.contains(action)) {
                            hasNavAction = true;
                            break;
                        }
                    }
                    
                    // Find POI
                    String poiValue = null;
                    for (String poi : component2) {
                        if (text.toLowerCase().contains(poi.toLowerCase())) {
                            poiValue = poi;
                            break;
                        }
                    }
                    
                    if (hasNavAction && poiValue != null) {
                        slots.add(new Slot("poi", poiValue));
                        return new NLUResult(intent, slots);
                    }
                }
                
                return new NLUResult("unknown", new ArrayList<>());
            }
        }
    }
}