package com.jenny.gomoku;

import com.jenny.gomoku.game.GomokuGame;

/** Simple launcher for the CLI game */
public class Main {
    public static void main(String[] args) {
        GomokuGame game = new GomokuGame(10, 10);
        game.startInteractive();
    }
}
