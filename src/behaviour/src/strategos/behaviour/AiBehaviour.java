package strategos.behaviour;


import strategos.*;
import strategos.units.*;

import java.util.*;
import java.util.function.*;

import static java.lang.Math.*;


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
        super.turnTick();

        Optional<Unit> nearest =
                getGameState().getUnitsInRange(getPosition(), getSightRadius())
                        .stream()
                        .min((a, b) -> {
                            double aX = getPosition().getX() -
                                        a.getPosition().getX();
                            double aY = getPosition().getY() -
                                        a.getPosition().getY();
                            double bX = getPosition().getX() -
                                        b.getPosition().getX();
                            double bY = getPosition().getY() -
                                        b.getPosition().getY();

                            return (int) (hypot(aX, aY) - hypot(bX, bY));
                        });

        if (nearest.isPresent()) {
            if (getGameState().getUnitsInRange(getPosition(), 1)
                    .contains(nearest.get()))
            {
                getGameState().attack(getUnit(), nearest.get().getPosition());
            }
            else {
                // TODO: Approach unit
            }
        }
        else {
            // TODO: Explore
        }
    }
}
