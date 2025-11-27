package com.jenny.gomoku.util;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Simple logger that writes game events into a file (gomoku.log)
 */
public class GameLogger {

    private static final String LOG_FILE = "gomoku.log";

    private static void writeLog(String message) {
        try (FileWriter writer = new FileWriter(LOG_FILE, true)) {
            writer.write(LocalDateTime.now() + " - " + message + "\n");
        } catch (IOException e) {
            System.out.println("LOG ERROR: " + e.getMessage());
        }
    }

    public static void logMove(String player, int row, int col) {
        writeLog("MOVE: " + player + " placed at (" + row + "," + col + ")");
    }

    public static void logWinner(String winner) {
        writeLog("WINNER: " + winner);
    }

    public static void logDraw() {
        writeLog("DRAW: Game ended in a tie.");
    }
}
