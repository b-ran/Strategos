package strategos.behaviour;


import strategos.*;
import strategos.units.*;
import sun.reflect.generics.reflectiveObjects.*;

import java.util.function.*;


public class BehaviourFactoryImpl implements BehaviourFactory {


    @Override
    public Behaviour createBehaviourArchers(GameState gameState, Unit unit) {
        return new BehaviourArchers(gameState, unit);
    }

    @Override
    public Behaviour createBehaviourCavalry(GameState gameState, Unit unit) {
        throw new NotImplementedException();
    }

    @Override
    public Behaviour createBehaviourElite(GameState gameState, Unit unit) {
        throw new NotImplementedException();
    }

    @Override
    public Behaviour createBehaviourSpearmen(GameState gameState, Unit unit) {
        throw new NotImplementedException();
    }

    @Override
    public Behaviour createBehaviourSwordsmen(GameState gameState, Unit unit) {
        throw new NotImplementedException();
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
