package com.jenny.gomoku;

import com.jenny.gomoku.db.DatabaseManager;
import org.junit.jupiter.api.*;

import java.io.File;
import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;


class DatabaseManagerTest {

    private static final String TEST_DB = "test_gomoku.db";

    // We will use a subclass that overrides the connection for testing
    static class TestDatabaseManager extends DatabaseManager {
        @Override
        protected Connection getConnection() throws SQLException {
            return DriverManager.getConnection("jdbc:sqlite:" + TEST_DB);
        }
    }

    private TestDatabaseManager db;

    @BeforeEach
    void setup() {
        // remove old test file
        File f = new File(TEST_DB);
        if (f.exists()) f.delete();
        db = new TestDatabaseManager();
        db.clearDatabase();
    }

    @AfterEach
    void teardown() {
        File f = new File(TEST_DB);
        if (f.exists()) f.delete();
    }

    @Test
    void testSaveMoveAndQuery() throws Exception {
        db.saveMove("Human", 1, 2, "X");

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + TEST_DB);
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT COUNT(*) AS total FROM games")) {
            assertTrue(rs.next());
            assertEquals(1, rs.getInt("total"));
        }
    }

    @Test
    void testClearDatabase() throws Exception {
        db.saveMove("Human", 1, 1, "X");
        db.saveMove("Computer", 2, 2, "O");
        db.clearDatabase();

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + TEST_DB);
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT COUNT(*) AS total FROM games")) {
            assertTrue(rs.next());
            assertEquals(0, rs.getInt("total"));
        }
    }
}
