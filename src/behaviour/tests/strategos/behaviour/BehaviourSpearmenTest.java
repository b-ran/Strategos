package strategos.behaviour;


import org.junit.*;
import strategos.*;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;


public class BehaviourSpearmenTest {

    private BehaviourSpearmen behaviour;

    @Before public void setUp() throws Exception {
        behaviour = new BehaviourSpearmen(TestUtil.getMockGameState(),
                TestUtil.getMockUnit()
        );
    }

    @Test public void getStrength() throws Exception {
        assertThat("Spearmen strength should be same as in Config",
                behaviour.getStrength(),
                is(Config.SPEARMEN_STRENGTH)
        );
    }

    @Test public void getToughness() throws Exception {
        assertThat("Spearmen toughness should be same as in Config",
                behaviour.getToughness(),
                is(Config.SPEARMEN_TOUGHNESS)
        );
    }

    @Test public void charge() throws Exception {
        // TODO
    }

    @Test public void attack() throws Exception {
        // TODO
    }

    @Test public void getActionPoints() throws Exception {
        assertThat("Spearmen action points should be same as in Config",
                behaviour.getMaxActionPoints(),
                is(Config.INFANTRY_ACTION_POINTS)
        );
    }
}