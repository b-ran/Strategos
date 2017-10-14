package strategos.model;

public interface GameStateFactory {

	/**
	 * Constructs a new GameState, repopulating it with units and barbarians
	 * @return
	 */
	GameState createNewState();
}
