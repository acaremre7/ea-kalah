package com.bol.kalah.state.impl;

import com.bol.kalah.model.Game;
import com.bol.kalah.model.enums.Side;
import com.bol.kalah.model.enums.State;
import com.bol.kalah.state.model.AbstractGameState;

public class FinishedGameState extends AbstractGameState {
    @Override
    public State getCurrentState() {
        return State.FINISHED;
    }

    @Override
    public Game initializeGame() {
        return new Game();
    }

    @Override
    public Side[] declareWinner(Game game) {
        int[] board = game.getBoard();

        if (board[SOUTH_KALAH] > board[NORTH_KALAH]) {
            return new Side[]{Side.SOUTH};
        } else if (board[NORTH_KALAH] > board[SOUTH_KALAH]) {
            return new Side[]{Side.NORTH};
        } else {
            return new Side[]{Side.SOUTH, Side.NORTH};
        }
    }
}
