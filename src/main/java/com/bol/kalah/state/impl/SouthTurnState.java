package com.bol.kalah.state.impl;

import com.bol.kalah.exception.IllegalKalahMoveException;
import com.bol.kalah.exception.UnsupportedKalahOperationException;
import com.bol.kalah.model.Game;
import com.bol.kalah.model.enums.Side;
import com.bol.kalah.model.enums.State;
import com.bol.kalah.state.GameStateFactory;
import com.bol.kalah.state.model.AbstractGameState;
import com.bol.kalah.state.model.GameState;

public class SouthTurnState extends AbstractGameState {

    @Override
    public State getCurrentState() {
        return State.SOUTH;
    }

    @Override
    public GameState play(Game game, int pit) throws IllegalKalahMoveException, UnsupportedKalahOperationException {
        validateMove(Side.SOUTH, game, pit);
        int index = sow(game, NORTH_KALAH, pit);
        if (index == SOUTH_KALAH) {
            return isGameOver(game) ? GameStateFactory.getGameState(State.END) : GameStateFactory.getGameState(State.SOUTH);
        } else if (index < SOUTH_KALAH) {
            int[] board = game.getBoard();
            if (board[index] == 1) {
                int stolenStones = board[12 - index];
                board[12 - index] = 0;
                board[index] = 0;
                board[SOUTH_KALAH] = board[SOUTH_KALAH] + stolenStones + 1;
                game.setBoard(board);
            }
            return isGameOver(game) ? GameStateFactory.getGameState(State.END) : GameStateFactory.getGameState(State.NORTH);
        } else {
            return isGameOver(game) ? GameStateFactory.getGameState(State.END) : GameStateFactory.getGameState(State.NORTH);
        }
    }
}
