package strategos.behaviour;


import strategos.*;
import strategos.units.*;


class BehaviourCavalry extends UnitBehaviour {

    BehaviourCavalry(GameState gameState) {
        super(gameState);
    }

    private BehaviourCavalry(BehaviourCavalry behaviourCavalry) {
        super(behaviourCavalry);
    }

    @Override int getMaxActionPoints() {
        return Config.CAVALRY_ACTION_POINTS;
    }

    @Override public int getStrength(Unit unit) {
        return Config.CAVALRY_STRENGTH;
    }

    @Override public int getToughness(Unit unit) {
        return Config.CAVALRY_TOUGHNESS;
    }

    @Override public Behaviour copy() {
        return new BehaviourCavalry(this);
    }
}
