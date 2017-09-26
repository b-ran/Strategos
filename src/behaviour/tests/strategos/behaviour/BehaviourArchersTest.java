package strategos.behaviour;


import org.junit.*;
import strategos.*;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;


public class BehaviourArchersTest {

    private BehaviourArchers behaviour;

    @Before public void setUp() throws Exception {
        behaviour = new BehaviourArchers(TestUtil.getMockGameState(),
                TestUtil.getMockUnit()
        );
    }

    @Test public void getStrength() throws Exception {
        assertThat("Archer strength should be same as in Config",
                behaviour.getStrength(),
                is(Config.ARCHERS_STRENGTH)
        );
    }

    @Test public void getToughness() throws Exception {
        assertThat("Archer toughness should be same as in Config",
                behaviour.getToughness(),
                is(Config.ARCHERS_TOUGHNESS)
        );
    }

    @Test public void charge() throws Exception {
        assertFalse(true);
    }

    @Test public void attack() throws Exception {
        assertFalse(true);
    }

    @Test public void getActionPoints() throws Exception {
        assertThat("Archer action points should be same as in Config",
                behaviour.getMaxActionPoints(),
                is(Config.INFANTRY_ACTION_POINTS)
        );
    }
}