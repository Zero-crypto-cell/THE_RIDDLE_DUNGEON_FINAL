package panels;

import javax.swing.*;
import java.awt.*;

public class MapPanel extends JPanel {

    // Fields MUST match Component Tree exactly
    private JPanel mapPanel;
    private JLabel title;
    private JButton room1;
    private JButton room2;
    private JButton room3;
    private JButton room4;
    private JButton room5;
    private JButton room6;

    private JButton[] roomButtons;

    public MapPanel() {
        // Initialize array for easier looping
        roomButtons = new JButton[]{room1, room2, room3, room4, room5, room6};

                if (title == null) {
            setupFallbackUI();
        } else {
            setupFormUI();
        }
    }

    private void setupFallbackUI() {
        setLayout(new BorderLayout(0, 15));
        setBackground(new Color(43, 45, 48));
        setBorder(BorderFactory.createLineBorder(new Color(212, 175, 55), 2));
        setPreferredSize(new Dimension(300, 500));

        title = new JLabel("DUNGEON MAP", SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 24));
        title.setForeground(new Color(212, 175, 55));
        add(title, BorderLayout.NORTH);

        JPanel btnContainer = new JPanel(new GridLayout(6, 1, 0, 10));
        btnContainer.setOpaque(false);
        btnContainer.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        roomButtons = new JButton[6];
        for (int i = 0; i < 6; i++) {
            roomButtons[i] = new JButton("ROOM " + (i + 1));
            roomButtons[i].setFont(new Font("Serif", Font.BOLD, 16));
            roomButtons[i].setFocusPainted(false);
            btnContainer.add(roomButtons[i]);
        }
        add(btnContainer, BorderLayout.CENTER);
    }

    private void setupFormUI() {
        // Style the title from the form
        if (title != null) {
            title.setFont(new Font("Serif", Font.BOLD, 24));
            title.setForeground(new Color(212, 175, 55));
            title.setHorizontalAlignment(SwingConstants.CENTER);
        }


        for (JButton btn : roomButtons) {
            if (btn != null) {
                btn.setFont(new Font("Serif", Font.BOLD, 16));
                btn.setFocusPainted(false);
                btn.setContentAreaFilled(false);
            }
        }
    }

    /**
     * Call this method to refresh the map based on game progress
     */
    public void updateMap(boolean[] completed, int currentRoomIndex) {
        for (int i = 0; i < 6; i++) {
            JButton btn = roomButtons[i];
            if (btn == null) continue;

            boolean isCurrent = (i == currentRoomIndex);
            boolean isCompleted = completed[i];

            // Reset styles
            btn.setBorder(null);
            btn.setForeground(Color.WHITE);

            if (isCompleted) {
                // COMPLETED ROOM
                btn.setText("✓ ROOM " + (i + 1));
                btn.setForeground(new Color(212, 175, 55)); // Gold Text
                btn.setBorder(BorderFactory.createLineBorder(new Color(212, 175, 55), 2));
            } else if (isCurrent) {
                // CURRENT ROOM
                btn.setText(" ROOM " + (i + 1));
                btn.setForeground(new Color(212, 175, 55)); // Gold Text
                btn.setBorder(BorderFactory.createLineBorder(new Color(212, 175, 55), 2));
            } else {
                // LOCKED ROOM
                btn.setText("X ROOM " + (i + 1));
                btn.setForeground(new Color(100, 100, 100)); // Grey Text
                btn.setBorder(BorderFactory.createLineBorder(new Color(60, 60, 60), 1));
            }
        }

        // Refresh UI
        revalidate();
        repaint();
    }
}