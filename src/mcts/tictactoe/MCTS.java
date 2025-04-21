/*
 * Copyright (c) 2024. Robin Hillyard
 */

package tictactoe;

import core.Node;
import core.State;

import java.util.Collection;
import java.util.Optional;
import java.util.Random;

/**
 * Class to represent a Monte Carlo Tree Search for TicTacToe with difficulty levels.
 */
public class MCTS {

    public static void main(String[] args) {
        // You can change difficulty here: 0 = Easy, 1 = Medium, 2 = Hard
        int difficulty = 2;

        int simulations = switch (difficulty) {
            case 0 -> 5;
            case 1 -> 100;
            case 2 -> 500;
            default -> 100;
        };

        MCTS mcts = new MCTS(new TicTacToeNode(new TicTacToe().new TicTacToeState()), simulations);
        Node<TicTacToe> bestMove = mcts.runSearch();
        System.out.println("Best move state:\n" + bestMove.state());

        // Uncomment to run benchmark
        // mcts.runBenchmark(100, difficulty);
    }

    private final Node<TicTacToe> root;
    private final int simulations;

    public MCTS(Node<TicTacToe> root, int simulations) {
        this.root = root;
        this.simulations = simulations;
    }

    public Node<TicTacToe> runSearch() {
        for (int i = 0; i < simulations; i++) {
            simulateFrom(root);
        }
        return bestChild(root);
    }

    /**
     * Run a benchmark to evaluate AI performance.
     *
     * @param trials          number of games to simulate
     * @param difficultyLevel 0 = Easy, 1 = Medium, 2 = Hard
     */
    public void runBenchmark(int trials, int difficultyLevel) {
        int wins = 0;
        int losses = 0;
        int draws = 0;

        for (int i = 0; i < trials; i++) {
            TicTacToe game = new TicTacToe();
            State<TicTacToe> state = game.start();
            int player = game.opener();

            while (!state.isTerminal()) {
                Node<TicTacToe> node = new TicTacToeNode(state);

                int sims = switch (difficultyLevel) {
                    case 0 -> 5;
                    case 1 -> 100;
                    case 2 -> 500;
                    default -> 100;
                };

                MCTS mcts = new MCTS(node, sims);
                Node<TicTacToe> bestNode = mcts.runSearch();
                state = bestNode.state();
                player = 1 - player;
            }

            Optional<Integer> result = state.winner();
            if (result.isEmpty()) {
                draws++;
            } else if (result.get() == game.opener()) {
                wins++;
            } else {
                losses++;
            }
        }

        System.out.printf("MCTS Benchmark over %d games [Difficulty: %s]\n", trials, switch (difficultyLevel) {
            case 0 -> "Easy";
            case 1 -> "Medium";
            case 2 -> "Hard";
            default -> "Unknown";
        });
        System.out.printf("Wins: %d | Losses: %d | Draws: %d\n", wins, losses, draws);
    }

    /**
     * Run one simulation iteration.
     */
    private void simulateFrom(Node<TicTacToe> node) {
        if (node.isLeaf()) return;

        Collection<Node<TicTacToe>> children = node.children();
        if (children.isEmpty()) {
            State<TicTacToe> state = node.state();
            for (var move : state.moves(state.player())) {
                State<TicTacToe> newState = state.next(move);
                TicTacToeNode newNode = new TicTacToeNode(newState);

                // Use heuristic scoring only for HARD mode (simulations >= 500)
                if (simulations >= 500 && newState instanceof TicTacToe.TicTacToeState) {
                    TicTacToe.TicTacToeState ts = (TicTacToe.TicTacToeState) newState;
                    if (ts.position() instanceof Position) {
                        Position pos = (Position) ts.position();
                        int score = pos.evaluate(state.player());
                        newNode.setPlayouts(1);
                        newNode.setWins(score);
                    }
                }

                node.addChild(newState);
            }
        }

        Node<TicTacToe> child = randomChild(node);
        simulateFrom(child);
        node.backPropagate();
    }


    /**
     * Select a random child node from the given node.
     */
    private Node<TicTacToe> randomChild(Node<TicTacToe> node) {
        int index = new Random().nextInt(node.children().size());
        return node.children().stream().skip(index).findFirst().orElseThrow();
    }

    /**
     * Select the child node with the highest win count.
     */
    private Node<TicTacToe> bestChild(Node<TicTacToe> node) {
        return node.children().stream()
                .max((a, b) -> Integer.compare(a.wins(), b.wins()))
                .orElseThrow();
    }
}
