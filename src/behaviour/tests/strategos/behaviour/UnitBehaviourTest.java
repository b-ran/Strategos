package strategos.behaviour;


import org.junit.*;
import org.junit.rules.*;
import strategos.*;
import strategos.terrain.*;
import strategos.units.*;

import java.util.*;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;


public class UnitBehaviourTest {

    @Rule public final ExpectedException nullException =
            ExpectedException.none();
    private Behaviour   behaviour;
    private MapLocation position1;
    private MapLocation position2;
    private Unit        unit;

    @Before public void setUp() throws Exception {
        unit = TestUtil.getMockUnit();
        behaviour = new UnitBehaviour(TestUtil.getMockGameState()) {
            @Override public int getStrength(Unit unit) {
                return 50;
            }

            @Override public int getToughness(Unit unit) {
                return 25;
            }

            @Override public Behaviour copy() {
                return null;
            }
        };
        position1 = new MapLocation() {
            @Override public int getX() {
                return 0;
            }

            @Override public Terrain getTerrain() {
                return null;
            }

            @Override public int getY() {
                return 0;
            }

            @Override public MapLocation getNeighbour(Direction direction) {
                return null;
            }

            @Override public void addNeighbour(
                    Direction direction, MapLocation location
            )
            {

            }

            @Override public boolean isInPlayArea() {
                return false;
            }

            @Override public Map<Direction, MapLocation> getNeighbours() {
                return null;
            }


            @Override public void setTerrain(Terrain terrain) {

            }
        };
        position2 = new MapLocation() {
            @Override public int getX() {
                return 0;
            }

            @Override public int getY() {
                return 0;
            }

            @Override public MapLocation getNeighbour(Direction direction) {
                return null;
            }

            @Override public void addNeighbour(
                    Direction direction, MapLocation location
            )
            {

            }

            @Override public boolean isInPlayArea() {
                return false;
            }

            @Override public Map<Direction, MapLocation> getNeighbours() {
                return null;
            }

            @Override public Terrain getTerrain() {
                return null;
            }

            @Override public void setTerrain(Terrain terrain) {

            }
        };
    }

    @Test public void getPosition() throws Exception {
        behaviour.setPosition(unit, position1);
        assertThat(
                "Must return provided MapLocation",
                behaviour.getPosition(unit),
                is(position1)
        );
        behaviour.setPosition(unit, position2);
        assertThat(
                "Must return provided MapLocation",
                behaviour.getPosition(unit),
                is(position2)
        );
    }

    @Test public void setPosition() throws Exception {
        assertFalse(true);
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