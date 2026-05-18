package gamemasters;
import model.IRiddle;
import model.RiddleImpl;

import java.util.Arrays;
import java.util.List;

public class Hayes extends GameMaster {
    private static final List<IRiddle> RIDDLE_POOL = Arrays.asList(
            new RiddleImpl("I bind the prisoner tight, link by link. Break one, and I am still strong. What am I?", "chain", "Hint: Made of iron rings."),
            new RiddleImpl("I have a tongue but cannot talk. I have a sole but cannot walk. What am I?", "shoe", "Hint: On your feet."),
            new RiddleImpl("What has a head and a tail but no body?", "coin", "Hint: Flip for luck."),
            new RiddleImpl("I can be long or short, I can be grown or bought. What am I?", "hair", "Hint: On your head."),
            new RiddleImpl("What has a neck but no head?", "bottle", "Hint: Holds potions."),
            new RiddleImpl("I have keys but open no locks. What am I?", "piano", "Hint: Makes music."),
            new RiddleImpl("What can you hold in your right hand but never in your left?", "your left hand", "Hint: Try it."),
            new RiddleImpl("I have a bed but never sleep. What am I?", "river", "Hint: Flows through the dungeon."),
            new RiddleImpl("What has an eye but cannot see?", "needle", "Hint: Used for sewing."),
            new RiddleImpl("I am taken from a mine and shut in a wooden case. What am I?", "pencil", "Hint: Used for writing.")
    );

    public Hayes() {
        super("Hayes", RIDDLE_POOL);
    }

    @Override
    public String greet() {
        return "Hayes points to the door. 'Speak the truth to pass.'";
    }
}
