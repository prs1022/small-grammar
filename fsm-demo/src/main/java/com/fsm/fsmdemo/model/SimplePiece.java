package com.fsm.fsmdemo.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rensong.pu
 * @date 2025/8/7
 */
@Data
public
class SimplePiece {
    public int id;
    public String type; //节点类型 literal，joint
    public String value; // 节点值
    public List<Integer> targets = new ArrayList<>();
    public boolean isFinal = false; // 添加终态标记

    // 构造函数也需要相应修改
    public SimplePiece(int id, String type, String value) {
        this.id = id;
        this.type = type;
        this.value = value;
    }

    public SimplePiece(int id, String type, String value, boolean isFinal) {
        this.id = id;
        this.type = type;
        this.value = value;
        this.isFinal = isFinal;
    }

    public void addTarget(int targetId) {
        targets.add(targetId);
    }
}
