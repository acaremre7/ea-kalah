package com.bol.kalah.state.impl;

import com.bol.kalah.model.Game;
import com.bol.kalah.model.enums.State;
import com.bol.kalah.state.model.AbstractGameState;

public class NewGameState extends AbstractGameState {

    @Override
    public State getCurrentState() {
        return State.NEW;
    }

    @Override
    public Game initializeGame() {
        return new Game();
    }
}
