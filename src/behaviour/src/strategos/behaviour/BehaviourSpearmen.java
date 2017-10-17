package strategos.behaviour;


import strategos.Config;
import strategos.model.GameState;
import strategos.units.Unit;


/**
 * @author Devon Mortimer
 * Code reviewer: Brandon Scott-Hill
 */
class BehaviourSpearmen extends UnitBehaviour {

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
