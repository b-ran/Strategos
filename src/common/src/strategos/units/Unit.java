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

    public Unit copy(UnitOwner newOwner, GameState newState);
}
