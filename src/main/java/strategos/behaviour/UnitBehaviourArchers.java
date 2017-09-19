package strategos.behaviour;


import strategos.model.units.*;
import sun.reflect.generics.reflectiveObjects.*;


class UnitBehaviourArchers extends BehaviourBase implements UnitBehaviour {

    private final HasBehaviour<UnitBehaviour> unit;

    UnitBehaviourArchers(HasBehaviour<UnitBehaviour> unit) {
        this.unit = unit;
    }

    @Override public void wary() {
        throw new NotImplementedException();
    }

    @Override public void entrench() {
        throw new NotImplementedException();
    }

    @Override public void charge() {
        throw new NotImplementedException();
    }

    @Override public boolean move() {
        throw new NotImplementedException();
    }

    @Override public int attack(Unit enemy) {
        throw new NotImplementedException();
    }

    @Override public int defend(Unit enemy) {
        throw new NotImplementedException();
    }

    @Override public int getStrength() {
        throw new NotImplementedException();
    }

    @Override public int getToughness() {
        throw new NotImplementedException();
    }
}
