package strategos.behaviour;


import strategos.model.units.*;


public interface UnitBehaviour extends Behaviour {

    void wary();

    void entrench();

    void charge();

    boolean move();

    int attack(Unit enemy);

    int defend(Unit enemy);

    int getStrength();

    int getToughness();
}
