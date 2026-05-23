package util;

import model.GameManager;

import java.io.*;
import javax.swing.JOptionPane;

public class FileManager {
    private static final String SAVE_FILE = "save.dat";

    /**
     * Saves the current GameManager state to disk.
     */
    public static void saveGame(GameManager game) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVE_FILE))) {
            oos.writeObject(game);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Failed to save game: " + e.getMessage(),
                    "Save Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Loads game state from disk. Returns null if file doesn't exist or is corrupted.
     */
    public static GameManager loadGame() {
        File file = new File(SAVE_FILE);
        if (!file.exists()) return null;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (GameManager) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null,
                    "Save file corrupted or unreadable. Starting new game.",
                    "Load Error", JOptionPane.WARNING_MESSAGE);
            return null;
        }
    }

    /**
     * Deletes the save file (used when player dies).
     */
    public static void deleteSaveFile() {
        File file = new File(SAVE_FILE);
        if (file.exists()) {
            file.delete();
        }
    }
}