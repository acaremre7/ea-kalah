package com.bol.kalah;

import com.bol.kalah.exception.IllegalKalahMoveException;
import com.bol.kalah.exception.UnsupportedKalahOperationException;
import com.bol.kalah.model.Game;
import com.bol.kalah.model.enums.Side;
import com.bol.kalah.model.enums.State;
import com.bol.kalah.service.GameService;
import com.bol.kalah.service.GameServiceImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.TestComponent;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@TestComponent
public class GameServiceTest {

    private GameService gameService;

    @Before
    public void setUp() throws UnsupportedKalahOperationException {
        gameService = new GameServiceImpl();
    }

    @After
    public void tearDown() {
        gameService = null;
    }

    @Test
    public void testGetCurrentState() throws Exception {
        Assert.assertEquals(State.NEW, gameService.getCurrentState());
        gameService.startNewGame();
        Assert.assertEquals(State.SOUTH, gameService.getCurrentState());
        gameService.play(5);
        Assert.assertEquals(State.NORTH, gameService.getCurrentState());
        gameService.play(12);
        Assert.assertEquals(State.SOUTH, gameService.getCurrentState());

        //Fast forwarding to end game, using reflection
        Game game = new Game();
        game.setBoard(new int[]{0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0});
        modifyGame(game);

        //Cannot test State.END because it's just a transition state before deciding who the winner is.
        gameService.play(5);
        Assert.assertEquals(State.FINISHED, gameService.getCurrentState());
    }

    @Test
    public void testPlay() throws UnsupportedKalahOperationException, NoSuchFieldException, IllegalAccessException, IllegalKalahMoveException {
        gameService.startNewGame();

        //Testing South's steal
        Game game = new Game();
        game.setBoard(new int[]{1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 5, 0, 0});
        modifyGame(game);
        gameService.play(0);
        Assert.assertArrayEquals(new int[]{0, 0, 0, 0, 0, 1, 6, 1, 0, 0, 0, 0, 0, 0}, gameService.getBoard());

        //Testing North's steal
        game.setBoard(new int[]{1, 0, 0, 0, 5, 0, 0, 1, 0, 0, 0, 0, 1, 0});
        modifyGame(game);
        gameService.play(12);
        gameService.play(7);
        Assert.assertArrayEquals(new int[]{0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 7}, gameService.getBoard());
    }

    @Test
    public void testGetBoard() throws Exception {
        Assert.assertThrows(UnsupportedKalahOperationException.class, gameService::getBoard);

        Game game = new Game();
        modifyGame(game);
        Assert.assertEquals(game.getBoard(), gameService.getBoard());
    }

    @Test
    public void testDeclareWinner() throws UnsupportedKalahOperationException, NoSuchFieldException, IllegalAccessException, IllegalKalahMoveException {
        Assert.assertThrows(UnsupportedKalahOperationException.class, gameService::declareWinner);

        //South wins !
        gameService.startNewGame();
        Game game = new Game();
        game.setBoard(new int[]{0, 0, 0, 0, 0, 1, 20, 0, 0, 0, 0, 0, 1, 0});
        modifyGame(game);
        gameService.play(5);
        Assert.assertArrayEquals(new Side[]{Side.SOUTH}, gameService.declareWinner());

        //North wins !
        gameService.startNewGame();
        game.setBoard(new int[]{0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 20});
        modifyGame(game);
        gameService.play(5);
        Assert.assertArrayEquals(new Side[]{Side.NORTH}, gameService.declareWinner());

        //It's a tie :(
        gameService.startNewGame();
        game.setBoard(new int[]{0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0});
        modifyGame(game);
        gameService.play(5);
        Assert.assertArrayEquals(new Side[]{Side.SOUTH, Side.NORTH}, gameService.declareWinner());
    }

    @Test
    public void testIllegalKalahMove() throws UnsupportedKalahOperationException, IllegalKalahMoveException {
        gameService.startNewGame();
        Assert.assertThrows(IllegalKalahMoveException.class, () -> gameService.play(11));
        Assert.assertThrows(IllegalKalahMoveException.class, () -> gameService.play(6));
        gameService.play(0);
        Assert.assertThrows(IllegalKalahMoveException.class, () -> gameService.play(0));
    }

    @Test
    public void testIllegalStateActions() throws UnsupportedKalahOperationException, NoSuchMethodException {
        Assert.assertThrows(UnsupportedKalahOperationException.class, () -> gameService.play(0));
        gameService.startNewGame();
        Assert.assertThrows(UnsupportedKalahOperationException.class, gameService::startNewGame);

        Method endGameMethod = gameService.getClass().getDeclaredMethod("endGame");
        endGameMethod.setAccessible(true);
        //We should expect InvocationTargetException instead of KalahException here, because we've
        //added another layer of abstraction with reflection. If you trace its stack trace, you'll
        //find our UnsupportedKalahOperationException anyways.
        Assert.assertThrows(InvocationTargetException.class, () -> endGameMethod.invoke(gameService));
    }

    private void modifyGame(Game game) throws NoSuchFieldException, IllegalAccessException {
        Field gameField = gameService.getClass().getDeclaredField("game");
        gameField.setAccessible(true);
        gameField.set(gameService, game);
    }

}
