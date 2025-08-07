package com.fsm.fsmdemo;

import com.fsm.fsmdemo.model.Joint;
import com.fsm.fsmdemo.model.Literal;
import com.fsm.fsmdemo.model.Piece;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author rensong.pu
 * @date 2025/8/7
 */
public class PieceManager {
    private Map<Piece, List<Piece>> pieceMap = new HashMap<>();

    private List<Piece> pieces = new ArrayList<>();

    public PieceManager(boolean b) {
    }

    public <T extends Piece> T createPiece(Class<T> tClass, Object o) {
        // 实例化 tClass
        T piece = null;
        try {
            piece = tClass.newInstance();
            piece.setId(pieces.size());
            pieces.add(piece);
            pieceMap.put(piece, new ArrayList<>());
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return piece;

    }

    public void connect(Piece from, Piece to) {
        pieceMap.get(from).add(to);
    }
}
