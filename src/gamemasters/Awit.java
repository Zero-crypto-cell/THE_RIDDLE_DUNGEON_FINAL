package gamemasters;
import model.IRiddle;
import model.RiddleImpl;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.IntStream;
    public class Awit extends GameMaster {
    private static final List<IRiddle> RIDDLE_POOL = IntStream.rangeClosed(1, 10)
            .mapToObj(i -> new RiddleImpl(getEnv("AWIT_RIDDLE_" + i), getEnv("AWIT_ANSWER_" + i), getEnv("AWIT_HINT_" + i)))
            .map(r -> (IRiddle) r)
            .toList();
    public Awit() {
        super("Awit", RIDDLE_POOL);
    }

    @Override
    public String greet() {
        return "Awit whispers, 'Listen closely to my riddle...'";
    }
    @Override
    public void startGame() {}
}
