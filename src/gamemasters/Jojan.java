package gamemasters;
import model.IRiddle;
import model.RiddleImpl;

import java.util.Arrays;
import java.util.List;

public class Jojan extends GameMaster {
    private static final List<IRiddle> RIDDLE_POOL = Arrays.asList(
            new RiddleImpl("I have no voice, but I can tell you everything. I have no ears, but I hear you. What am I?", "echo", "Hint: Shout in a hallway."),
            new RiddleImpl("The more of me you take, the more you leave behind. What am I?", "footsteps", "Hint: Walking leaves me."),
            new RiddleImpl("I can be cracked, made, told, and played. What am I?", "joke", "Hint: Makes you laugh."),
            new RiddleImpl("What has a heart that doesn't beat?", "deck of cards", "Hint: Used for games."),
            new RiddleImpl("I am not alive, but I grow. I don't have lungs, but I need air. What am I?", "fire", "Hint: Lights the darkness."),
            new RiddleImpl("What can you break, even if you never pick it up or touch it?", "promise", "Hint: Words can break it."),
            new RiddleImpl("I have cities, but no houses. I have forests, but no trees. What am I?", "map", "Hint: Shows the way."),
            new RiddleImpl("What has a ring but no finger?", "phone", "Hint: It rings."),
            new RiddleImpl("I am full of holes but still holds water. What am I?", "sponge", "Hint: Used for cleaning."),
            new RiddleImpl("What goes up but never comes down?", "age", "Hint: Time passes.")
    );

    public Jojan() {
        super("Jojan", RIDDLE_POOL);
    }

    @Override
    public String greet() {
        return "Jojan smiles mysteriously. 'A simple question for you.'";
    }
}
