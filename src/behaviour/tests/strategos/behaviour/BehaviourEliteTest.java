package strategos.behaviour;


import org.junit.*;
import strategos.*;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;


public class BehaviourEliteTest {

    private BehaviourElite behaviour;

    @Before public void setUp() throws Exception {
        behaviour = new BehaviourElite(TestUtil.getMockGameState(),
                TestUtil.getMockUnit()
        );
    }

    @Test public void getStrength() throws Exception {
        assertThat("Elite strength should be same as in Config",
                behaviour.getStrength(),
                is(Config.ELITE_STRENGTH)
        );
    }

    @Test public void getToughness() throws Exception {
        assertThat("Elite toughness should be same as in Config",
                behaviour.getToughness(),
                is(Config.ELITE_TOUGHNESS)
        );
    }

    @Test public void charge() throws Exception {
        // TODO
    }

    @Test public void attack() throws Exception {
        // TODO
    }

    @Test public void getActionPoints() throws Exception {
        assertThat("Elite action points should be same as in Config",
                behaviour.getMaxActionPoints(),
                is(Config.INFANTRY_ACTION_POINTS)
        );
    }
}