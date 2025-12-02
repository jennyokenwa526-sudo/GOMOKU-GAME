package com.jenny.gomoku.model;
/**
 * Represents the Gomoku board and provides methods to inspect and modify it.
 * Uses '.' for empty cells and 'X'/'O' for players.
 */
public class Board {

    private final int rows;
    private final int cols;
    private final char[][] grid;

    /**
     * Create a board with given number of rows and columns.
     * @param rows number of rows (must be > 0)
     * @param cols number of columns (must be > 0)
     */
    public Board(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new char[rows][cols];

        // Fill grid with dots
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                grid[r][c] = '.';
            }
        }
    }

    // ===== REQUIRED BY TESTS AND GAME =====
    /**
     * Return number of rows.
     * @return rows
     */
    public int getRows() {
        return rows;
    }
    /**
     * Return number of columns.
     * @return cols
     */
    public int getCols() {
        return cols;
    }
/**
 * Returns the character at the given cell.
 * @param row zero-based row index
 * @param col zero-based column index
 * @return cell character ('.','X' or 'O'); '\0' if out-of-range
 */
    /** REQUIRED by GomokuGameTest */
    public char getCell(int row, int col) {
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            return '\0';
        }
        return grid[row][col];
    }
/**
 * Checks whether the given cell is a valid empty move.
 * @param row zero-based row
 * @param col zero-based col
 * @return true if the cell is in bounds and empty
 */
    /** REQUIRED by RandomAI.java */
    public boolean isValidMove(int row, int col) {
        return row >= 0 && row < rows &&
                col >= 0 && col < cols &&
                grid[row][col] == '.';
    }

    /** REQUIRED by RandomAITest */
    public boolean isCellEmpty(int row, int col) {
        return isValidMove(row, col);
    }
/**
 * Place a move on the board.
 * @param row zero-based row
 * @param col zero-based col
 * @param symbol 'X' or 'O'
 * @return true if placed successfully
 */
    /** REQUIRED by GomokuGame */
    public boolean placeMove(int row, int col, char symbol) {
        if (!isValidMove(row, col)) return false;
        grid[row][col] = symbol;
        return true;
    }
    /**
     * Returns true when no empty cells remain.
     * @return true if board is full
     */
    public boolean isFull() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == '.') return false;
            }
        }
        return true;
    }
/**
 * Check whether the given player has 5 in a row.
 * @param symbol player symbol (X or O)
 * @return true if the player won
 */
    /** 5-in-a-row win check */
    public boolean checkWin(char symbol) {
        // Horizontal
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c <= cols - 5; c++) {
                if (grid[r][c] == symbol && grid[r][c+1] == symbol &&
                        grid[r][c+2] == symbol && grid[r][c+3] == symbol &&
                        grid[r][c+4] == symbol) return true;
            }
        }

        // Vertical
        for (int r = 0; r <= rows - 5; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == symbol && grid[r+1][c] == symbol &&
                        grid[r+2][c] == symbol && grid[r+3][c] == symbol &&
                        grid[r+4][c] == symbol) return true;
            }
        }

        // Diagonal ↘
        for (int r = 0; r <= rows - 5; r++) {
            for (int c = 0; c <= cols - 5; c++) {
                if (grid[r][c] == symbol && grid[r+1][c+1] == symbol &&
                        grid[r+2][c+2] == symbol && grid[r+3][c+3] == symbol &&
                        grid[r+4][c+4] == symbol) return true;
            }
        }

        // Diagonal ↗
        for (int r = 4; r < rows; r++) {
            for (int c = 0; c <= cols - 5; c++) {
                if (grid[r][c] == symbol && grid[r-1][c+1] == symbol &&
                        grid[r-2][c+2] == symbol && grid[r-3][c+3] == symbol &&
                        grid[r-4][c+4] == symbol) return true;
            }
        }

        return false;
    }
    /**
     * Prints the board to System.out
     */
    public void printBoard() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                System.out.print(grid[r][c] + " ");
            }
            System.out.println();
        }
    }
}
