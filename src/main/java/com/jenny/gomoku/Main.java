package com.jenny.gomoku;

import com.jenny.gomoku.ai.RandomAI;
import com.jenny.gomoku.game.GomokuGame;
import com.jenny.gomoku.model.Board;
/**
 * Application entry point. Creates a board and starts the interactive game.
 */
public class Main {
    public static void main(String[] args) {

        Board board = new Board(10, 10);
        GomokuGame game = new GomokuGame(board, new RandomAI());

        game.startInteractive();
    }
}
