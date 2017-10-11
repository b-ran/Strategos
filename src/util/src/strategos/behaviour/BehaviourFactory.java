package strategos.behaviour;


import strategos.model.GameState;

import java.util.function.*;


public interface BehaviourFactory {

    Behaviour createBehaviourArchers(GameState gameState);

    Behaviour createBehaviourCavalry(GameState gameState);

    Behaviour createBehaviourElite(GameState gameState);

    Behaviour createBehaviourSpearmen(GameState gameState);

    Behaviour createBehaviourSwordsmen(GameState gameState);

    Behaviour createBehaviourCastle(GameState gameState);

    Behaviour createBehaviourBridge(GameState gameState);

    Behaviour createBehaviourHealthPotion(GameState gameState);

    Behaviour createAiBehaviour(GameState gameState, Function<GameState, Behaviour> factoryMethod);
}
