package strategos.units;


import strategos.*;
import strategos.behaviour.Behaviour;
import strategos.model.GameState;
import strategos.model.MapLocation;
import strategos.model.UnitOwner;

import java.io.Serializable;


public interface Unit extends Serializable {

    void setBehaviour(Behaviour behaviour);

    public Behaviour getBehaviour();

    MapLocation getPosition();

    void setPosition(MapLocation position);

    /**
     * Resets the unit's Action Points and, if it is a barbarian, runs the AI code.
     */
    void turnTick();

    void wary();

    boolean getWary();

    void entrench();

    boolean getEntrench();

    void charge();

    boolean move(Direction direction);

    int attack(Unit enemy);

    int defend(Unit enemy);

    int getStrength();

    int getToughness();

    UnitOwner getOwner();

    int getHitpoints();

    boolean isAlive();

    int getSightRadius();

    int getActionPoints();

    int getAttackRange();

    /**
     * Deep copy implementation.
     * @param newOwner
     * @param newState
     * @return
     */
    Unit copy(UnitOwner newOwner, GameState newState);
}
