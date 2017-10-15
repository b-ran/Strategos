package strategos.behaviour;


import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import strategos.Direction;
import strategos.model.GameState;
import strategos.model.MapLocation;
import strategos.units.Unit;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class BaseBehaviourTest {

    @Rule public final ExpectedException nullException = ExpectedException.none();
    private GameState gameState;
    private Behaviour behaviour;

    @BeforeClass public static void beforeAll() {
        TestUtil.logAll();
    }

    @Before public void setUp() throws Exception {
        gameState = TestUtil.getMockGameState();
    }

    @Test public void BaseBehaviour_nullState() throws Exception {
        nullException.expect(NullPointerException.class);
        makeBaseBehaviour(null);
    }

    private static BaseBehaviour makeBaseBehaviour(GameState gameState) {
        return new BaseBehaviour(gameState) {
            @Override public MapLocation getPosition(Unit unit) {
                return null;
            }

            @Override public void setPosition(Unit unit, MapLocation position) {

            }

            @Override public void turnTick(Unit unit) {

            }

            @Override public void wary(Unit unit) {

            }

            @Override public boolean getWary(Unit unit) {
                return false;
            }

            @Override public void entrench(Unit unit) {

            }

            @Override public boolean getEntrench(Unit unit) {
                return false;
            }

            @Override public void charge(Unit unit) {

            }

            @Override public boolean move(Unit unit, Direction direction) {
                return false;
            }

            @Override public int attack(Unit unit, Unit enemy) {
                return 0;
            }

            @Override public int defend(Unit unit, Unit enemy) {
                return 0;
            }

            @Override public int getStrength(Unit unit) {
                return 0;
            }

            @Override public int getToughness(Unit unit) {
                return 0;
            }

            @Override public int getHitpoints(Unit unit) {
                return 0;
            }

            @Override public boolean isAlive(Unit unit) {
                return false;
            }

            @Override public int getSightRadius(Unit unit) {
                return 0;
            }

            @Override public int getActionPoints(Unit unit) {
                return 0;
            }

            @Override public Behaviour copy(GameState newState) {
                return null;
            }

            @Override public int getAttackRange() {
                return 0;
            }
        };
    }

    @Test public void getGameState() throws Exception {
        assertThat("Provided GameState instance must be returned",
                   makeBaseBehaviour(gameState).getGameState(),
                   is(gameState)
        );
    }

    @Test public void getPosition_setPosition() throws Exception {
        behaviour = makeBaseBehaviour(this.gameState);
        Unit unit = TestUtil.getMockUnit();
        MapLocation position1 = TestUtil.getMockLocation();
        MapLocation position2 = TestUtil.getMockLocation();

        behaviour.setPosition(unit, position1);
        assertThat("Must return provided MapLocation", behaviour.getPosition(unit), is(position1));
        behaviour.setPosition(unit, position2);
        assertThat("Must return provided MapLocation", behaviour.getPosition(unit), is(position2));
    }
}