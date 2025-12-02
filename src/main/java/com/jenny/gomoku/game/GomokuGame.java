package com.jenny.gomoku.game;

import com.jenny.gomoku.ai.AI;
import com.jenny.gomoku.db.DatabaseManager;
import com.jenny.gomoku.model.Board;
import com.jenny.gomoku.util.GameLogger;

import java.util.Scanner;
/**
 * Main game controller: contains the game loop, processes player and AI moves,
 * logs events and stores moves to the database.
 */
public class GomokuGame {

    private Board board;
    private final AI ai;
    private final Scanner scanner;
    private final DatabaseManager db = DatabaseManager.getInstance();

    /**
     * Create a game controller with a board and an AI player.
     * @param board the board instance
     * @param ai AI strategy implementation
     */
    public GomokuGame(Board board, AI ai) {
        this.board = board;
        this.ai = ai;
        this.scanner = new Scanner(System.in);
    }
    /**
     * Try to place a move for the given player.
     * @param player 'X' for human, 'O' for computer
     * @param row zero-based row index
     * @param col zero-based column index
     * @return true if the move was placed
     */
    public boolean makeMove(char player, int row, int col) {
        boolean ok = board.placeMove(row, col, player);

        if (ok) {
            GameLogger.logMove(player == 'X' ? "Human" : "Computer", row, col);
            db.saveMove(player == 'X' ? "Human" : "Computer", row, col, String.valueOf(player));
        }
        return ok;
    }
    /**
     * Ask AI to pick and make a move.
     * @return true if AI move succeeded
     */
    public boolean aiMove() {
        int[] mv = ai.getMove(board);
        return makeMove('O', mv[0], mv[1]);
    }
    /**
     * Start the command-line interactive loop. Recognized commands:
     *  - "<row> <col>" place move,
     *  - "save" save board to DB,
     *  - "load <id>" load board from DB,
     *  - "list" list saved boards,
     *  - "exit" quit.
     */
    public void startInteractive() {
        System.out.println("Welcome to Gomoku!");
        board.printBoard();

        while (true) {
            System.out.print("Enter 'row col': ");

            int r, c;
            try {
                r = scanner.nextInt();
                c = scanner.nextInt();
            } catch (Exception e) {
                scanner.nextLine();
                continue;
            }

            if (!makeMove('X', r, c)) {
                System.out.println("Invalid move.");
                continue;
            }

            board.printBoard();
            if (board.checkWin('X')) {
                System.out.println("You win!");
                db.saveResult("Human Wins");
                break;
            }

            if (board.isFull()) {
                System.out.println("Draw!");
                db.saveResult("Draw");
                break;
            }

            aiMove();
            board.printBoard();

            if (board.checkWin('O')) {
                System.out.println("Computer wins!");
                db.saveResult("Computer Wins");
                break;
            }

            if (board.isFull()) {
                System.out.println("Draw!");
                db.saveResult("Draw");
                break;
            }
        }
    }
}
