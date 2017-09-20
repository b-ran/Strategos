package strategos.behaviour;


import strategos.behaviour.util.HasBehaviour;
import strategos.behaviour.util.MapLocation;


public interface Behaviour {

    MapLocation getPosition();

    void setPosition(MapLocation position);

    static void setUnitBehaviourArchers(HasBehaviour<UnitBehaviour> unit) {
        unit.setBehaviour(new UnitBehaviourArchers(unit));
    }

    static void setAiBehaviourArcher(HasBehaviour<UnitBehaviour> unit) {
        unit.setBehaviour(new AiBehaviourArchers(unit));
    }
}
