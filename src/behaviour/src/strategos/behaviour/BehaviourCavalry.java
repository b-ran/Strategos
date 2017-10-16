package strategos.behaviour;


import strategos.Config;
import strategos.model.GameState;
import strategos.units.Unit;


/**
 * @author Devon Mortimer
 * Code reviewer: Brandon Scott-Hill
 */
class BehaviourCavalry extends UnitBehaviour {

    BehaviourCavalry(GameState gameState) {
        super(gameState);
    }

    private BehaviourCavalry(BehaviourCavalry behaviourCavalry, GameState newState) {
        super(behaviourCavalry, newState);
    }

    @Override int getMaxActionPoints() {
        return Config.CAVALRY_ACTION_POINTS;
    }

    @Override public String toString() {
        return "BehaviourCavalry{} " + super.toString();
    }

    @Override public int getStrength(Unit unit) {
        return Config.CAVALRY_STRENGTH;
    }

    @Override public int getToughness(Unit unit) {
        return Config.CAVALRY_TOUGHNESS;
    }

    @Override public Behaviour copy(GameState newState) {
        return new BehaviourCavalry(this, newState);
    }
}
