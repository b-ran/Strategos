package strategos.behaviour;


import strategos.*;
import strategos.units.*;


abstract class BaseBehaviour implements Behaviour {

    private final GameState gameState;
    private final Unit      unit;

    BaseBehaviour(GameState gameState, Unit unit) {
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
