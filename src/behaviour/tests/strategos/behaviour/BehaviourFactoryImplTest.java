package strategos.behaviour;


import org.junit.*;
import strategos.*;
import strategos.units.*;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;


public class BehaviourFactoryImplTest {

    private BehaviourFactoryImpl factory;
    private GameState            gameState;
    private Unit                 unit;

    @Before public void setUp() throws Exception {
        factory = new BehaviourFactoryImpl();
        gameState = TestUtil.getMockGameState();
        unit = TestUtil.getMockUnit();
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