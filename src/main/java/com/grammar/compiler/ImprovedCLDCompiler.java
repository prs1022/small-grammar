package com.grammar.compiler;

import com.grammar.fsm.*;
import com.grammar.grammar.GrammarParser;
import com.grammar.compiler.SenseGrammarListener.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import java.util.*;

/**
 * Improved CLD compiler that uses the SenseGrammarListener approach
 * to properly parse Sense grammar and build FSMs
 */
public class ImprovedCLDCompiler {
    
    private final GrammarParser grammarParser;
    
    public ImprovedCLDCompiler() {
        this.grammarParser = new GrammarParser();
    }
    
    /**
     * Compile CLD grammar text into multiple FSMs (one per statement)
     */
    public List<FiniteStateMachine> compile(String grammarText) {
        ParseTree grammarTree = grammarParser.parseGrammar(grammarText);
        
        if (grammarParser.hasErrors()) {
            throw new RuntimeException("Grammar parsing failed: " + grammarParser.getErrors());
        }
        
        // Use listener to extract grammar components
        SenseGrammarListener listener = new SenseGrammarListener();
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(listener, grammarTree);
        
        // Build FSMs from statements
        List<FiniteStateMachine> fsms = new ArrayList<>();
        
        for (StatementDeclaration statement : listener.getStatements()) {
            FiniteStateMachine fsm = buildFSMFromStatement(statement, listener);
            fsms.add(fsm);
        }
        
        return fsms;
    }
    
    /**
     * Build FSM from a statement declaration
     */
    private FiniteStateMachine buildFSMFromStatement(StatementDeclaration statement, SenseGrammarListener listener) {
        FiniteStateMachine fsm = new FiniteStateMachine(statement.name, statement.intentName);
        
        // Create state counter
        StateCounter stateCounter = new StateCounter();
        
        // Build FSM from expression
        FSMState startState = fsm.getStartState();
        FSMState endState = new FSMState("final", true);
        fsm.addState(endState);
        
        buildFSMFromExpression(statement.expression, startState, endState, fsm, stateCounter, listener);
        
        return fsm;
    }
    
    /**
     * Build FSM states and transitions from expression node
     */
    private void buildFSMFromExpression(ExpressionNode node, FSMState fromState, FSMState toState, 
                                      FiniteStateMachine fsm, StateCounter stateCounter, SenseGrammarListener listener) {
        
        if (node instanceof LiteralExpressionNode) {
            LiteralExpressionNode literal = (LiteralExpressionNode) node;
            FSMTransition transition = new FSMTransition(toState, FSMTransition.TransitionType.LITERAL);
            fromState.addTransition(literal.text.toLowerCase(), transition);
            
        } else if (node instanceof PrimeExpressionNode) {
            PrimeExpressionNode prime = (PrimeExpressionNode) node;
            
            // Find prime definition
            PrimeDeclaration primeDecl = findPrimeDeclaration(prime.primeName.name, listener);
            if (primeDecl != null) {
                buildPrimeTransitions(primeDecl, prime.primeName.name, fromState, toState, fsm, stateCounter, listener);
            } else {
                // Wildcard prime
                FSMTransition transition = new FSMTransition(toState, FSMTransition.TransitionType.SLOT, 
                    prime.primeName.name, "*", false);
                fromState.addTransition("*", transition);
            }
            
        } else if (node instanceof ReferenceExpressionNode) {
            ReferenceExpressionNode reference = (ReferenceExpressionNode) node;
            
            // Find production definition
            ProductionDeclaration prodDecl = findProductionDeclaration(reference.referenceName.name, listener);
            if (prodDecl != null) {
                buildFSMFromExpression(prodDecl.expression, fromState, toState, fsm, stateCounter, listener);
            }
            
        } else if (node instanceof SequenceExpressionNode) {
            SequenceExpressionNode sequence = (SequenceExpressionNode) node;
            
            FSMState currentState = fromState;
            for (int i = 0; i < sequence.children.length; i++) {
                FSMState nextState;
                if (i == sequence.children.length - 1) {
                    nextState = toState;
                } else {
                    nextState = new FSMState("seq_" + stateCounter.next(), false);
                    fsm.addState(nextState);
                }
                
                buildFSMFromExpression(sequence.children[i], currentState, nextState, fsm, stateCounter, listener);
                currentState = nextState;
            }
            
        } else if (node instanceof ChoiceExpressionNode) {
            ChoiceExpressionNode choice = (ChoiceExpressionNode) node;
            
            for (ExpressionNode child : choice.children) {
                buildFSMFromExpression(child, fromState, toState, fsm, stateCounter, listener);
            }
            
        } else if (node instanceof RepeatExpressionNode) {
            RepeatExpressionNode repeat = (RepeatExpressionNode) node;
            
            if (repeat.minCount == 0 && repeat.maxCount == 1) {
                // Optional: create epsilon transition
                FSMTransition epsilonTransition = new FSMTransition(toState, FSMTransition.TransitionType.EPSILON);
                fromState.addTransition("", epsilonTransition);
                
                // Also create the actual transition
                buildFSMFromExpression(repeat.child, fromState, toState, fsm, stateCounter, listener);
                
            } else if (repeat.minCount == 0 && repeat.maxCount == Integer.MAX_VALUE) {
                // Zero or more: create epsilon transition and loop
                FSMTransition epsilonTransition = new FSMTransition(toState, FSMTransition.TransitionType.EPSILON);
                fromState.addTransition("", epsilonTransition);
                
                FSMState loopState = new FSMState("loop_" + stateCounter.next(), false);
                fsm.addState(loopState);
                
                buildFSMFromExpression(repeat.child, fromState, loopState, fsm, stateCounter, listener);
                buildFSMFromExpression(repeat.child, loopState, loopState, fsm, stateCounter, listener);
                
                FSMTransition exitTransition = new FSMTransition(toState, FSMTransition.TransitionType.EPSILON);
                loopState.addTransition("", exitTransition);
                
            } else if (repeat.minCount == 1 && repeat.maxCount == Integer.MAX_VALUE) {
                // One or more: create required transition and loop
                FSMState loopState = new FSMState("loop_" + stateCounter.next(), false);
                fsm.addState(loopState);
                
                buildFSMFromExpression(repeat.child, fromState, loopState, fsm, stateCounter, listener);
                buildFSMFromExpression(repeat.child, loopState, loopState, fsm, stateCounter, listener);
                
                FSMTransition exitTransition = new FSMTransition(toState, FSMTransition.TransitionType.EPSILON);
                loopState.addTransition("", exitTransition);
            }
        }
    }
    
