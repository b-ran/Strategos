package strategos.networking.external_testing;

import strategos.GameCollections;
import strategos.GameState;
import strategos.SaveInstance;
import strategos.UnitOwner;
import strategos.units.Unit;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ExternalTestSaveInstance implements SaveInstance {
	private final GameCollections world;
	private final List<UnitOwner> players;

	public ExternalTestSaveInstance(GameState newState, GameCollections world, List<UnitOwner> oldPlayers, UnitOwner turn) {

		List<Unit> allUnits = new ArrayList<>();

		players = new ArrayList<>();

		for (int i = 0; i < oldPlayers.size(); i++) {
			players.add(oldPlayers.get(i).copy(newState));
		}

		players.stream().map(UnitOwner::getUnits).forEach(allUnits::addAll);

		this.world = new ExternalTestWorld(
				new ExternalTestMap(world.getMap().getData().clone(),
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
		return null;
	}
}
