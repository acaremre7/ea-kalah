package com.bol.kalah.model;

import java.io.Serializable;

public class Game implements Serializable {
    private int[] board;

    public Game() {
        board = new int[]{6, 6, 6, 6, 6, 6, 0, 6, 6, 6, 6, 6, 6, 0};
    }

    public int[] getBoard() {
        return board;
    }

    public void setBoard(int[] board) {
        this.board = board;
    }
}
