package com.jenny.gomoku.ai;

import com.jenny.gomoku.model.Board;
import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.*;

class RandomAITest {

    @RepeatedTest(10)
    void aiPicksValidEmptyPosition() {
        Board b = new Board(5, 5);
        b.placeMove(0, 0, 'X'); // occupy one cell
        RandomAI ai = new RandomAI();
        int[] m = ai.getMove(b);
        assertNotNull(m);
        assertEquals(2, m.length);
        assertTrue(m[0] >= 0 && m[0] < b.getRows());
        assertTrue(m[1] >= 0 && m[1] < b.getCols());
        assertTrue(b.isCellEmpty(m[0], m[1]));
    }
}
