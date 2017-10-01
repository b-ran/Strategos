package strategos.behaviour;


import strategos.*;
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
    } //TODO: Maybe have your own config in your library

    @Override public int getToughness(Unit unit) {
        return Config.SPEARMEN_TOUGHNESS;
    } //TODO: Maybe have your own config in your library

    @Override public Behaviour copy() {
        return new BehaviourSpearmen(this);
    }
}
