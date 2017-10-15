package strategos.behaviour;


import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import strategos.Config;
import strategos.units.Unit;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;


public class BehaviourSwordsmenTest {

    private Behaviour behaviour;
    private Unit unit;

    @BeforeClass public static void beforeAll() {
        TestUtil.logAll();
    }

    @Before public void setUp() throws Exception {
        BehaviourFactory behaviourFactory = new BehaviourFactoryImpl();
        behaviour = behaviourFactory.createBehaviourSwordsmen(TestUtil.getMockGameState());
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