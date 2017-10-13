package strategos.behaviour;


import strategos.Config;
import strategos.model.GameState;
import strategos.units.Unit;

import java.util.logging.*;


class BehaviourCastle extends UnitBehaviour {

    private static Logger logger = Logger.getLogger("strategos.behaviour");

    //TODO: Where is your javadoc?

    BehaviourCastle(GameState gameState) {
        super(gameState);
    }

    private BehaviourCastle(BehaviourCastle behaviourCastle, GameState newState) {
        super(behaviourCastle, newState);
    }

    @Override public int getStrength(Unit unit) {
        return Config.CASTLE_STRENGTH;
    }

    @Override public int getToughness(Unit unit) {
        return Config.CASTLE_TOUGHNESS;
    }

    @Override public Behaviour copy(GameState newState) {
        return new BehaviourCastle(this, newState);
    }

    @Override public void charge(Unit unit) {
        // Archers cannot charge, so this blank method overrides the default
        // behaviour
    }

    @Override public int attack(Unit unit, Unit enemy) {
        logger.info(String.format("%s: range attack %s", this.getClass(), enemy));

        if (enemy == null) {
            throw new NullPointerException("Method attack() requires a non-null enemy");
        }

        if (!isAlive(unit) || !enemy.isAlive()) {
            logger.info(String.format("%s: cannot attack", this.getClass()));
            return 0;
        }

        if (getActionPoints(unit) <= 0) {
            logger.info(String.format("%s: not enough action points to attack", this.getClass()));
            return 0;
        }

        setActionPoints(getActionPoints(unit) - 1);
        enemy.defend(unit);

        return 0;
    }

    @Override
    int getMaxActionPoints() {
        return 0;
    }

    @Override
    public int getAttackRange() {
        return Config.CASTLE_RANGE;
    }

    @Override
    public String toString() {
        return "BehaviourArchers{} " + super.toString();
    }
}
