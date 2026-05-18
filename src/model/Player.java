package model;

public class Player {
    private int hearts;
    private int currentRoomIndex;

    public Player() {
        this.hearts = 3; // Start with 3 hearts for Easy difficulty
        this.currentRoomIndex = 0;
    }

    // Adjusts hearts when entering Room 3 (Medium) or Room 5 (Hard)
    public void adjustHeartsForRoom(int roomNumber) {
        if (roomNumber == 3 || roomNumber == 5) {
            this.hearts++;
        }
    }

    public boolean loseHeart() {
        this.hearts--;
        return this.hearts > 0;
    }

    public int getHearts() {
        return hearts;
    }

    public boolean isAlive() {
        return hearts > 0;
    }

    public void nextRoom() {
        this.currentRoomIndex++;
    }

    public int getCurrentRoomIndex() {
        return currentRoomIndex;
    }

    public boolean hasWon() {
        return currentRoomIndex >= 6;
    }

    public void setCurrentRoomIndex(int index) {
        this.currentRoomIndex = index;
    }

    public void setHearts(int hearts) {
        this.hearts = hearts;
    }
}