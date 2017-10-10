package strategos.behaviour;


import strategos.*;
import strategos.behaviour.config.BehaviourConfig;


class BehaviourHealthPotion extends StaticBehaviour {

    BehaviourHealthPotion(GameState gameState) {
        super(gameState, BehaviourConfig.POTION_HITPOINTS);
    }

    private BehaviourHealthPotion(BehaviourHealthPotion behaviourHealthPotion) {
        super(behaviourHealthPotion);
    }

    @Override public Behaviour copy() {
        return new BehaviourHealthPotion(this);
    }

    @Override
    public String toString() {
        return "BehaviourHealthPotion{} " + super.toString();
    }
}
