package strategos.behaviour;


import org.junit.Before;
import org.junit.Test;
import strategos.Config;
import strategos.units.Unit;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;


public class BehaviourCavalryTest {

    private Behaviour behaviour;
    private Unit unit;

    @Before
    public void setUp() throws Exception {
        BehaviourFactory behaviourFactory = new BehaviourFactoryImpl();
        behaviour = behaviourFactory.createBehaviourCavalry(TestUtil.getMockGameState());
        unit = TestUtil.getMockUnit();
    }

    @Test
    public void getStrength() throws Exception {
        assertThat("Cavalry strength should be same as in BehaviourConfig",
                behaviour.getStrength(unit),
                is(Config.CAVALRY_STRENGTH)
        );
    }

    @Test
    public void getToughness() throws Exception {
        assertThat("Cavalry toughness should be same as in BehaviourConfig",
                behaviour.getToughness(unit),
                is(Config.CAVALRY_TOUGHNESS)
        );
    }

    @Test
    public void charge() throws Exception {
        assertFalse(true);
    }

    @Test
    public void attack() throws Exception {
        assertFalse(true);
    }

    @Test
    public void getActionPoints() throws Exception {
        behaviour.turnTick(unit);
        assertThat("Cavalry action points should be same as in BehaviourConfig",
                behaviour.getActionPoints(unit),
                is(Config.CAVALRY_ACTION_POINTS)
        );
    }
}