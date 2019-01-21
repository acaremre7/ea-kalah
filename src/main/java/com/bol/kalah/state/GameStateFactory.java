package com.bol.kalah.state;

import com.bol.kalah.exception.UnsupportedKalahOperationException;
import com.bol.kalah.model.enums.State;
import com.bol.kalah.state.impl.*;
import com.bol.kalah.state.model.GameState;

public class GameStateFactory {

    public static GameState getGameState(State state) throws UnsupportedKalahOperationException {
        if (state != null) {
            switch (state) {
                case NEW:
                    return new NewGameState();
                case SOUTH:
                    return new SouthTurnState();
                case NORTH:
                    return new NorthTurnState();
                case END:
                    return new EndGameState();
                case FINISHED:
                    return new FinishedGameState();
            }
        }
        throw new UnsupportedKalahOperationException("Requested an unknown state from GameStateFactory.");
    }
}
