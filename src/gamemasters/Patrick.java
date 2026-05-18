package gamemasters;
import model.IRiddle;
import model.RiddleImpl;

import java.util.Arrays;
import java.util.List;

public class Patrick extends GameMaster {
    private static final List<IRiddle> RIDDLE_POOL = Arrays.asList(
            new RiddleImpl("I am the end of the dungeon, but the start of freedom. I am heavy wood and iron bars. What am I?", "gate", "Hint: It stands between you and the outside world."),
            new RiddleImpl("I have a spine but no bones, pages but no face. I hold secrets of the dungeon. What am I?", "spellbook", "Hint: Wizards use me."),
            new RiddleImpl("What has a crown but no head, a trunk but no body?", "tree", "Hint: Found in the forest."),
            new RiddleImpl("I can be cracked, I can be made, I can be told, I can be played. What am I?", "joke", "Hint: Makes you laugh."),
            new RiddleImpl("The more you take, the more you leave behind. What am I?", "footsteps", "Hint: Walking leaves me."),
            new RiddleImpl("I have a mouth but cannot speak, I have a bed but cannot sleep. What am I?", "river", "Hint: Flows through the dungeon."),
            new RiddleImpl("What has an eye but cannot see, a tongue but cannot taste?", "needle", "Hint: Used for sewing."),
            new RiddleImpl("I am always in front of you but cannot be seen. What am I?", "future", "Hint: What's to come."),
            new RiddleImpl("What can you catch but not throw, that grows in the dungeon's glow?", "cold", "Hint: You might get this."),
            new RiddleImpl("I have cities but no houses, forests but no trees, rivers but no water. What am I?", "map", "Hint: Look at the wall.")
    );

    public Patrick() {
        super("Patrick", RIDDLE_POOL);
    }

    @Override
    public String greet() {
        return "Patrick stands firm. 'Only one answer unlocks this chain.'";
    }
}
