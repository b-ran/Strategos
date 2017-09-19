package strategos.behaviour;


import strategos.model.*;


public interface Behaviour {

    Hex getPosition();

    void setPosition(Hex position);

    static void setUnitBehaviourArchers(HasBehaviour<UnitBehaviour> unit) {
        unit.setBehaviour(new UnitBehaviourArchers(unit));
    }

    static void setAiBehaviourArcher(HasBehaviour<UnitBehaviour> unit) {
        unit.setBehaviour(new AiBehaviourArchers(unit));
    }
}
