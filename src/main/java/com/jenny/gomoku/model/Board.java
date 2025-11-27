package com.jenny.gomoku.model;

import java.util.Arrays;

/**
 * Represents the Gomoku board and provides move and win-checking logic.
 * Empty cells are represented with '.' character.
 */
public class Board {
    private final int rows;
    private final int cols;
    private final char[][] grid;

    public Board(int rows, int cols) {
        if (rows <= 0 || cols <= 0) throw new IllegalArgumentException("rows/cols must be > 0");
        this.rows = rows;
        this.cols = cols;
        this.grid = new char[rows][cols];
        clearBoard();
    }

    /** Set all cells to empty '.' */
    public void clearBoard() {
        for (char[] row : grid) Arrays.fill(row, '.');
    }

    /**
     * Place a move on the board.
     * @param row zero-based row
     * @param col zero-based column
     * @param player 'X' or 'O'
     * @return true if placed successfully, false if invalid or occupied
     */
    public boolean placeMove(int row, int col, char player) {
        if (!isWithin(row, col)) return false;
        if (grid[row][col] != '.') return false;
        grid[row][col] = player;
        return true;
    }

    /** Returns true if given cell is inside the board */
    public boolean isWithin(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    /** Returns true if the cell is empty ('.') */
    public boolean isCellEmpty(int row, int col) {
        if (!isWithin(row, col)) return false;
        return grid[row][col] == '.';
    }

    public char getCell(int row, int col) {
        if (!isWithin(row, col)) throw new IndexOutOfBoundsException("Invalid cell");
        return grid[row][col];
    }

    public int getRows() { return rows; }
    public int getCols() { return cols; }

    /** Returns true if no empty cells remain */
    public boolean isFull() {
        for (char[] r : grid) for (char c : r) if (c == '.') return false;
        return true;
    }

    /** Check if the given player has 5 in a row */
    public boolean checkWin(char player) {
        final int need = 5;
        // horizontal
        for (int r = 0; r < rows; r++) {
            int cnt = 0;
            for (int c = 0; c < cols; c++) {
                cnt = (grid[r][c] == player) ? cnt + 1 : 0;
                if (cnt >= need) return true;
            }
        }
        // vertical
        for (int c = 0; c < cols; c++) {
            int cnt = 0;
            for (int r = 0; r < rows; r++) {
                cnt = (grid[r][c] == player) ? cnt + 1 : 0;
                if (cnt >= need) return true;
            }
        }
        // diagonal down-right
        for (int r = 0; r <= rows - need; r++) {
            for (int c = 0; c <= cols - need; c++) {
                boolean ok = true;
                for (int i = 0; i < need; i++) {
                    if (grid[r + i][c + i] != player) { ok = false; break; }
                }
                if (ok) return true;
            }
        }
        // diagonal up-right
        for (int r = need - 1; r < rows; r++) {
            for (int c = 0; c <= cols - need; c++) {
                boolean ok = true;
                for (int i = 0; i < need; i++) {
                    if (grid[r - i][c + i] != player) { ok = false; break; }
                }
                if (ok) return true;
            }
        }
        return false;
    }

    /** Prints board to console */
    public void printBoard() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                System.out.print(grid[r][c]);
                if (c < cols - 1) System.out.print(' ');
            }
            System.out.println();
        }
    }
}
