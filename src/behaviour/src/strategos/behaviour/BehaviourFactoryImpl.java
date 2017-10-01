package strategos.behaviour;


import strategos.*;

import java.util.function.*;


public class BehaviourFactoryImpl implements BehaviourFactory {

    //TODO: where is your javadoc?

    @Override public Behaviour createBehaviourArchers(GameState gameState) {
        return new BehaviourArchers(gameState);
    }

    @Override public Behaviour createBehaviourCavalry(GameState gameState) {
        return new BehaviourCavalry(gameState);
    }

    @Override public Behaviour createBehaviourElite(GameState gameState) {
        return new BehaviourElite(gameState);
    }

    @Override public Behaviour createBehaviourSpearmen(GameState gameState) {
        return new BehaviourSpearmen(gameState);
    }

    @Override public Behaviour createBehaviourSwordsmen(GameState gameState) {
        return new BehaviourSwordsmen(gameState);
    }

    //TODO: too much indentation make harder to read
    /*
    @Override public Behaviour createAiBehaviour(GameState gameState, Function<GameState, Behaviour> factoryMethod) {
        return new AiBehaviour(gameState, factoryMethod);
    }
     */

    @Override public Behaviour createAiBehaviour(
            GameState gameState, Function<GameState, Behaviour> factoryMethod
    )
    {
        return new AiBehaviour(gameState, factoryMethod);
    }
}
