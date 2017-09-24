package strategos;

import strategos.terrain.Terrain;
import strategos.units.Unit;

import java.util.List;

public interface GameState {

	public void save();

	public void load(int saveIndex);

	/**
	 * Finds the Unit (if such a Unit exists) at a given index on the Hex board. If no unit is found at the index, it
	 * 		will return null.
	 *
	 * @param location
	 * @return the Unit at the position, if one exists. If no unit is found, it returns null.
	 */
	public Unit getUnitAt(MapLocation location);

	/**
	 * Send a move command to the given Unit. Fail the command if the tile is impassable, already contains a Unit, or
	 * 		if the Unit does not have enough movement points to satisfy the amount commanded. If the amount is greater
	 * 		than the number of points, move the Unit its maximum  number of points. Assume that Units may only move in
	 * 		straight lines.
	 *
	 * @param unit the unit to be moved. If this Unit is null, fail the command.
	 * @param direction the direction to move in, using the Direction enum in the Config file.
	 * @param amount the magnitude to move by, or the maximum number of action points left.
	 */
	public void move(Unit unit, Direction direction, int amount);

	/**
	 * Send an attack command to the given Unit, attacking the specified position. Delegate the attack to the Unit
	 * 		itself, which should fail if the target is out of range.
	 *
	 * @deprecated this method uses old integer coordinates. This method will be removed in a few versions in favour
	 * 		of using a MapLocation. This version converts the x and y coordinates to a MapLocation.
	 * @param unit the unit to attack with. If this Unit is null, fail the command.
	 * @param targetX
	 * @param targetY
	 */
	public void attack(Unit unit, int targetX, int targetY);

	/**
	 * Send an attack command to the given Unit, attacking the specified position. Delegate the attack to the Unit
	 * 		itself, which should fail if the target is out of range.
	 *
	 * @param unit the unit to attack with. If this Unit is null, fail the command.
	 * @param location
	 */
	public void attack(Unit unit, MapLocation location);

	public void wary(Unit unit);

	public void entrench(Unit unit);

	/**
	 * Find all Units within a given number of tiles from a MapLocation.
	 *
	 * @param location
	 * @param range
	 * @return a List of Units within range.
	 */
	public List<Unit> getUnitsInRange(MapLocation location, int range);

	/**
	 * Gets the Terrain at a given location. If the MapLocation is not inside the play area, the Terrain is expected to
	 * 		be a Mountain.
	 * @param location
	 * @return the Terrain at location
	 */
	public Terrain getTerrainAt(MapLocation location);

	public List<MapLocation> getTilesInRange(MapLocation location, int range);

	public void nextTurn();
}
