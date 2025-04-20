package com.phasmidsoftware.dsaipg.projects.mcts.game2048;

import com.phasmidsoftware.dsaipg.projects.mcts.core.Game;
import com.phasmidsoftware.dsaipg.projects.mcts.core.State;

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