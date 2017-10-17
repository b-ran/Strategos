package strategos.behaviour;


import strategos.model.GameState;

import java.util.function.*;


/**
 * @author Devon Mortimer
 * The interface BehaviourFactory describes a factory class that generates
 * instances of {@link strategos.behaviour.Behaviour}.
 */
public interface BehaviourFactory {

    /**
     * Create archers behaviour.
     *
     * @param gameState the game state
     * @return the behaviour
     */
    Behaviour createBehaviourArchers(GameState gameState);

    /**
     * Create cavalry behaviour.
     *
     * @param gameState the game state
     * @return the behaviour
     */
    Behaviour createBehaviourCavalry(GameState gameState);

    /**
     * Create elite behaviour.
     *
     * @param gameState the game state
     * @return the behaviour
     */
    Behaviour createBehaviourElite(GameState gameState);

    /**
     * Create spearmen behaviour.
     *
     * @param gameState the game state
     * @return the behaviour
     */
    Behaviour createBehaviourSpearmen(GameState gameState);

    /**
     * Create swordsmen behaviour.
     *
     * @param gameState the game state
     * @return the behaviour
     */
    Behaviour createBehaviourSwordsmen(GameState gameState);

    /**
     * Create castle behaviour.
     *
     * @param gameState the game state
     * @return the behaviour
     */
    Behaviour createBehaviourCastle(GameState gameState);

    /**
     * Create bridge behaviour.
     *
     * @param gameState the game state
     * @return the behaviour
     */
    Behaviour createBehaviourBridge(GameState gameState);

    /**
     * Create health potion behaviour.
     *
     * @param gameState the game state
     * @return the behaviour
     */
    Behaviour createBehaviourHealthPotion(GameState gameState);

    /**
     * Create AI behaviour.
     *
     * An AI behaviour wraps a standard behaviour created by running the
     * provided factory method. The AI uses the
     * {@link strategos.behaviour.Behaviour#turnTick(strategos.units.Unit)}
     * method to perform the AI functionality after calling it on the wrapped
     * behaviour. The wrapped behaviour is used for all other operations.
     *
     * @param gameState the game state
     * @param factoryMethod the factory method producing a behaviour instance
     * @return the behaviour
     */
    Behaviour createAiBehaviour(GameState gameState, Function<GameState, Behaviour> factoryMethod);
}
