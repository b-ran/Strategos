package strategos.behaviour;


import strategos.*;
import strategos.units.*;


class BehaviourArchers extends UnitBehaviour {

    BehaviourArchers(GameState gameState, Unit unit) {
        super(gameState, unit);
    }

    @Override public int getStrength() {
        return 18;
    }

    @Override public int getToughness() {
        return 10;
    }

    @Override public void charge() {
        // Archers cannot charge
    }

    @Override public int attack(Unit enemy) {
        if (enemy == null) {
            throw new NullPointerException(
                    "Method attack() requires a non-null enemy");
        }

        if (!isAlive() || !enemy.isAlive()) {
            return 0;
        }

        if (getActionPoints() <= 0) {
            return 0;
        }

        setActionPoints(getActionPoints() - 1);
        enemy.defend(getUnit());

        return 0;
    }
}
