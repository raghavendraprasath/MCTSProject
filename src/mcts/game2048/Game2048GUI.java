package game2048;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Game2048GUI extends JFrame {
    private final String playerName;
    private final JLabel[][] tiles = new JLabel[4][4];
    private final JLabel scoreLabel;
    private State2048 state;
    private final MCTS2048 mcts;
    private int moveCount = 0;
    private long totalTime = 0;

    public Game2048GUI(String playerName) {
        this.playerName = playerName;
        this.state = new State2048();
        this.mcts = new MCTS2048(new Game2048());
        this.scoreLabel = new JLabel("Score: 0");
        setupUI();
        updateGrid();

        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                boolean moved = false;
                long startTime = System.nanoTime();

                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP -> moved = state.moveUp();
                    case KeyEvent.VK_DOWN -> moved = state.moveDown();
                    case KeyEvent.VK_LEFT -> moved = state.moveLeft();
                    case KeyEvent.VK_RIGHT -> moved = state.moveRight();
                }

                if (moved) {
                    moveCount++;
                    long endTime = System.nanoTime();
                    totalTime += (endTime - startTime);
                    state.spawnTile();
                    updateGrid();

                    if (state.isTerminal()) {
                        printPerformanceSummary();
                        JOptionPane.showMessageDialog(null, "Game Over! Highest tile: " + state.getMaxTile());
                    }
                }
            }
        });

        setFocusable(true);
        setVisible(true);
    }

    private void setupUI() {
        setTitle("2048 Game - " + playerName);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 500);
        setLayout(new BorderLayout());

        JPanel gridPanel = new JPanel(new GridLayout(4, 4));
        gridPanel.setBackground(Color.DARK_GRAY);
        Font font = new Font("Arial", Font.BOLD, 32);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                tiles[i][j] = new JLabel("", SwingConstants.CENTER);
                tiles[i][j].setFont(font);
                tiles[i][j].setOpaque(true);
                tiles[i][j].setBackground(Color.LIGHT_GRAY);
                gridPanel.add(tiles[i][j]);
            }
        }

        scoreLabel.setFont(new Font("Arial", Font.BOLD, 18));
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(scoreLabel, BorderLayout.NORTH);
        add(gridPanel, BorderLayout.CENTER);
    }

    private void updateGrid() {
        int[][] grid = state.getGrid();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int val = grid[i][j];
                tiles[i][j].setText(val == 0 ? "" : String.valueOf(val));
                tiles[i][j].setBackground(getTileColor(val));
            }
        }
        scoreLabel.setText("Score: " + (int) state.evaluate());
    }

    private Color getTileColor(int value) {
        return switch (value) {
            case 2 -> new Color(0xeee4da);
            case 4 -> new Color(0xede0c8);
            case 8 -> new Color(0xf2b179);
            case 16 -> new Color(0xf59563);
            case 32 -> new Color(0xf67c5f);
            case 64 -> new Color(0xf65e3b);
            case 128 -> new Color(0xedcf72);
            case 256 -> new Color(0xedcc61);
            case 512 -> new Color(0xedc850);
            case 1024 -> new Color(0xedc53f);
            case 2048 -> new Color(0xedc22e);
            default -> Color.LIGHT_GRAY;
        };
    }

    private void printPerformanceSummary() {
        int maxTile = state.getMaxTile();
        double score = state.evaluate();
        double avgTimeMs = (totalTime / 1_000_000.0) / moveCount;

        System.out.println("-------- GAME OVER --------");
        System.out.println("Player: " + playerName);
        System.out.println("Moves made: " + moveCount);
        System.out.printf("Average Time Per Move: %.2f ms%n", avgTimeMs);
        System.out.println("Final Score (Evaluation): " + score);
        System.out.println("Max Tile: " + maxTile);
        System.out.println("---------------------------");
    }
}
