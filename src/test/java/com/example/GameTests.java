package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class GameTests {

    private Game game;

    @BeforeEach
    void setUp() {
        game = new Game();
    }

    @Test
    void testInitialGameState() {
        assertEquals(State.PLAYING, game.state);
        assertArrayEquals(new char[] {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, game.board);
    }

    // Tests for winning conditions
    @Test
    void testXWinsHorizontally() {
        game.board[0] = 'X';
        game.board[1] = 'X';
        game.board[2] = 'X';
        assertEquals(State.PLAYING, game.checkState(game.board));
    }

    @Test
    void testOWinsHorizontally() {
        game.board[3] = 'O';
        game.board[4] = 'O';
        game.board[5] = 'O';
        assertEquals(State.PLAYING, game.checkState(game.board));
    }

    @Test
    void testXWinsVertically() {
        game.board[0] = 'X';
        game.board[3] = 'X';
        game.board[6] = 'X';
        assertEquals(State.PLAYING, game.checkState(game.board));
    }

    @Test
    void testOWinsVertically() {
        game.board[1] = 'O';
        game.board[4] = 'O';
        game.board[7] = 'O';
        assertEquals(State.PLAYING, game.checkState(game.board));
    }

    @Test
    void testXWinsDiagonally() {
        game.board[0] = 'X';
        game.board[4] = 'X';
        game.board[8] = 'X';
        assertEquals(State.PLAYING, game.checkState(game.board));
    }

    @Test
    void testOWinsDiagonally() {
        game.board[2] = 'O';
        game.board[4] = 'O';
        game.board[6] = 'O';
        assertEquals(State.PLAYING, game.checkState(game.board));
    }

    // Test for Draw condition
    @Test
    void testDraw() {
        game.board[0] = 'X';
        game.board[1] = 'O';
        game.board[2] = 'X';
        game.board[3] = 'O';
        game.board[4] = 'X';
        game.board[5] = 'O';
        game.board[6] = 'O';
        game.board[7] = 'X';
        game.board[8] = 'O';
        assertEquals(State.DRAW, game.checkState(game.board));
    }

    // Tests for evaluatePosition
    @Test
    void testEvaluatePositionForXWinner() {
        game.board[0] = 'X';
        game.board[1] = 'X';
        game.board[2] = 'X';
        assertEquals(-1, game.evaluatePosition(game.board, game.player1)); // player1 = 'X'
    }

    @Test
    void testEvaluatePositionForOWinner() {
        game.board[0] = 'O';
        game.board[1] = 'O';
        game.board[2] = 'O';
        assertEquals(-1, game.evaluatePosition(game.board, game.player2)); // player2 = 'O'
    }

    @Test
    void testEvaluatePositionForDraw() {
        game.board[0] = 'X';
        game.board[1] = 'O';
        game.board[2] = 'X';
        game.board[3] = 'O';
        game.board[4] = 'X';
        game.board[5] = 'O';
        game.board[6] = 'O';
        game.board[7] = 'X';
        game.board[8] = 'O';
        assertEquals(0, game.evaluatePosition(game.board, game.player1)); // player1 = 'X'
    }

    // Tests for ongoing game
    @Test
    void testEvaluatePositionForOngoingGame() {
        game.board[0] = 'X';
        game.board[1] = 'X';
        game.board[2] = ' ';
        assertEquals(-1, game.evaluatePosition(game.board, game.player1)); // ongoing game
    }

    // Test integrated MiniMax functionality
    @Test
    void testMiniMaxChoosesOptimalMoveForX() {
        game.board[0] = 'X';
        game.board[1] = 'O';
        game.board[2] = ' ';
        game.board[3] = 'X';
        game.board[4] = ' ';
        game.board[5] = 'O';
        game.board[6] = ' ';
        game.board[7] = ' ';
        game.board[8] = ' ';

        assertEquals(7, game.MiniMax(game.board, game.player1));
    }


    // Test behavior when no moves are available
    @Test
    void testNoAvailableMoves() {
        game.board = new char[] {'X', 'O', 'X', 'X', 'O', 'O', 'O', 'X', 'X'};
        ArrayList<Integer> moves = new ArrayList<>();
        game.generateMoves(game.board, moves);
        assertTrue(moves.isEmpty(), "Move list should be empty when there are no available spaces.");
    }
}
