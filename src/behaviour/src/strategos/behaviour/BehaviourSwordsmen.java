package strategos.behaviour;


import strategos.behaviour.config.*;
import strategos.model.GameState;
import strategos.units.*;


class BehaviourSwordsmen extends UnitBehaviour {

    //TODO: Where is your javadoc?

    BehaviourSwordsmen(GameState gameState) {
        super(gameState);
    }

    private BehaviourSwordsmen(BehaviourSwordsmen behaviourSwordsmen, GameState newState) {
        super(behaviourSwordsmen, newState);
    }

    @Override public int getStrength(Unit unit) {
        return BehaviourConfig.SWORDSMEN_STRENGTH;
    }

    @Override public int getToughness(Unit unit) {
        return BehaviourConfig.SWORDSMEN_TOUGHNESS;
    }

    @Override public Behaviour copy(GameState newState) {
        return new BehaviourSwordsmen(this, newState);
    }

    @Override
    public String toString() {
        return "BehaviourSwordsmen{} " + super.toString();
    }
}
