package com.grammar.fsm;

import com.grammar.nlu.ImprovedFSMNLUEngine;
import com.grammar.nlu.NLUResult;
import org.junit.jupiter.api.Test;

public class FSMDebugTest {
    
    @Test
    void debugSimpleMatch() {
        ImprovedFSMNLUEngine engine = new ImprovedFSMNLUEngine();
        
        // 简单的语法规则
        String simpleGrammar = "namespace test;\n\n" +
                "@type(name=\"item\")\n" +
                "<item>: \"苹果\";\n\n" +
                "@attr(intention=\"simple_buy\")\n" +
                "@statement\n" +
                "simple_buy: \"买\" <item>;\n";
        
        System.out.println("语法规则:");
        System.out.println(simpleGrammar);
        
        engine.loadGrammarString(simpleGrammar, "debug.cld");
        
        // 测试简单匹配
        String testInput = "买苹果";
        System.out.println("测试输入: " + testInput);
        
        NLUResult result = engine.parseText(testInput);
        System.out.println("匹配结果: " + result.toJsonString());
        
        // 打印FSM信息
        engine.printStatistics();
        
        // 打印FSM详细结构
        for (var fsm : engine.getFSMs()) {
            System.out.println("\nFSM: " + fsm.getName());
            for (var state : fsm.getAllStates()) {
                System.out.println("  State: " + state.getId() + " (final: " + state.isFinal() + ")");
                for (var entry : state.getTransitions().entrySet()) {
                    var transition = entry.getValue();
                    System.out.println("    Transition: '" + entry.getKey() + "' -> " + 
                        transition.getTargetState().getId() + " (type: " + transition.getType() + 
                        ", slot: " + transition.getSlotName() + ")");
                }
            }
        }
        
        // 测试更复杂的例子
        String complexGrammar = "namespace test2;\n\n" +
                "@type(name=\"item\")\n" +
                "<item>: \"苹果\";\n\n" +
                "@attr(intention=\"want_buy\")\n" +
                "@statement\n" +
                "want_buy: \"我要\" \"买\" <item>;\n";
        
        System.out.println("\n复杂语法规则:");
        System.out.println(complexGrammar);
        
        engine.clearAllCaches();
        engine.loadGrammarString(complexGrammar, "debug2.cld");
        
        String testInput2 = "我要买苹果";
        System.out.println("测试输入: " + testInput2);
        
        NLUResult result2 = engine.parseText(testInput2);
        System.out.println("匹配结果: " + result2.toJsonString());
    }
}