package strategos.behaviour;


import strategos.*;
import strategos.behaviour.config.Config;
import strategos.units.*;


class BehaviourElite extends UnitBehaviour {

    //TODO: Where is your javadoc?

    BehaviourElite(GameState gameState) {
        super(gameState);
    }

    private BehaviourElite(BehaviourElite behaviourElite) {
        super(behaviourElite);
    }

    @Override public int getStrength(Unit unit) {
        return Config.ELITE_STRENGTH;
    }

    @Override public int getToughness(Unit unit) {
        return Config.ELITE_TOUGHNESS;
    }

    @Override public Behaviour copy() {
        return new BehaviourElite(this);
    }
}
