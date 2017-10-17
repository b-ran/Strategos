package strategos.model;

import strategos.hexgrid.Map;
import strategos.units.Unit;

import java.util.ArrayList;
import java.util.List;

/**
 * A snapshot of the world at a given point. Used for packaging the game's world for storing as a save, or sending over
 * 		the network to the other instance.
 */
public class SaveState implements SaveInstance {
	private final GameCollections world;
	private final List<UnitOwner> players;
	private final UnitOwner turn;

	public SaveState(GameState newState, GameCollections world, List<UnitOwner> oldPlayers, UnitOwner turn) {

		List<Unit> allUnits = new ArrayList<>();

		players = new ArrayList<>();

		// deep copy player list
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
