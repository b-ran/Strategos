package strategos.behaviour;


import strategos.*;


public class BehaviourHealthPotion extends StaticBehaviour {

    BehaviourHealthPotion(GameState gameState) {
        super(gameState);
    }

    private BehaviourHealthPotion(BehaviourHealthPotion behaviourHealthPotion) {
        super(behaviourHealthPotion);
    }

    @Override public Behaviour copy() {
        return new BehaviourHealthPotion(this);
    }
}
