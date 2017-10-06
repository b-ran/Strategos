package strategos.behaviour;


import strategos.*;
import strategos.behaviour.config.*;
import strategos.units.*;


class BehaviourCavalry extends UnitBehaviour {

    //TODO: Where is your javadoc?

    BehaviourCavalry(GameState gameState) {
        super(gameState);
    }

    private BehaviourCavalry(BehaviourCavalry behaviourCavalry) {
        super(behaviourCavalry);
    }

    @Override int getMaxActionPoints() {
        return BehaviourConfig.CAVALRY_ACTION_POINTS;
    }

    @Override public int getStrength(Unit unit) {
        return BehaviourConfig.CAVALRY_STRENGTH;
    }

    @Override public int getToughness(Unit unit) {
        return BehaviourConfig.CAVALRY_TOUGHNESS;
    }

    @Override public Behaviour copy() {
        return new BehaviourCavalry(this);
    }

    @Override
    public String toString() {
        return "BehaviourCavalry{} " + super.toString();
    }
}
