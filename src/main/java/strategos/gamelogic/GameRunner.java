package strategos.gamelogic;

import mapcreation.mapgeneration.TerrainGeneration;
import strategos.behaviour.BehaviourFactory;
import strategos.behaviour.BehaviourFactoryImpl;
import strategos.mapGenerator.Generator;
import strategos.model.GameState;
import strategos.model.GameStateFactory;
import strategos.model.units.*;
import strategos.networking.handlers.NetworkingHandlerImpl;
import strategos.ui.Ui;

public class GameRunner {

	private GameRunner() {
		Generator generator = new TerrainGeneration();
		BehaviourFactory factory = new BehaviourFactoryImpl();

		GameStateFactory stateCreator = new StateCreator(factory, generator);

		GameState gameState = stateCreator.createNewState();

		new Ui(gameState, new NetworkingHandlerImpl());
	}

	public static void main(String[] args) {
		new GameRunner();
	}
}
