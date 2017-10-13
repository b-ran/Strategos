package strategos.behaviour;


import strategos.*;
import strategos.model.GameState;
import strategos.units.*;

import java.util.logging.*;


abstract class StaticBehaviour extends BaseBehaviour {

    private static Logger logger = Logger.getLogger("strategos.behaviour");
    private int hitpointsMax;
    private int hitpoints;

    @Override public boolean getWary(Unit unit) {
        return false;
    }

    @Override public boolean getEntrench(Unit unit) {
        return false;
    }

    @Override public int getHitpoints(Unit unit) {
        return Math.max(hitpoints * (100 / hitpointsMax), 0);
    }

    StaticBehaviour(GameState gameState, int hitpoints) {
        super(gameState);

        hitpointsMax = hitpoints;
        this.hitpoints = hitpoints;
    }

    StaticBehaviour(StaticBehaviour behaviour, GameState newState) {
        super(behaviour, newState);

        this.hitpointsMax = behaviour.hitpointsMax;
        this.hitpoints = behaviour.hitpoints;
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

        hitpoints -= enemy.getStrength();

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

        if (hitpointsMax != that.hitpointsMax) return false;
        return hitpoints == that.hitpoints;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + hitpointsMax;
        result = 31 * result + hitpoints;
        return result;
    }

    @Override public boolean isAlive(Unit unit) {
        return hitpoints > 0;
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
                "hitpointsMax=" + hitpointsMax +
                ", hitpoints=" + hitpoints +
                "} " + super.toString();
    }

    @Override
    public int getAttackRange() {
        return 0;
    }
}
