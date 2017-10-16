package strategos.behaviour;


import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import strategos.model.GameState;
import strategos.units.Unit;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;


/**
 * @author Devon Mortimer
 */
public class BehaviourFactoryImplTest {

    private BehaviourFactory factory;
    private GameState gameState;
    private Unit unit;

    @BeforeClass public static void beforeAll() {
        TestUtil.logAll();
    }

    @Before public void setUp() throws Exception {
        this.factory = new BehaviourFactoryImpl();
        this.gameState = TestUtil.getMockGameState();
        this.unit = TestUtil.getMockUnit();
    }

    @Test public void createBehaviourArchers() throws Exception {
        assertThat(
                "Method createBehaviourArchers() should create an instance of BehaviourArchers",
                this.factory.createBehaviourArchers(this.gameState),
                instanceOf(BehaviourArchers.class)
        );
    }

    @Test public void createBehaviourCavalry() throws Exception {
        assertThat(
                "Method createBehaviourCavalry() should create an instance of BehaviourCavalry",
                this.factory.createBehaviourCavalry(this.gameState),
                instanceOf(BehaviourCavalry.class)
        );
    }

    @Test public void createBehaviourElite() throws Exception {
        assertThat(
                "Method createBehaviourElite() should create an instance of BehaviourElite",
                this.factory.createBehaviourElite(this.gameState),
                instanceOf(BehaviourElite.class)
        );
    }

    @Test public void createBehaviourSpearmen() throws Exception {
        assertThat(
                "Method createBehaviourSpearmen() should create an instance of BehaviourSpearmen",
                this.factory.createBehaviourSpearmen(this.gameState),
                instanceOf(BehaviourSpearmen.class)
        );
    }

    @Test public void createBehaviourSwordsmen() throws Exception {
        assertThat(
                "Method createBehaviourSwordsmen() should create an instance of BehaviourSwordsmen",
                this.factory.createBehaviourSwordsmen(this.gameState),
                instanceOf(BehaviourSwordsmen.class)
        );
    }

    @Test public void createAiBehaviour() throws Exception {
        assertThat(
                "Method createAiBehaviour() should create an instance of AiBehaviour",
                this.factory.createAiBehaviour(this.gameState, this.factory::createBehaviourArchers),
                instanceOf(AiBehaviour.class)
        );
    }

    @Test public void globalStatePreservedArchers() throws Exception {
        assertThat(
                "Method createBehaviourArchers() should create identical instances each call",
                this.factory.createBehaviourArchers(this.gameState),
                equalTo(this.factory.createBehaviourArchers(this.gameState))
        );
    }

    @Test public void globalStatePreservedBridge() throws Exception {
        assertThat(
                "Method createBehaviourBridge() should create identical instances each call",
                this.factory.createBehaviourBridge(this.gameState),
                equalTo(this.factory.createBehaviourBridge(this.gameState))
        );
    }

    @Test public void globalStatePreservedCavalry() throws Exception {
        assertThat(
                "Method createBehaviourCavalry() should create identical instances each call",
                this.factory.createBehaviourCavalry(this.gameState),
                equalTo(this.factory.createBehaviourCavalry(this.gameState))
        );
    }

    @Test public void globalStatePreservedElite() throws Exception {
        assertThat(
                "Method createBehaviourElite() should create identical instances each call",
                this.factory.createBehaviourElite(this.gameState),
                equalTo(this.factory.createBehaviourElite(this.gameState))
        );
    }

    @Test public void globalStatePreservedHealthPotion() throws Exception {
        assertThat(
                "Method createBehaviourHealthPotion() should create identical instances each call",
                this.factory.createBehaviourHealthPotion(this.gameState),
                equalTo(this.factory.createBehaviourHealthPotion(this.gameState))
        );
    }

    @Test public void globalStatePreservedSpearmen() throws Exception {
        assertThat(
                "Method createBehaviourSpearmen() should create identical instances each call",
                this.factory.createBehaviourSpearmen(this.gameState),
                equalTo(this.factory.createBehaviourSpearmen(this.gameState))
        );
    }

    @Test public void globalStatePreservedSwordsmen() throws Exception {
        assertThat(
                "Method createBehaviourSwordsmen() should create identical instances each call",
                this.factory.createBehaviourSwordsmen(this.gameState),
                equalTo(this.factory.createBehaviourSwordsmen(this.gameState))
        );
    }
}