package game2048;

import javax.swing.*;

public class Game2048Runner {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            WelcomeScreen2048 welcome = new WelcomeScreen2048();
            welcome.setVisible(true);
        });
    }
}