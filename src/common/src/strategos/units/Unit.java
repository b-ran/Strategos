package strategos.units;


import strategos.*;
import strategos.behaviour.Behaviour;
import strategos.model.GameState;
import strategos.model.MapLocation;
import strategos.model.UnitOwner;

import java.io.Serializable;


public interface Unit extends Serializable {

    /**
     * Sets the behaviour stored in this unit to be the parameter. Only used in deep copy
     * @param behaviour
     */
    void setBehaviour(Behaviour behaviour);

    /**
     * Gets the behaviour object stored in this unit
     * @return
     */
    Behaviour getBehaviour();

    /**
     * Requests the unit's position from the Behaviour
     * @return
     */
    MapLocation getPosition();

    void setPosition(MapLocation position);

    /**
     * Resets the unit's Action Points and, if it is a barbarian, runs the AI code.
     */
    void turnTick();

    /**
     * Delegates a call to make the unit Wary to the Behaviour
     */
    void wary();

    /**
     * Tells whether or not this unit is in the Wary state
     * @return
     */
    boolean getWary();

    /**
     * Delegates a call to make the unit Entrench to the Behaviour
     */
    void entrench();

    /**
     * Tells whether or not this unit is in the Entrench state
     * @return
     */
    boolean getEntrench();

    /**
     * Tells the unit to charge - currently unused. Delegates to the behaviour
     */
    void charge();

    /**
     * Tells the unit to move 1 tile in the specified direction. Delegates to the behaviour object
     * @param direction A Direction enum value.
     * @return
     */
    boolean move(Direction direction);

    /**
     * Tells the unit to attack a given enemy. Assumed that the enemy is within attack range, and delegates the command
     *      to the Unit Behaviour. Assumes that the enemy is a valid target, and is not null
     * @param enemy the Unit to attack
     * @return
     */
    int attack(Unit enemy);

    /**
     * Resolve a defence against a given attack. Called by attack().
     * @param enemy
     * @return
     */
    int defend(Unit enemy);

    /**
     * Gets the strength value stored in the Behaviour
     * @return
     */
    int getStrength();

    /**
     * Gets the toughness value stored in the Behaviour
     * @return
     */
    int getToughness();

    /**
     * Gets the UnitOwner to which this Unit belongs.
     * @return
     */
    UnitOwner getOwner();

    /**
     * Gets the number of hitpoints of the unit specified by the behaviour
     * @return
     */
    int getHitpoints();

    /**
     * Delegates a call to the behaviour to check that this unit is alive, i.e. hitpoints > 0
     * @return
     */
    boolean isAlive();

    /**
     * Gets the sight radius defined by the behaviour
     * @return
     */
    int getSightRadius();

    /**
     * Delegates a call to the behaviour to determine the number of action points remaining
     * @return
     */
    int getActionPoints();

    /**
     * Gets the attack radius defined by the behaviour
     * @return
     */
    int getAttackRange();

    /**
     * Deep copy implementation.
     * @param newOwner
     * @param newState
     * @return
     */
    Unit copy(UnitOwner newOwner, GameState newState);
}
