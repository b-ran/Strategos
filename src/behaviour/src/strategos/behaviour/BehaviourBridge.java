package strategos.behaviour;


import strategos.*;
import strategos.behaviour.config.BehaviourConfig;


class BehaviourBridge extends StaticBehaviour {

    BehaviourBridge(GameState gameState) {
        super(gameState, BehaviourConfig.BRIDGE_HITPOINTS);
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
