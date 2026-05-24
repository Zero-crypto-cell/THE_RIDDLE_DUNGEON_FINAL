package mains;

import panels.mainMenuPanel;
import panels.GamePanel;
import panels.MapPanel;
import model.GameManager;

import javax.swing.*;
import java.awt.*;

public class Application extends JFrame {
    private GameManager game;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    // Panel references (Only existing ones)
    private mainMenuPanel mainMenuPanel;
    private GamePanel gamePanel;
    private MapPanel mapPanel;

    public Application() {
        game = GameManager.getInstance();
        initializeGUI();
        showMainMenu();
    }

    private void initializeGUI() {
        setTitle("The Riddle Dungeon");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        //initialize panels with the current instance
        mainMenuPanel = new mainMenuPanel(this,game);
        gamePanel = new GamePanel(this,game);
        mapPanel = new MapPanel(this,game);
        gamePanel.setOnReturnToMenu(this::showMainMenu);

        mainPanel.add(mainMenuPanel, "MENU");
        mainPanel.add(gamePanel, "GAME");
        mainPanel.add(mapPanel, "MAP");

        setContentPane(mainPanel);
    }

    // --- Navigation Methods ---

    public void showMainMenu() {
        // Check for saved game
        GameManager saved = GameManager.loadProgress();
        if (saved != null) {
            game = saved;
        } else {
            game = GameManager.getInstance();
            game.resetGame();
        }

        mainMenuPanel.updateGameInstance(game);
        cardLayout.show(mainPanel, "MENU");
        mainMenuPanel.refresh();
    }

    public void showGame() {

        cardLayout.show(mainPanel, "GAME");
        if(gamePanel != null){
            gamePanel.updateGameInstance(game);
        }
    }

    public void setGame(GameManager game) {
        if (game != null) {
            this.game = game;
            mainMenuPanel.updateGameInstance(game);
            gamePanel.updateGameInstance(game);
        }
    }

    public void showMap() {
        cardLayout.show(mainPanel, "MAP");
        mapPanel.refresh();
    }

    // --- Utility Methods ---

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public boolean showConfirmDialog(String message) {
        int result = JOptionPane.showConfirmDialog(this, message, "Confirmation",
                JOptionPane.YES_NO_OPTION);
        return result == JOptionPane.YES_OPTION;
    }
}
