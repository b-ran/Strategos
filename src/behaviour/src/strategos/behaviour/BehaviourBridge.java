package strategos.behaviour;


import strategos.*;


class BehaviourBridge extends StaticBehaviour {

    BehaviourBridge(GameState gameState) {
        super(gameState);
    }

    private BehaviourBridge(BehaviourBridge behaviour) {
        super(behaviour);
    }

    @Override public Behaviour copy() {
        return new BehaviourBridge(this);
    }
}
