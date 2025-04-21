package game2048;
import game2048.Game2048;
import game2048.MCTS2048;
import game2048.Move2048;
import game2048.State2048;

import core.State;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Game2048Test {

    @Test
    public void testStartReturnsState2048() {
        Game2048 game = new Game2048();
        State<Game2048> state = game.start();
        assertNotNull(state);
        assertTrue(state instanceof State2048);
    }

    @Test
    public void testOpenerIsZero() {
        Game2048 game = new Game2048();
        assertEquals(0, game.opener());
    }
}