    /**
     * Build transitions for a prime (slot)
     */
    private void buildPrimeTransitions(PrimeDeclaration primeDecl, String slotName, FSMState fromState, FSMState toState,
                                     FiniteStateMachine fsm, StateCounter stateCounter, SenseGrammarListener listener) {
        
        // Extract slot values from prime expression
        List<String> slotValues = extractSlotValues(primeDecl.expression);
        
        for (String value : slotValues) {
            FSMTransition transition = new FSMTransition(toState, FSMTransition.TransitionType.SLOT, 
                slotName, value, false);
            fromState.addTransition(value.toLowerCase(), transition);
        }
    }
    
    /**
     * Extract slot values from prime expression
     */
    private List<String> extractSlotValues(ExpressionNode node) {
        List<String> values = new ArrayList<>();
        
        if (node instanceof LiteralExpressionNode) {
            values.add(((LiteralExpressionNode) node).text);
        } else if (node instanceof ChoiceExpressionNode) {
            ChoiceExpressionNode choice = (ChoiceExpressionNode) node;
            for (ExpressionNode child : choice.children) {
                values.addAll(extractSlotValues(child));
            }
        } else if (node instanceof SequenceExpressionNode) {
            // For sequence, concatenate values (simplified)
            SequenceExpressionNode sequence = (SequenceExpressionNode) node;
            StringBuilder sb = new StringBuilder();
            for (ExpressionNode child : sequence.children) {
                List<String> childValues = extractSlotValues(child);
                if (!childValues.isEmpty()) {
                    if (sb.length() > 0) sb.append(" ");
                    sb.append(childValues.get(0)); // Take first value for simplicity
                }
            }
            if (sb.length() > 0) {
                values.add(sb.toString());
            }
        }
        
        return values;
    }
    
    /**
     * Find prime declaration by name
     */
    private PrimeDeclaration findPrimeDeclaration(String name, SenseGrammarListener listener) {
        return listener.getPrimes().stream()
            .filter(p -> p.name.equals(name))
            .findFirst()
            .orElse(null);
    }
    
    /**
     * Find production declaration by name
     */
    private ProductionDeclaration findProductionDeclaration(String name, SenseGrammarListener listener) {
        return listener.getProductions().stream()
            .filter(p -> p.name.equals(name))
            .findFirst()
            .orElse(null);
    }
    
    public List<String> getErrors() {
        return grammarParser.getErrors();
    }
    
    // Helper class for state counting
    private static class StateCounter {
        private int count = 0;
        
        public int next() {
            return ++count;
        }
    }
}