package com.grammar.analyzer;

import com.grammar.compiler.SenseGrammarListener;
import com.grammar.compiler.SenseGrammarListener.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Analyzer for detecting rule conflicts, redundancies, and optimization opportunities
 */
public class RuleAnalyzer {
    
    /**
     * Analyze grammar rules and return analysis results
     */
    public AnalysisResult analyze(SenseGrammarListener listener) {
        AnalysisResult result = new AnalysisResult();
        
        // Check for naming conflicts
        checkNamingConflicts(listener, result);
        
        // Check for redundant rules
        checkRedundantRules(listener, result);
        
        // Check for unreachable rules
        checkUnreachableRules(listener, result);
        
        // Check for circular dependencies
        checkCircularDependencies(listener, result);
        
        // Suggest optimizations
        suggestOptimizations(listener, result);
        
        return result;
    }
    
    /**
     * Check for naming conflicts between different rule types
     */
    private void checkNamingConflicts(SenseGrammarListener listener, AnalysisResult result) {
        Set<String> allNames = new HashSet<>();
        Map<String, String> nameToType = new HashMap<>();
        
        // Check primes
        for (PrimeDeclaration prime : listener.getPrimes()) {
            if (allNames.contains(prime.name)) {
                result.addError("Naming conflict: '" + prime.name + 
                    "' conflicts with " + nameToType.get(prime.name));
            } else {
                allNames.add(prime.name);
                nameToType.put(prime.name, "prime");
            }
        }
        
        // Check productions
        for (ProductionDeclaration prod : listener.getProductions()) {
            if (allNames.contains(prod.name)) {
                result.addError("Naming conflict: '" + prod.name + 
                    "' conflicts with " + nameToType.get(prod.name));
            } else {
                allNames.add(prod.name);
                nameToType.put(prod.name, "production");
            }
        }
        
        // Check statements
        for (StatementDeclaration stmt : listener.getStatements()) {
            if (allNames.contains(stmt.name)) {
                result.addError("Naming conflict: '" + stmt.name + 
                    "' conflicts with " + nameToType.get(stmt.name));
            } else {
                allNames.add(stmt.name);
                nameToType.put(stmt.name, "statement");
            }
        }
    }
    
    /**
     * Check for redundant rules (rules with identical expressions)
     */
    private void checkRedundantRules(SenseGrammarListener listener, AnalysisResult result) {
        Map<String, List<String>> expressionToNames = new HashMap<>();
        
        // Group rules by expression
        for (PrimeDeclaration prime : listener.getPrimes()) {
            String expr = expressionToString(prime.expression);
            expressionToNames.computeIfAbsent(expr, k -> new ArrayList<>()).add(prime.name);
        }
        
        for (ProductionDeclaration prod : listener.getProductions()) {
            String expr = expressionToString(prod.expression);
            expressionToNames.computeIfAbsent(expr, k -> new ArrayList<>()).add(prod.name);
        }
        
        // Report redundancies
        for (Map.Entry<String, List<String>> entry : expressionToNames.entrySet()) {
            if (entry.getValue().size() > 1) {
                result.addWarning("Redundant rules with identical expressions: " + 
                    String.join(", ", entry.getValue()));
            }
        }
    }
    
    /**
     * Check for unreachable rules (rules that are never referenced)
     */
    private void checkUnreachableRules(SenseGrammarListener listener, AnalysisResult result) {
        Set<String> definedRules = new HashSet<>();
        Set<String> referencedRules = new HashSet<>();
        
        // Collect all defined rules
        listener.getPrimes().forEach(p -> definedRules.add(p.name));
        listener.getProductions().forEach(p -> definedRules.add(p.name));
        
        // Collect all referenced rules
        for (StatementDeclaration stmt : listener.getStatements()) {
            collectReferences(stmt.expression, referencedRules);
        }
        
        for (ProductionDeclaration prod : listener.getProductions()) {
            collectReferences(prod.expression, referencedRules);
        }
        
        // Find unreachable rules
        Set<String> unreachable = new HashSet<>(definedRules);
        unreachable.removeAll(referencedRules);
        
        for (String unreachableRule : unreachable) {
            result.addWarning("Unreachable rule: '" + unreachableRule + "' is never referenced");
        }
    }
    
