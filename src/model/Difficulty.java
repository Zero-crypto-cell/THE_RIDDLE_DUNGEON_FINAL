package model;

public enum Difficulty {
    EASY(3),
    MEDIUM(4),
    HARD(5);

    private final int startingHearts;

    Difficulty(int hearts) {
        this.startingHearts = hearts;
    }

    public int getStartingHearts() {
        return startingHearts;
    }
}
