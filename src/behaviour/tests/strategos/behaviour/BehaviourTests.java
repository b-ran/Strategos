package strategos.behaviour;

import org.junit.Before;
import org.junit.Test;
import strategos.units.Unit;

public class BehaviourTests {

    private Behaviour behaviour;
    private Unit unit;

    @Before
    public void setUp() throws Exception {
        BehaviourFactory behaviourFactory = new BehaviourFactoryImpl();
        behaviour = behaviourFactory.createBehaviourArchers(TestUtil.getMockGameState());
        unit = TestUtil.getMockUnit();
    }

    @Test
    public void ranged_attack_1() {

    }

    @Test
    public void test_behaviour_creation(){

    }

    @Test
    public void move_1() {

    }

    @Test
    public void move_2() {

    }


}
