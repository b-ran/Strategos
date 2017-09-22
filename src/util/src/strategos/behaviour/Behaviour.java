package strategos.behaviour;


import strategos.MapLocation;

public interface Behaviour {

    MapLocation getPosition();

    void setPosition(MapLocation position);

    void turnTick();

    void wary();

    void entrench();

    void charge();

    boolean move();

    int attack(Movable enemy);

    int defend(Movable enemy);

    int getStrength();

    int getToughness();

}
