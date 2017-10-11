package strategos.behaviour;


import strategos.*;
import strategos.units.*;

import java.util.logging.*;


abstract class BaseBehaviour implements Behaviour {

    //TODO: Where is your javadoc?

    private static Logger logger = Logger.getLogger("strategos.behaviour");

    private final transient GameState gameState;
    private       MapLocation position;

    BaseBehaviour(GameState gameState) {
        logger.fine(String.format("Create behaviour of type %s", this.getClass()));

        if (gameState == null) {
            throw new NullPointerException("BaseBehaviour constructor requires non-null gameState");
        }

        this.gameState = gameState;
    }

    BaseBehaviour(BaseBehaviour behaviour, GameState newState) {
        logger.fine(String.format("Copy behaviour %s", behaviour));

        if (behaviour == null) {
            throw new NullPointerException("BaseBehaviour constructor requires non-null behaviour");
        }

        gameState = newState;
        position = behaviour.position;
    }

    @Override public MapLocation getPosition(Unit unit) {
        assert position != null : "Method getPosition() shouldn't be returning null";
        return position;
    }

    @Override public void setPosition(Unit unit, MapLocation position) {
        if (position == null) {
            throw new NullPointerException("Method setPosition() requires non-null position");
        }
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseBehaviour that = (BaseBehaviour) o;

        if (!gameState.equals(that.gameState)) return false;
        return position != null ? position.equals(that.position) : that.position == null;

    }

    @Override
    public int hashCode() {
        int result = gameState.hashCode();
        result = 31 * result + (position != null ? position.hashCode() : 0);
        return result;
    }

    final GameState getGameState() {
        return gameState;
    }

    @Override
    public String toString() {
        return "BaseBehaviour{" +
                "position=" + position +
                '}';
    }
}
