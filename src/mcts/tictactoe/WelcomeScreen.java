package tictactoe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

public class WelcomeScreen extends JFrame {
    private JTextField player1Field;
    private JTextField player2Field;

    public WelcomeScreen() {
        setTitle("TicTacToe with MCTS");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Welcome to Tic Tac Toe", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setOpaque(true);
        titleLabel.setBackground(new Color(30, 144, 255));
        titleLabel.setForeground(Color.WHITE);
        add(titleLabel, BorderLayout.NORTH);

        JPanel inputPanel = new JPanel(new GridLayout(4, 1));
        JLabel p1Label = new JLabel("Enter Player 1 Name:");
        player1Field = new JTextField();
        JLabel p2Label = new JLabel("Enter Player 2 Name (Optional for AI):");
        player2Field = new JTextField();
        inputPanel.add(p1Label);
        inputPanel.add(player1Field);
        inputPanel.add(p2Label);
        inputPanel.add(player2Field);
        add(inputPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        JButton playAIButton = new JButton("Play vs Computer");
        playAIButton.setBackground(Color.GREEN);
        playAIButton.addActionListener(this::startVsAI);

        JButton playMultiplayerButton = new JButton("Multiplayer");
        playMultiplayerButton.setBackground(Color.YELLOW);
        playMultiplayerButton.addActionListener(this::startMultiplayer);

        buttonPanel.add(playAIButton);
        buttonPanel.add(playMultiplayerButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void startVsAI(ActionEvent e) {
        String player1 = player1Field.getText().trim();
        if (player1.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter Player 1 name");
            return;
        }
        Map<String, Integer> leaderboard = new HashMap<>();
        new TicTacToeGUI(player1, "Computer", false, leaderboard).setVisible(true);
        dispose();
    }

    private void startMultiplayer(ActionEvent e) {
        String player1 = player1Field.getText().trim();
        String player2 = player2Field.getText().trim();
        if (player1.isEmpty() || player2.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both names for multiplayer mode");
            return;
        }
        Map<String, Integer> leaderboard = new HashMap<>();
        new TicTacToeGUI(player1, player2, true, leaderboard).setVisible(true);
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new WelcomeScreen().setVisible(true));
    }
}