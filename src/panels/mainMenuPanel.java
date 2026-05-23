package panels;

import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import mains.Application;
import model.GameManager;

public class mainMenuPanel extends JPanel {

    private BufferedImage backgroundImg;
    private JLabel title;
    private JButton newGame;
    private JButton loadGame;
    private JButton Exit;
    private Application mainApp;
    private GameManager game;

    public mainMenuPanel(Application app, GameManager game) {
        this.mainApp = app;
        this.game = game;
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(900, 650));
        loadBackgroundImage();
        setupUI();
    }

    private void loadBackgroundImage() {
        try {
            File imageFile = new File("src/mainmenu.jpg");
            backgroundImg = ImageIO.read(imageFile);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Could not load background image. Please ensure 'src/mainmenu.jpg' exists.",
                    "Image Load Error", JOptionPane.WARNING_MESSAGE);
            backgroundImg = null;
        }
    }

    private void setupUI() {
        setOpaque(false);

        if (title != null) {
            title.setText("THE RIDDLE DUNGEON");
            title.setFont(new Font("Serif", Font.BOLD, 36));
            title.setForeground(new Color(212, 175, 55));
            title.setHorizontalAlignment(SwingConstants.CENTER);
        }

        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        buttonPanel.setOpaque(false);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(50, 300, 50, 300));

        if (newGame != null) {
            styleButton(newGame);
            newGame.addActionListener(e -> mainApp.showGame());
            buttonPanel.add(newGame);
        }

        if (loadGame != null) {
            styleButton(loadGame);
            loadGame.addActionListener(e -> {
                if (util.FileManager.loadGame() != null) {
                    mainApp.showGame();
                } else {
                    JOptionPane.showMessageDialog(this, "No save file found.");
                }
            });
            buttonPanel.add(loadGame);
        }

        if (Exit != null) {
            styleButton(Exit);
            Exit.addActionListener(e -> System.exit(0));
            buttonPanel.add(Exit);
        }

        if (title != null) add(title, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("Serif", Font.BOLD, 18));
        button.setForeground(new Color(212, 175, 55));
        button.setBackground(new Color(0, 0, 0, 100));
        button.setBorder(BorderFactory.createLineBorder(new Color(212, 175, 55), 2));
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(300, 50));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        if (backgroundImg != null) {
            g2d.drawImage(backgroundImg, 0, 0, getWidth(), getHeight(), this);
        } else {
            g2d.setColor(new Color(20, 20, 30));
            g2d.fillRect(0, 0, getWidth(), getHeight());
        }
    }

    public void refresh() {
        // Re-apply styles or update dynamic content if needed
    }

    public JButton getNewGame() { return newGame; }
    public JButton getLoadGame() { return loadGame; }
    public JButton getExit() { return Exit; }
}