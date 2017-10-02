package strategos.behaviour;


import strategos.*;
import strategos.units.*;


abstract class StaticBehaviour extends BaseBehaviour {

    private boolean isAlive;

    @Override public boolean getWary(Unit unit) {
        return false;
    }

    @Override public boolean getEntrench(Unit unit) {
        return false;
    }

    @Override public int getHitpoints(Unit unit) {
        return 1;
    }

    StaticBehaviour(GameState gameState) {
        super(gameState);

        isAlive = true;
    }

    StaticBehaviour(StaticBehaviour behaviour) {
        super(behaviour);

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

    @Override public boolean isAlive(Unit unit) {
        return isAlive;
    }

    @Override public int getSightRadius(Unit unit) {
        return 0;
    }

    @Override public int getActionPoints(Unit unit) {
        return 0;
    }
}
