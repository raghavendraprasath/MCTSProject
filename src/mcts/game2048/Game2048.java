package game2048;

import core.Game;
import core.State;

public class Game2048 implements Game<Game2048> {
    @Override
    public State<Game2048> start() {
        return new State2048();
    }

    @Override
    public int opener() {
        return 0; // Only one player
    }
}