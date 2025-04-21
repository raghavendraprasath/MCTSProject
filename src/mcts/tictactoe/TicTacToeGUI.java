package tictactoe;

import core.Node;
import core.State;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class TicTacToeGUI extends JFrame {
    private JButton[][] buttons = new JButton[3][3];
    private Position position;
    private int currentPlayer = TicTacToe.X;
    private String player1Name, player2Name;
    private boolean isMultiplayer;
    private int player1Score = 0, player2Score = 0;
    private JLabel scoreLabel;
    private JComboBox<String> difficultyBox;
    private JCheckBox darkModeCheckBox;
    private Map<String, Integer> leaderboard;
    private final Color WIN_COLOR = new Color(144, 238, 144);
    private final Color DARK_BG = new Color(30, 30, 30);
    private final Color DARK_BTN = new Color(60, 60, 60);

    public TicTacToeGUI(String player1, String player2, boolean isMultiplayer, Map<String, Integer> leaderboard) {
        this.player1Name = player1;
        this.player2Name = player2;
        this.isMultiplayer = isMultiplayer;
        this.leaderboard = leaderboard;
        position = Position.parsePosition(". . .\n. . .\n. . .", -1);

        setTitle("TicTacToe with MCTS");
        setSize(500, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        topPanel.add(new JLabel("Select Difficulty:"));
        difficultyBox = new JComboBox<>(new String[]{"Easy", "Medium", "Hard"});
        topPanel.add(difficultyBox);

        scoreLabel = new JLabel();
        updateScoreLabel();
        topPanel.add(scoreLabel);

        darkModeCheckBox = new JCheckBox("Dark Mode");
        darkModeCheckBox.addActionListener(e -> applyTheme());
        topPanel.add(darkModeCheckBox);

        add(topPanel, BorderLayout.NORTH);

        JPanel boardPanel = new JPanel(new GridLayout(3, 3));
        Font btnFont = new Font("Arial", Font.BOLD, 36);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                final int row = i;
                final int col = j;
                JButton btn = new JButton();
                btn.setFont(btnFont);
                btn.addActionListener(e -> handleMove(row, col));
                buttons[i][j] = btn;
                boardPanel.add(btn);
            }
        }
        add(boardPanel, BorderLayout.CENTER);
        applyTheme();
    }

    private void applyTheme() {
        boolean dark = darkModeCheckBox.isSelected();
        Color bg = dark ? DARK_BG : Color.WHITE;
        Color fg = Color.BLACK;
        Color btnBg = dark ? DARK_BTN : Color.WHITE;

        getContentPane().setBackground(bg);
        scoreLabel.setForeground(fg);

        for (JButton[] row : buttons) {
            for (JButton btn : row) {
                btn.setBackground(btnBg);
                btn.setForeground(fg);
            }
        }
    }

    private void handleMove(int row, int col) {
        if (position.full() || position.winner().isPresent()) return;
        try {
            position = position.move(currentPlayer, row, col);
            updateBoard();

            if (position.winner().isPresent()) {
                String winner = currentPlayer == TicTacToe.X ? player1Name : player2Name;
                if (currentPlayer == TicTacToe.X) player1Score++;
                else player2Score++;
                leaderboard.put(winner, leaderboard.getOrDefault(winner, 0) + 1);
                updateScoreLabel();
                highlightWinningLine();
                showMessage(winner + " wins!");
                return;
            } else if (position.full()) {
                showMessage("It's a draw!");
                return;
            }

            currentPlayer = 1 - currentPlayer;

            if (!isMultiplayer) {
                runMCTS();
                currentPlayer = 1 - currentPlayer;
            }
        } catch (RuntimeException ex) {
            JOptionPane.showMessageDialog(this, "Invalid move", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void runMCTS() {
        State<TicTacToe> state = new TicTacToe().new TicTacToeState(position);
        Node<TicTacToe> root = new TicTacToeNode(state);

        int sims = switch (difficultyBox.getSelectedIndex()) {
            case 0 -> 5;
            case 1 -> 100;
            case 2 -> 500;
            default -> 100;
        };

        long startTime = System.nanoTime();
        MCTS mcts = new MCTS(root, sims);
        Node<TicTacToe> best = mcts.runSearch();
        long endTime = System.nanoTime();

        // Benchmark Output
        System.out.println("MCTS Benchmarking:");
        System.out.println("  Difficulty: " + difficultyBox.getSelectedItem());
        System.out.println("  Simulations (Rollouts): " + sims);
        System.out.println("  Time taken: " + (endTime - startTime) / 1_000_000.0 + " ms");
        System.out.println("  Best move leads to position:\n" + ((TicTacToe.TicTacToeState) best.state()).position());

        position = ((TicTacToe.TicTacToeState) best.state()).position();
        updateBoard();

        if (position.winner().isPresent()) {
            player2Score++;
            leaderboard.put(player2Name, leaderboard.getOrDefault(player2Name, 0) + 1);
            updateScoreLabel();
            highlightWinningLine();
            showMessage(player2Name + " wins!");
        } else if (position.full()) {
            showMessage("It's a draw!");
        }
    }


    private void updateBoard() {
        for (int i = 0; i < 3; i++) {
            int[] row = position.projectRow(i);
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText(row[j] == TicTacToe.X ? "X" : row[j] == TicTacToe.O ? "O" : "");
            }
        }
    }

    private void updateScoreLabel() {
        scoreLabel.setText(player1Name + ": " + player1Score + " | " + player2Name + ": " + player2Score);
    }

    private void showMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg);
        int option = JOptionPane.showConfirmDialog(this, "Play again?", "Restart", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            position = Position.parsePosition(". . .\n. . .\n. . .", -1);
            currentPlayer = TicTacToe.X;
            updateBoard();
        } else {
            dispose();
            new WelcomeScreen().setVisible(true);
        }
    }

    private void highlightWinningLine() {
        int[][] grid = new int[3][3];
        for (int i = 0; i < 3; i++) grid[i] = position.projectRow(i);
        int last = position.getLastPlayer();

        for (int i = 0; i < 3; i++) {
            if (grid[i][0] == last && grid[i][1] == last && grid[i][2] == last) {
                for (int j = 0; j < 3; j++) buttons[i][j].setBackground(WIN_COLOR);
                return;
            }
            if (grid[0][i] == last && grid[1][i] == last && grid[2][i] == last) {
                for (int j = 0; j < 3; j++) buttons[j][i].setBackground(WIN_COLOR);
                return;
            }
        }
        if (grid[0][0] == last && grid[1][1] == last && grid[2][2] == last) {
            buttons[0][0].setBackground(WIN_COLOR);
            buttons[1][1].setBackground(WIN_COLOR);
            buttons[2][2].setBackground(WIN_COLOR);
        } else if (grid[0][2] == last && grid[1][1] == last && grid[2][0] == last) {
            buttons[0][2].setBackground(WIN_COLOR);
            buttons[1][1].setBackground(WIN_COLOR);
            buttons[2][0].setBackground(WIN_COLOR);
        }
    }
}
