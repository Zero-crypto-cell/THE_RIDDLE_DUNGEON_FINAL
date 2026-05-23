package model;

import util.FileManager;

import java.io.Serializable;
import java.util.Random;
import javax.swing.JOptionPane;

public class GameManager implements Serializable {
    private static final long serialVersionUID = 1L;
    private static GameManager instance;

    private Player player;
    private int currentRoomIndex;
    private boolean[] completedRooms;
    private Random random;

    private GameManager() {
        this.random = new Random();
        resetGame();
    }

    public static synchronized GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    public void resetGame() {
        this.player = new Player();
        this.currentRoomIndex = 0;
        this.completedRooms = new boolean[6];
    }

    public Player getPlayer() { return player; }
    public int getCurrentRoomIndex() { return currentRoomIndex; }
    public boolean[] getCompletedRooms() { return completedRooms; }

    public void nextRoom() {
        // Heart Warning Logic: Check BEFORE incrementing room index
        if (player.getHearts() == 1) {
            JOptionPane.showMessageDialog(null,
                    "⚠️ WARNING: You are down to your last heart! One more mistake and you die.",
                    "Low Health Warning",
                    JOptionPane.WARNING_MESSAGE);
        }
        this.currentRoomIndex++;
    }

    public void completeRoom(int index) {
        if (index >= 0 && index < 6) {
            completedRooms[index] = true;
        }
    }

    public void loseHeart() {
        player.loseHeart();
        if (!player.isAlive()) {
            FileManager.deleteSaveFile(); // Delete save on death
        }
    }

    public void saveProgress() {
        FileManager.saveGame(this);
    }

    public static GameManager loadProgress() {
        GameManager loaded = FileManager.loadGame();
        if (loaded != null) {
            instance = loaded;
        }
        return loaded;
    }

    public String getRandomRiddle(String gamemasterName, int type) {
        int riddleNum = random.nextInt(10) + 1;
        String prefix = gamemasterName.toUpperCase() + "_";
        String suffix = type == 1 ? "RIDDLE_" : (type == 2 ? "ANSWER_" : "HINT_");

        String key = prefix + suffix + riddleNum;
        String val = gamemasters.GameMaster.getEnv(key);

        // Clean hints
        if (type == 3 && val.toLowerCase().startsWith("hint:")) {
            val = val.substring(5).trim();
        }
        return val;
    }
}