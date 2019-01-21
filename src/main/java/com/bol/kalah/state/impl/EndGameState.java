package com.bol.kalah.state.impl;

import com.bol.kalah.model.Game;
import com.bol.kalah.model.enums.State;
import com.bol.kalah.state.model.AbstractGameState;

import java.util.Arrays;
import java.util.stream.IntStream;

public class EndGameState extends AbstractGameState {

    @Override
    public State getCurrentState() {
        return State.END;
    }

    @Override
    public Game endGame(Game game) {
        int[] board = game.getBoard();

        int southLeftovers = IntStream.of(Arrays.copyOfRange(board, 0, SOUTH_KALAH)).sum();
        int northLeftovers = IntStream.of(Arrays.copyOfRange(board, 7, NORTH_KALAH)).sum();

        int northScore = board[NORTH_KALAH] + northLeftovers;
        int southScore = board[SOUTH_KALAH] + southLeftovers;

        board = new int[]{0, 0, 0, 0, 0, 0, southScore, 0, 0, 0, 0, 0, 0, northScore};
        game.setBoard(board);
        return game;
    }
}
