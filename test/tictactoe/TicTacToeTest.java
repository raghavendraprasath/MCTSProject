package com.phasmidsoftware.dsaipg.projects.mcts.tictactoe;

import com.phasmidsoftware.dsaipg.projects.mcts.core.Node;
import com.phasmidsoftware.dsaipg.projects.mcts.core.State;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Optional;

public class TicTacToeTest {

    @Test
    public void runGame() {
        long seed = 0L;
        TicTacToe target = new TicTacToe(seed); // games run here will all be deterministic.
        State<TicTacToe> state = target.runGame();
        Optional<Integer> winner = state.winner();
        if (winner.isPresent()) assertEquals(Integer.valueOf(TicTacToe.X), winner.get());
        else fail("no winner");
    }

    /**
     * Additional: Verify a different seed produces a valid state with a winner or draw.
     */
    @Test
    public void testRunGameWithDifferentSeed() {
        long seed = 42L;
        TicTacToe target = new TicTacToe(seed);
        State<TicTacToe> state = target.runGame();
        assertTrue(state.isTerminal());
        assertNotNull(state.toString());
    }

    /**
     * Additional: Test Hard mode MCTS outcome from known initial state.
     */
    @Test
    public void testHardModeMCTSOutput() {
        TicTacToe game = new TicTacToe(99L);
        State<TicTacToe> state = game.start();

        Node<TicTacToe> root = new TicTacToeNode(state);
        MCTS mcts = new MCTS(root, 500); // hard mode with heuristics
        Node<TicTacToe> result = mcts.runSearch();

        assertNotNull(result);
        assertNotNull(result.state());
        assertFalse(result.state().isTerminal()); // Should not be terminal after 1 move
    }
}