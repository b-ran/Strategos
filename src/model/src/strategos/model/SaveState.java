package strategos.model;

import strategos.hexgrid.Map;

import java.util.ArrayList;

public class SaveState {
	public final World world;
	public final ArrayList<Player> players;
	public final Player turn;

	public SaveState(World world, ArrayList<Player> players, Player turn) {
		this.world = new World(new Map(world.getMap().getData(), world.getMap().getRadius()),
				new ArrayList<>(world.getAllUnits()));

		this.players = new ArrayList<>(players);
		this.turn = this.players.get(players.indexOf(turn));
	}
}
