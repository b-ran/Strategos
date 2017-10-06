package strategos.behaviour;

import org.junit.Before;
import org.junit.Test;
import strategos.behaviour.config.BehaviourConfig;
import strategos.units.Unit;

import static org.mockito.Mockito.*;

public class BehaviourTests {

    private Behaviour behaviour;
    private Unit unit;

    @Before
    public void setUp() throws Exception {
        BehaviourFactory behaviourFactory = new BehaviourFactoryImpl();
        behaviour = behaviourFactory.createBehaviourArchers(TestUtil.getMockGameState());
        unit = mock(Unit.class);
    }

    @Test
    public void dmg_1() {
        Unit attacker = mock(Unit.class);
        when(attacker.getStrength()).thenReturn(10);
        when(unit.getHitpoints()).thenReturn(BehaviourConfig.UNIT_HITPOINTS);
        behaviour.defend(unit, attacker);
        verify(attacker).getStrength();
        verify(unit).getToughness();
    }

}
