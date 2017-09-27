package strategos.behaviour;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import strategos.Direction;
import strategos.MapLocation;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

public class UnitBehaviourTest {
    @Rule
    public final ExpectedException nullException = ExpectedException.none();
    private UnitBehaviour behaviour;
    private MapLocation   position1;
    private MapLocation   position2;

    @Before
    public void setUp() throws Exception {
        behaviour = new UnitBehaviour(TestUtil.getMockGameState(), TestUtil.getMockUnit()) {
            @Override
            public int getStrength() {
                return 50;
            }

            @Override
            public int getToughness() {
                return 25;
            }
        };
        position1 = new MapLocation() {
            @Override
            public int getX() {
                return 0;
            }

            @Override
            public int getY() {
                return 0;
            }

            @Override
            public MapLocation getNeighbour(Direction direction) {
                return null;
            }

            @Override
            public void addNeighbour(Direction direction, MapLocation location) {

            }

            @Override
            public boolean isInPlayArea() {
                return false;
            }
        };
        position2 = new MapLocation() {
            @Override
            public int getX() {
                return 0;
            }

            @Override
            public int getY() {
                return 0;
            }

            @Override
            public MapLocation getNeighbour(Direction direction) {
                return null;
            }

            @Override
            public void addNeighbour(Direction direction, MapLocation location) {

            }

            @Override
            public boolean isInPlayArea() {
                return false;
            }
        };
    }

    @Test
    public void getPosition() throws Exception {
        behaviour.setPosition(position1);
        assertThat("Must return provided MapLocation", behaviour.getPosition(), is(position1));
        behaviour.setPosition(position2);
        assertThat("Must return provided MapLocation", behaviour.getPosition(), is(position2));
    }

    @Test
    public void setPosition() throws Exception {
        assertFalse(true);
    }

    @Test
    public void turnTick() throws Exception {
        assertFalse(true);
    }

    @Test
    public void wary() throws Exception {
        assertFalse(true);
    }

    @Test
    public void entrench() throws Exception {
        assertFalse(true);
    }

    @Test
    public void charge() throws Exception {
        assertFalse(true);
    }

    @Test
    public void move() throws Exception {
        assertFalse(true);
    }

    @Test
    public void attack() throws Exception {
        assertFalse(true);
    }

    @Test
    public void defend() throws Exception {
        assertFalse(true);
    }

    @Test
    public void isAlive() throws Exception {
        assertFalse(true);
    }

    @Test
    public void getSightRadius() throws Exception {
        assertFalse(true);
    }

    @Test
    public void getActionPoints() throws Exception {
        assertFalse(true);
    }

    @Test
    public void setActionPoints() throws Exception {
        assertFalse(true);
    }

    @Test
    public void getMaxActionPoints() throws Exception {
        assertFalse(true);
    }

}