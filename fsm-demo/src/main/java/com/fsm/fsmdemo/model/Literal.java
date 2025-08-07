package com.fsm.fsmdemo.model;

import lombok.Data;

/**
 * @author rensong.pu
 * @date 2025/8/7
 */
public class Literal extends Piece{

    private int stringId=-1;

    private boolean continuous;

    public int getStringId() {
        return stringId;
    }

    public void setStringId(int stringId) {
        this.stringId = stringId;
    }

    public boolean isContinuous() {
        return continuous;
    }

    public void setContinuous(boolean continuous) {
        this.continuous = continuous;
    }
}