    /**
     * Check for circular dependencies
     */
    private void checkCircularDependencies(SenseGrammarListener listener, AnalysisResult result) {
        Map<String, Set<String>> dependencies = new HashMap<>();
        
        // Build dependency graph
        for (ProductionDeclaration prod : listener.getProductions()) {
            Set<String> refs = new HashSet<>();
            collectReferences(prod.expression, refs);
            dependencies.put(prod.name, refs);
        }
        
        // Check for cycles using DFS
        Set<String> visited = new HashSet<>();
        Set<String> recursionStack = new HashSet<>();
        
        for (String rule : dependencies.keySet()) {
            if (!visited.contains(rule)) {
                if (hasCycle(rule, dependencies, visited, recursionStack)) {
                    result.addError("Circular dependency detected involving rule: '" + rule + "'");
                }
            }
        }
    }
    
    /**
     * Suggest optimizations
     */
    private void suggestOptimizations(SenseGrammarListener listener, AnalysisResult result) {
        // Suggest rule merging for similar patterns
        suggestRuleMerging(listener, result);
        
        // Suggest factoring out common prefixes
        suggestPrefixFactoring(listener, result);
        
        // Suggest slot type optimizations
        suggestSlotOptimizations(listener, result);
    }
    
    private void suggestRuleMerging(SenseGrammarListener listener, AnalysisResult result) {
        // Find rules with similar patterns that could be merged
        Map<String, List<String>> patternGroups = new HashMap<>();
        
        for (PrimeDeclaration prime : listener.getPrimes()) {
            String pattern = getExpressionPattern(prime.expression);
            patternGroups.computeIfAbsent(pattern, k -> new ArrayList<>()).add(prime.name);
        }
        
        for (Map.Entry<String, List<String>> entry : patternGroups.entrySet()) {
            if (entry.getValue().size() > 2) {
                result.addSuggestion("Consider merging similar rules: " + 
                    String.join(", ", entry.getValue()));
            }
        }
    }
    
    private void suggestPrefixFactoring(SenseGrammarListener listener, AnalysisResult result) {
        // Analyze choice expressions for common prefixes
        for (StatementDeclaration stmt : listener.getStatements()) {
            analyzeForCommonPrefixes(stmt.expression, stmt.name, result);
        }
    }
    
    private void suggestSlotOptimizations(SenseGrammarListener listener, AnalysisResult result) {
        // Check for slots with single values that could be literals
        for (PrimeDeclaration prime : listener.getPrimes()) {
            if (isSingleValueSlot(prime.expression)) {
                result.addSuggestion("Slot '" + prime.name + 
                    "' has only one value, consider using a literal instead");
            }
        }
    }
    
    // Helper methods
    private String expressionToString(ExpressionNode node) {
        if (node == null) return "";
        
        if (node instanceof LiteralExpressionNode) {
            return "\"" + ((LiteralExpressionNode) node).text + "\"";
        } else if (node instanceof PrimeExpressionNode) {
            return "<" + ((PrimeExpressionNode) node).primeName + ">";
        } else if (node instanceof ReferenceExpressionNode) {
            return ((ReferenceExpressionNode) node).referenceName.toString();
        } else if (node instanceof SequenceExpressionNode) {
            SequenceExpressionNode seq = (SequenceExpressionNode) node;
            return Arrays.stream(seq.children)
                .map(this::expressionToString)
                .collect(Collectors.joining(" "));
        } else if (node instanceof ChoiceExpressionNode) {
            ChoiceExpressionNode choice = (ChoiceExpressionNode) node;
            return Arrays.stream(choice.children)
                .map(this::expressionToString)
                .collect(Collectors.joining(" | "));
        }
        
        return node.getClass().getSimpleName();
    }
    
