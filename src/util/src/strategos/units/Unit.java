package strategos.units;


import strategos.*;


public interface Unit {

    MapLocation getPosition();

    void setPosition(MapLocation position);

    void turnTick();

    void wary();

    void entrench();

    void charge();

    boolean move();

    int attack(Unit enemy);

    int defend(Unit enemy);

    int getStrength();

    int getToughness();

    UnitOwner getOwner();

    boolean isAlive();

    int getSightRadius();

    int getActionPoints();
}
