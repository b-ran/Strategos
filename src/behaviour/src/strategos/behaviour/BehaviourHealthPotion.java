package strategos.behaviour;


import strategos.behaviour.config.BehaviourConfig;
import strategos.model.GameState;


class BehaviourHealthPotion extends StaticBehaviour {

    BehaviourHealthPotion(GameState gameState) {
        super(gameState, BehaviourConfig.POTION_HITPOINTS);
    }

    private BehaviourHealthPotion(BehaviourHealthPotion behaviourHealthPotion, GameState newState) {
        super(behaviourHealthPotion, newState);
    }

    @Override public Behaviour copy(GameState newState) {
        return new BehaviourHealthPotion(this, newState);
    }

    @Override
    public String toString() {
        return "BehaviourHealthPotion{} " + super.toString();
    }
}
