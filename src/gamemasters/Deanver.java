package gamemasters;
import model.IRiddle;
import model.RiddleImpl;

import java.util.Arrays;
import java.util.List;

public class Deanver extends GameMaster {
    private static final List<IRiddle> RIDDLE_POOL = Arrays.asList(
            new RiddleImpl("The more you take from me, the bigger I get. What am I?", "hole", "Hint: Digging in the dungeon floor creates me."),
            new RiddleImpl("I have cities but no houses. I have mountains but no trees. I have water but no fish. What am I?", "map", "Hint: Look at the wall."),
            new RiddleImpl("What can you catch but not throw?", "cold", "Hint: You might get this in a damp dungeon."),
            new RiddleImpl("I speak without a mouth and hear without ears. What am I?", "echo", "Hint: Shout and I'll repeat."),
            new RiddleImpl("What has hands but cannot clap?", "clock", "Hint: Tells time on the wall."),
            new RiddleImpl("I have a neck but no head. What am I?", "bottle", "Hint: Holds potions."),
            new RiddleImpl("What gets wet while drying?", "towel", "Hint: After a bath."),
            new RiddleImpl("I have a face but no eyes. What am I?", "clock", "Hint: Has numbers."),
            new RiddleImpl("What can travel around the world while staying in a corner?", "stamp", "Hint: On a letter."),
            new RiddleImpl("I have a tail but no body. What am I?", "coin", "Hint: Flip me for luck.")
    );

    public Deanver() {
        super("Deanver", RIDDLE_POOL);
    }

    @Override
    public String greet() {
        return "Deanver crosses his arms. 'Prove your intellect.'";
    }
}