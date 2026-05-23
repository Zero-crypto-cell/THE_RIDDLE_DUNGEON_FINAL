package panels;

import javax.swing.*;
import java.awt.*;

public class MastersPanel extends JPanel {
    private JLabel lblMasterName;
    private JTextArea txtDialogue;
    private JButton btnStartRiddle;
    private Runnable onStartRiddle;

    public MastersPanel(Runnable onStartRiddle) {
        this.onStartRiddle = onStartRiddle;
        setLayout(new BorderLayout());
        setBackground(new Color(20, 20, 25));
        setBorder(BorderFactory.createLineBorder(new Color(212, 175, 55), 2));

        initializeUI();
    }

    private void initializeUI() {
        // Top: Master Name
        lblMasterName = new JLabel("", SwingConstants.CENTER);
        lblMasterName.setFont(new Font("Serif", Font.BOLD, 32));
        lblMasterName.setForeground(new Color(212, 175, 55));
        add(lblMasterName, BorderLayout.NORTH);

        // Center: Dialogue/Riddle Intro
        txtDialogue = new JTextArea();
        txtDialogue.setEditable(false);
        txtDialogue.setLineWrap(true);
        txtDialogue.setWrapStyleWord(true);
        txtDialogue.setFont(new Font("Serif", Font.ITALIC, 18));
        txtDialogue.setBackground(new Color(30, 30, 40));
        txtDialogue.setForeground(Color.WHITE);
        txtDialogue.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(new JScrollPane(txtDialogue), BorderLayout.CENTER);

        // Bottom: Button
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnPanel.setOpaque(false);
        btnStartRiddle = new JButton("I am Ready");
        btnStartRiddle.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnStartRiddle.addActionListener(e -> {
            if (onStartRiddle != null) onStartRiddle.run();
        });
        btnPanel.add(btnStartRiddle);
        add(btnPanel, BorderLayout.SOUTH);
    }

    public void setupMaster(String name, String greeting) {
        lblMasterName.setText(name);
        txtDialogue.setText(greeting + "\n\nSolve my riddle to pass.");
    }
}