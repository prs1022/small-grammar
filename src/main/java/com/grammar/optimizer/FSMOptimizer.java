package com.grammar.optimizer;

import com.grammar.fsm.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * FSM optimizer that performs various optimizations on finite state machines
 */
public class FSMOptimizer {
    
    /**
     * Optimize a finite state machine
     */
    public FiniteStateMachine optimize(FiniteStateMachine originalFSM) {
        System.out.println("Optimizing FSM: " + originalFSM.getName());
        
        FiniteStateMachine optimized = originalFSM;
        
        // Step 1: Remove unreachable states
        optimized = removeUnreachableStates(optimized);
        
        // Step 2: Merge equivalent states
        optimized = mergeEquivalentStates(optimized);
        
        // Step 3: Minimize transitions
        optimized = minimizeTransitions(optimized);
        
        // Step 4: Optimize state ordering for better cache locality
        optimized = optimizeStateOrdering(optimized);
        
        System.out.println("FSM optimization complete. States: " + 
            originalFSM.getAllStates().size() + " -> " + optimized.getAllStates().size());
        
        return optimized;
    }
    
    /**
     * Remove states that are not reachable from the start state
     */
    private FiniteStateMachine removeUnreachableStates(FiniteStateMachine fsm) {
        Set<FSMState> reachableStates = new HashSet<>();
        Queue<FSMState> queue = new LinkedList<>();
        
        // BFS to find all reachable states
        queue.offer(fsm.getStartState());
        reachableStates.add(fsm.getStartState());
        
        while (!queue.isEmpty()) {
            FSMState current = queue.poll();
            
            for (FSMTransition transition : current.getTransitions().values()) {
                FSMState target = transition.getTargetState();
                if (!reachableStates.contains(target)) {
                    reachableStates.add(target);
                    queue.offer(target);
                }
            }
        }
        
        // If all states are reachable, return original FSM
        if (reachableStates.size() == fsm.getAllStates().size()) {
            return fsm;
        }
        
        // Create new FSM with only reachable states
        FiniteStateMachine optimizedFSM = new FiniteStateMachine(fsm.getName(), fsm.getIntentName());
        Map<FSMState, FSMState> stateMapping = new HashMap<>();
        
        // Copy reachable states
        for (FSMState state : reachableStates) {
            FSMState newState = new FSMState(state.getId(), state.isFinal());
            // Copy attributes
            for (Map.Entry<String, String> attr : state.getAttributes().entrySet()) {
                newState.setAttribute(attr.getKey(), attr.getValue());
            }
            
            if (state == fsm.getStartState()) {
                // This will be the start state
                stateMapping.put(state, optimizedFSM.getStartState());
                optimizedFSM.getStartState().setAttribute("optimized", "true");
            } else {
                optimizedFSM.addState(newState);
                stateMapping.put(state, newState);
            }
        }
        
        // Copy transitions
        for (FSMState originalState : reachableStates) {
            FSMState newState = stateMapping.get(originalState);
            
            for (Map.Entry<String, FSMTransition> entry : originalState.getTransitions().entrySet()) {
                FSMTransition originalTransition = entry.getValue();
                FSMState targetState = stateMapping.get(originalTransition.getTargetState());
                
                if (targetState != null) {
                    FSMTransition newTransition = new FSMTransition(
                        targetState,
                        originalTransition.getType(),
                        originalTransition.getSlotName(),
                        originalTransition.getSlotValue(),
                        originalTransition.isOptional()
                    );
                    
                    newState.addTransition(entry.getKey(), newTransition);
                }
            }
        }
        
        return optimizedFSM;
    }
    
