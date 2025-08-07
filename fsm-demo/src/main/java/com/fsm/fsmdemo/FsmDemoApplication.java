package com.fsm.fsmdemo;

import com.fsm.fsmdemo.model.Block;
import com.fsm.fsmdemo.model.Joint;
import com.fsm.fsmdemo.model.Literal;
import com.fsm.fsmdemo.model.StringSet;

/**
 * FSM结构图
 * [Literal:"我"]
 *     |
 *     ↓
 * [Joint:选择入口]
 *    ↙              ↘
 * [Literal:"爱"]  [Literal:"喜欢"]
 *    ↘              ↙
 * [Joint:选择出口]
 *     |
 *     ↓
 * [Literal:"中国"]
 */
//@SpringBootApplication
public class FsmDemoApplication {

    public static void main(String[] args) {
        // 1. 创建字符串表
        StringSet strings = new StringSet();
        strings.add("我");
        strings.add("爱");
        strings.add("喜欢");
        strings.add("中国");

        // 2. 创建PieceManager管理所有节点
        PieceManager pieceManager = new PieceManager(false);

        // 3. 构建语法: "我" ("爱" | "喜欢") "中国"

        // 创建字面量节点
        Literal literalWo = pieceManager.createPiece(Literal.class, null);
        literalWo.setStringId(strings.get("我"));

        Literal literalAi = pieceManager.createPiece(Literal.class, null);
        literalAi.setStringId(strings.get("爱"));

        Literal literalXiHuan = pieceManager.createPiece(Literal.class, null);
        literalXiHuan.setStringId(strings.get("喜欢"));

        Literal literalZhongGuo = pieceManager.createPiece(Literal.class, null);
        literalZhongGuo.setStringId(strings.get("中国"));


        // 创建选择节点 ("爱" | "喜欢")
        // 先创建Joint节点作为选择的入口和出口
        Joint choiceEntry = pieceManager.createPiece(Joint.class, null);
        Joint choiceExit = pieceManager.createPiece(Joint.class, null);

        // 连接选择分支
        pieceManager.connect(choiceEntry, literalAi);
        pieceManager.connect(choiceEntry, literalXiHuan);
        pieceManager.connect(literalAi, choiceExit);
        pieceManager.connect(literalXiHuan, choiceExit);

        // 创建序列节点连接所有部分: "我" + ("爱" | "喜欢") + "中国"
        Joint sequenceJoint = pieceManager.createPiece(Joint.class, null);

        // 连接序列
        pieceManager.connect(literalWo, choiceEntry);
        pieceManager.connect(choiceExit, literalZhongGuo);

        // 4. 打印构建结果
        System.out.println("构建的FSM节点:");
        System.out.println("1. 字面量节点 '我', stringId=" + literalWo.getStringId());
        System.out.println("2. 字面量节点 '爱', stringId=" + literalAi.getStringId());
        System.out.println("3. 字面量节点 '喜欢', stringId=" + literalXiHuan.getStringId());
        System.out.println("4. 字面量节点 '中国', stringId=" + literalZhongGuo.getStringId());
        System.out.println("5. 选择入口Joint节点");
        System.out.println("6. 选择出口Joint节点");

        System.out.println("\n连接关系:");
        System.out.println("'我' -> 选择入口");
        System.out.println("选择入口 -> '爱'");
        System.out.println("选择入口 -> '喜欢'");
        System.out.println("'爱' -> 选择出口");
        System.out.println("'喜欢' -> 选择出口");
        System.out.println("选择出口 -> '中国'");

        // 5. 最终形成Block
        Block block = new Block();
        block.setEntryId(literalWo.getId());  // 入口是"我"
        block.setExitId(literalZhongGuo.getId());  // 出口是"中国"

        System.out.println("\n最终Block:");
        System.out.println("EntryId: " + block.getEntryId());
        System.out.println("ExitId: " + block.getExitId());
    }

}
