package com.jenny.gomoku;

import java.sql.*;

/**
 * Simple DatabaseManager using SQLite.
 * Keeps API compatible with your earlier code.
 */
public class DatabaseManager {
    private static final String DB_URL = "jdbc:sqlite:gomoku.db";

    public DatabaseManager() {
        createTableIfNotExists();
    }

    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    private void createTableIfNotExists() {
        String sql = "CREATE TABLE IF NOT EXISTS games (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "player TEXT," +
                "row INTEGER," +
                "col INTEGER," +
                "symbol TEXT" +
                ")";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveMove(String player, int row, int col, String symbol) {
        String sql = "INSERT INTO games(player, row, col, symbol) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, player);
            pstmt.setInt(2, row);
            pstmt.setInt(3, col);
            pstmt.setString(4, symbol);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showAllMoves() {
        String sql = "SELECT * FROM games";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("Saved moves:");
            while (rs.next()) {
                System.out.println(
                        rs.getString("player") + " played " +
                                rs.getString("symbol") + " at (" +
                                rs.getInt("row") + ", " + rs.getInt("col") + ")"
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void clearDatabase() {
        String sql = "DELETE FROM games";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
