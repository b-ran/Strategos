package strategos.behaviour;


import strategos.*;

import java.util.function.*;
import java.util.logging.*;


public class BehaviourFactoryImpl implements BehaviourFactory {

    private static Logger logger = Logger.getLogger("strategos.behaviour");

    //TODO: where is your javadoc?

    public BehaviourFactoryImpl(Level level, Handler handler) {
        logger.setLevel(level);
        logger.addHandler(handler);
    }

    public BehaviourFactoryImpl(Level level) {
        logger.setLevel(level);
        Handler handler = new ConsoleHandler();
        handler.setFormatter(new SimpleFormatter());
        handler.setLevel(level);
        logger.addHandler(handler);
    }

    public BehaviourFactoryImpl() {}

    @Override public Behaviour createBehaviourArchers(GameState gameState) {
        logger.info("Create BehaviourArchers with factory");
        return new BehaviourArchers(gameState);
    }

    @Override public Behaviour createBehaviourCavalry(GameState gameState) {
        logger.info("Create BehaviourCavalry with factory");
        return new BehaviourCavalry(gameState);
    }

    @Override public Behaviour createBehaviourElite(GameState gameState) {
        logger.info("Create BehaviourElite with factory");
        return new BehaviourElite(gameState);
    }

    @Override public Behaviour createBehaviourSpearmen(GameState gameState) {
        logger.info("Create BehaviourSpearmen with factory");
        return new BehaviourSpearmen(gameState);
    }

    @Override public Behaviour createBehaviourSwordsmen(GameState gameState) {
        logger.info("Create BehaviourSwordsmen with factory");
        return new BehaviourSwordsmen(gameState);
    }

    @Override
    public Behaviour createBehaviourCastle(GameState gameState) {
        logger.info("Create BehaviourCastle with factory");
        return new BehaviourBridge(gameState);
    }

    @Override public Behaviour createBehaviourBridge(GameState gameState) {
        logger.info("Create BehaviourBridge with factory");
        return new BehaviourBridge(gameState);
    }

    @Override public Behaviour createBehaviourHealthPotion(GameState gameState) {
        logger.info("Create BehaviourHealthPotion with factory");
        return new BehaviourHealthPotion(gameState);
    }

    @Override public Behaviour createAiBehaviour(GameState gameState, Function<GameState, Behaviour> factoryMethod) {
        logger.info(String.format("Create AiBehaviour with factory using %s inner behaviour", factoryMethod));
        return new AiBehaviour(gameState, factoryMethod);
    }
}
