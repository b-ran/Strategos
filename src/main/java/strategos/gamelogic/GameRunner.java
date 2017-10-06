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
import strategos.ui.Ui;
import strategos.units.Unit;

import java.util.ArrayList;
import java.util.List;

import static strategos.Config.*;

public class GameRunner {

	private Ui ui;
	private GameState gameState;
	private BehaviourFactory factory;

	public GameRunner() {
		TerrainGeneration generator = new TerrainGeneration();
		factory = new BehaviourFactoryImpl();
		Map map = new Map(MAP_DIAMETER);
		Paintable[][] terrainMap = generator.populateMap(map.getData());

		for (int x = 0; x < terrainMap.length; x++) {
			for (int y = 0; y < terrainMap.length; y++) {
				map.getData()[x][y].setTerrain(terrainMap[x][y].getTerrain());
			}
		}

		List<UnitOwner> players = constructPlayers();

		World world = new World(map, new ArrayList<>());
		gameState = new Strategos(world, players.get(0), players.get(1), players.get(2));

		populateUnits(gameState.getPlayers());

		List<Unit> allUnits = new ArrayList<>();
		for (UnitOwner player : players) {
			allUnits.addAll(player.getUnits());
		}

		world.setAllUnits(allUnits);

		ui = new Ui(gameState);
	}

	public List<UnitOwner> constructPlayers() {
		List<UnitOwner> unitOwners = new ArrayList<>();
		unitOwners.add(new Player(false));
		unitOwners.add(new Player(false));
		unitOwners.add(new Player(true));

		return unitOwners;
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

	public void run() {
	}

	public static void main(String[] args) {
		GameRunner g = new GameRunner();
		g.run();
	}
}
