package strategos.behaviour;


import strategos.*;


class BehaviourHealthPotion extends StaticBehaviour {

    BehaviourHealthPotion(GameState gameState) {
        super(gameState);
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
