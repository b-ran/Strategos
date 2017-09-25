package strategos.behaviour;


import org.junit.*;
import strategos.*;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;


public class BehaviourCavalryTest {

    private BehaviourCavalry behaviour;

    @Before public void setUp() throws Exception {
        behaviour = new BehaviourCavalry(TestUtil.getMockGameState(),
                TestUtil.getMockUnit()
        );
    }

    @Test public void getStrength() throws Exception {
        assertThat("Cavalry strength should be same as in Config",
                behaviour.getStrength(),
                is(Config.CAVALRY_STRENGTH)
        );
    }

    @Test public void getToughness() throws Exception {
        assertThat("Cavalry toughness should be same as in Config",
                behaviour.getToughness(),
                is(Config.CAVALRY_TOUGHNESS)
        );
    }

    @Test public void charge() throws Exception {
        // TODO
    }

    @Test public void attack() throws Exception {
        // TODO
    }

    @Test public void getActionPoints() throws Exception {
        assertThat("Cavalry action points should be same as in Config",
                behaviour.getMaxActionPoints(),
                is(Config.CAVALRY_ACTION_POINTS)
        );
    }
}