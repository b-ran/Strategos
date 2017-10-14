package strategos.model.units;

import strategos.Paintable;
import strategos.behaviour.Behaviour;
import strategos.behaviour.BehaviourFactory;
import strategos.hexgrid.Map;
import strategos.mapGenerator.Generator;
import strategos.model.*;
import strategos.units.Bridge;
import strategos.units.HealthPotion;
import strategos.units.Unit;

import java.util.ArrayList;
import java.util.List;

import static strategos.Config.*;
import static strategos.Config.NUM_CAVALRY;
import static strategos.Config.NUM_ELITES;

public class StateCreator implements GameStateFactory {

	private final BehaviourFactory factory;
	private final Generator generator;

	public StateCreator(BehaviourFactory factory, Generator generator) {
		this.factory = factory;
		this.generator = generator;
	}

	@Override
	public GameState createNewState() {

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

		newState.getWorld().getAllUnits().addAll(barbarians.getUnits());

		for (UnitOwner player : newState.getPlayers()) {

			List<Unit> units = createUnits(player, newState);
			player.setUnits(units);
			newState.getWorld().getAllUnits().addAll(units);
		}

		return newState;
	}

	private List<Unit> createUnits(UnitOwner player, GameState newState) {
		if (player.isNPC()) {
			return createBarbarianUnits(player, newState);
		}

		MapLocation startLocation = newState.getWorld().getMap().get(MAP_DIAMETER / 2, 0);
		int direction = 1;

		if (newState.getPlayers().indexOf(player) == 0) {
			startLocation = newState.getWorld().getMap().get(MAP_DIAMETER / 2, MAP_DIAMETER - 1);
			direction = -1;
		}
		return createPlayerUnits(startLocation, player, newState, direction);
	}

	private List<Unit> createPlayerUnits(MapLocation startLocation, UnitOwner player, GameState newState, int direction) {
		List<Unit> units = new ArrayList<>();

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

		return units;
	}

	private List<Unit> createBarbarianUnits(UnitOwner player, GameState newState) {
		List<Unit> barbarianUnits = new ArrayList<>();

		Bridge bridge = new BridgeImpl(factory.createBehaviourBridge(newState), player, newState.getWorld().getMap().get(5,4));
		barbarianUnits.add(bridge);
		bridge = new BridgeImpl(factory.createBehaviourBridge(newState), player, newState.getWorld().getMap().get(10,9));
		barbarianUnits.add(bridge);
		bridge = new BridgeImpl(factory.createBehaviourBridge(newState), player, newState.getWorld().getMap().get(7,7));
		barbarianUnits.add(bridge);

		SwordsmenImpl barbSwordsman = (new SwordsmenImpl(factory.createAiBehaviour(newState, factory::createBehaviourSwordsmen), player, newState.getWorld().getMap().get(0, 10)));
		barbarianUnits.add(barbSwordsman);
		barbSwordsman = (new SwordsmenImpl(factory.createAiBehaviour(newState, factory::createBehaviourSwordsmen), player, newState.getWorld().getMap().get(0, 8)));
		barbarianUnits.add(barbSwordsman);
		barbSwordsman = (new SwordsmenImpl(factory.createAiBehaviour(newState, factory::createBehaviourSwordsmen), player, newState.getWorld().getMap().get(14, 4)));
		barbarianUnits.add(barbSwordsman);
		barbSwordsman = (new SwordsmenImpl(factory.createAiBehaviour(newState, factory::createBehaviourSwordsmen), player, newState.getWorld().getMap().get(14, 6)));
		barbarianUnits.add(barbSwordsman);
		HealthPotionImpl healthPotion = new HealthPotionImpl(factory.createBehaviourHealthPotion(newState), player, newState.getWorld().getMap().get(14, 5));
		barbarianUnits.add(healthPotion);
		healthPotion = new HealthPotionImpl(factory.createBehaviourHealthPotion(newState), player, newState.getWorld().getMap().get(0, 9));
		barbarianUnits.add(healthPotion);

		return barbarianUnits;
	}

	public Unit spawnBarbarian(double unitType, GameState gameState, MapLocation location) {
		if (unitType <= 1) {
			return new SwordsmenImpl(factory.createAiBehaviour(gameState, factory::createBehaviourSwordsmen), gameState.getPlayers().get(2), location);
		}
		if (unitType <= 2) {
			return new SpearmenImpl(factory.createAiBehaviour(gameState, factory::createBehaviourSpearmen), gameState.getPlayers().get(2), location);
		}
		if (unitType <= 3) {
			return new CavalryImpl(factory.createAiBehaviour(gameState, factory::createBehaviourCavalry), gameState.getPlayers().get(2), location);
		}
		if (unitType <= 4) {
			return new ArchersImpl(factory.createAiBehaviour(gameState, factory::createBehaviourArchers), gameState.getPlayers().get(2), location);
		}
		return new EliteImpl(factory.createAiBehaviour(gameState, factory::createBehaviourElite), gameState.getPlayers().get(2), location);
	}

}
