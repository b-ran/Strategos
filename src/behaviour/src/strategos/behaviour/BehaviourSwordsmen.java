package strategos.behaviour;


import strategos.*;
import strategos.behaviour.config.BehaviourConfig;
import strategos.units.*;


class BehaviourSwordsmen extends UnitBehaviour {

    //TODO: Where is your javadoc?

    BehaviourSwordsmen(GameState gameState) {
        super(gameState);
    }

    private BehaviourSwordsmen(BehaviourSwordsmen behaviourSwordsmen) {
        super(behaviourSwordsmen);
    }

    @Override public int getStrength(Unit unit) {
        return BehaviourConfig.SWORDSMEN_STRENGTH;
    }

    @Override public int getToughness(Unit unit) {
        return BehaviourConfig.SWORDSMEN_TOUGHNESS;
    }

    @Override public Behaviour copy() {
        return new BehaviourSwordsmen(this);
    }
}
