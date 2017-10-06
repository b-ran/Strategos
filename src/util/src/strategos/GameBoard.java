package strategos;

import strategos.terrain.Terrain;

import java.io.Serializable;

public interface GameBoard extends Serializable {

	public MapLocation[][] getData();

	public MapLocation get(int x, int y);

	public void set(int x, int y, MapLocation location);

	public int getDiameter();

	public Terrain getTerrainAt(MapLocation location);
}
