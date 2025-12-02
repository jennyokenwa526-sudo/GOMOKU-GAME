package com.jenny.gomoku.util;
/**
 * Simple logger used by the game. Writes to both console and gomoku.log.
 */
public class GameLogger {

    public static void logMessage(String msg) {
        System.out.println("[LOG] " + msg);
    }
    /**
     * Simple logger used by the game. Writes to both console and gomoku.log.
     */
    public static void logMove(String player, int row, int col) {
        System.out.println("[MOVE] " + player + " played (" + row + ", " + col + ")");
    }
    /**
     * Log a result line.
     * @param  winner or draw text
     */
    public static void logWinner(String winner) {
        System.out.println("[WIN] " + winner + " wins the game!");
    }

    public static void logDraw() {
        System.out.println("[DRAW] Game ended in a draw.");
    }
}
