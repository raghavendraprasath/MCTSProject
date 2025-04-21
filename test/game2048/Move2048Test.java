package game2048;
import game2048.Game2048;
import game2048.MCTS2048;
import game2048.Move2048;
import game2048.State2048;
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