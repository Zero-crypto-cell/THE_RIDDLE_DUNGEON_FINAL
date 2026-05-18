package gamemasters;
import model.IRiddle;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public abstract class GameMaster {
    private final String name;
    private final List<IRiddle> riddlePool;
    private IRiddle currentRiddle;
    private static final Random random = new Random();

    public GameMaster(String name, List<IRiddle> riddlePool) {
        this.name = name;
        this.riddlePool = new ArrayList<>(riddlePool);
        selectRandomRiddle();
    }

    private void selectRandomRiddle() {
        if (!riddlePool.isEmpty()) {
            this.currentRiddle = riddlePool.get(random.nextInt(riddlePool.size()));
        }
    }

    public String getName() { return name; }
    public IRiddle getRiddle() { return currentRiddle; }

    // Re-roll to get a different riddle from the pool
    public void rerollRiddle() {
        selectRandomRiddle();
    }

    public abstract String greet();
}