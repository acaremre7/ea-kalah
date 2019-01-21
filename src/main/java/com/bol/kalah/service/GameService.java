package com.bol.kalah.service;

import com.bol.kalah.exception.IllegalKalahMoveException;
import com.bol.kalah.exception.UnsupportedKalahOperationException;
import com.bol.kalah.model.enums.Side;
import com.bol.kalah.model.enums.State;

public interface GameService {

    /**
     * Gets current state of the game.
     *
     * @return returns @State enum, according to game's current state.
     */
    State getCurrentState();

    /**
     * Gets current state of the board.
     *
     * @return Returns an integer array representation of the board.
     * @throws UnsupportedKalahOperationException Thrown when board isn't created yet, in State.NEW.
     */
    int[] getBoard() throws UnsupportedKalahOperationException;

    /**
     * Starts a new game by initializing the board.
     * Switches state to State.South immediately, as South always starts first.
     *
     * @throws UnsupportedKalahOperationException Thrown when an unfinished game already exists.
     */
    void startNewGame() throws UnsupportedKalahOperationException;

    /**
     * Plays from the pit specified
     *
     * @param pit id of the pit
     * @throws IllegalKalahMoveException          Thrown when specified move is illegal.
     * @throws UnsupportedKalahOperationException Thrown when game isn't in a state where you can make a move.
     */
    void play(int pit) throws IllegalKalahMoveException, UnsupportedKalahOperationException;

    /**
     * Returns the side of who won the game. Returns both sides if it's a draw.
     *
     * @return Side of the winner(s).
     * @throws UnsupportedKalahOperationException Thrown when the winner isn't decided yet.
     */
    Side[] declareWinner() throws UnsupportedKalahOperationException;
}
