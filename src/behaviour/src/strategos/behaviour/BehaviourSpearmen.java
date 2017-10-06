package strategos.behaviour;


import strategos.GameState;
import strategos.behaviour.config.BehaviourConfig;
import strategos.units.Unit;


class BehaviourSpearmen extends UnitBehaviour {

    //TODO: Where is your javadoc?

    BehaviourSpearmen(GameState gameState) {
        super(gameState);
    }

    private BehaviourSpearmen(BehaviourSpearmen behaviourSpearmen) {
        super(behaviourSpearmen);
    }

    @Override
    public int getStrength(Unit unit) {
        return BehaviourConfig.SPEARMEN_STRENGTH;
    }

    @Override
    public int getToughness(Unit unit) {
        return BehaviourConfig.SPEARMEN_TOUGHNESS;
    }

    @Override
    public Behaviour copy() {
        return new BehaviourSpearmen(this);
    }

    @Override
    public String toString() {
        return "BehaviourSpearmen{} " + super.toString();
    }
}
