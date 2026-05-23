package gamemasters;

import model.IRiddle;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;

public abstract class GameMaster {
    private final String name;
    private final List<IRiddle> riddlePool;
    private IRiddle currentRiddle;
    private static final Random random = new Random();

    // Static block to load .env once
    protected static final Properties env = new Properties();
    static {
        try(FileReader r = new FileReader(".env")){
            env.load(r);
        }catch (IOException ignored){
            // Silently fail if .env is missing
        }
    }

    public GameMaster(String name, List<IRiddle> riddlePool) {
        this.name = name;
        this.riddlePool = new ArrayList<>(riddlePool);
        selectRandomRiddle();
    }

    /**
     * FIXED: Robustly cleans quotes and whitespace from .env values
     */
    public static String getEnv(String key){
        String value = env.getProperty(key);
        if(value != null){
            value = value.trim();
            // Remove surrounding double quotes if they exist
            if(value.startsWith("\"") && value.endsWith("\"")){
                value = value.substring(1, value.length() - 1);
            }
            return value.trim(); // Final trim to remove any internal padding
        }
        return "";
    }

    private void selectRandomRiddle(){
        if(!riddlePool.isEmpty()){
            this.currentRiddle = riddlePool.get(random.nextInt(riddlePool.size()));
        }
    }

    public String getName() { return name; }
    public IRiddle getRiddle() { return currentRiddle; }
    public void rerollRiddle() { selectRandomRiddle(); }
    public abstract String greet();
    public abstract void startGame();
}