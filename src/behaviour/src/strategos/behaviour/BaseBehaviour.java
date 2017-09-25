package strategos.behaviour;


import strategos.*;
import strategos.units.*;


abstract class BaseBehaviour implements Behaviour {

    private final GameState gameState;
    private final Unit      unit;

    BaseBehaviour(GameState gameState, Unit unit) {
        if (gameState == null) {
            throw new NullPointerException(
                    "BaseBehaviour constructor requires non-null gameState");
        }
        if (unit == null) {
            throw new NullPointerException(
                    "BaseBehaviour constructor requires non-null unit");
        }

        this.gameState = gameState;
        this.unit = unit;
    }

    final GameState getGameState() {
        return gameState;
    }

    final Unit getUnit() {
        return unit;
    }
}
