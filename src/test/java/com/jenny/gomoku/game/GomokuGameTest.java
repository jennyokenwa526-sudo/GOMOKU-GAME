package com.jenny.gomoku.game;

import com.jenny.gomoku.model.Board;
import com.jenny.gomoku.ai.RandomAI;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GomokuGameTest {

    @Test
    public void testHumanMoveValid() {
        Board board = new Board(10, 10);
        GomokuGame game = new GomokuGame(board, new RandomAI());

        boolean result = game.makeMove('X', 2, 3);

        assertTrue(result);
        assertEquals('X', board.getCell(2, 3));
    }

    @Test
    public void testHumanMoveInvalid() {
        Board board = new Board(10, 10);
        GomokuGame game = new GomokuGame(board, new RandomAI());

        game.makeMove('X', 2, 3);
        boolean result = game.makeMove('X', 2, 3);

        assertFalse(result);
    }

    @Test
    public void testAIMove() {
        Board board = new Board(10, 10);
        GomokuGame game = new GomokuGame(board, new RandomAI());

        boolean result = game.aiMove();
        assertTrue(result);
    }
}
