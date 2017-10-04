package strategos.behaviour;


import strategos.*;


class BehaviourHealthPotion extends StaticBehaviour {

    BehaviourHealthPotion(GameState gameState) {
        super(gameState);
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
