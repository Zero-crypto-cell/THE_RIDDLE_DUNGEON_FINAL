// Room.java
package model;
import gamemasters.GameMaster;

public class Room {
    private final int roomNumber;
    private final String difficultyLabel; // "Easy", "Medium", "Hard"
    private final GameMaster gameMaster;

    private boolean isLocked;
    private boolean visited;
    private boolean solved;

    private int attempts;

//    private String description;
//    private String reward;

    public Room(int roomNumber, String difficultyLabel, GameMaster gameMaster) {
        this.roomNumber = roomNumber;
        this.difficultyLabel = difficultyLabel;
        this.gameMaster = gameMaster;

//        this.description = description;
//        this.reward = reward;

        this.isLocked = true;
        this.visited = false;
        this.solved = false;
        this.attempts = 0;
    }

    // Check player's answer
    public boolean attemptAnswer(String playerAnswer) {

        if (playerAnswer == null) {
            return false;
        }

        attempts++;

        String playerInput = playerAnswer.trim().toLowerCase();
        String correctAnswer = gameMaster.getRiddle()
                .getAnswer()
                .trim()
                .toLowerCase();

        if (playerInput.equals(correctAnswer)) {
            solved = true;
            unlock();
            return true;
        }

        return false;
    }

    // Unlock room
    public void unlock() {
        this.isLocked = false;
    }

    // Mark room as visited
    public void visit() {
        this.visited = true;
    }

    // Show hint after several failed attempts
    public boolean canShowHint() {
        return attempts >= 3;
    }

    // Getters
    public boolean isLocked() {
        return isLocked;
    }

    public boolean isVisited() {
        return visited;
    }

    public boolean isSolved() {
        return solved;
    }

    public int getAttempts() {
        return attempts;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getDifficultyLabel() {
        return difficultyLabel;
    }

//    public String getDescription() {
//        return description;
//    }
//
//    public String getReward() {
//        return reward;
//    }

    public String getMasterName() {
        return gameMaster.getName();
    }

    public String getGreeting() {
        return gameMaster.greet();
    }

    public String getRiddleQuestion() {
        return gameMaster.getRiddle().getQuestion();
    }

    public String getRiddleHint() {
        return gameMaster.getRiddle().getHint();
    }

}