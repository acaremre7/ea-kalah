package com.bol.kalah.service;

import com.bol.kalah.exception.IllegalKalahMoveException;
import com.bol.kalah.exception.UnsupportedKalahOperationException;
import com.bol.kalah.model.Game;
import com.bol.kalah.model.enums.Side;
import com.bol.kalah.model.enums.State;
import com.bol.kalah.state.GameStateFactory;
import com.bol.kalah.state.model.GameState;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;

@Service
@SessionScope
public class GameServiceImpl implements GameService, Serializable {

    private GameState gameState;
    private Game game;

    public GameServiceImpl() throws UnsupportedKalahOperationException {
        gameState = GameStateFactory.getGameState(State.NEW);
    }

    @Override
    public State getCurrentState() {
        return gameState.getCurrentState();
    }

    @Override
    public int[] getBoard() throws UnsupportedKalahOperationException {
        if (game == null) {
            throw new UnsupportedKalahOperationException("Game isn't started yet");
        } else {
            return game.getBoard();
        }
    }

    @Override
    public synchronized void startNewGame() throws UnsupportedKalahOperationException {
        game = gameState.initializeGame();
        gameState = GameStateFactory.getGameState(State.SOUTH);
    }

    @Override
    public synchronized void play(int pit) throws IllegalKalahMoveException, UnsupportedKalahOperationException {
        gameState = gameState.play(game, pit);
        if (gameState.getCurrentState() == State.END) {
            endGame();
        }
    }

    @Override
    public Side[] declareWinner() throws UnsupportedKalahOperationException {
        return gameState.declareWinner(game);
    }

    /**
     * Since State.END is a transition state before State.FINISHED, there is no direct access to this method
     *
     * @throws UnsupportedKalahOperationException thrown when there are stones in both sides
     */
    private void endGame() throws UnsupportedKalahOperationException {
        game = gameState.endGame(game);
        gameState = GameStateFactory.getGameState(State.FINISHED);
    }
}
