package strategos.model;

import strategos.Direction;
import strategos.units.Unit;

import java.util.List;

public interface EntityHandler {

	/**
	 * Finds the Unit (if such a Unit exists) at a given index on the Hex board. If no unit is found at the index, it
	 * 		will return null.
	 *
	 * @param location
	 * @return the Unit at the position, if one exists. If no unit is found, it returns null.
	 */
	Unit getUnitAt(MapLocation location);

	/**
	 * Send a move command to the given Unit. Fail the command if the tile is impassable, already contains a Unit, or
	 * 		if the Unit does not have enough movement points to satisfy the amount commanded. If the amount is greater
	 * 		than the number of points, move the Unit its maximum  number of points. Assume that Units may only move in
	 * 		straight lines.
	 *
	 * @param unit the unit to be moved. If this Unit is null, fail the command.
	 * @param direction the direction to move in, using the Direction enum in the Config file.
	 */
	void move(Unit unit, Direction direction);


	/**
	 * Send a move command to the given Unit. Fail the command if the tile is impassable, already contains a Unit, or
	 * 		if the Unit does not have enough movement points to satisfy the amount commanded. If the amount is greater
	 * 		than the number of points, move the Unit its maximum  number of points. Assume that Units may only move in
	 * 		straight lines.
	 *
	 * @param unit the unit to be moved. If this Unit is null, fail the command.
	 * @param mapLocation the map location to move too.
	 */
	void move(Unit unit, MapLocation mapLocation);

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
	void attack(Unit unit, int targetX, int targetY);

	/**
	 * Send an attack command to the given Unit, attacking the specified position. Delegate the attack to the Unit
	 * 		itself. If the target is out of range or invalid, the attack will fail.
	 *
	 * @param unit the unit to attack with. If the attack is invalid fail the command.
	 * @param location
	 */
	public void attack(Unit unit, MapLocation location);

	/**
	 * Puts the unit in the wary state. Delegates the command to the Unit itself.
	 *
	 * @param unit
	 */
	void wary(Unit unit);

	/**
	 * Puts the unit in the entrenched state. Delegates the command to the Unit itself.
	 *
	 * @param unit
	 */
	public void entrench(Unit unit);

	public void setThisInstancePlayer(UnitOwner thisInstancePlayer);

	/**
	 * This method sets the player of this instance of the Strategos game.
	 * @return
	 */
	UnitOwner getThisInstancePlayer();

	GameCollections getWorld();

	List<UnitOwner> getPlayers();
}
