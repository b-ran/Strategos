package strategos.gamelogic;

import mapcreation.mapgeneration.TerrainGeneration;
import strategos.behaviour.BehaviourFactory;
import strategos.behaviour.BehaviourFactoryImpl;
import strategos.mapGenerator.Generator;
import strategos.model.GameState;
import strategos.model.GameStateFactory;
import strategos.model.StateCreator;
import strategos.networking.handlers.NetworkingHandlerImpl;
import strategos.ui.Ui;

public class GameRunner {

	private GameState gameState;

	public GameRunner() {
		Generator generator = new TerrainGeneration();
		BehaviourFactory factory = new BehaviourFactoryImpl();

		GameStateFactory stateCreator = new StateCreator(factory, generator);

		gameState = stateCreator.createNewState();

		Ui ui = new Ui(gameState, new NetworkingHandlerImpl());
	}

	public static void main(String[] args) {
		GameRunner g = new GameRunner();
	}
}
