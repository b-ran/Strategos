package strategos.behaviour;


import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import strategos.model.GameState;
import strategos.model.MapLocation;
import strategos.units.Unit;

import static org.junit.Assert.assertFalse;


/**
 * @author Devon Mortimer
 */
public class UnitBehaviourTest {

    @Rule public final ExpectedException nullException = ExpectedException.none();
    private Behaviour behaviour;
    private MapLocation position1;
    private MapLocation position2;
    private Unit unit;

    @BeforeClass public static void beforeAll() {
        TestUtil.logAll();
    }

    @Before public void setUp() throws Exception {
        this.unit = TestUtil.getMockUnit();
        this.behaviour = new UnitBehaviour(TestUtil.getMockGameState()) {
            @Override public int getStrength(Unit unit) {
                return 50;
            }

            @Override public int getToughness(Unit unit) {
                return 25;
            }

            @Override public Behaviour copy(GameState newState) {
                return null;
            }
        };
    }

    @Test public void turnTick() throws Exception {
        assertFalse(true);
    }

    @Test public void wary() throws Exception {
        assertFalse(true);
    }

    @Test public void entrench() throws Exception {
        assertFalse(true);
    }

    @Test public void charge() throws Exception {
        assertFalse(true);
    }

    @Test public void move() throws Exception {
        assertFalse(true);
    }

    @Test public void attack() throws Exception {
        assertFalse(true);
    }

    @Test public void defend() throws Exception {
        assertFalse(true);
    }

    @Test public void isAlive() throws Exception {
        assertFalse(true);
    }

    @Test public void getSightRadius() throws Exception {
        assertFalse(true);
    }

    @Test public void getActionPoints() throws Exception {
        assertFalse(true);
    }

    @Test public void setActionPoints() throws Exception {
        assertFalse(true);
    }
}