package strategos.model;

import strategos.GameCollections;
import strategos.GameState;
import strategos.SaveInstance;
import strategos.UnitOwner;
import strategos.hexgrid.Map;
import strategos.units.Unit;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.*;


public class SaveState implements SaveInstance {
	private final GameCollections world;
	private final List<UnitOwner> players;
	private final UnitOwner turn;

	public SaveState(GameState newState, GameCollections world, List<UnitOwner> oldPlayers, UnitOwner turn) {

		List<Unit> allUnits = new ArrayList<>();

		players = new ArrayList<>();

		for (int i = 0; i < oldPlayers.size(); i++) {
			players.add(oldPlayers.get(i).copy(newState));
		}

		players.stream().map(UnitOwner::getUnits).forEach(allUnits::addAll);

		this.turn = players.get(oldPlayers.indexOf(turn));

		this.world = new World(
				new Map(world.getMap().getData().clone(),
						world.getMap().getDiameter()),
				allUnits);
	}

	public GameCollections getWorld() {
		return world;
	}

	public List<UnitOwner> getPlayers() {
		return players;
	}

	public UnitOwner getTurn() {
		return turn;
	}
}
