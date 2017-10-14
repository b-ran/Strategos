package strategos.behaviour;


import strategos.Direction;
import strategos.model.GameState;
import strategos.units.Unit;

import java.util.logging.Logger;


abstract class StaticBehaviour extends BaseBehaviour {

    private static Logger logger = Logger.getLogger("strategos.behaviour");
    private int hitpointsMax;
    private int hitpoints;

    StaticBehaviour(GameState gameState, int hitpoints) {
        super(gameState);

        this.hitpointsMax = hitpoints;
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

    @Override public boolean getWary(Unit unit) {
        return false;
    }

    @Override public void entrench(Unit unit) {

    }

    @Override public boolean getEntrench(Unit unit) {
        return false;
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

        this.hitpoints -= enemy.getStrength();

        return 0;
    }

    @Override public int getStrength(Unit unit) {
        return 0;
    }

    @Override public int getToughness(Unit unit) {
        return 0;
    }

    @Override public int getHitpoints(Unit unit) {
        return Math.max(this.hitpoints * (100 / this.hitpointsMax), 0);
    }

    @Override public boolean isAlive(Unit unit) {
        return this.hitpoints > 0;
    }

    @Override public int getSightRadius(Unit unit) {
        return 0;
    }

    @Override public int getActionPoints(Unit unit) {
        return 0;
    }

    @Override public int getAttackRange() {
        return 0;
    }

    @Override public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + this.hitpointsMax;
        result = 31 * result + this.hitpoints;
        return result;
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        StaticBehaviour that = (StaticBehaviour) o;

        if (this.hitpointsMax != that.hitpointsMax) return false;
        return this.hitpoints == that.hitpoints;
    }

    @Override public String toString() {
        return "StaticBehaviour{" + "hitpointsMax=" + this.hitpointsMax + ", hitpoints=" + this.hitpoints + "} " +
               super.toString();
    }
}
