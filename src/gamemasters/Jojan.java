package gamemasters;
import model.IRiddle;
import model.RiddleImpl;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.IntStream;

public class Jojan extends GameMaster {
    private static final List<IRiddle> RIDDLE_POOL = IntStream.rangeClosed(1, 10)
            .mapToObj(i -> new RiddleImpl(getEnv("JOJAN_RIDDLE_" + i), getEnv("JOJAN_ANSWER_" + i), getEnv("JOJAN_HINT_" + i)))
            .map(r -> (IRiddle) r)
            .toList();

    public Jojan() {
        super("Jojan", RIDDLE_POOL);
    }

    @Override
    public String greet() {
        return "Jojan speaks in riddles...";
    }
    @Override
    public void startGame() {}
}

