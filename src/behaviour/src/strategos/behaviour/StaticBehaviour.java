package strategos.behaviour;


import strategos.*;
import strategos.units.*;

import java.util.logging.*;


abstract class StaticBehaviour extends BaseBehaviour {

    private static Logger logger = Logger.getLogger("strategos.behaviour");
    private boolean isAlive;

    @Override public boolean getWary(Unit unit) {
        return false;
    }

    @Override public boolean getEntrench(Unit unit) {
        return false;
    }

    @Override public int getHitpoints(Unit unit) {
        return isAlive ? 100 : 0;
    }

    StaticBehaviour(GameState gameState) {
        super(gameState);

        isAlive = true;
    }

    StaticBehaviour(StaticBehaviour behaviour, GameState newState) {
        super(behaviour, newState);

        this.isAlive = behaviour.isAlive;
    }

    @Override public void turnTick(Unit unit) {

    }

    @Override public void wary(Unit unit) {

    }

    @Override public void entrench(Unit unit) {

    }

    @Override public void charge(Unit unit) {

    }

    @Override public boolean move(Unit unit, Direction direction) {
        return false;
    }

    @Override public int attack(Unit unit, Unit enemy) {
        return 0;
    }

    @Override public int defend(Unit unit, Unit enemy) {
        logger.fine(String.format("%s: defend against %s", this.getClass(), unit));

        if (enemy == null) {
            throw new NullPointerException("Method defend() requires a non-null enemy");
        }

        isAlive = false;

        return 0;
    }

    @Override public int getStrength(Unit unit) {
        return 0;
    }

    @Override public int getToughness(Unit unit) {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        StaticBehaviour that = (StaticBehaviour) o;

        return isAlive == that.isAlive;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (isAlive ? 1 : 0);
        return result;
    }

    @Override public boolean isAlive(Unit unit) {
        return isAlive;
    }

    @Override public int getSightRadius(Unit unit) {
        return 0;
    }

    @Override public int getActionPoints(Unit unit) {
        return 0;
    }

    @Override
    public String toString() {
        return "StaticBehaviour{" +
                "isAlive=" + isAlive +
                "} " + super.toString();
    }

    @Override
    public int getAttackRange() {
        return 0;
    }
}
