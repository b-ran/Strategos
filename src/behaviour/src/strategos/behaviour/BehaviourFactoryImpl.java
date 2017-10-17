package strategos.behaviour;


import strategos.model.GameState;

import java.util.function.Function;
import java.util.logging.*;


/**
 * @author Devon Mortimer
 * Code reviewer: Brandon Scott-Hill
 */
public class BehaviourFactoryImpl implements BehaviourFactory {

    private static Logger logger = Logger.getLogger("strategos.behaviour");

    public BehaviourFactoryImpl(Level level, Handler handler) {
        this();
        logger.setLevel(level);
        logger.addHandler(handler);
    }

    public BehaviourFactoryImpl() {
        logger.setUseParentHandlers(false);
    }

    public BehaviourFactoryImpl(Level level) {
        this();
        logger.setLevel(level);
        Handler handler = new ConsoleHandler();
        handler.setFormatter(new SimpleFormatter());
        handler.setLevel(level);
        logger.addHandler(handler);
    }

    @Override public Behaviour createBehaviourArchers(GameState gameState) {
        logger.fine("Create BehaviourArchers with factory");
        return new BehaviourArchers(gameState);
    }

    @Override public Behaviour createBehaviourCavalry(GameState gameState) {
        logger.fine("Create BehaviourCavalry with factory");
        return new BehaviourCavalry(gameState);
    }

    @Override public Behaviour createBehaviourElite(GameState gameState) {
        logger.fine("Create BehaviourElite with factory");
        return new BehaviourElite(gameState);
    }

    @Override public Behaviour createBehaviourSpearmen(GameState gameState) {
        logger.fine("Create BehaviourSpearmen with factory");
        return new BehaviourSpearmen(gameState);
    }

    @Override public Behaviour createBehaviourSwordsmen(GameState gameState) {
        logger.fine("Create BehaviourSwordsmen with factory");
        return new BehaviourSwordsmen(gameState);
    }

    @Override public Behaviour createBehaviourCastle(GameState gameState) {
        logger.fine("Create BehaviourCastle with factory");
        return new BehaviourBridge(gameState);
    }

    @Override public Behaviour createBehaviourBridge(GameState gameState) {
        logger.fine("Create BehaviourBridge with factory");
        return new BehaviourBridge(gameState);
    }

    @Override public Behaviour createBehaviourHealthPotion(GameState gameState) {
        logger.fine("Create BehaviourHealthPotion with factory");
        return new BehaviourHealthPotion(gameState);
    }

    @Override public Behaviour createAiBehaviour(GameState gameState, Function<GameState, Behaviour> factoryMethod) {
        logger.fine(String.format("Create AiBehaviour with factory using %s inner behaviour", factoryMethod));
        return new AiBehaviour(gameState, factoryMethod);
    }
}
