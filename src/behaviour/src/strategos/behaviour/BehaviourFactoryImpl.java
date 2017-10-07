package strategos.behaviour;


import strategos.*;

import java.util.function.*;
import java.util.logging.*;


public class BehaviourFactoryImpl implements BehaviourFactory {

    //TODO: where is your javadoc?

    public BehaviourFactoryImpl(Level level, Handler handler) {
        Logger logger = Logger.getLogger("strategos.behaviour");
        logger.setLevel(level);
        logger.addHandler(handler);
    }

    public BehaviourFactoryImpl(Level level) {
        Logger logger = Logger.getLogger("strategos.behaviour");
        logger.setLevel(level);
        Handler handler = new ConsoleHandler();
        handler.setFormatter(new SimpleFormatter());
        handler.setLevel(level);
        logger.addHandler(handler);
    }

    public BehaviourFactoryImpl() {}

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

    @Override
    public Behaviour createBehaviourCastle(GameState gameState) {
        return new BehaviourBridge(gameState);
    }

    @Override public Behaviour createBehaviourBridge(GameState gameState) {
        return new BehaviourBridge(gameState);
    }

    @Override public Behaviour createBehaviourHealthPotion(GameState gameState) {
        return new BehaviourHealthPotion(gameState);
    }

    @Override public Behaviour createAiBehaviour(GameState gameState, Function<GameState, Behaviour> factoryMethod) {
        return new AiBehaviour(gameState, factoryMethod);
    }
}
