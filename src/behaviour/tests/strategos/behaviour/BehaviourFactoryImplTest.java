package strategos.behaviour;


import org.junit.*;
import strategos.*;
import strategos.terrain.*;
import strategos.units.*;

import java.util.*;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;


public class BehaviourFactoryImplTest {

    private BehaviourFactoryImpl factory;
    private GameState            gameState;
    private Unit                 unit;

    @Before public void setUp() throws Exception {
        factory = new BehaviourFactoryImpl();
        gameState = new GameState() {
            @Override public void save() {

            }

            @Override public void load(int saveIndex) {

            }

            @Override public Unit getUnitAt(MapLocation location) {
                return null;
            }

            @Override
            public void move(Unit unit, Direction direction, int amount) {

            }

            @Override public void attack(Unit unit, int targetX, int targetY) {

            }

            @Override public void attack(Unit unit, MapLocation location) {

            }

            @Override public void wary(Unit unit) {

            }

            @Override public void entrench(Unit unit) {

            }

            @Override
            public List<Unit> getUnitsInRange(MapLocation location, int range) {
                return null;
            }

            @Override public Terrain getTerrainAt(MapLocation location) {
                return null;
            }

            @Override public void nextTurn() {

            }
        };
        unit = new Unit() {
            @Override public MapLocation getPosition() {
                return null;
            }

            @Override public void setPosition(MapLocation position) {

            }

            @Override public void turnTick() {

            }

            @Override public void wary() {

            }

            @Override public void entrench() {

            }

            @Override public void charge() {

            }

            @Override public boolean move(Direction direction) {
                return false;
            }

            @Override public int attack(Unit enemy) {
                return 0;
            }

            @Override public int defend(Unit enemy) {
                return 0;
            }

            @Override public int getStrength() {
                return 0;
            }

            @Override public int getToughness() {
                return 0;
            }

            @Override public UnitOwner getOwner() {
                return null;
            }

            @Override public boolean isAlive() {
                return false;
            }

            @Override public int getSightRadius() {
                return 0;
            }

            @Override public int getActionPoints() {
                return 0;
            }
        };
    }

    @Test public void createBehaviourArchers() throws Exception {
        assertThat(
                "Method createBehaviourArchers() should create an instance of BehaviourArchers",
                factory.createBehaviourArchers(gameState, unit),
                instanceOf(BehaviourArchers.class)
        );
    }

    @Test public void createBehaviourCavalry() throws Exception {
        assertThat(
                "Method createBehaviourCavalry() should create an instance of BehaviourCavalry",
                factory.createBehaviourCavalry(gameState, unit),
                instanceOf(BehaviourCavalry.class)
        );
    }

    @Test public void createBehaviourElite() throws Exception {
        assertThat(
                "Method createBehaviourElite() should create an instance of BehaviourElite",
                factory.createBehaviourElite(gameState, unit),
                instanceOf(BehaviourElite.class)
        );
    }

    @Test public void createBehaviourSpearmen() throws Exception {
        assertThat(
                "Method createBehaviourSpearmen() should create an instance of BehaviourSpearmen",
                factory.createBehaviourSpearmen(gameState, unit),
                instanceOf(BehaviourSpearmen.class)
        );
    }

    @Test public void createBehaviourSwordsmen() throws Exception {
        assertThat(
                "Method createBehaviourSwordsmen() should create an instance of BehaviourSwordsmen",
                factory.createBehaviourSwordsmen(gameState, unit),
                instanceOf(BehaviourSwordsmen.class)
        );
    }

    @Test public void createAiBehaviour() throws Exception {
        assertThat(
                "Method createAiBehaviour() should create an instance of AiBehaviour",
                factory.createAiBehaviour(gameState,
                        unit,
                        BehaviourArchers::new
                ),
                instanceOf(AiBehaviour.class)
        );
    }
}