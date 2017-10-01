package strategos.behaviour;


import strategos.*;


abstract class BaseBehaviour implements Behaviour {

    //TODO: Where is your javadoc?

    private final GameState gameState;

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
    }

    final GameState getGameState() {
        return gameState;
    }
}
