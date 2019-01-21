package com.bol.kalah;

import com.bol.kalah.exception.UnsupportedKalahOperationException;
import com.bol.kalah.state.GameStateFactory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.TestComponent;

@TestComponent
public class GameStateFactoryTest {

    @Test
    public void testGameStateFactoryException() {
        Assert.assertThrows(UnsupportedKalahOperationException.class, () -> GameStateFactory.getGameState(null));
    }
}
