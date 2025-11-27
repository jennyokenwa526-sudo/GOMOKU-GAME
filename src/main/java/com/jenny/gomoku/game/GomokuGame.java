package com.jenny.gomoku.game;

import com.jenny.gomoku.model.Board;
import com.jenny.gomoku.ai.RandomAI;
import com.jenny.gomoku.util.GameLogger;

/**
 * Controls the main gameplay loop for Gomoku.
 * Exposes small API used in tests (makeMove, aiMove, getBoard).
 */
public class GomokuGame {

    private final Board board;
    private final RandomAI ai;

    public GomokuGame(Board board) {
        this.board = board;
        this.ai = new RandomAI();
    }

    /** convenience constructor to create a fresh board */
    public GomokuGame(int rows, int cols) {
        this(new Board(rows, cols));
    }

    /** return the underlying board (for tests) */
    public Board getBoard() {
        return board;
    }

    /**
     * Places a move for the given player symbol.
     * @param player char 'X' or 'O'
     * @return true if placed, false otherwise
     */
    public boolean makeMove(char player, int row, int col) {
        boolean ok = board.placeMove(row, col, player);
        if (ok) GameLogger.logMove(player == 'X' ? "Human" : "Computer", row, col);
        return ok;
    }

    /** Ask the AI to pick and apply a move. Returns true if AI placed a move. */
    public boolean aiMove() {
        int[] mv = ai.getMove(board);
        if (mv == null || mv.length != 2) return false;
        if (mv[0] < 0 || mv[1] < 0) return false;
        boolean ok = board.placeMove(mv[0], mv[1], 'O');
        if (ok) GameLogger.logMove("Computer", mv[0], mv[1]);
        return ok;
    }

    /** The interactive console loop (unused by tests, used by Main) */
    public void startInteractive() {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        System.out.println("Welcome to Gomoku!");
        board.printBoard();
        while (true) {
            System.out.print("Your move (row col): ");
            int r = scanner.nextInt();
            int c = scanner.nextInt();
            if (!makeMove('X', r, c)) {
                System.out.println("Invalid move. Try again.");
                continue;
            }
            board.printBoard();
            if (board.checkWin('X')) { System.out.println("You win!"); GameLogger.logWinner("Human"); break; }
            if (board.isFull()) { System.out.println("Draw"); GameLogger.logDraw(); break; }

            System.out.println("Computer is thinking...");
            aiMove();
            board.printBoard();
            if (board.checkWin('O')) { System.out.println("Computer wins"); GameLogger.logWinner("Computer"); break; }
            if (board.isFull()) { System.out.println("Draw"); GameLogger.logDraw(); break; }
        }
    }
}
