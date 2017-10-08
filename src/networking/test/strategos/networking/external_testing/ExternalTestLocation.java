package strategos.networking.external_testing;

import strategos.Direction;
import strategos.MapLocation;
import strategos.terrain.Terrain;

import java.util.Map;

public class ExternalTestLocation implements MapLocation {

	private int x;
	private int y;

	public ExternalTestLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

	/**
	 * Get the neighbour at the specified orientation relative to this MapLocation.
	 * Return a not in-play-area MapLocation if no neighbour exists at that position.
	 *
	 * @param direction - The Direction that the desired MapLocation is, relative to this MapLocation.
	 * @return A MapLocation at the specified Direction.
	 */
	@Override
	public MapLocation getNeighbour(Direction direction) {
		return null;
	}

	@Override
	public void addNeighbour(Direction direction, MapLocation location) {

	}

	/**
	 * Check if a given MapLocation can be moved into or selected.
	 *
	 * @return true if the MapLocation is in the play area, false, otherwise
	 */
	@Override
	public boolean isInPlayArea() {
		return false;
	}

	/**
	 * Gets the terrain contained by this Paintable.
	 *
	 * @return a Terrain object at this Paintable
	 */
	@Override
	public Terrain getTerrain() {
		return null;
	}

	@Override
	public void setTerrain(Terrain terrain) {

	}

	@Override
	public Map<Direction, MapLocation> getNeighbours() {
		return null;
	}
}
