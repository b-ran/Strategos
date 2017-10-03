package strategos.behaviour;


import strategos.*;
import strategos.behaviour.config.*;
import strategos.units.*;


class BehaviourArchers extends UnitBehaviour {

    //TODO: Where is your javadoc?

    BehaviourArchers(GameState gameState) {
        super(gameState);
    }

    private BehaviourArchers(BehaviourArchers behaviourArchers) {
        super(behaviourArchers);
    }

    @Override public int getStrength(Unit unit) {
        return BehaviourConfig.ARCHERS_STRENGTH;
    }

    @Override public int getToughness(Unit unit) {
        return BehaviourConfig.ARCHERS_TOUGHNESS;
    }

    @Override public Behaviour copy() {
        return new BehaviourArchers(this);
    }

    @Override public void charge(Unit unit) {
        // Archers cannot charge, so this blank method overrides the default
        // behaviour
    }

    @Override public int attack(Unit unit, Unit enemy) {
        if (enemy == null) {
            throw new NullPointerException("Method attack() requires a non-null enemy");
        }

        if (!isAlive(unit) || !enemy.isAlive()) {
            return 0;
        }

        if (getActionPoints(unit) <= 0) {
            return 0;
        }

        setActionPoints(getActionPoints(unit) - 1);
        enemy.defend(unit);

        return 0;
    }

    @Override
    public int getAttackRange() {
        return BehaviourConfig.ARCHER_RANGE;
    }
}
