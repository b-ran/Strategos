package strategos.behaviour;


import strategos.Config;
import strategos.model.GameState;
import strategos.units.Unit;


/**
 * @author Devon Mortimer
 * Code reviewer: Brandon Scott-Hill
 */
class BehaviourElite extends UnitBehaviour {

    //TODO: Where is your javadoc?

    BehaviourElite(GameState gameState) {
        super(gameState);
    }

    private BehaviourElite(BehaviourElite behaviourElite, GameState newState) {
        super(behaviourElite, newState);
    }

    @Override public int getStrength(Unit unit) {
        return Config.ELITE_STRENGTH;
    }

    @Override public int getToughness(Unit unit) {
        return Config.ELITE_TOUGHNESS;
    }

    @Override public Behaviour copy(GameState newState) {
        return new BehaviourElite(this, newState);
    }

    @Override public String toString() {
        return "BehaviourElite{} " + super.toString();
    }
}
