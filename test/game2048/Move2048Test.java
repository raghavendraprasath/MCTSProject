package com.phasmidsoftware.dsaipg.projects.mcts.game2048;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Move2048Test {

    @Test
    public void testEnumValues() {
        assertEquals(0, Move2048.UP.player());
        assertEquals(0, Move2048.DOWN.player());
        assertEquals(0, Move2048.LEFT.player());
        assertEquals(0, Move2048.RIGHT.player());
    }

    @Test
    public void testMoveValuesCount() {
        assertEquals(4, Move2048.values().length);
    }
}