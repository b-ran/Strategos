package strategos.behaviour;


import strategos.*;
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
        return Config.CAVALRY_ACTION_POINTS;
    } //TODO: Maybe have your own config in your library

    @Override public int getStrength(Unit unit) {
        return Config.CAVALRY_STRENGTH;
    } //TODO: Maybe have your own config in your library

    @Override public int getToughness(Unit unit) {
        return Config.CAVALRY_TOUGHNESS;
    } //TODO: Maybe have your own config in your library

    @Override public Behaviour copy() {
        return new BehaviourCavalry(this);
    }
}
