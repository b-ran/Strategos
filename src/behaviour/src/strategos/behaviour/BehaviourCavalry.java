package strategos.behaviour;


import strategos.Config;
import strategos.model.GameState;
import strategos.units.*;


class BehaviourCavalry extends UnitBehaviour {

    //TODO: Where is your javadoc?

    BehaviourCavalry(GameState gameState) {
        super(gameState);
    }

    private BehaviourCavalry(BehaviourCavalry behaviourCavalry, GameState newState) {
        super(behaviourCavalry, newState);
    }

    @Override int getMaxActionPoints() {
        return Config.CAVALRY_ACTION_POINTS;
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

    @Override
    public String toString() {
        return "BehaviourCavalry{} " + super.toString();
    }
}
