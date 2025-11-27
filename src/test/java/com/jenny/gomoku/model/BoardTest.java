package com.jenny.gomoku.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    void testPlaceAndInvalidPlace() {
        Board b = new Board(10, 10);
        assertTrue(b.placeMove(0, 0, 'X'));
        assertFalse(b.placeMove(0, 0, 'O')); // occupied
        assertFalse(b.placeMove(-1, 0, 'X')); // out of bounds
        assertFalse(b.placeMove(10, 10, 'X')); // out of bounds
    }

    @Test
    void testHorizontalWin() {
        Board b = new Board(5, 9);
        for (int c = 0; c < 5; c++) b.placeMove(2, c, 'X');
        assertTrue(b.checkWin('X'));
    }

    @Test
    void testVerticalWin() {
        Board b = new Board(9, 5);
        for (int r = 0; r < 5; r++) b.placeMove(r, 3, 'O');
        assertTrue(b.checkWin('O'));
    }

    @Test
    void testDiagonalWins() {
        Board b = new Board(9, 9);
        for (int i = 0; i < 5; i++) b.placeMove(i, i, 'X');
        assertTrue(b.checkWin('X'));
        Board b2 = new Board(9, 9);
        for (int i = 0; i < 5; i++) b2.placeMove(4 + i, i, 'O'); // anti-diag
        assertTrue(b2.checkWin('O'));
    }

    @Test
    void testNoFalsePositive() {
        Board b = new Board(10, 10);
        for (int i = 0; i < 4; i++) b.placeMove(0, i, 'X');
        b.placeMove(0, 4, 'O');
        assertFalse(b.checkWin('X'));
    }

    @Test
    void testFullBoard() {
        Board b = new Board(3, 3);
        for (int r = 0; r < 3; r++)
            for (int c = 0; c < 3; c++)
                b.placeMove(r, c, 'X');
        assertTrue(b.isFull());
    }
}
