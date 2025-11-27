package com.jenny.gomoku.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class GameLoggerTest {

    @Test
    public void testLogMove() {
        assertDoesNotThrow(() -> GameLogger.logMove("Human", 2, 3));
    }

    @Test
    public void testLogWinner() {
        assertDoesNotThrow(() -> GameLogger.logWinner("Human"));
    }

    @Test
    public void testLogDraw() {
        assertDoesNotThrow(GameLogger::logDraw);
    }
}
