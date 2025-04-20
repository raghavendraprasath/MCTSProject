package com.phasmidsoftware.dsaipg.projects.mcts.game2048;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeScreen2048 extends JFrame {
    public WelcomeScreen2048() {
        setTitle("Welcome to 2048 - MCTS Edition");
        setSize(400, 300);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel title = new JLabel("2048", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 32));
        add(title, BorderLayout.NORTH);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        inputPanel.add(new JLabel("Enter your name:"));
        JTextField nameField = new JTextField(20);
        inputPanel.add(nameField);
        add(inputPanel, BorderLayout.CENTER);

        JButton startButton = new JButton("Start Game");
        add(startButton, BorderLayout.SOUTH);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText().trim();
                if (!name.isEmpty()) {
                    dispose();
                    new Game2048GUI(name);
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a name.");
                }
            }
        });
    }
}
