package strategos.behaviour;


import org.junit.*;
import strategos.*;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;


public class BehaviourSwordsmenTest {

    private BehaviourSwordsmen behaviour;

    @Before public void setUp() throws Exception {
        behaviour = new BehaviourSwordsmen(TestUtil.getMockGameState(),
                TestUtil.getMockUnit()
        );
    }

    @Test public void getStrength() throws Exception {
        assertThat("Swordsmen strength should be same as in Config",
                behaviour.getStrength(),
                is(Config.SWORDSMEN_STRENGTH)
        );
    }

    @Test public void getToughness() throws Exception {
        assertThat("Swordsmen toughness should be same as in Config",
                behaviour.getToughness(),
                is(Config.SWORDSMEN_TOUGHNESS)
        );
    }

    @Test public void charge() throws Exception {
        // TODO
    }

    @Test public void attack() throws Exception {
        // TODO
    }

    @Test public void getActionPoints() throws Exception {
        assertThat("Swordsmen action points should be same as in Config",
                behaviour.getMaxActionPoints(),
                is(Config.INFANTRY_ACTION_POINTS)
        );
    }
}