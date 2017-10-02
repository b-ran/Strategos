package strategos.behaviour;


import org.junit.*;
import strategos.*;
import strategos.units.*;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;


public class BehaviourSwordsmenTest {

    private Behaviour behaviour;
    private Unit      unit;

    @Before public void setUp() throws Exception {
        BehaviourFactory behaviourFactory = new BehaviourFactoryImpl();
        behaviour =
                behaviourFactory.createBehaviourSwordsmen(TestUtil.getMockGameState());
        unit = TestUtil.getMockUnit();
    }

    @Test public void getStrength() throws Exception {
        assertThat(
                "Swordsmen strength should be same as in BehaviourConfig",
                behaviour.getStrength(unit),
                is(Config.SWORDSMEN_STRENGTH)
        );
    }

    @Test public void getToughness() throws Exception {
        assertThat(
                "Swordsmen toughness should be same as in BehaviourConfig",
                behaviour.getToughness(unit),
                is(Config.SWORDSMEN_TOUGHNESS)
        );
    }

    @Test public void charge() throws Exception {
        assertFalse(true);
    }

    @Test public void attack() throws Exception {
        assertFalse(true);
    }

    @Test public void getActionPoints() throws Exception {
        behaviour.turnTick(unit);
        assertThat(
                "Swordsmen action points should be same as in BehaviourConfig",
                behaviour.getActionPoints(unit),
                is(Config.INFANTRY_ACTION_POINTS)
        );
    }
}