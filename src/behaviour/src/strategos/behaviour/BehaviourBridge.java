package strategos.behaviour;


import strategos.Config;
import strategos.model.GameState;


class BehaviourBridge extends StaticBehaviour {

    BehaviourBridge(GameState gameState) {
        super(gameState, Config.BRIDGE_HITPOINTS);
    }

    private BehaviourBridge(BehaviourBridge behaviour, GameState newState) {
        super(behaviour, newState);
    }

    @Override public Behaviour copy(GameState newState) {
        return new BehaviourBridge(this, newState);
    }

    @Override
    public String toString() {
        return "BehaviourBridge{} " + super.toString();
    }
}
