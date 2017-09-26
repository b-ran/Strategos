package strategos.behaviour;


import strategos.*;
import strategos.units.*;


class BehaviourCavalry extends UnitBehaviour {

    BehaviourCavalry(GameState gameState, Unit unit) {
        super(gameState, unit);
    }

    @Override int getMaxActionPoints() {
        return Config.CAVALRY_ACTION_POINTS;
    }

    @Override public int getStrength() {
        return Config.CAVALRY_STRENGTH;
    }

    @Override public int getToughness() {
        return Config.CAVALRY_TOUGHNESS;
    }
}
