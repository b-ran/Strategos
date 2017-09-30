package strategos.behaviour;


import strategos.*;
import strategos.units.*;


public interface Behaviour {

    MapLocation getPosition();

    void setPosition(MapLocation position);

    void turnTick();

    void wary();

    void entrench();

    void charge();

    boolean move(Direction direction);

    int attack(Unit enemy);

    int defend(Unit enemy);

    int getStrength();

    int getToughness();

    boolean isAlive();

    int getSightRadius();

    int getActionPoints();

    public Behaviour copy();

}
