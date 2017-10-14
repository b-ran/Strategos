package strategos.model;

import strategos.Direction;
import strategos.Observable;
import strategos.terrain.Terrain;
import strategos.units.Unit;

import java.util.List;

public interface GameState extends Observable, EntityHandler, TileHandler {

	void newGame();

	public void save();

	public void load(SaveInstance toRestore);

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

	/**
	 * Packages the GameState's current information into a SaveInstance and returns it.
	 * The exported SaveInstance contains the World, the Players, and the current Player whose turn it is.
	 *
	 * @return a SaveInstance containing the data structures of the GameState.
	 */
	public SaveInstance export();

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
	 * Resets values and prepares the game for the next player to act.
	 */
	public void nextTurn();

	public List<SaveInstance> getSaves();

	/**
	 * Gets the UnitOwner whose turn it is.
	 * @return a UnitOwner that is stored in currentTurn
	 */
	public UnitOwner getCurrentTurn();

	int getWinner();
}