    private void collectReferences(ExpressionNode node, Set<String> references) {
        if (node == null) return;
        
        if (node instanceof PrimeExpressionNode) {
            references.add(((PrimeExpressionNode) node).primeName.name);
        } else if (node instanceof ReferenceExpressionNode) {
            references.add(((ReferenceExpressionNode) node).referenceName.name);
        } else if (node instanceof SequenceExpressionNode) {
            for (ExpressionNode child : ((SequenceExpressionNode) node).children) {
                collectReferences(child, references);
            }
        } else if (node instanceof ChoiceExpressionNode) {
            for (ExpressionNode child : ((ChoiceExpressionNode) node).children) {
                collectReferences(child, references);
            }
        } else if (node instanceof RepeatExpressionNode) {
            collectReferences(((RepeatExpressionNode) node).child, references);
        }
    }
    
    private boolean hasCycle(String node, Map<String, Set<String>> dependencies, 
                           Set<String> visited, Set<String> recursionStack) {
        visited.add(node);
        recursionStack.add(node);
        
        Set<String> neighbors = dependencies.get(node);
        if (neighbors != null) {
            for (String neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    if (hasCycle(neighbor, dependencies, visited, recursionStack)) {
                        return true;
                    }
                } else if (recursionStack.contains(neighbor)) {
                    return true;
                }
            }
        }
        
        recursionStack.remove(node);
        return false;
    }
    
    private String getExpressionPattern(ExpressionNode node) {
        // Simplified pattern extraction
        if (node instanceof ChoiceExpressionNode) {
            return "CHOICE";
        } else if (node instanceof SequenceExpressionNode) {
            return "SEQUENCE";
        } else if (node instanceof LiteralExpressionNode) {
            return "LITERAL";
        }
        return "OTHER";
    }
    
    private void analyzeForCommonPrefixes(ExpressionNode node, String ruleName, AnalysisResult result) {
        if (node instanceof ChoiceExpressionNode) {
            ChoiceExpressionNode choice = (ChoiceExpressionNode) node;
            // Simplified common prefix detection
            if (choice.children.length > 2) {
                result.addSuggestion("Rule '" + ruleName + 
                    "' has multiple choices, consider factoring common prefixes");
            }
        }
    }
    
    private boolean isSingleValueSlot(ExpressionNode node) {
        return node instanceof LiteralExpressionNode;
    }
    
    /**
     * Analysis result container
     */
    public static class AnalysisResult {
        private final List<String> errors = new ArrayList<>();
        private final List<String> warnings = new ArrayList<>();
        private final List<String> suggestions = new ArrayList<>();
        
        public void addError(String error) {
            errors.add(error);
        }
        
        public void addWarning(String warning) {
            warnings.add(warning);
        }
        
        public void addSuggestion(String suggestion) {
            suggestions.add(suggestion);
        }
        
        public List<String> getErrors() { return new ArrayList<>(errors); }
        public List<String> getWarnings() { return new ArrayList<>(warnings); }
        public List<String> getSuggestions() { return new ArrayList<>(suggestions); }
        
        public boolean hasErrors() { return !errors.isEmpty(); }
        public boolean hasWarnings() { return !warnings.isEmpty(); }
        public boolean hasSuggestions() { return !suggestions.isEmpty(); }
        
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Analysis Result:\n");
            
            if (!errors.isEmpty()) {
                sb.append("Errors:\n");
                errors.forEach(e -> sb.append("  - ").append(e).append("\n"));
            }
            
            if (!warnings.isEmpty()) {
                sb.append("Warnings:\n");
                warnings.forEach(w -> sb.append("  - ").append(w).append("\n"));
            }
            
            if (!suggestions.isEmpty()) {
                sb.append("Suggestions:\n");
                suggestions.forEach(s -> sb.append("  - ").append(s).append("\n"));
            }
            
            if (errors.isEmpty() && warnings.isEmpty() && suggestions.isEmpty()) {
                sb.append("No issues found.\n");
            }
            
            return sb.toString();
        }
    }
}