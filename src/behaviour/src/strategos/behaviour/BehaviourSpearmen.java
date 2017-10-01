package strategos.behaviour;


import strategos.*;
import strategos.behaviour.config.Config;
import strategos.units.*;


class BehaviourSpearmen extends UnitBehaviour {

    //TODO: Where is your javadoc?

    BehaviourSpearmen(GameState gameState) {
        super(gameState);
    }

    private BehaviourSpearmen(BehaviourSpearmen behaviourSpearmen) {
        super(behaviourSpearmen);
    }

    @Override public int getStrength(Unit unit) {
        return Config.SPEARMEN_STRENGTH;
    }

    @Override public int getToughness(Unit unit) {
        return Config.SPEARMEN_TOUGHNESS;
    }

    @Override public Behaviour copy() {
        return new BehaviourSpearmen(this);
    }
}
