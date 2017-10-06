package strategos.behaviour;


import org.junit.*;
import strategos.*;
import strategos.units.*;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;


public class BehaviourFactoryImplTest {

    private BehaviourFactory factory;
    private GameState        gameState;
    private Unit             unit;

    @Before public void setUp() throws Exception {
        factory = new BehaviourFactoryImpl();
        gameState = TestUtil.getMockGameState();
        unit = TestUtil.getMockUnit();
    }

    @Test public void createBehaviourArchers() throws Exception {
        assertThat(
                "Method createBehaviourArchers() should create an instance of BehaviourArchers",
                factory.createBehaviourArchers(gameState),
                instanceOf(BehaviourArchers.class)
        );
    }

    @Test public void createBehaviourCavalry() throws Exception {
        assertThat(
                "Method createBehaviourCavalry() should create an instance of BehaviourCavalry",
                factory.createBehaviourCavalry(gameState),
                instanceOf(BehaviourCavalry.class)
        );
    }

    @Test public void createBehaviourElite() throws Exception {
        assertThat(
                "Method createBehaviourElite() should create an instance of BehaviourElite",
                factory.createBehaviourElite(gameState),
                instanceOf(BehaviourElite.class)
        );
    }

    @Test public void createBehaviourSpearmen() throws Exception {
        assertThat(
                "Method createBehaviourSpearmen() should create an instance of BehaviourSpearmen",
                factory.createBehaviourSpearmen(gameState),
                instanceOf(BehaviourSpearmen.class)
        );
    }

    @Test public void createBehaviourSwordsmen() throws Exception {
        assertThat(
                "Method createBehaviourSwordsmen() should create an instance of BehaviourSwordsmen",
                factory.createBehaviourSwordsmen(gameState),
                instanceOf(BehaviourSwordsmen.class)
        );
    }

    @Test public void createAiBehaviour() throws Exception {
        assertThat(
                "Method createAiBehaviour() should create an instance of AiBehaviour",
                factory.createAiBehaviour(gameState, factory::createBehaviourArchers),
                instanceOf(AiBehaviour.class)
        );
    }

    @Test public void globalStatePreservedArchers() throws Exception {
        assertThat(
                "Method createBehaviourArchers() should create identical instances each call",
                factory.createBehaviourArchers(gameState),
                equalTo(factory.createBehaviourArchers(gameState))
        );
    }

    @Test public void globalStatePreservedBridge() throws Exception {
        assertThat(
                "Method createBehaviourBridge() should create identical instances each call",
                factory.createBehaviourBridge(gameState),
                equalTo(factory.createBehaviourBridge(gameState))
        );
    }

    @Test public void globalStatePreservedCavalry() throws Exception {
        assertThat(
                "Method createBehaviourCavalry() should create identical instances each call",
                factory.createBehaviourCavalry(gameState),
                equalTo(factory.createBehaviourCavalry(gameState))
        );
    }

    @Test public void globalStatePreservedElite() throws Exception {
        assertThat(
                "Method createBehaviourElite() should create identical instances each call",
                factory.createBehaviourElite(gameState),
                equalTo(factory.createBehaviourElite(gameState))
        );
    }

    @Test public void globalStatePreservedHealthPotion() throws Exception {
        assertThat(
                "Method createBehaviourHealthPotion() should create identical instances each call",
                factory.createBehaviourHealthPotion(gameState),
                equalTo(factory.createBehaviourHealthPotion(gameState))
        );
    }

    @Test public void globalStatePreservedSpearmen() throws Exception {
        assertThat(
                "Method createBehaviourSpearmen() should create identical instances each call",
                factory.createBehaviourSpearmen(gameState),
                equalTo(factory.createBehaviourSpearmen(gameState))
        );
    }

    @Test public void globalStatePreservedSwordsmen() throws Exception {
        assertThat(
                "Method createBehaviourSwordsmen() should create identical instances each call",
                factory.createBehaviourSwordsmen(gameState),
                equalTo(factory.createBehaviourSwordsmen(gameState))
        );
    }
}