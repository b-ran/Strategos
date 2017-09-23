package strategos;

import strategos.units.Unit;

import java.util.List;

public interface GameState {

	public void save();

	public void load();

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

	public void attack(Unit unit, int targetX, int targetY);

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

	public void nextTurn();
}
