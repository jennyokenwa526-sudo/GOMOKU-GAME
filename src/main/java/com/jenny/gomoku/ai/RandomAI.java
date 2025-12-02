package com.jenny.gomoku.ai;

import com.jenny.gomoku.model.Board;

import java.util.Random;
/**
 * Random AI implementation that picks any empty cell at random.
 */
public class RandomAI implements AI {

    private final Random rand = new Random();

    @Override
    public int[] getMove(Board board) {
        // pick a random empty cell
        while (true) {
            int r = rand.nextInt(10);
            int c = rand.nextInt(10);

            if (board.isValidMove(r, c))
                return new int[]{r, c};
        }
    }
}
