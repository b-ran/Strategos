package strategos.model;

import strategos.GameCollections;
import strategos.SaveInstance;
import strategos.UnitOwner;
import strategos.hexgrid.Map;

import java.util.ArrayList;

public class SaveState implements SaveInstance {
	private final GameCollections world;
	private final ArrayList<UnitOwner> players;
	private final UnitOwner turn;

	public SaveState(GameCollections world, ArrayList<UnitOwner> players, UnitOwner turn) {
		this.world = new World(
				new Map(world.getMap().getData().clone(),
				world.getMap().getRadius()),
				new ArrayList<>(world.getAllUnits()));

		this.players = new ArrayList<>(players);

		for (UnitOwner p : this.players) {
			p.setUnits(p.getUnits());
	}
		this.turn = this.players.get(players.indexOf(turn));
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
