package panels;

import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class mainMenuPanel extends JPanel {

    private BufferedImage backgroundImg;
    private JLabel title;
    private JButton newGame;
    private JButton loadGame;
    private JButton Exit;

    public mainMenuPanel() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(900, 650));
        loadBackgroundImage();
        setupUI();
    }

    private void loadBackgroundImage() {
        try {
            File imageFile = new File("src/images/mainmenu.jpg");
            backgroundImg = ImageIO.read(imageFile);
        } catch (IOException e) {
            System.out.println("⚠️ Could not load background image. Ensure file exists.");
            backgroundImg = null;
        }
    }

    private void setupUI() {
        setOpaque(false);

        // Style the title
        if (title != null) {
            title.setText("THE RIDDLE DUNGEON");
            title.setFont(new Font("Serif", Font.BOLD, 36));
            title.setForeground(new Color(212, 175, 55)); // Gold
            title.setHorizontalAlignment(SwingConstants.CENTER);
        }

        // Button panel with GridLayout
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        buttonPanel.setOpaque(false);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(50, 300, 50, 300));

        // Style New Game button
        if (newGame != null) {
            styleButton(newGame);
            buttonPanel.add(newGame);
        }

        // Style Load Game button
        if (loadGame != null) {
            styleButton(loadGame);
            buttonPanel.add(loadGame);
        }

        // Style Exit button
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
        button.setForeground(new Color(212, 175, 55)); // Gold text
        button.setBackground(new Color(0, 0, 0, 100));  // Semi-transparent black
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

    public JButton getNewGame() { return newGame; }
    public JButton getLoadGame() { return loadGame; }
    public JButton getExit() { return Exit; }
}