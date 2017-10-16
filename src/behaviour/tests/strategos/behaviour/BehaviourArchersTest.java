package strategos.behaviour;


import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import strategos.Config;
import strategos.units.Unit;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;


public class BehaviourArchersTest {

    private Behaviour behaviour;
    private Unit unit;

    @BeforeClass public static void beforeAll() {
        TestUtil.logAll();
    }

    @Before public void setUp() throws Exception {
        BehaviourFactory behaviourFactory = new BehaviourFactoryImpl();
        this.behaviour = behaviourFactory.createBehaviourArchers(TestUtil.getMockGameState());
        this.unit = TestUtil.getMockUnit();
    }

    @Test public void getStrength() throws Exception {
        assertThat(
                "Archer strength should be same as in BehaviourConfig", this.behaviour.getStrength(this.unit),
                is(Config.ARCHERS_STRENGTH)
        );
    }

    @Test public void getToughness() throws Exception {
        assertThat(
                "Archer toughness should be same as in BehaviourConfig", this.behaviour.getToughness(this.unit),
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
        this.behaviour.turnTick(this.unit);
        assertThat(
                "Archer action points should be same as in BehaviourConfig", this.behaviour.getActionPoints(this.unit),
                is(Config.INFANTRY_ACTION_POINTS)
        );
    }
}