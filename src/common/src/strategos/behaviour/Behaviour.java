package strategos.behaviour;


import strategos.Direction;
import strategos.model.GameState;
import strategos.model.MapLocation;
import strategos.units.Unit;

import java.io.Serializable;


/**
 * @author Devon Mortimer
 * The interface Behaviour controls the actions of a unit.
 * This class is a strategy and should have the containing unit passed as a
 * parameter to methods.
 */
public interface Behaviour extends Serializable {

    /**
     * Gets the position of the behaviour.
     *
     * @param unit the unit
     * @return the position
     */
    MapLocation getPosition(Unit unit);

    /**
     * Sets the position of the behaviour.
     *
     * @param unit the unit
     * @param position the position
     */
    void setPosition(Unit unit, MapLocation position);

    /**
     * Prepares the behaviour for the next turn.
     *
     * @param unit the unit
     */
    void turnTick(Unit unit);

    /**
     * Toggles wary.
     *
     * @param unit the unit
     */
    void wary(Unit unit);

    /**
     * Gets the current wary status.
     *
     * @param unit the unit
     * @return the wary
     */
    boolean getWary(Unit unit);

    /**
     * Toggles entrench.
     *
     * @param unit the unit
     */
    void entrench(Unit unit);

    /**
     * Gets the current entrench status.
     *
     * @param unit the unit
     * @return the entrench
     */
    boolean getEntrench(Unit unit);

    /**
     * Activates charge.
     *
     * @deprecated This method is unused in the current implementation
     * @param unit the unit
     */
    void charge(Unit unit);

    /**
     * Moves the behaviour in a specified direction.
     *
     * @param unit the unit
     * @param direction the direction
     * @return true if the move was successful
     */
    boolean move(Unit unit, Direction direction);

    /**
     * Attack a given unit.
     *
     * @param unit the unit
     * @param enemy the enemy unit
     * @return unused return
     */
    int attack(Unit unit, Unit enemy);

    /**
     * Defend against a given unit.
     *
     * @param unit the unit
     * @param enemy the enemy unit
     * @return unused return
     */
    int defend(Unit unit, Unit enemy);

    /**
     * Gets the strength of the behaviour.
     *
     * @param unit the unit
     * @return the strength
     */
    int getStrength(Unit unit);

    /**
     * Gets the toughness of the behaviour.
     *
     * @param unit the unit
     * @return the toughness
     */
    int getToughness(Unit unit);

    /**
     * Gets the hitpoints of the behaviour.
     *
     * @param unit the unit
     * @return the hitpoints
     */
    int getHitpoints(Unit unit);

    /**
     * Checks if behaviour is still alive.
     *
     * @param unit the unit
     * @return true if behaviour is alive
     */
    boolean isAlive(Unit unit);

    /**
     * Gets the sight radius for this behaviour.
     *
     * @param unit the unit
     * @return the sight radius
     */
    int getSightRadius(Unit unit);

    /**
     * Gets the current action points.
     *
     * @param unit the unit
     * @return the action points
     */
    int getActionPoints(Unit unit);

    /**
     * Copy behaviour instance.
     *
     * @param newState the new state to adopt as internal state
     * @return the copy behaviour
     */
    Behaviour copy(GameState newState);

    /**
     * Gets the attack range for this behaviour.
     *
     * @return the attack range
     */
    int getAttackRange();
}
