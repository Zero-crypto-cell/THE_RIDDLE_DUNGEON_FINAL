package gamemasters;
import model.IRiddle;
import model.RiddleImpl;

import java.util.Arrays;
import java.util.List;

public class Kirby extends GameMaster {
    private static final List<IRiddle> RIDDLE_POOL = Arrays.asList(
            new RiddleImpl("I have a head but no face, and a bed but never sleep. I have teeth but cannot bite. What am I?", "key", "Hint: You use me to open the door behind me."),
            new RiddleImpl("What has a lock but no door?", "lock of hair", "Hint: Found on your head."),
            new RiddleImpl("I open paths but have no feet. What am I?", "key", "Hint: Fits in a lock."),
            new RiddleImpl("What has many teeth but cannot bite?", "comb", "Hint: Used for hair."),
            new RiddleImpl("I guard treasures but have no sword. What am I?", "lock", "Hint: Needs a key."),
            new RiddleImpl("What has a ring but no finger?", "key", "Hint: Opens doors."),
            new RiddleImpl("I have a spine but no bones. What am I?", "book", "Hint: Holds knowledge."),
            new RiddleImpl("What has pages but no face?", "book", "Hint: You read me."),
            new RiddleImpl("I light the way but have no eyes. What am I?", "torch", "Hint: Found in dungeons."),
            new RiddleImpl("What has a handle but no door?", "sword", "Hint: A warrior's tool.")
    );

    public Kirby() {
        super("Kirby", RIDDLE_POOL);
    }

    @Override
    public String greet() {
        return "Kirby blocks the path! 'Let's play a game!'";
    }
}
