package strategos.behaviour;


import strategos.behaviour.util.CombatUnit;


public interface UnitBehaviour extends Behaviour {

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
