package strategos.gamelogic;


import mapcreation.mapgeneration.TerrainGeneration;
import strategos.GameState;
import strategos.MapLocation;
import strategos.Paintable;
import strategos.UnitOwner;
import strategos.behaviour.Behaviour;
import strategos.behaviour.BehaviourFactory;
import strategos.behaviour.BehaviourFactoryImpl;
import strategos.hexgrid.Map;
import strategos.model.Player;
import strategos.model.Strategos;
import strategos.model.World;
import strategos.model.units.*;
import strategos.networking.handlers.NetworkingHandlerImpl;
import strategos.ui.Ui;
import strategos.units.Unit;

import java.util.ArrayList;
import java.util.List;

import static strategos.Config.*;

public class GameRunner {

	private Ui ui;
	private GameState gameState;
	private static BehaviourFactory factory;

	public GameRunner() {
		TerrainGeneration generator = new TerrainGeneration();
		factory = new BehaviourFactoryImpl();

		gameState = createNewState(generator);

		/*populateUnits(gameState.getPlayers());

		List<Unit> allUnits = new ArrayList<>();
		for (UnitOwner player : players) {
			allUnits.addAll(player.getUnits());
		}*/

//		world.setAllUnits(allUnits);

		ui = new Ui(gameState, new NetworkingHandlerImpl());
	}

	public void populateUnits(List<UnitOwner> unitOwners) {
		for (int i = 0; i < 2; i++) {
			unitOwners.get(i).setUnits(constructUnits(unitOwners.get(i)));
		}

		unitOwners.get(2).setUnits(constructBarbarianUnits(unitOwners.get(2)));
	}

	private List<Unit> constructUnits(UnitOwner player) {
		List<Unit> units = new ArrayList<>();
		MapLocation startLocation = gameState.getWorld().getMap().get(MAP_DIAMETER / 2, 1);

		if (gameState.getPlayers().indexOf(player) == 0) {
			startLocation = gameState.getWorld().getMap().get(MAP_DIAMETER / 2, MAP_DIAMETER - 1);
		}
		Behaviour behaviour = factory.createBehaviourSwordsmen(gameState);
		units.add(new SwordsmenImpl(behaviour, player, startLocation));

		/*units.add(new SwordsmenImpl(factory.createBehaviourSwordsmen(gameState), player));
		units.add(new SwordsmenImpl(factory.createBehaviourSwordsmen(gameState), player));

		units.add(new SpearmenImpl(factory.createBehaviourSpearmen(gameState), player));
		units.add(new SpearmenImpl(factory.createBehaviourSpearmen(gameState), player));

		units.add(new CavalryImpl(factory.createBehaviourCavalry(gameState), player));

		units.add(new EliteImpl(factory.createBehaviourElite(gameState), player));

		units.add(new ArchersImpl(factory.createBehaviourArchers(gameState), player));
		units.add(new ArchersImpl(factory.createBehaviourArchers(gameState), player));*/

		return units;
	}

	private List<Unit> constructBarbarianUnits(UnitOwner player) {
		List<Unit> units = new ArrayList<>();
		/*units.add(new SwordsmenImpl(factory.createAiBehaviour(gameState, factory::createBehaviourSwordsmen), player, gameState.getWorld().getMap().get()));
		units.add(new SwordsmenImpl(factory.createAiBehaviour(gameState, factory::createBehaviourSwordsmen), player));

		units.add(new SpearmenImpl(factory.createAiBehaviour(gameState, factory::createBehaviourSpearmen), player));
		units.add(new SpearmenImpl(factory.createAiBehaviour(gameState, factory::createBehaviourSpearmen), player));*/

		return units;
	}

	public static GameState createNewState(TerrainGeneration generator) {

		Map map = new Map(MAP_DIAMETER);
		Paintable[][] terrainMap = generator.populateMap(map.getData());

		for (int x = 0; x < terrainMap.length; x++) {
			for (int y = 0; y < terrainMap.length; y++) {
				map.getData()[x][y].setTerrain(terrainMap[x][y].getTerrain());
			}
		}

		UnitOwner playerOne = new Player(false);
		UnitOwner playerTwo = new Player(false);
		UnitOwner barbarians = new Player(true);
		GameState newState = new Strategos(new World(map, new ArrayList<>()), playerOne, playerTwo, barbarians);
		Behaviour behaviour = factory.createBehaviourArchers(newState);
		playerOne.getUnits().add(new ArchersImpl(behaviour, playerOne, newState.getWorld().getMap().get(3, 6)));
		behaviour = factory.createBehaviourSwordsmen(newState);
		playerTwo.getUnits().add(new SwordsmenImpl(behaviour, playerTwo, newState.getWorld().getMap().get(5, 6)));
		newState.getWorld().getAllUnits().addAll(playerOne.getUnits());
		newState.getWorld().getAllUnits().addAll(playerTwo.getUnits());
		/*for (UnitOwner player : newState.getPlayers()) {
			if (player.isNPC()) {
				continue;
			}
			List<Unit> units = new ArrayList<>();
			MapLocation startLocation = newState.getWorld().getMap().get(MAP_DIAMETER / 2, 0);
			int direction = 1;

			if (newState.getPlayers().indexOf(player) == 0) {
				startLocation = newState.getWorld().getMap().get(MAP_DIAMETER / 2, MAP_DIAMETER - 1);
				direction = -1;
			}

			for (int i = 0; i < NUM_SWORDSMEN; i++) {
				Behaviour behaviour = factory.createBehaviourSwordsmen(newState);
				units.add(new SwordsmenImpl(behaviour, player, newState.getWorld().getMap().get(startLocation.getX() + (i + 4) * direction, startLocation.getY() + (i * direction))));
			}
			for (int i = 0; i < NUM_SPEARMEN; i++) {
				Behaviour behaviour = factory.createBehaviourSpearmen(newState);
				units.add(new SpearmenImpl(behaviour, player, newState.getWorld().getMap().get(startLocation.getX() + (i + 3) * direction, startLocation.getY() + (i * direction))));
			}
			for (int i = 0; i < NUM_ARCHERS; i++) {
				Behaviour behaviour = factory.createBehaviourArchers(newState);
				units.add(new ArchersImpl(behaviour, player, newState.getWorld().getMap().get(startLocation.getX() + (i + 2) * direction, startLocation.getY() + (i * direction))));
			}
			for (int i = 0; i < NUM_CAVALRY; i++) {
				Behaviour behaviour = factory.createBehaviourCavalry(newState);
				units.add(new CavalryImpl(behaviour, player, newState.getWorld().getMap().get(startLocation.getX() + (i + 1) * direction, startLocation.getY() + (i * direction))));
			}
			for (int i = 0; i < NUM_ELITES; i++) {
				Behaviour behaviour = factory.createBehaviourElite(newState);
				units.add(new EliteImpl(behaviour, player, newState.getWorld().getMap().get(startLocation.getX(), startLocation.getY() + (i * direction))));
			}

			player.setUnits(units);
			newState.getWorld().getAllUnits().addAll(units);
		}*/

		return newState;
	}

	public void run() {
	}

	public static void main(String[] args) {
		GameRunner g = new GameRunner();
		g.run();
	}
}