    /**
     * Merge states that have identical outgoing transitions
     */
    private FiniteStateMachine mergeEquivalentStates(FiniteStateMachine fsm) {
        Map<String, List<FSMState>> equivalenceClasses = new HashMap<>();
        
        // Group states by their transition signatures
        for (FSMState state : fsm.getAllStates()) {
            String signature = getStateSignature(state);
            equivalenceClasses.computeIfAbsent(signature, k -> new ArrayList<>()).add(state);
        }
        
        // Find classes with multiple states (candidates for merging)
        Map<FSMState, FSMState> mergeMapping = new HashMap<>();
        boolean hasMerges = false;
        
        for (List<FSMState> equivalentStates : equivalenceClasses.values()) {
            if (equivalentStates.size() > 1) {
                // Keep the first state, merge others into it
                FSMState representative = equivalentStates.get(0);
                for (int i = 1; i < equivalentStates.size(); i++) {
                    mergeMapping.put(equivalentStates.get(i), representative);
                    hasMerges = true;
                }
            }
        }
        
        if (!hasMerges) {
            return fsm; // No merging needed
        }
        
        // Create new FSM with merged states
        FiniteStateMachine optimizedFSM = new FiniteStateMachine(fsm.getName(), fsm.getIntentName());
        Map<FSMState, FSMState> stateMapping = new HashMap<>();
        
        // Create states (excluding merged ones)
        for (FSMState state : fsm.getAllStates()) {
            if (!mergeMapping.containsKey(state)) {
                FSMState newState;
                if (state == fsm.getStartState()) {
                    newState = optimizedFSM.getStartState();
                } else {
                    newState = new FSMState(state.getId(), state.isFinal());
                    optimizedFSM.addState(newState);
                }
                
                // Copy attributes
                for (Map.Entry<String, String> attr : state.getAttributes().entrySet()) {
                    newState.setAttribute(attr.getKey(), attr.getValue());
                }
                
                stateMapping.put(state, newState);
            }
        }
        
        // Map merged states to their representatives
        for (Map.Entry<FSMState, FSMState> merge : mergeMapping.entrySet()) {
            stateMapping.put(merge.getKey(), stateMapping.get(merge.getValue()));
        }
        
        // Copy transitions with updated target states
        for (FSMState originalState : fsm.getAllStates()) {
            if (!mergeMapping.containsKey(originalState)) {
                FSMState newState = stateMapping.get(originalState);
                
                for (Map.Entry<String, FSMTransition> entry : originalState.getTransitions().entrySet()) {
                    FSMTransition originalTransition = entry.getValue();
                    FSMState targetState = stateMapping.get(originalTransition.getTargetState());
                    
                    FSMTransition newTransition = new FSMTransition(
                        targetState,
                        originalTransition.getType(),
                        originalTransition.getSlotName(),
                        originalTransition.getSlotValue(),
                        originalTransition.isOptional()
                    );
                    
                    newState.addTransition(entry.getKey(), newTransition);
                }
            }
        }
        
        return optimizedFSM;
    }
    
    /**
     * Minimize transitions by combining similar ones
     */
    private FiniteStateMachine minimizeTransitions(FiniteStateMachine fsm) {
        // For now, just return the original FSM
        // This could be enhanced to combine transitions with same target and type
        return fsm;
    }
    
    /**
     * Optimize state ordering for better cache locality
     */
    private FiniteStateMachine optimizeStateOrdering(FiniteStateMachine fsm) {
        // For now, just return the original FSM
        // This could be enhanced to reorder states based on access patterns
        return fsm;
    }
    
    /**
     * Generate a signature for a state based on its transitions
     */
    private String getStateSignature(FSMState state) {
        StringBuilder signature = new StringBuilder();
        
        signature.append("final:").append(state.isFinal()).append(";");
        
        // Sort transitions for consistent signature
        List<String> transitionSigs = new ArrayList<>();
        for (Map.Entry<String, FSMTransition> entry : state.getTransitions().entrySet()) {
            FSMTransition transition = entry.getValue();
            String transSig = String.format("%s->%s:%s:%s:%s",
                entry.getKey(),
                transition.getTargetState().getId(),
                transition.getType(),
                transition.getSlotName(),
                transition.getSlotValue());
            transitionSigs.add(transSig);
        }
        
        Collections.sort(transitionSigs);
        signature.append("transitions:").append(String.join(",", transitionSigs));
        
        return signature.toString();
    }
    
    /**
     * Get optimization statistics
     */
    public OptimizationStats getOptimizationStats(FiniteStateMachine original, FiniteStateMachine optimized) {
        int originalStates = original.getAllStates().size();
        int optimizedStates = optimized.getAllStates().size();
        
        int originalTransitions = original.getAllStates().stream()
            .mapToInt(state -> state.getTransitions().size())
            .sum();
        
        int optimizedTransitions = optimized.getAllStates().stream()
            .mapToInt(state -> state.getTransitions().size())
            .sum();
        
        return new OptimizationStats(
            originalStates, optimizedStates,
            originalTransitions, optimizedTransitions
        );
    }
    
    /**
     * Optimization statistics
     */
    public static class OptimizationStats {
        public final int originalStates;
        public final int optimizedStates;
        public final int originalTransitions;
        public final int optimizedTransitions;
        
        public OptimizationStats(int originalStates, int optimizedStates,
                               int originalTransitions, int optimizedTransitions) {
            this.originalStates = originalStates;
            this.optimizedStates = optimizedStates;
            this.originalTransitions = originalTransitions;
            this.optimizedTransitions = optimizedTransitions;
        }
        
        public double getStateReduction() {
            return originalStates > 0 ? 
                (double)(originalStates - optimizedStates) / originalStates * 100 : 0;
        }
        
        public double getTransitionReduction() {
            return originalTransitions > 0 ? 
                (double)(originalTransitions - optimizedTransitions) / originalTransitions * 100 : 0;
        }
        
        @Override
        public String toString() {
            return String.format("OptimizationStats{states: %d->%d (%.1f%% reduction), " +
                "transitions: %d->%d (%.1f%% reduction)}", 
                originalStates, optimizedStates, getStateReduction(),
                originalTransitions, optimizedTransitions, getTransitionReduction());
        }
    }
}