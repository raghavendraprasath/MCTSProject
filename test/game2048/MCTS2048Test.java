package com.phasmidsoftware.dsaipg.projects.mcts.game2048;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MCTS2048Test {

    private MCTS2048 mcts;
    private State2048 initialState;

    @BeforeEach
    public void setup() {
        mcts = new MCTS2048(new Game2048());
        initialState = new State2048();
    }

    @Test
    public void testBestMoveReturnsNonNullMove() {
        Move2048 move = mcts.bestMove(initialState, 10);
        assertNotNull(move);
    }

    @Test
    public void testBestMoveReturnsValidDirection() {
        Move2048 move = mcts.bestMove(initialState, 5);
        assertTrue(move == Move2048.UP || move == Move2048.DOWN || move == Move2048.LEFT || move == Move2048.RIGHT);
    }
}
