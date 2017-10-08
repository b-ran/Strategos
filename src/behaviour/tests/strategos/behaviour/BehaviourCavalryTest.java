package strategos.behaviour;


import org.junit.*;
import strategos.*;
import strategos.units.*;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;


public class BehaviourCavalryTest {

    private Behaviour behaviour;
    private Unit      unit;

    @BeforeClass public static void beforeAll() {
        TestUtil.logAll();
    }

    @Before public void setUp() throws Exception {
        BehaviourFactory behaviourFactory = new BehaviourFactoryImpl();
        behaviour =
                behaviourFactory.createBehaviourCavalry(TestUtil.getMockGameState());
        unit = TestUtil.getMockUnit();
    }

    @Test public void getStrength() throws Exception {
        assertThat("Cavalry strength should be same as in BehaviourConfig",
                behaviour.getStrength(unit),
                is(Config.CAVALRY_STRENGTH)
        );
    }

    @Test public void getToughness() throws Exception {
        assertThat("Cavalry toughness should be same as in BehaviourConfig",
                behaviour.getToughness(unit),
                is(Config.CAVALRY_TOUGHNESS)
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
        assertThat("Cavalry action points should be same as in BehaviourConfig",
                behaviour.getActionPoints(unit),
                is(Config.CAVALRY_ACTION_POINTS)
        );
    }
}