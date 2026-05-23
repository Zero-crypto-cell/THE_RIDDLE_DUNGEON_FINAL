package model;

import gamemasters.GameMaster;
import javax.swing.JOptionPane;

public class Room {
    // === Room Identity ===
    private final int roomNumber;
    private final String difficultyLabel;
    private final GameMaster gameMaster;

    // === State ===
    private boolean isSolved;

    public Room(int roomNumber, String difficultyLabel, GameMaster gameMaster) {
        this.roomNumber = roomNumber;
        this.difficultyLabel = difficultyLabel;
        this.gameMaster = gameMaster;
        this.isSolved = false;
    }

    // === Check Answer ===
    public boolean confirmAnswer(String playerAnswer) {
        if (playerAnswer == null || playerAnswer.trim().isEmpty()) {
            return false;
        }

        String input = playerAnswer.trim().toLowerCase();
        String correct = gameMaster.getRiddle().getAnswer().trim().toLowerCase();

        if (input.equals(correct)) {
            this.isSolved = true;
            return true;
        }
        return false;
    }

    // === Show Key Reward Popup ===
    public void awardKeyReward() {
        // Call this ONLY after checkAnswer() returns true
        JOptionPane.showMessageDialog(
                null,
                "🗝️ Congratulations!\n\nYou obtained a Key!",
                "Chest Opened!",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    // === Getters ===
    public boolean isSolved() { return isSolved; }
    public int getRoomNumber() { return roomNumber; }
    public String getDifficultyLabel() { return difficultyLabel; }
    public String getGreeting() { return gameMaster.greet(); }
    public String getRiddleQuestion() { return gameMaster.getRiddle().getQuestion(); }
    public String getRiddleHint() { return gameMaster.getRiddle().getHint(); }
}