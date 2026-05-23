package gamemasters;
import model.IRiddle;
import model.RiddleImpl;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.IntStream;

public class Patrick extends GameMaster {
    private static final List<IRiddle> RIDDLE_POOL = IntStream.rangeClosed(1, 10)
            .mapToObj(i -> new RiddleImpl(getEnv("PATRICK_RIDDLE_" + i), getEnv("PATRICK_ANSWER_" + i), getEnv("PATRICK_HINT_" + i)))
            .map(r -> (IRiddle) r)
            .toList();

    public Patrick() {
        super("Patrick", RIDDLE_POOL);
    }

    @Override
    public String greet() {
        return "Patrick smiles and presents a riddle...";
    }

    @Override
    public void startGame() {}
}
