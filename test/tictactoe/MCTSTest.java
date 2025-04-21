package tictactoe;

import core.Node;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MCTSTest {

    @Test
    public void testEasyDifficulty() {
        TicTacToeNode root = new TicTacToeNode(new TicTacToe().new TicTacToeState());
        MCTS mcts = new MCTS(root, 5); // Easy difficulty
        Node<TicTacToe> result = mcts.runSearch();
        assertNotNull(result);
        System.out.println("Easy mode best move:\n" + result.state());
    }

    @Test
    public void testMediumDifficulty() {
        TicTacToeNode root = new TicTacToeNode(new TicTacToe().new TicTacToeState());
        MCTS mcts = new MCTS(root, 100); // Medium difficulty
        Node<TicTacToe> result = mcts.runSearch();
        assertNotNull(result);
        System.out.println("Medium mode best move:\n" + result.state());
    }

    @Test
    public void testHardDifficultyWithHeuristics() {
        TicTacToeNode root = new TicTacToeNode(new TicTacToe().new TicTacToeState());
        MCTS mcts = new MCTS(root, 500); // Hard difficulty triggers heuristics
        Node<TicTacToe> result = mcts.runSearch();
        assertNotNull(result);
        System.out.println("Hard mode (heuristic) best move:\n" + result.state());
    }

    @Test
    public void testBenchmarkRuns() {
        MCTS mcts = new MCTS(new TicTacToeNode(new TicTacToe().new TicTacToeState()), 100);
        mcts.runBenchmark(5, 2); // Run 5 trials at Hard difficulty
    }
}
