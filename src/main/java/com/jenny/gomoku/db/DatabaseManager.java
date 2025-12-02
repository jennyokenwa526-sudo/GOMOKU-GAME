package com.jenny.gomoku.db;

import java.sql.*;
/**
 * Singleton DatabaseManager for SQLite persistence.
 * Provides saveMove, saveResult, clearDatabase and (optionally) saveBoard/loadBoard.
 */
public class DatabaseManager {

    private static DatabaseManager instance;
    /**
     * Return the singleton instance.
     * @return DatabaseManager singleton
     */
    // Singleton accessor
    public static synchronized DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    // PROTECTED no-args constructor (needed for TestDatabaseManager subclass)
    protected DatabaseManager() {}
    /**
     * Returns a database connection. Protected so test classes can override it.
     * @return Connection
     * @throws SQLException on error
     */
    // Connection method (overridden in tests)
    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:gomoku.db");
    }

    // Creates table when needed
    private void createTableIfNeeded() {
        String sql = "CREATE TABLE IF NOT EXISTS games (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "player TEXT, " +
                "row INTEGER, " +
                "col INTEGER, " +
                "symbol TEXT" +
                ")";

        try (Connection conn = getConnection();
             Statement st = conn.createStatement()) {
            st.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Save a single move into the games table.
     * @param player player name ("Human" or "Computer")
     * @param row zero-based row
     * @param col zero-based col
     * @param symbol symbol placed ("X" or "O")
     */
    // ✔ FINAL WORKING VERSION: matches your tests (4 parameters)
    public void saveMove(String player, int row, int col, String symbol) {
        createTableIfNeeded();

        String sql = "INSERT INTO games (player, row, col, symbol) VALUES (?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, player);
            ps.setInt(2, row);
            ps.setInt(3, col);
            ps.setString(4, symbol);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Save a game result into the DB (e.g. "Human Wins").
     * @param result textual result
     */
    // Needed by your game for the result
    public void saveResult(String result) {
        createTableIfNeeded();

        String sql = "INSERT INTO games (player, row, col, symbol) VALUES (?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "RESULT");
            ps.setInt(2, -1);
            ps.setInt(3, -1);
            ps.setString(4, result);

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Clear the games table (used by tests).
     */
    // Used by tests
    public void clearDatabase() {
        String sql = "DELETE FROM games";

        try (Connection conn = getConnection();
             Statement st = conn.createStatement()) {
            st.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
