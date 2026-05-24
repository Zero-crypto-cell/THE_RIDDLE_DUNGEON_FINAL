package panels;

import mains.Application;
import model.GameManager;
import model.Player;
import model.Room;
import gamemasters.Awit;
import gamemasters.Deanver;
import gamemasters.Kirby;
import gamemasters.Jojan;
import gamemasters.Hayes;
import gamemasters.Patrick;


import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    // Fields MUST match Component Tree exactly
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

    private Application application;
    private GameManager game;
    private Player player;
    private Room[] rooms;
    private boolean[] completedRooms;
    private boolean isProcessingAnswer = false;

    // Navigation callback to return to menu
    private Runnable onReturnToMenu;

    public GamePanel(Application application, GameManager game) {
        this.application = application;
        this.game = game;
        initializeGame();
    }

    public void updateGameInstance(GameManager game) {
        if (game != null) {
            this.game = game;
            this.player = game.getPlayer();
        }
        loadLevel();
    }

    // Setter for navigation callback
    public void setOnReturnToMenu(Runnable callback) {
        this.onReturnToMenu = callback;
    }

    public void initializeGame() {
        setBackground(new Color(255, 167, 30));
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(900, 650));

        if (gamePanel == null) {
            setupFallbackUI();
        } else {
            gamePanel.setBackground(new Color(255, 167, 30));
            add(gamePanel, BorderLayout.CENTER);
        }

        player = new Player();
        completedRooms = new boolean[6];
        createRooms();
        setupListeners();
        loadLevel();

        revalidate();
        repaint();
    }

    private void setupFallbackUI() {
        gamePanel = new JPanel(new BorderLayout(15, 15));
        gamePanel.setBackground(new Color(255, 167, 30));
        gamePanel.setBorder(BorderFactory.createEmptyBorder(24, 36, 24, 36));

        title = new JLabel("PUZZLE TIME", SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 34));
        title.setForeground(new Color(43, 45, 48));
        gamePanel.add(title, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new BorderLayout(12, 12));
        centerPanel.setOpaque(false);

        JPanel statusPanel = new JPanel(new BorderLayout());
        statusPanel.setOpaque(false);
        lblHearts = new JLabel();
        lblHearts.setFont(new Font("SansSerif", Font.BOLD, 20));
        lblRoomInfo = new JLabel("", SwingConstants.RIGHT);
        lblRoomInfo.setFont(new Font("SansSerif", Font.BOLD, 16));
        statusPanel.add(lblHearts, BorderLayout.WEST);
        statusPanel.add(lblRoomInfo, BorderLayout.EAST);
        centerPanel.add(statusPanel, BorderLayout.NORTH);

        txtRiddle = new JTextArea();
        txtRiddle.setEditable(false);
        txtRiddle.setLineWrap(true);
        txtRiddle.setWrapStyleWord(true);
        txtRiddle.setFont(new Font("Serif", Font.PLAIN, 20));
        txtRiddle.setForeground(Color.WHITE);
        txtRiddle.setBackground(new Color(43, 45, 48));
        txtRiddle.setBorder(BorderFactory.createEmptyBorder(18, 18, 18, 18));
        centerPanel.add(new JScrollPane(txtRiddle), BorderLayout.CENTER);

        lblHint = new JTextArea();
        lblHint.setEditable(false);
        lblHint.setLineWrap(true);
        lblHint.setWrapStyleWord(true);
        lblHint.setFont(new Font("SansSerif", Font.PLAIN, 16));
        lblHint.setForeground(Color.ORANGE);
        lblHint.setBackground(new Color(43, 45, 48));
        lblHint.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        lblHint.setPreferredSize(new Dimension(200, 90));
        centerPanel.add(lblHint, BorderLayout.SOUTH);

        gamePanel.add(centerPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout(12, 0));
        bottomPanel.setOpaque(false);
        answer = new JLabel("Answer:");
        answer.setFont(new Font("SansSerif", Font.BOLD, 16));
        txtInput = new JTextField();
        txtInput.setFont(new Font("SansSerif", Font.PLAIN, 16));
        SOLVEButton = new JButton("SOLVE");
        Map = new JButton("MAP");
        btnSave = new JButton("Save");

        JPanel inputPanel = new JPanel(new BorderLayout(8, 0));
        inputPanel.setOpaque(false);
        inputPanel.add(answer, BorderLayout.WEST);
        inputPanel.add(txtInput, BorderLayout.CENTER);
        inputPanel.add(SOLVEButton, BorderLayout.EAST);

        JPanel actionPanel = new JPanel(new GridLayout(1, 2, 8, 0));
        actionPanel.setOpaque(false);
        actionPanel.add(Map);
        actionPanel.add(btnSave);

        bottomPanel.add(inputPanel, BorderLayout.CENTER);
        bottomPanel.add(actionPanel, BorderLayout.EAST);
        gamePanel.add(bottomPanel, BorderLayout.SOUTH);

        add(gamePanel, BorderLayout.CENTER);
    }

    private void setupListeners() {
        if (SOLVEButton != null) SOLVEButton.addActionListener(e -> checkAnswer());
        if (txtInput != null) txtInput.addActionListener(e -> checkAnswer());
        if (Map != null) Map.addActionListener(e -> {
            if (application != null) {
                application.showMap();
            }
        });
        if (btnSave != null) {
            btnSave.setText("Save");
            btnSave.addActionListener(e -> {
                if (game != null) {
                    game.saveProgress();
                    JOptionPane.showMessageDialog(this, "Game saved.");
                }
            });
        }

    }

    private int countCompletedRooms() {
        int count = 0;
        for (boolean b : completedRooms) if (b) count++;
        return count;
    }

    private void createRooms() {
        rooms = new Room[6];
        rooms[0] = new Room(1, "Easy", new Kirby());
        rooms[1] = new Room(2, "Easy", new Deanver());
        rooms[2] = new Room(3, "Medium", new Jojan());
        rooms[3] = new Room(4, "Medium", new Hayes());
        rooms[4] = new Room(5, "Hard", new Awit());
        rooms[5] = new Room(6, "Hard", new Patrick());
    }

    public void loadLevel() {
        if (player.hasWon()) { showEndScreen(true); return; }
        if (!player.isAlive()) { showEndScreen(false); return; }

        Room current = rooms[player.getCurrentRoomIndex()];
        player.adjustHeartsForRoom(current.getRoomNumber());

        StringBuilder hearts = new StringBuilder();
        for (int i = 0; i < player.getHearts(); i++) hearts.append("❤️");
        if (lblHearts != null) lblHearts.setText(hearts.toString());

        if (lblRoomInfo != null) lblRoomInfo.setText("Room " + current.getRoomNumber() + " | " + current.getDifficultyLabel());
        if (txtRiddle != null) txtRiddle.setText(current.getGreeting() + "\n\n" + current.getRiddleQuestion());
        if (lblHint != null) {
            lblHint.setText(current.getRiddleHint());
            lblHint.setForeground(Color.ORANGE);
            lblHint.setBackground(new Color(43, 45, 48));
        }
        if (txtInput != null) { txtInput.setText(""); txtInput.requestFocus(); }

        isProcessingAnswer = false;
        revalidate(); repaint();
    }

    //changed by patrick
    private void checkAnswer() {
        if (isProcessingAnswer || player == null || player.hasWon() || !player.isAlive()) return;
        isProcessingAnswer = true;

        // 2. Lock inputs immediately so the user can't spam input during timers
        if (txtInput != null) txtInput.setEnabled(false);
        if (SOLVEButton != null) SOLVEButton.setEnabled(false);

        Room current = rooms[player.getCurrentRoomIndex()];
        String ans = txtInput.getText() != null ? txtInput.getText().trim() : "";

        if (current.attemptAnswer(ans)) {
            completedRooms[player.getCurrentRoomIndex()] = true;
            if (game != null) {
                game.completeRoom(player.getCurrentRoomIndex());
            }
            if (lblHint != null) {
                lblHint.setText("✅ CORRECT! The door unlocks...");
                lblHint.setForeground(Color.GREEN);
            }
            Timer t = new Timer(1500, e -> {
                player.nextRoom();
                if (txtInput != null) txtInput.setEnabled(true);
                if (SOLVEButton != null) SOLVEButton.setEnabled(true);
                loadLevel();
            });
            t.setRepeats(false);
            t.start();
        } else {
            player.loseHeart();
            if (lblHint != null) {
                lblHint.setText("❌ WRONG! You lose a heart.");
                lblHint.setForeground(Color.RED);
            }
            StringBuilder hearts = new StringBuilder();
            for (int i = 0; i < player.getHearts(); i++) hearts.append("❤️");
            if (lblHearts != null) lblHearts.setText(hearts.toString());
            if (!player.isAlive()) {
                Timer t = new Timer(1500, e -> {
                    isProcessingAnswer = false; // FIX: Allows state machine to reset on respawn/end screen
                    if (txtInput != null) txtInput.setEnabled(true);
                    if (SOLVEButton != null) SOLVEButton.setEnabled(true);
                    loadLevel();
                });
                t.setRepeats(false);
                t.start();
            } else {
                Timer t = new Timer(1200, e -> {
                    if (lblHint != null) {
                        lblHint.setForeground(Color.ORANGE);
                        lblHint.setText(current.getRiddleHint());
                    }
                    // Re-enable inputs now that penalty timer is done
                    if (txtInput != null) txtInput.setEnabled(true);
                    if (SOLVEButton != null) SOLVEButton.setEnabled(true);
                    isProcessingAnswer = false;
                });
                t.setRepeats(false);
                t.start();
            }
        }
    }
    // Fixed: Uses JDialog instead of removeAll() to preserve form bindings
    private void showEndScreen(boolean won) {
        // Get the parent window
        Window parentWindow = SwingUtilities.getWindowAncestor(this);

        // Create dialog with correct constructor
        JDialog dialog = new JDialog(parentWindow, "Game Over", Dialog.ModalityType.APPLICATION_MODAL);

        dialog.setLayout(new GridBagLayout());
        dialog.getContentPane().setBackground(new Color(15, 15, 20));
        dialog.setUndecorated(true);
        dialog.getRootPane().setBorder(BorderFactory.createLineBorder(new Color(212, 175, 55), 2));

        JLabel msg = new JLabel(won ? "🏆 ESCAPED!" : "💀 YOU DIED");
        msg.setFont(new Font("Serif", Font.BOLD, 36));
        msg.setForeground(won ? Color.YELLOW : Color.RED);

        JLabel sub = new JLabel(won ? "You conquered the dungeon!" : "The dungeon claims another soul.");
        sub.setFont(new Font("SansSerif", Font.PLAIN, 16));
        sub.setForeground(Color.WHITE);

        JButton restart = new JButton("Return to Menu");
        restart.setFont(new Font("SansSerif", Font.BOLD, 14));
        restart.setForeground(new Color(212, 175, 55));
        restart.setBackground(new Color(0, 0, 0, 80));
        restart.setBorder(BorderFactory.createLineBorder(new Color(212, 175, 55), 2));
        restart.setFocusPainted(false);
        restart.setContentAreaFilled(false);
        restart.setCursor(new Cursor(Cursor.HAND_CURSOR));

        restart.addActionListener(e -> {
            dialog.dispose();
            if (onReturnToMenu != null) {
                onReturnToMenu.run();
            }
        });

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 30, 15, 30);
        gbc.gridy = 0; dialog.add(msg, gbc);
        gbc.gridy = 1; dialog.add(sub, gbc);
        gbc.gridy = 2; dialog.add(restart, gbc);

        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

}
