package strategos.behaviour;


import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import strategos.model.GameState;
import strategos.units.Unit;

import static org.junit.Assert.assertThat;


/**
 * @author Devon Mortimer
 */
public class UnitBehaviourTest {

    @Rule public final ExpectedException nullException = ExpectedException.none();
    private Behaviour behaviour;
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

    @Test public void wary() throws Exception {
        assertThat("Wary should be false before call", this.behaviour.getWary(this.unit), CoreMatchers.is(false));
        this.behaviour.turnTick(this.unit);
        this.behaviour.wary(this.unit);
        assertThat("Wary should be true after call", this.behaviour.getWary(this.unit), CoreMatchers.is(true));
        this.behaviour.turnTick(this.unit);
        this.behaviour.wary(this.unit);
        assertThat("Wary should be false after second call", this.behaviour.getWary(this.unit), CoreMatchers.is(false));
    }

    @Test public void entrench() throws Exception {
        assertThat("Entrench should be false before call", this.behaviour.getEntrench(this.unit), CoreMatchers.is(false));
        this.behaviour.turnTick(this.unit);
        this.behaviour.entrench(this.unit);
        assertThat("Entrench should be true after call", this.behaviour.getEntrench(this.unit), CoreMatchers.is(true));
        this.behaviour.turnTick(this.unit);
        this.behaviour.entrench(this.unit);
        assertThat("Entrench should be false after second call", this.behaviour.getEntrench(this.unit), CoreMatchers.is(false));
    }
}