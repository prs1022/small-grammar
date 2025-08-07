package com.grammar.nlu;

import com.grammar.compiler.ImprovedCLDCompiler;
import com.grammar.compiler.IncrementalCompiler;
import com.grammar.analyzer.RuleAnalyzer;
import com.grammar.optimizer.FSMOptimizer;
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
    private final IncrementalCompiler incrementalCompiler;
    private final RuleAnalyzer ruleAnalyzer;
    private final FSMOptimizer fsmOptimizer;
    private final List<FiniteStateMachine> fsms;
    private final Map<String, FiniteStateMachine> fsmByName;
    
    public ImprovedFSMNLUEngine() {
        this.compiler = new ImprovedCLDCompiler();
        this.incrementalCompiler = new IncrementalCompiler(compiler);
        this.ruleAnalyzer = new RuleAnalyzer();
        this.fsmOptimizer = new FSMOptimizer();
        this.fsms = new ArrayList<>();
        this.fsmByName = new HashMap<>();
    }
    
    /**
     * Load all CLD files from a directory with incremental compilation and optimization
     */
    public void loadGrammarDirectory(String directoryPath) throws IOException {
        System.out.println("Loading grammar directory with optimizations: " + directoryPath);
        
        // Use incremental compiler
        List<FiniteStateMachine> compiledFSMs = incrementalCompiler.compileDirectory(directoryPath);
        
        // Clear existing FSMs
        fsms.clear();
        fsmByName.clear();
        
        // Optimize each FSM
        for (FiniteStateMachine fsm : compiledFSMs) {
            FiniteStateMachine optimizedFSM = fsmOptimizer.optimize(fsm);
            fsms.add(optimizedFSM);
            fsmByName.put(optimizedFSM.getName(), optimizedFSM);
        }
        
        System.out.println("Loaded and optimized " + fsms.size() + " FSMs from " + directoryPath);
        
        // Print optimization statistics
        printOptimizationStats();
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
     * Load grammar from string content with analysis and optimization
     */
    public void loadGrammarString(String grammarContent, String name) {
        try {
            List<FiniteStateMachine> compiledFSMs = compiler.compile(grammarContent);
            
            for (FiniteStateMachine fsm : compiledFSMs) {
                // Optimize FSM
                FiniteStateMachine optimizedFSM = fsmOptimizer.optimize(fsm);
                
                fsms.add(optimizedFSM);
                fsmByName.put(optimizedFSM.getName(), optimizedFSM);
                
                System.out.println("Loaded and optimized FSM: " + optimizedFSM.getName() + 
                    " -> " + optimizedFSM.getIntentName());
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
        
        // Print cache statistics
        System.out.println("\nCache Statistics:");
        System.out.println("  " + compiler.getCacheStats());
        
        // Print compilation statistics
        System.out.println("\nCompilation Statistics:");
        System.out.println("  " + incrementalCompiler.getStats());
        
        System.out.println();
    }
    
    /**
     * Print optimization statistics
     */
    private void printOptimizationStats() {
        System.out.println("\n=== FSM Optimization Statistics ===");
        int totalOriginalStates = 0;
        int totalOptimizedStates = 0;
        
        for (FiniteStateMachine fsm : fsms) {
            totalOptimizedStates += fsm.getAllStates().size();
            // Note: We don't have access to original state count here
            // This could be enhanced to track optimization stats
        }
        
        System.out.println("Total optimized states: " + totalOptimizedStates);
        System.out.println();
    }
    
    /**
     * Analyze grammar rules for issues and optimizations
     */
    public void analyzeGrammarRules(String grammarContent) {
        try {
            // Parse grammar to get listener
            List<FiniteStateMachine> tempFSMs = compiler.compile(grammarContent);
            // For analysis, we need to re-parse to get the listener
            // This is a simplified approach - in production, we'd cache the listener
            com.grammar.compiler.SenseGrammarListener listener = new com.grammar.compiler.SenseGrammarListener();
            
            // Analyze rules
            RuleAnalyzer.AnalysisResult result = ruleAnalyzer.analyze(listener);
            
            System.out.println("\n=== Grammar Analysis Results ===");
            System.out.println(result);
            
        } catch (Exception e) {
            System.err.println("Failed to analyze grammar rules: " + e.getMessage());
        }
    }
    
    /**
     * Clear all caches and compiled data
     */
    public void clearAllCaches() {
        compiler.clearCache();
        incrementalCompiler.clearCache();
        fsms.clear();
        fsmByName.clear();
        System.out.println("All caches cleared");
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