package strategos.behaviour;


import strategos.Config;
import strategos.model.GameState;
import strategos.units.Unit;


class BehaviourSpearmen extends UnitBehaviour {

    //TODO: Where is your javadoc?

    BehaviourSpearmen(GameState gameState) {
        super(gameState);
    }

    private BehaviourSpearmen(BehaviourSpearmen behaviourSpearmen, GameState newState) {
        super(behaviourSpearmen, newState);
    }

    @Override public int getStrength(Unit unit) {
        return Config.SPEARMEN_STRENGTH;
    }

    @Override public int getToughness(Unit unit) {
        return Config.SPEARMEN_TOUGHNESS;
    }

    @Override public Behaviour copy(GameState newState) {
        return new BehaviourSpearmen(this, newState);
    }

    @Override public String toString() {
        return "BehaviourSpearmen{} " + super.toString();
    }
}
