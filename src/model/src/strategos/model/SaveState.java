package strategos.model;

import strategos.GameCollections;
import strategos.SaveInstance;
import strategos.UnitOwner;
import strategos.hexgrid.Map;
import strategos.units.Unit;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.*;


public class SaveState implements SaveInstance {
	private final GameCollections world;
	private final ArrayList<UnitOwner> players;
	private final UnitOwner turn;

	// Visibility may be narrowed to package private
	public SaveState(GameCollections world, ArrayList<UnitOwner> oldPlayers, UnitOwner turn) {

		this.players = new ArrayList<>();
		List<Unit> allUnits = new ArrayList<>();

		// for loop may be replaced by streams
		//// players = oldPlayers.stream().map(UnitOwner::copy).collect(Collectors.toList());
		//// players.stream().map(UnitOwner::getUnits).forEach(allUnits::addAll);
		// remember to remove the redundant players assignment before
		for (int i = 0; i < oldPlayers.size(); i++) {
			this.players.add(oldPlayers.get(i).copy());
			allUnits.addAll(this.players.get(i).getUnits());
		}

		this.turn = this.players.get(oldPlayers.indexOf(turn));

		// already did this in the previous for loop
		// remove this loop
		for (UnitOwner u : this.players) {
			allUnits.addAll(u.getUnits());
		}
		this.world = new World(
				new Map(world.getMap().getData().clone(),
						world.getMap().getRadius()),
				allUnits);
	}

	public GameCollections getWorld() {
		return world;
	}

	public ArrayList<UnitOwner> getPlayers() {
		return players;
	}

	public UnitOwner getTurn() {
		return turn;
	}
}
