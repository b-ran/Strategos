package strategos.behaviour;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import strategos.Direction;
import strategos.GameState;
import strategos.MapLocation;
import strategos.units.Unit;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class BaseBehaviourTest {
    @Rule
    public final ExpectedException nullException = ExpectedException.none();
    private GameState gameState;
    private Unit      unit;

    private static BaseBehaviour makeBaseBehaviour(GameState gameState, Unit unit) {
        return new BaseBehaviour(gameState, unit) {
            @Override
            public MapLocation getPosition() {
                return null;
            }

            @Override
            public void setPosition(MapLocation position) {

            }

            @Override
            public void turnTick() {

            }

            @Override
            public void wary() {

            }

            @Override
            public void entrench() {

            }

            @Override
            public void charge() {

            }

            @Override
            public boolean move(Direction direction) {
                return false;
            }

            @Override
            public int attack(Unit enemy) {
                return 0;
            }

            @Override
            public int defend(Unit enemy) {
                return 0;
            }

            @Override
            public int getStrength() {
                return 0;
            }

            @Override
            public int getToughness() {
                return 0;
            }

            @Override
            public boolean isAlive() {
                return false;
            }

            @Override
            public int getSightRadius() {
                return 0;
            }

            @Override
            public int getActionPoints() {
                return 0;
            }
        };
    }

    @Before
    public void setUp() throws Exception {
        gameState = TestUtil.getMockGameState();
        unit = TestUtil.getMockUnit();
    }

    @Test
    public void BaseBehaviour_nullState() throws Exception {
        nullException.expect(NullPointerException.class);
        makeBaseBehaviour(null, unit);
    }

    @Test
    public void BaseBehaviour_nullUnit() throws Exception {
        nullException.expect(NullPointerException.class);
        makeBaseBehaviour(gameState, null);
    }

    @Test
    public void getGameState() throws Exception {
        assertThat(
                "Provided GameState instance must be returned",
                makeBaseBehaviour(gameState, unit).getGameState(),
                is(gameState)
        );
    }

    @Test
    public void getUnit() throws Exception {
        assertThat(
                "Provided Unit instance must be returned",
                makeBaseBehaviour(gameState, unit).getUnit(),
                is(unit)
        );
    }

}