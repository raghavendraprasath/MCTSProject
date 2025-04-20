package com.phasmidsoftware.dsaipg.projects.mcts.game2048;

import com.phasmidsoftware.dsaipg.projects.mcts.core.*;

import java.util.Random;

public class MCTS2048 {
    private final Game<Game2048> game;

    public MCTS2048(Game<Game2048> game) {
        this.game = game;
    }

    public Move2048 bestMove(State<Game2048> state, int simulations) {
        Random random = new Random();
        Move2048 best = null;
        double bestScore = Double.NEGATIVE_INFINITY;

        for (Move2048 move : Move2048.values()) {
            double totalScore = 0;

            for (int i = 0; i < simulations; i++) {
                State<Game2048> sim = ((State2048) state).applyMove(move);
                totalScore += ((State2048) sim).evaluate();
            }

            if (totalScore > bestScore) {
                bestScore = totalScore;
                best = move;
            }
        }

        return best;
    }
}
