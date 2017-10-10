package strategos.behaviour;


import strategos.*;
import strategos.behaviour.config.BehaviourConfig;


class BehaviourBridge extends StaticBehaviour {

    BehaviourBridge(GameState gameState) {
        super(gameState, BehaviourConfig.BRIDGE_HITPOINTS);
    }

    private BehaviourBridge(BehaviourBridge behaviour) {
        super(behaviour);
    }

    @Override public Behaviour copy() {
        return new BehaviourBridge(this);
    }

    @Override
    public String toString() {
        return "BehaviourBridge{} " + super.toString();
    }
}
