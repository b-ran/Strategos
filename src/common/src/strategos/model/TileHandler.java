package strategos.model;

import strategos.terrain.Terrain;
import strategos.units.Unit;

import java.util.List;

public interface TileHandler {

	/**
	 * Gets the Terrain at a given location. If the MapLocation is not inside the play area, the Terrain is expected to
	 * 		be a Mountain.
	 * @param location
	 * @return the Terrain at location
	 */
	public Terrain getTerrainAt(MapLocation location);

	/**
	 * Find all MapLocations within a given number of tiles from a MapLocation. Includes the MapLocation parameter.
	 *
	 * @param location
	 * @param range
	 * @return a List of MapLocations within range.
	 */
	public List<MapLocation> getTilesInRange(MapLocation location, int range);


	/**
	 * Find all Units within a given number of tiles from a MapLocation.
	 *
	 * @param location
	 * @param range
	 * @return a List of Units within range.
	 */
	public List<Unit> getUnitsInRange(MapLocation location, int range);

	/**
	 * Find all Units within a attack range of a Unit.
	 *
	 * @param unit
	 * @return a List of Units within attack range.
	 */
	public List<Unit> getUnitsInAttackRange(Unit unit);

	/**
	 * Find all Tiles within a within move range of a Unit.
	 *
	 * @param unit
	 * @return a List of Units within move range.
	 */
	public List<MapLocation> getTilesInMoveRange(Unit unit);

}
