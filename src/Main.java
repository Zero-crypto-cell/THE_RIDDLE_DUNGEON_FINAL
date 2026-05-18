import panels.GamePanel;
import panels.mainMenuPanel;
import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("The Riddle Dungeon");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(950, 700);
            frame.setLocationRelativeTo(null);

            JPanel cardPanel = new JPanel(new CardLayout());
            mainMenuPanel menu = new mainMenuPanel();
            GamePanel game = new GamePanel();

            // 🔑 CRITICAL FIX: Tell GamePanel how to return to the menu
            game.setOnReturnToMenu(() -> {
                CardLayout cl = (CardLayout) cardPanel.getLayout();
                cl.show(cardPanel, "MENU");
            });

            cardPanel.add(menu, "MENU");
            cardPanel.add(game, "GAME");
            frame.add(cardPanel);
            frame.setVisible(true);

            CardLayout cl = (CardLayout) cardPanel.getLayout();

            menu.getNewGame().addActionListener(e -> {
                game.initializeGame();
                cl.show(cardPanel, "GAME");
            });

            menu.getLoadGame().addActionListener(e -> {
                game.initializeGame();
                cl.show(cardPanel, "GAME");
            });

            menu.getNewGame().addActionListener(e -> {
                game.initializeGame(); // Resets player and creates new rooms
                cl.show(cardPanel, "GAME");
            });

            menu.getExit().addActionListener(e -> System.exit(0));
        });
    }
}