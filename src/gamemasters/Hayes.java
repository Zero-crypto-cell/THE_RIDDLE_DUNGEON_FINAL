package gamemasters;
import model.IRiddle;
import model.RiddleImpl;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.IntStream;

public class Hayes extends GameMaster {
    private static final List<IRiddle> RIDDLE_POOL = IntStream.rangeClosed(1, 10)
            .mapToObj(i -> new RiddleImpl(getEnv("HAYES_RIDDLE_" + i), getEnv("HAYES_ANSWER_" + i), getEnv("HAYES_HINT_" + i)))
            .map(r -> (IRiddle) r)
            .toList();

    public Hayes() {
        super("Hayes", RIDDLE_POOL);
    }

    @Override
    public String greet() {
        return "Hayes steps out of the shadows with a riddle...";
    }
    @Override
    public void startGame() {}
}
