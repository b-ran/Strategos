package strategos.behaviour;


import strategos.GameState;
import strategos.behaviour.config.BehaviourConfig;
import strategos.units.Unit;


class BehaviourElite extends UnitBehaviour {

    //TODO: Where is your javadoc?

    BehaviourElite(GameState gameState) {
        super(gameState);
    }

    private BehaviourElite(BehaviourElite behaviourElite) {
        super(behaviourElite);
    }

    @Override
    public int getStrength(Unit unit) {
        return BehaviourConfig.ELITE_STRENGTH;
    }

    @Override
    public int getToughness(Unit unit) {
        return BehaviourConfig.ELITE_TOUGHNESS;
    }

    @Override
    public Behaviour copy() {
        return new BehaviourElite(this);
    }

    @Override
    public String toString() {
        return "BehaviourElite{} " + super.toString();
    }
}
