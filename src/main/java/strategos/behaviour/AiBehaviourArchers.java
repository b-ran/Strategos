package strategos.behaviour;


import sun.reflect.generics.reflectiveObjects.*;


/**
 * @author Devon Mortimer
 * @since 19/09/2017
 */
public class AiBehaviourArchers extends AiBehaviour {

    private final HasBehaviour<UnitBehaviour> unit;

    AiBehaviourArchers(HasBehaviour<UnitBehaviour> unit) {
        this.unit = unit;
        setBehaviour(new UnitBehaviourArchers(this));
    }

    @Override public void turnTick() {
        throw new NotImplementedException();
    }
}
