package strategos.behaviour;


import strategos.*;
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
        return Config.SWORDSMEN_STRENGTH;
    } //TODO: Maybe have your own config in your library

    @Override public int getToughness(Unit unit) {
        return Config.SWORDSMEN_TOUGHNESS;
    } //TODO: Maybe have your own config in your library

    @Override public Behaviour copy() {
        return new BehaviourSwordsmen(this);
    }
}
