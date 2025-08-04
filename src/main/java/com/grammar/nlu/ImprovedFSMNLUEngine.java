package com.grammar.nlu;

import com.grammar.compiler.ImprovedCLDCompiler;
import com.grammar.fsm.FiniteStateMachine;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

/**
 * Improved FSM-based NLU Engine using the enhanced compiler
 */
public class ImprovedFSMNLUEngine {
    
    private final ImprovedCLDCompiler compiler;
    private final List<FiniteStateMachine> fsms;
    private final Map<String, FiniteStateMachine> fsmByName;
    
    public ImprovedFSMNLUEngine() {
        this.compiler = new ImprovedCLDCompiler();
        this.fsms = new ArrayList<>();
        this.fsmByName = new HashMap<>();
    }
    
    /**
     * Load all CLD files from a directory
     */
    public void loadGrammarDirectory(String directoryPath) throws IOException {
        Path dir = Paths.get(directoryPath);
        
        if (!Files.exists(dir) || !Files.isDirectory(dir)) {
            throw new IOException("Directory does not exist: " + directoryPath);
        }
        
        try (Stream<Path> paths = Files.walk(dir)) {
            paths.filter(path -> path.toString().endsWith(".cld"))
                 .forEach(this::loadGrammarFile);
        }
        
        System.out.println("Loaded " + fsms.size() + " FSMs from " + directoryPath);
    }
    
    /**
     * Load a single CLD file
     */
    public void loadGrammarFile(String filePath) {
        loadGrammarFile(Paths.get(filePath));
    }
    
    /**
     * Load a single CLD file
     */
    private void loadGrammarFile(Path filePath) {
        try {
            String content = Files.readString(filePath);
            loadGrammarString(content, filePath.getFileName().toString());
            
        } catch (Exception e) {
            System.err.println("Failed to load grammar file: " + filePath);
            System.err.println("Error: " + e.getMessage());
        }
    }
    
    /**
     * Load grammar from string content
     */
    public void loadGrammarString(String grammarContent, String name) {
        try {
            List<FiniteStateMachine> compiledFSMs = compiler.compile(grammarContent);
            
            for (FiniteStateMachine fsm : compiledFSMs) {
                fsms.add(fsm);
                fsmByName.put(fsm.getName(), fsm);
                
                System.out.println("Loaded FSM: " + fsm.getName() + " -> " + fsm.getIntentName());
            }
            
        } catch (Exception e) {
            System.err.println("Failed to load grammar from string: " + name);
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Parse input text using all loaded FSMs
     */
    public NLUResult parseText(String input) {
        List<NLUResult> results = new ArrayList<>();
        
        // Try matching against all FSMs
        for (FiniteStateMachine fsm : fsms) {
            NLUResult result = fsm.match(input);
            if (!result.getIntent().equals("unknown")) {
                results.add(result);
            }
        }
        
        // Return the best match (most slots found, then longest match)
        if (!results.isEmpty()) {
            results.sort((a, b) -> {
                int slotCompare = Integer.compare(b.getSlots().size(), a.getSlots().size());
                if (slotCompare != 0) {
                    return slotCompare;
                }
                // Could add more sophisticated scoring here
                return 0;
            });
            return results.get(0);
        }
        
        return new NLUResult("unknown", new ArrayList<>());
    }
    
    /**
     * Parse input text using a specific FSM
     */
    public NLUResult parseText(String input, String fsmName) {
        FiniteStateMachine fsm = fsmByName.get(fsmName);
        if (fsm != null) {
            return fsm.match(input);
        }
        
        return new NLUResult("unknown", new ArrayList<>());
    }
    
    /**
     * Get all loaded FSMs
     */
    public List<FiniteStateMachine> getFSMs() {
        return new ArrayList<>(fsms);
    }
    
    /**
     * Get FSM by name
     */
    public FiniteStateMachine getFSM(String name) {
        return fsmByName.get(name);
    }
    
    /**
     * Get statistics about loaded FSMs
     */
    public void printStatistics() {
        System.out.println("\n=== Improved FSM NLU Engine Statistics ===");
        System.out.println("Total FSMs loaded: " + fsms.size());
        
        for (FiniteStateMachine fsm : fsms) {
            System.out.println(String.format("  %s -> %s (%d states)", 
                fsm.getName(), fsm.getIntentName(), fsm.getAllStates().size()));
        }
        System.out.println();
    }
    
    /**
     * Main method for demonstration
     */
    public static void main(String[] args) {
        ImprovedFSMNLUEngine engine = new ImprovedFSMNLUEngine();
        
        try {
            // Load grammar files from resources directory
            engine.loadGrammarFile("src/main/resources/weather.cld");
            engine.loadGrammarFile("src/main/resources/navigation.cld");
            
            engine.printStatistics();
            
            // Test cases that should work with our current FSM implementation
            String[] testCases = {
                // Simple weather patterns that should match
                "今天天气怎么样",
                "明天天气怎么样",
                "北京天气怎么样",
                "天气怎么样",
                
                // Navigation patterns that work
                "navigate to 公园",
                "go to 商场", 
                "导航到People's Square",
                "去Central Park",
                "前往airport",
                
                // Unknown input
                "unknown input"
            };
            
            System.out.println("=== Test Results ===");
            for (String testCase : testCases) {
                NLUResult result = engine.parseText(testCase);
                System.out.println("Input: \"" + testCase + "\"");
                System.out.println("Output: " + result.toJsonString());
                System.out.println();
            }
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}