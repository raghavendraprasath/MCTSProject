package game2048;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import game2048.State2048;
import game2048.Move2048;
import game2048.Game2048;
import game2048.MCTS2048;

public class State2048Test {

    private State2048 state;

    @BeforeEach
    public void setup() {
        state = new State2048();
    }

    @Test
    public void testInitialGridHasTwoTiles() {
        int[][] grid = state.getGrid();
        int nonZeroCount = 0;
        for (int[] row : grid)
            for (int val : row)
                if (val != 0) nonZeroCount++;
        assertEquals(2, nonZeroCount, "Initial state should have exactly two tiles spawned");
    }

    @Test
    public void testApplyMoveDoesNotMutateOriginal() {
        int[][] before = state.getGrid();
        State2048 newState = state.applyMove(Move2048.LEFT);
        int[][] after = state.getGrid();
        assertNotSame(state, newState);
        assertArrayEquals(before, after, "Original state should not be mutated");
    }

    @Test
    public void testGetMaxTileReturnsMaxTile() {
        int max = 0;
        for (int[] row : state.getGrid()) {
            for (int val : row) {
                max = Math.max(max, val);
            }
        }
        assertEquals(max, state.getMaxTile(), "getMaxTile() should return the max tile");
    }

    @Test
    public void testMoveActuallyChangesBoard() {
        State2048 moved = state.applyMove(Move2048.RIGHT);
        assertNotEquals(state.toString(), moved.toString(), "Board should change after applying move");
    }

    @Test
    public void testEvaluateScorePositive() {
        double score = state.evaluate();
        assertTrue(score > 0, "Score should be a positive value for non-empty board");
    }

    @Test
    public void testAvailableMovesNotEmptyInitially() {
        assertFalse(state.getAvailableMoves().isEmpty(), "Available moves should not be empty at the beginning");
    }
}
