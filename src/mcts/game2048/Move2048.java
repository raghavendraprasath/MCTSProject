package game2048;

import core.Move;

public enum Move2048 implements Move<Game2048> {
    UP, DOWN, LEFT, RIGHT;

    @Override
    public int player() {
        return 0; // Single player
    }
}