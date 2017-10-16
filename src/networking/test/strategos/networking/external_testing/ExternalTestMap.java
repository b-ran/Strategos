package strategos.networking.external_testing;

import strategos.model.GameBoard;
import strategos.model.MapLocation;
import strategos.terrain.Terrain;

public class ExternalTestMap implements GameBoard{
	private final int diameter;
	private MapLocation[][] data;

	public ExternalTestMap(MapLocation[][] data, int diameter) {
		this.diameter = diameter;
		this.data = data;
	}

	@Override
	public MapLocation[][] getData() {
		return data;
	}

	@Override
	public MapLocation get(int x, int y) {
		return data[x][y];
	}

	@Override
	public void set(int x, int y, MapLocation location) {
		data[x][y] = location;
	}

	@Override
	public int getDiameter() {
		return diameter;
	}

	@Override
	public Terrain getTerrainAt(MapLocation location) {
		return null;
	}
}
