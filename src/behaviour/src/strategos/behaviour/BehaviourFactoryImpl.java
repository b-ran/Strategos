package strategos.behaviour;


import strategos.*;
import strategos.units.*;

import java.util.function.*;


public class BehaviourFactoryImpl implements BehaviourFactory {


    @Override
    public Behaviour createBehaviourArchers(GameState gameState, Unit unit) {
        return new BehaviourArchers(gameState, unit);
    }

    @Override
    public Behaviour createBehaviourCavalry(GameState gameState, Unit unit) {
        return new BehaviourCavalry(gameState, unit);
    }

    @Override
    public Behaviour createBehaviourElite(GameState gameState, Unit unit) {
        return new BehaviourElite(gameState, unit);
    }

    @Override
    public Behaviour createBehaviourSpearmen(GameState gameState, Unit unit) {
        return new BehaviourSpearmen(gameState, unit);
    }

    @Override
    public Behaviour createBehaviourSwordsmen(GameState gameState, Unit unit) {
        return new BehaviourSwordsmen(gameState, unit);
    }

    @Override public Behaviour createAiBehaviour(
            GameState gameState,
            Unit unit,
            BiFunction<GameState, Unit, Behaviour> factoryMethod
    )
    {
        return new AiBehaviour(gameState, unit, factoryMethod);
    }
}
