package strategos.behaviour;


import strategos.behaviour.util.HasBehaviour;
import sun.reflect.generics.reflectiveObjects.*;


class AiBehaviourArchers extends AiBehaviour {

    private final HasBehaviour<UnitBehaviour> unit;

    AiBehaviourArchers(HasBehaviour<UnitBehaviour> unit) {
        this.unit = unit;
        setBehaviour(new UnitBehaviourArchers(this));
    }

    @Override public void turnTick() {
        throw new NotImplementedException();
    }
}
