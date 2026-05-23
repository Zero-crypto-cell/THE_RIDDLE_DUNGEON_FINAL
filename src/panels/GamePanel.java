package panels;

import model.GameManager;
import util.FileManager;
import gamemasters.GameMaster;
import mains.Application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GamePanel extends JPanel {
    // Form Bound Fields (Must match IntelliJ Form if used, otherwise fallback applies)
    private JPanel gamePanel;
    private JLabel title;
    private JLabel lblHearts;
    private JTextArea txtRiddle;
    private JLabel lblRoomInfo;
    private JLabel answer;
    private JButton SOLVEButton;
    private JTextField txtInput;
    private JTextArea lblHint;
    private JButton Map;
    private JButton btnSave;

    private Application mainApp;
    private GameManager game;
    private CardLayout cardLayout;
    private JPanel contentContainer;
    private MastersPanel dialogPanel;

    private boolean isProcessing = false;

    public GamePanel(Application app, GameManager game) {
        this.mainApp = app;
        this.game = game;
        initializeUI();
    }

    private void initializeUI() {
        setBackground(new Color(43, 45, 48));
        setLayout(new BorderLayout());

        // Card Layout Container
        cardLayout = new CardLayout();
        contentContainer = new JPanel(cardLayout);
        contentContainer.setOpaque(false);

        // 1. Main Game UI (Form Bound or Fallback)
        if (gamePanel == null) {
            createFallbackUI();
        } else {
            gamePanel.setBackground(new Color(43, 45, 48));
        }
        contentContainer.add(gamePanel != null ? gamePanel : this, "GAME");

        // 2. Game Master Dialog
        dialogPanel = new MastersPanel(() -> {
            cardLayout.show(contentContainer, "GAME");
            loadLevel();
        });
        contentContainer.add(dialogPanel, "DIALOG");

        add(contentContainer, BorderLayout.CENTER);
        setupListeners();
        showGameMasterIntro();
    }

    private void styleButton(JButton btn) {
        btn.setFont(new Font("SansSerif", Font.BOLD, 14));
        btn.setForeground(new Color(212, 175, 55));
        btn.setBackground(new Color(0, 0, 0, 80));
        btn.setBorder(BorderFactory.createLineBorder(new Color(212, 175, 55), 2));
        btn.setFocusPainted(false);
        btn.setContentAreaFilled(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private void createFallbackUI() {
        gamePanel = new JPanel(new BorderLayout());
        gamePanel.setOpaque(false);

        JPanel top = new JPanel(new BorderLayout());
        top.setOpaque(false);
        title = new JLabel("The Riddle Dungeon", SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 28));
        title.setForeground(new Color(212, 175, 55));
        top.add(title, BorderLayout.NORTH);

        JPanel infoRow = new JPanel(new BorderLayout());
        infoRow.setOpaque(false);
        lblHearts = new JLabel("❤️❤️❤️", SwingConstants.LEFT);
        lblHearts.setFont(new Font("SansSerif", Font.BOLD, 20));
        lblHearts.setForeground(Color.RED);
        lblRoomInfo = new JLabel("Room 1 | Easy", SwingConstants.RIGHT);
        lblRoomInfo.setFont(new Font("SansSerif", Font.PLAIN, 16));
        lblRoomInfo.setForeground(Color.WHITE);
        infoRow.add(lblHearts, BorderLayout.WEST);
        infoRow.add(lblRoomInfo, BorderLayout.EAST);
        top.add(infoRow, BorderLayout.SOUTH);
        gamePanel.add(top, BorderLayout.NORTH);

        txtRiddle = new JTextArea("Welcome...");
        txtRiddle.setEditable(false);
        txtRiddle.setLineWrap(true);
        txtRiddle.setFont(new Font("Serif", Font.ITALIC, 18));
        txtRiddle.setBackground(new Color(30, 30, 40));
        txtRiddle.setForeground(Color.CYAN);
        gamePanel.add(new JScrollPane(txtRiddle), BorderLayout.CENTER);

        JPanel bottom = new JPanel(new BorderLayout(10, 10));
        bottom.setOpaque(false);
        JPanel inputRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
        inputRow.setOpaque(false);
        answer = new JLabel("Answer:");
        answer.setForeground(Color.WHITE);
        txtInput = new JTextField(20);
        SOLVEButton = new JButton("SOLVE");
        inputRow.add(answer);
        inputRow.add(txtInput);
        inputRow.add(SOLVEButton);
        bottom.add(inputRow, BorderLayout.NORTH);

        lblHint = new JTextArea("Hint...");
        lblHint.setEditable(false);
        lblHint.setForeground(Color.ORANGE);
        lblHint.setBackground(new Color(43, 45, 48));
        bottom.add(lblHint, BorderLayout.CENTER);

        Map = new JButton("🗺️ MAP");
        styleButton(Map);

        btnSave = new JButton("💾 SAVE");
        styleButton(btnSave);

        JPanel btns = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btns.setOpaque(false);
        btns.add(btnSave);
        btns.add(Map);
        bottom.add(btns, BorderLayout.SOUTH);

        gamePanel.add(bottom, BorderLayout.SOUTH);
    }

    private void setupListeners() {
        if (SOLVEButton != null) SOLVEButton.addActionListener(e -> checkAnswer());
        if (txtInput != null) txtInput.addActionListener(e -> checkAnswer());

        if (Map != null) Map.addActionListener(e -> mainApp.showMap());

        if (btnSave != null) {
            btnSave.addActionListener(e -> {
                game.saveProgress();
                JOptionPane.showMessageDialog(this, "Game Saved!", "Success", JOptionPane.INFORMATION_MESSAGE);
            });
        }
    }

    private void showGameMasterIntro() {
        int roomIdx = game.getCurrentRoomIndex();
        if (roomIdx >= 6) return;

        String[] masters = {"Kirby", "Deanver", "Jojan", "Hayes", "Awit", "Patrick"};
        String masterName = masters[roomIdx];

        // Get greeting from .env or default
        String greeting = "I am " + masterName + ". Solve my riddle.";

        dialogPanel.setupMaster(masterName, greeting);
        cardLayout.show(contentContainer, "DIALOG");
    }

    public void updateGameInstance(GameManager newGame) {
        this.game = newGame;
        showGameMasterIntro();
    }

    private void loadLevel() {
        if (game.getCurrentRoomIndex() >= 6) {
            JOptionPane.showMessageDialog(this, "YOU ESCAPED!");
            mainApp.showMainMenu();
            return;
        }

        if (!game.getPlayer().isAlive()) {
            // Show Game Over Popup with Return to Menu
            int choice = JOptionPane.showOptionDialog(this,
                    "GAME OVER\nYou have run out of hearts.",
                    "Defeat",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.ERROR_MESSAGE,
                    null,
                    new String[]{"Return to Main Menu"},
                    "Return to Main Menu");

            // Regardless of choice, go back to menu
            mainApp.showMainMenu();
            return;
        }

        // Update Hearts
        StringBuilder hearts = new StringBuilder();
        for (int i = 0; i < game.getPlayer().getHearts(); i++) hearts.append("❤️");
        if (lblHearts != null) lblHearts.setText(hearts.toString());

        // Get Riddle from .env via GameMaster static helper
        int roomIdx = game.getCurrentRoomIndex();
        String[] masters = {"Kirby", "Deanver", "Jojan", "Hayes", "Awit", "Patrick"};
        String master = masters[roomIdx];

        String q = game.getRandomRiddle(master, 1);
        String h = game.getRandomRiddle(master, 3);

        if (lblRoomInfo != null) lblRoomInfo.setText("Room " + (roomIdx + 1));
        if (txtRiddle != null) txtRiddle.setText(q);
        if (lblHint != null) {
            lblHint.setText(h);
            lblHint.setForeground(Color.ORANGE);
        }
        if (txtInput != null) {
            txtInput.setText("");
            txtInput.requestFocus();
        }

        isProcessing = false;
        revalidate(); repaint();
    }

    private void checkAnswer() {
        if (isProcessing) return;
        isProcessing = true;

        int roomIdx = game.getCurrentRoomIndex();
        String[] masters = {"Kirby", "Deanver", "Jojan", "Hayes", "Awit", "Patrick"};

        // Get correct answer from .env and normalize it
        String correctAnsRaw = game.getRandomRiddle(masters[roomIdx], 2);
        String correctAns = correctAnsRaw.toLowerCase().trim();

        String playerAns = txtInput.getText().toLowerCase().trim();

        if (playerAns.equals(correctAns)) {
            // ✅ CORRECT ANSWER LOGIC (No Timer)
            game.completeRoom(roomIdx);
            if (lblHint != null) {
                lblHint.setText("✅ CORRECT!");
                lblHint.setForeground(Color.GREEN);
            }

            // Immediately move to next room/intro
            game.nextRoom(); // Triggers heart warning if needed
            game.saveProgress();
            showGameMasterIntro();

            isProcessing = false;

        } else {
            // ❌ WRONG ANSWER LOGIC (No Timer)
            game.loseHeart();
            if (lblHint != null) {
                lblHint.setText("❌ WRONG!");
                lblHint.setForeground(Color.RED);
            }

            // Update hearts display immediately
            StringBuilder hearts = new StringBuilder();
            for (int i = 0; i < game.getPlayer().getHearts(); i++) hearts.append("❤️");
            if (lblHearts != null) lblHearts.setText(hearts.toString());

            if (!game.getPlayer().isAlive()) {
                // Immediately trigger Game Overflow
                loadLevel();
            } else {
                // Immediately reset hint to original hint for the current room
                if (lblHint != null) {
                    lblHint.setForeground(Color.ORANGE);
                    lblHint.setText(game.getRandomRiddle(masters[roomIdx], 3));
                }
                isProcessing = false;
            }
        }
    }
}