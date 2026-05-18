package gamemasters;
import model.IRiddle;
import model.RiddleImpl;

import java.util.Arrays;
import java.util.List;

public class Awit extends GameMaster {
    private static final List<IRiddle> RIDDLE_POOL = Arrays.asList(
            new RiddleImpl("I follow you in the light, but disappear in the dark. I grow tall when the candle is low. What am I?", "shadow", "Hint: Cast by your body."),
            new RiddleImpl("The person who makes it has no need of it; the person who buys it has no use for it. The person who uses it can neither see nor feel it. What am I?", "coffin", "Hint: Final resting place."),
            new RiddleImpl("I am not alive, but I can die. What am I?", "battery", "Hint: Powers your torch."),
            new RiddleImpl("What has roots as nobody sees, is taller than trees, up, up it goes, and yet never grows?", "mountain", "Hint: Stands tall in the distance."),
            new RiddleImpl("I can be written, I can be spoken, I can be exposed, I can be broken. What am I?", "secret", "Hint: Keep it hidden."),
            new RiddleImpl("What has many keys but can't open a single lock?", "piano", "Hint: Makes music."),
            new RiddleImpl("I am always hungry, I must always be fed. The finger I touch will soon turn red. What am I?", "fire", "Hint: Lights the darkness."),
            new RiddleImpl("What can run but never walks, has a mouth but never talks, has a head but never weeps, has a bed but never sleeps?", "river", "Hint: Flows through the dungeon."),
            new RiddleImpl("I have a thousand wheels, but move I do not. Call me what I am, call me a lot. What am I?", "parking lot", "Hint: Where carriages rest."),
            new RiddleImpl("What belongs to you, but other people use it more than you?", "your name", "Hint: Others call you by it.")
    );

    public Awit() {
        super("Awit", RIDDLE_POOL);
    }

    @Override
    public String greet() {
        return "Awit whispers, 'Listen closely to my riddle...'";
    }
}
