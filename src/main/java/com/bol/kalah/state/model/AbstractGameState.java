package com.bol.kalah.state.model;

import com.bol.kalah.exception.IllegalKalahMoveException;
import com.bol.kalah.exception.UnsupportedKalahOperationException;
import com.bol.kalah.model.Game;
import com.bol.kalah.model.enums.Side;

import java.util.Arrays;
import java.util.stream.IntStream;

public abstract class AbstractGameState implements GameState {
    protected final int SOUTH_KALAH = 6;
    protected final int NORTH_KALAH = 13;

    @Override
    public Game initializeGame() throws UnsupportedKalahOperationException {
        throw new UnsupportedKalahOperationException("You can't initialize a new game now.");
    }

    @Override
    public GameState play(Game game, int pit) throws UnsupportedKalahOperationException, IllegalKalahMoveException {
        throw new UnsupportedKalahOperationException("You can't play now.");
    }

    @Override
    public Game endGame(Game game) throws UnsupportedKalahOperationException {
        throw new UnsupportedKalahOperationException("You can't end game now. There are still stones in both sides.");
    }

    @Override
    public Side[] declareWinner(Game game) throws UnsupportedKalahOperationException {
        throw new UnsupportedKalahOperationException("Game isn't finished yet.");
    }

    protected void validateMove(Side side, Game game, int pit) throws IllegalKalahMoveException {
        if (pit < 0 || pit == SOUTH_KALAH || pit >= NORTH_KALAH) {
            throw new IllegalKalahMoveException("You can't do that.");
        } else if (pit < SOUTH_KALAH && side == Side.NORTH || (pit > SOUTH_KALAH && side == Side.SOUTH)) {
            throw new IllegalKalahMoveException("You can't play from your opponent's pits.");
        } else {
            int[] board = game.getBoard();
            if (board[pit] == 0) {
                throw new IllegalKalahMoveException("There's no stone in pit " + pit + ".");
            }
        }
    }

    /**
     * Sows stones counter clockwise.
     *
     * @param game          Current game instance.
     * @param opponentKalah Opponent's kalah ID.
     * @param pit           Which pit player is starting to sow stones from.
     * @return Returns the ID of the pit where it puts its last stone.
     */
    protected int sow(Game game, int opponentKalah, int pit) {
        int[] board = game.getBoard();
        int stoneCount = board[pit];
        int currentIndex = pit;

        board[pit] = 0;
        while (stoneCount > 0) {
            currentIndex = (currentIndex + 1) % 14;
            if (currentIndex != opponentKalah) {
                board[currentIndex]++;
                stoneCount--;
            }
        }
        game.setBoard(board);
        return currentIndex;
    }

    /**
     * Returns if game is over. Checks if there are stones either sides.
     *
     * @param game Current game instance.
     * @return Returns if the game is over or not.
     */
    protected boolean isGameOver(Game game) {
        int[] board = game.getBoard();
        int[] south = Arrays.copyOfRange(board, 0, SOUTH_KALAH);
        int[] north = Arrays.copyOfRange(board, 7, NORTH_KALAH);
        return IntStream.of(south).allMatch(x -> x == 0) || IntStream.of(north).allMatch(x -> x == 0);
    }
}
