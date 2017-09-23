package strategos.behaviour;


import strategos.*;
import strategos.units.*;

import java.util.function.*;


public interface BehaviourFactory {

    Behaviour createBehaviourArchers(GameState gameState, Unit unit);

    Behaviour createBehaviourCavalry(GameState gameState, Unit unit);

    Behaviour createBehaviourElite(GameState gameState, Unit unit);

    Behaviour createBehaviourSpearmen(GameState gameState, Unit unit);

    Behaviour createBehaviourSwordsmen(GameState gameState, Unit unit);

    Behaviour createAiBehaviour(GameState gameState, Unit unit, BiFunction<GameState, Unit, Behaviour> factoryMethod);
}
