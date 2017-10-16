package strategos.model;

import strategos.terrain.Terrain;

import java.io.Serializable;

public interface GameBoard extends Serializable {

	MapLocation[][] getData();

	MapLocation get(int x, int y);

	void set(int x, int y, MapLocation location);

	int getDiameter();

	Terrain getTerrainAt(MapLocation location);
}
