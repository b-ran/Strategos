package strategos.behaviour;


import strategos.*;
import strategos.units.*;


public abstract class NullBehaviour implements Behaviour {

    @Override public MapLocation getPosition() {
        return null;
    }

    @Override public void setPosition(MapLocation position) {
    }

    @Override public void turnTick() {
    }

    @Override public boolean move(Direction direction) {
        return false;
    }

    @Override public int attack(Unit enemy) {
        return 0;
    }

    @Override public int defend(Unit enemy) {
        return 0;
    }

    @Override public int getStrength() {
        return 0;
    }

    @Override public int getToughness() {
        return 0;
    }
}
