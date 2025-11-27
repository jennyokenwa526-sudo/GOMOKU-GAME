package com.jenny.gomoku.ai;

import com.jenny.gomoku.model.Board;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Simple AI that chooses a random valid move.
 */
public class RandomAI {

    private final Random random = new Random();

    /**
     * Returns a random available move as [row, col].
     * If no moves available returns [-1, -1].
     */
    public int[] getMove(Board board) {
        List<int[]> available = new ArrayList<>();
        for (int r = 0; r < board.getRows(); r++) {
            for (int c = 0; c < board.getCols(); c++) {
                if (board.isCellEmpty(r, c)) available.add(new int[]{r, c});
            }
        }
        if (available.isEmpty()) return new int[]{-1, -1};
        return available.get(random.nextInt(available.size()));
    }
}
