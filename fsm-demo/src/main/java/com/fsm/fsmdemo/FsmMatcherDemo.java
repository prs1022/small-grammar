package com.fsm.fsmdemo;

import com.fsm.fsmdemo.model.MatchState;
import com.fsm.fsmdemo.model.SimplePiece;

import java.util.*;
/**
 * @author rensong.pu
 * @date 2025/8/7
 */
public class FsmMatcherDemo {
    public static void main(String[] args) {
        // 假设我们已经有了之前构建的FSM结构
        // "我" ("爱" | "喜欢") "中国"

        // 创建模拟的Piece节点
        Map<Integer, SimplePiece> pieces = new HashMap<>();

        // 创建节点
        SimplePiece literalWo = new SimplePiece(0, "Literal", "我");
        SimplePiece choiceEntry = new SimplePiece(1, "Joint", "选择入口");
        SimplePiece literalAi = new SimplePiece(2, "Literal", "爱");
        SimplePiece literalXiHuan = new SimplePiece(3, "Literal", "喜欢");
        SimplePiece choiceExit = new SimplePiece(4, "Joint", "选择出口");
        SimplePiece literalZhongGuo = new SimplePiece(5, "Literal", "中国");

        // 设置连接关系
        literalWo.addTarget(1);  // "我" -> 选择入口

        choiceEntry.addTarget(2);  // 选择入口 -> "爱"
        choiceEntry.addTarget(3);  // 选择入口 -> "喜欢"

        literalAi.addTarget(4);    // "爱" -> 选择出口
        literalXiHuan.addTarget(4); // "喜欢" -> 选择出口

        choiceExit.addTarget(5);   // 选择出口 -> "中国"

        // 添加到pieces映射中
        pieces.put(0, literalWo);
        pieces.put(1, choiceEntry);
        pieces.put(2, literalAi);
        pieces.put(3, literalXiHuan);
        pieces.put(4, choiceExit);
        pieces.put(5, literalZhongGuo);

        // 字符串映射表
        Map<Integer, String> stringTable = new HashMap<>();
        stringTable.put(0, "我");
        stringTable.put(1, "爱");
        stringTable.put(2, "喜欢");
        stringTable.put(3, "中国");

        // 反向映射，用于查找stringId
        Map<String, Integer> stringToId = new HashMap<>();
        stringToId.put("我", 0);
        stringToId.put("爱", 1);
        stringToId.put("喜欢", 2);
        stringToId.put("中国", 3);

        // 开始匹配演示
        System.out.println("=== FSM匹配过程演示 ===");

        // 匹配句子: "我喜欢中国"
        String input = "我喜欢中国";
        System.out.println("输入句子: " + input);

        // 匹配过程
        matchProcess(pieces, stringTable, stringToId, input, 0);

        System.out.println("\n" + "=".repeat(30));

        // 匹配句子: "我爱你中国"
        input = "我爱你中国";
        System.out.println("输入句子: " + input);
        matchProcess(pieces, stringTable, stringToId, input, 0);
    }

    private static void matchProcess(Map<Integer, SimplePiece> pieces,
                                     Map<Integer, String> stringTable,
                                     Map<String, Integer> stringToId,
                                     String input, int entryId) {

        System.out.println("\n开始匹配过程:");
        System.out.println("入口节点ID: " + entryId);

        // 使用队列进行广度优先搜索
        Queue<MatchState> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>(); // 避免重复访问相同状态

        MatchState initialState = new MatchState(entryId, 0, "初始状态");
        queue.offer(initialState);
        visited.add(entryId + ":0");

        boolean matched = false;

        while (!queue.isEmpty() && !matched) {
            int levelSize = queue.size();

            for (int i = 0; i < levelSize; i++) {
                MatchState currentState = queue.poll();
                System.out.println("  处理状态: 节点ID=" + currentState.pieceId + ", 位置=" + currentState.position + ", 路径='" + currentState.path + "'");

                // 检查是否已经处理完所有输入字符
                if (currentState.position >= input.length()) {
                    // 输入已经处理完毕，检查当前状态是否为终态
                    SimplePiece piece = pieces.get(currentState.pieceId);
                    if (piece != null && piece.isFinal) {
                        System.out.println("\n🎉 匹配成功!");
                        System.out.println("完整匹配路径: " + currentState.path);
                        matched = true;
                        break;
                    }
                    continue;
                }

                SimplePiece currentPiece = pieces.get(currentState.pieceId);
                if (currentPiece == null) continue;

                // 处理字面量节点
                if ("Literal".equals(currentPiece.type)) {
                    String expected = currentPiece.value;
                    if (expected != null && input.startsWith(expected, currentState.position)) {
                        System.out.println("    ✓ 匹配成功: '" + expected + "'");
                        int newPosition = currentState.position + expected.length();

                        // 如果已经处理完所有输入，检查目标节点是否为终态
                        if (newPosition >= input.length()) {
                            for (int targetId : currentPiece.targets) {
                                SimplePiece targetPiece = pieces.get(targetId);
                                if (targetPiece != null && targetPiece.isFinal) {
                                    System.out.println("\n🎉 匹配成功!");
                                    System.out.println("完整匹配路径: " + currentState.path + " -> " + expected);
                                    matched = true;
                                    break;
                                }
                            }
                            if (matched) break;
                        }

                        // 转移到目标节点
                        for (int targetId : currentPiece.targets) {
                            String newPath = currentState.path + " -> " + expected;
                            String stateKey = targetId + ":" + newPosition;
                            if (!visited.contains(stateKey)) {
                                MatchState newState = new MatchState(targetId, newPosition, newPath);
                                queue.offer(newState);
                                visited.add(stateKey);
                                System.out.println("    转移到节点 " + targetId);
                            }
                        }
                    }
                }
                // 处理Joint节点
                else if ("Joint".equals(currentPiece.type)) {
                    System.out.println("    → Joint节点，直接转移");
                    for (int targetId : currentPiece.targets) {
                        String stateKey = targetId + ":" + currentState.position;
                        if (!visited.contains(stateKey)) {
                            MatchState newState = new MatchState(targetId, currentState.position, currentState.path);
                            queue.offer(newState);
                            visited.add(stateKey);
                            System.out.println("    转移到节点 " + targetId);
                        }
                    }
                }
            }
        }

        if (!matched) {
            System.out.println("\n❌ 匹配失败");
        }
    }

    // 处理epsilon转移的辅助方法
    private static Set<MatchState> expandEpsilonTransitions(Set<MatchState> states, Map<Integer, SimplePiece> pieces) {
        Set<MatchState> result = new HashSet<>(states);
        Queue<MatchState> queue = new LinkedList<>(states);

        while (!queue.isEmpty()) {
            MatchState state = queue.poll();
            SimplePiece piece = pieces.get(state.pieceId);

            if (piece != null && "Joint".equals(piece.type)) {
                for (int targetId : piece.targets) {
                    MatchState newState = new MatchState(targetId, state.position, state.path);
                    if (result.add(newState)) {
                        queue.add(newState);
                    }
                }
            }
        }

        return result;
    }


}
