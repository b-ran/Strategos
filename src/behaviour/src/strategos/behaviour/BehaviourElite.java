package strategos.behaviour;


import strategos.*;
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
    } //TODO: Maybe have your own config in your library

    @Override public int getToughness(Unit unit) {
        return Config.ELITE_TOUGHNESS;
    } //TODO: Maybe have your own config in your library

    @Override public Behaviour copy() {
        return new BehaviourElite(this);
    }
}
