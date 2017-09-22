package strategos.behaviour;


public interface Behaviour {

    MapLocation getPosition();

    void setPosition(MapLocation position);

    void turnTick();

    void wary();

    void entrench();

    void charge();

    boolean move();

    int attack(CombatUnit enemy);

    int defend(CombatUnit enemy);

    int getStrength();

    int getToughness();

}
