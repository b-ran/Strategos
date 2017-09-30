package strategos.model;

import strategos.GameCollections;
import strategos.SaveInstance;
import strategos.UnitOwner;
import strategos.hexgrid.Map;
import strategos.units.Unit;

import java.util.ArrayList;
import java.util.List;

public class SaveState implements SaveInstance {
	private final GameCollections world;
	private final ArrayList<UnitOwner> players;
	private final UnitOwner turn;

	public SaveState(GameCollections world, ArrayList<UnitOwner> oldPlayers, UnitOwner turn) {

		this.players = new ArrayList<>();
		List<Unit> allUnits = new ArrayList<>();

		for (int i = 0; i < oldPlayers.size(); i++) {
			this.players.add(oldPlayers.get(i).copy());
			allUnits.addAll(this.players.get(i).getUnits());
		}

		this.turn = this.players.get(oldPlayers.indexOf(turn));

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
