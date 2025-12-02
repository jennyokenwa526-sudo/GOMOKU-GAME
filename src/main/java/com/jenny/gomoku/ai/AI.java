
package com.jenny.gomoku.ai;

import com.jenny.gomoku.model.Board;
/**
 * Strategy interface for AI implementations.
 */
public interface AI {
    /**
     * Choose a move on the given board.
     * @param board current board
     * @return int[]{row,col} or {-1,-1} if no move
     */
    int[] getMove(Board board);
}
