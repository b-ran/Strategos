package strategos;

import strategos.terrain.Terrain;

public interface GameBoard {

	public MapLocation[][] getData();

	public MapLocation get(int x, int y);

	public void set(int x, int y, MapLocation location);

	public int getRadius();

	public Terrain getTerrainAt(MapLocation location);
}
