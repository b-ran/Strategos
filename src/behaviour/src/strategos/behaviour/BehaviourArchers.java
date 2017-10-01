package strategos.behaviour;


import strategos.*;
import strategos.units.*;


class BehaviourArchers extends UnitBehaviour {

    BehaviourArchers(GameState gameState) {
        super(gameState);
    }

    private BehaviourArchers(BehaviourArchers behaviourArchers) {
        super(behaviourArchers);
    }

    @Override public int getStrength(Unit unit) {
        return Config.ARCHERS_STRENGTH;
    }

    @Override public int getToughness(Unit unit) {
        return Config.ARCHERS_TOUGHNESS;
    }

    @Override public Behaviour copy() {
        return new BehaviourArchers(this);
    }

    @Override public void charge(Unit unit) {
        // Archers cannot charge
    }

    @Override public int attack(Unit unit, Unit enemy) {
        if (enemy == null) {
            throw new NullPointerException(
                    "Method attack() requires a non-null enemy");
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
}
