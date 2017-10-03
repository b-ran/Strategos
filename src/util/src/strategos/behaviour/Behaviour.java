package strategos.behaviour;


import strategos.*;
import strategos.units.*;


public interface Behaviour {

    MapLocation getPosition(Unit unit);

    void setPosition(Unit unit, MapLocation position);

    void turnTick(Unit unit);

    void wary(Unit unit);

    boolean getWary(Unit unit);

    void entrench(Unit unit);

    boolean getEntrench(Unit unit);

    void charge(Unit unit);

    boolean move(Unit unit, Direction direction);

    int attack(Unit unit, Unit enemy);

    int defend(Unit unit, Unit enemy);

    int getStrength(Unit unit);

    int getToughness(Unit unit);

    int getHitpoints(Unit unit);

    boolean isAlive(Unit unit);

    int getSightRadius(Unit unit);

    int getActionPoints(Unit unit);

    Behaviour copy();

    int getAttackRange();
}
