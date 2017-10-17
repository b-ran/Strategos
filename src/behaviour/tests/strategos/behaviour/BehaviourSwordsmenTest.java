package strategos.behaviour;


import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import strategos.Config;
import strategos.units.Unit;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;


/**
 * @author Devon Mortimer
 */
public class BehaviourSwordsmenTest {

    private Behaviour behaviour;
    private Unit unit;

    @BeforeClass public static void beforeAll() {
        TestUtil.logAll();
    }

    @Before public void setUp() throws Exception {
        BehaviourFactory behaviourFactory = new BehaviourFactoryImpl();
        this.behaviour = behaviourFactory.createBehaviourSwordsmen(TestUtil.getMockGameState());
        this.unit = TestUtil.getMockUnit();
    }

    @Test public void getStrength() throws Exception {
        assertThat(
                "Swordsmen strength should be same as in BehaviourConfig", this.behaviour.getStrength(this.unit),
                is(Config.SWORDSMEN_STRENGTH)
        );
    }

    @Test public void getToughness() throws Exception {
        assertThat(
                "Swordsmen toughness should be same as in BehaviourConfig", this.behaviour.getToughness(this.unit),
                is(Config.SWORDSMEN_TOUGHNESS)
        );
    }

    @Test public void getActionPoints() throws Exception {
        this.behaviour.turnTick(this.unit);
        assertThat(
                "Swordsmen action points should be same as in BehaviourConfig", this.behaviour.getActionPoints(this.unit),
                is(Config.INFANTRY_ACTION_POINTS)
        );
    }
}