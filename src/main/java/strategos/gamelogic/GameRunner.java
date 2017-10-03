package strategos.gamelogic;


import mapcreation.mapgeneration.TerrainGeneration;
import strategos.GameState;
import strategos.Paintable;
import strategos.UnitOwner;
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

	public GameRunner() {
		TerrainGeneration generator = new TerrainGeneration();
		Map map = new Map(MAP_DIAMETER);
		Paintable[][] terrainMap = generator.populateMap(map.getData());

		for (int x = 0; x < terrainMap.length; x++) {
			for (int y = 0; y < terrainMap.length; y++) {
				map.getData()[x][y].setTerrain(terrainMap[x][y].getTerrain());
			}
		}

		List<UnitOwner> players = constructPlayers();
		List<Unit> allUnits = new ArrayList<>();
		for (UnitOwner player : players) {
			allUnits.addAll(player.getUnits());
		}
		World world = new World(map, allUnits);
		gameState = new Strategos(world, players.get(0), players.get(1), players.get(2));
		ui = new Ui(gameState);
	}

	public static List<UnitOwner> constructPlayers() {
		List<UnitOwner> unitOwners = new ArrayList<>();
		unitOwners.add(new Player(false));
		unitOwners.add(new Player(false));
		unitOwners.add(new Player(true));

		for (int i = 0; i < 2; i++) {
			unitOwners.get(i).setUnits(constructUnits(unitOwners.get(i)));
		}

		unitOwners.get(2).setUnits(constructBarbarianUnits(unitOwners.get(2)));

		return unitOwners;
	}

	private static List<Unit> constructUnits(UnitOwner player) {
		List<Unit> units = new ArrayList<>();
		units.add(new SwordsmenImpl(null, player));
		units.add(new SwordsmenImpl(null, player));
		units.add(new SpearmenImpl(null, player));
		units.add(new SpearmenImpl(null, player));
		units.add(new CavalryImpl(null, player));
		units.add(new EliteImpl(null, player));
		units.add(new ArchersImpl(null, player));
		units.add(new ArchersImpl(null, player));

		return units;
	}

	private static List<Unit> constructBarbarianUnits(UnitOwner player) {
		List<Unit> units = new ArrayList<>();
		units.add(new SwordsmenImpl(null, player));
		units.add(new SwordsmenImpl(null, player));
		units.add(new SpearmenImpl(null, player));
		units.add(new SpearmenImpl(null, player));

		return units;
	}

	public void run() {
	}

	public static void main(String[] args) {
		GameRunner g = new GameRunner();
		g.run();
	}
}
