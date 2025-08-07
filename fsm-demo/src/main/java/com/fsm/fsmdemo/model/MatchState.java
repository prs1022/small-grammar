package com.fsm.fsmdemo.model;

import java.util.Objects;

/**
 * @author rensong.pu
 * @date 2025/8/7
 */
public class MatchState {
    public int pieceId;
    public int position;
    public String path;

    public MatchState(int pieceId, int position, String path) {
        this.pieceId = pieceId;
        this.position = position;
        this.path = path;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MatchState that = (MatchState) obj;
        return pieceId == that.pieceId && position == that.position;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceId, position);
    }
}
