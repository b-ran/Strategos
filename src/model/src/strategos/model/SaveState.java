package strategos.model;

import strategos.GameCollections;
import strategos.UnitOwner;
import strategos.hexgrid.Map;

import java.util.ArrayList;

public class SaveState {
	public final GameCollections world;
	public final ArrayList<UnitOwner> players;
	public final UnitOwner turn;

	public SaveState(GameCollections world, ArrayList<UnitOwner> players, UnitOwner turn) {
		this.world = new World(new Map(world.getMap().getData(), world.getMap().getRadius()),
				new ArrayList<>(world.getAllUnits()));

		this.players = new ArrayList<>(players);
		this.turn = this.players.get(players.indexOf(turn));
	}
}
