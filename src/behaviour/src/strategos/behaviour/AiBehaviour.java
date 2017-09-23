package strategos.behaviour;


import strategos.*;
import strategos.units.*;
import sun.reflect.generics.reflectiveObjects.*;

import java.util.function.*;


class AiBehaviour extends BehaviourBase {

    AiBehaviour(
            GameState gameState,
            Unit unit,
            BiFunction<GameState, Unit, Behaviour> factoryMethod
    )
    {
        super(gameState, unit, factoryMethod);
    }

    @Override public void turnTick() {
        throw new NotImplementedException();
    }
}
