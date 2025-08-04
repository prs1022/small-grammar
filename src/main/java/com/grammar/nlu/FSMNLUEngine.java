package com.grammar.nlu;

import com.grammar.compiler.CLDCompiler;
import com.grammar.fsm.FiniteStateMachine;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

/**
 * FSM-based NLU Engine that loads CLD files and compiles them into FSMs
 */
public class FSMNLUEngine {
    
    private final CLDCompiler compiler;
    private final List<FiniteStateMachine> fsms;
    private final Map<String, FiniteStateMachine> fsmByName;
    
    public FSMNLUEngine() {
        this.compiler = new CLDCompiler();
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
            FiniteStateMachine fsm = compiler.compile(content);
            
            fsms.add(fsm);
            fsmByName.put(fsm.getName(), fsm);
            
            System.out.println("Loaded FSM: " + fsm.getName() + " -> " + fsm.getIntentName());
            
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
            FiniteStateMachine fsm = compiler.compile(grammarContent);
            fsms.add(fsm);
            fsmByName.put(name, fsm);
            
            System.out.println("Loaded FSM from string: " + name + " -> " + fsm.getIntentName());
            
        } catch (Exception e) {
            System.err.println("Failed to load grammar from string: " + name);
            System.err.println("Error: " + e.getMessage());
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
        
        // Return the best match (most slots found)
        if (!results.isEmpty()) {
            results.sort((a, b) -> Integer.compare(b.getSlots().size(), a.getSlots().size()));
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
        System.out.println("\n=== FSM NLU Engine Statistics ===");
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
        FSMNLUEngine engine = new FSMNLUEngine();
        
        try {
            // Load the weather.cld file
            engine.loadGrammarFile("src/main/resources/weather.cld");
            
            // Also load from string for demonstration
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
            
            engine.printStatistics();
            
            // Test cases
            String[] testCases = {
                "查询今天北京的天气怎么样",
                "看看明天上海天气",
                "今天天气",
                "导航到People's Square",
                "去Central Park",
                "前往Times Square"
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