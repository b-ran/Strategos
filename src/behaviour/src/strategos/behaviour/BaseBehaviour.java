package strategos.behaviour;


import strategos.*;
import strategos.units.*;


abstract class BaseBehaviour implements Behaviour {

    //TODO: Where is your javadoc?

    private final GameState gameState;
    private MapLocation position;

    BaseBehaviour(GameState gameState) {
        if (gameState == null) {
            throw new NullPointerException(
                    "BaseBehaviour constructor requires non-null gameState");
        }

        this.gameState = gameState;
    }

    BaseBehaviour(BaseBehaviour behaviour) {
        if (behaviour == null) {
            throw new NullPointerException(
                    "BaseBehaviour constructor requires non-null behaviour");
        }

        gameState = behaviour.gameState;
        position = behaviour.position;
    }

    @Override public MapLocation getPosition(Unit unit) {
        assert position != null
                : "Method getPosition() shouldn't be returning null";
        return position;
    }

    @Override public void setPosition(Unit unit, MapLocation position) {
        if (position == null) {
            throw new NullPointerException(
                    "Method setPosition() requires non-null position");
        }
        this.position = position;
    }

    final GameState getGameState() {
        return gameState;
    }
}
