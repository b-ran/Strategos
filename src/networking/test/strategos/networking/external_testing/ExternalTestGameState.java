package strategos.networking.external_testing;

import strategos.*;
import strategos.model.*;
import strategos.terrain.Terrain;
import strategos.units.Unit;

import java.util.List;
import java.util.Observer;

public class ExternalTestGameState implements GameState {

	private GameCollections world;
	private List<UnitOwner> players;

	public ExternalTestGameState(GameCollections world, List<UnitOwner> players) {
		this.world = world;
		this.players = players;
	}

	@Override
	public void newGame() {
	}

	@Override
	public void save() {

	}

	@Override
	public void load(SaveInstance toRestore) {
		world = toRestore.getWorld();
		players = toRestore.getPlayers();
	}

	/**
	 * Finds the Unit (if such a Unit exists) at a given index on the Hex board. If no unit is found at the index, it
	 * will return null.
	 *
	 * @param location
	 * @return the Unit at the position, if one exists. If no unit is found, it returns null.
	 */
	@Override
	public Unit getUnitAt(MapLocation location) {
		return null;
	}

	@Override
	public void move(Unit unit, Direction direction) {

	}

	/**
	 * Send a move command to the given Unit. Fail the command if the tile is impassable, already contains a Unit, or
	 * if the Unit does not have enough movement points to satisfy the amount commanded. If the amount is greater
	 * than the number of points, move the Unit its maximum  number of points. Assume that Units may only move in
	 * straight lines.
	 *
	 * @param unit        the unit to be moved. If this Unit is null, fail the command.
	 * @param mapLocation the map location to move too.
	 */
	@Override
	public void move(Unit unit, MapLocation mapLocation) {

	}

	/**
	 * Send an attack command to the given Unit, attacking the specified position. Delegate the attack to the Unit
	 * itself, which should fail if the target is out of range.
	 *
	 * @param unit    the unit to attack with. If this Unit is null, fail the command.
	 * @param targetX
	 * @param targetY
	 * @deprecated this method uses old integer coordinates. This method will be removed in a few versions in favour
	 * of using a MapLocation. This version converts the x and y coordinates to a MapLocation.
	 */
	@Override
	public void attack(Unit unit, int targetX, int targetY) {

	}

	/**
	 * Send an attack command to the given Unit, attacking the specified position. Delegate the attack to the Unit
	 * itself. If the target is out of range or invalid, the attack will fail.
	 *
	 * @param unit     the unit to attack with. If the attack is invalid fail the command.
	 * @param location
	 */
	@Override
	public void attack(Unit unit, MapLocation location) {

	}

	/**
	 * Puts the unit in the wary state. Delegates the command to the Unit itself.
	 *
	 * @param unit
	 */
	@Override
	public void wary(Unit unit) {

	}

	/**
	 * Puts the unit in the entrenched state. Delegates the command to the Unit itself.
	 *
	 * @param unit
	 */
	@Override
	public void entrench(Unit unit) {

	}

	/**
	 * Find all Units within a given number of tiles from a MapLocation.
	 *
	 * @param location
	 * @param range
	 * @return a List of Units within range.
	 */
	@Override
	public List<Unit> getUnitsInRange(MapLocation location, int range) {
		return null;
	}

	/**
	 * Find all Units within a attack range of a Unit.
	 *
	 * @param unit
	 * @return a List of Units within attack range.
	 */
	@Override
	public List<Unit> getUnitsInAttackRange(Unit unit) {
		return null;
	}

	/**
	 * Find all Tiles within a within move range of a Unit.
	 *
	 * @param unit
	 * @return a List of Units within move range.
	 */
	@Override
	public List<MapLocation> getTilesInMoveRange(Unit unit) {
		return null;
	}

	/**
	 * Packages the GameState's current information into a SaveInstance and returns it.
	 * The exported SaveInstance contains the World, the Players, and the current Player whose turn it is.
	 *
	 * @return a SaveInstance containing the data structures of the GameState.
	 */
	@Override
	public SaveInstance export() {
		return new ExternalTestSaveInstance(this, getWorld(), getPlayers(), getCurrentTurn());
	}

	/**
	 * Gets the Terrain at a given location. If the MapLocation is not inside the play area, the Terrain is expected to
	 * be a Mountain.
	 *
	 * @param location
	 * @return the Terrain at location
	 */
	@Override
	public Terrain getTerrainAt(MapLocation location) {
		return null;
	}

	/**
	 * Find all MapLocations within a given number of tiles from a MapLocation. Includes the MapLocation parameter.
	 *
	 * @param location
	 * @param range
	 * @return a List of MapLocations within range.
	 */
	@Override
	public List<MapLocation> getTilesInRange(MapLocation location, int range) {
		return null;
	}

	/**
	 * Resets values and prepares the game for the next player to act.
	 */
	@Override
	public void nextTurn() {

	}

	@Override
	public void setThisInstancePlayer(UnitOwner thisInstancePlayer) {
		
	}

	@Override
	public UnitOwner getThisInstancePlayer() {
		return null;
	}

	@Override
	public GameCollections getWorld() {
		return world;
	}

	@Override
	public List<UnitOwner> getPlayers() {
		return players;
	}

	@Override
	public List<SaveInstance> getSaves() {
		return null;
	}

	/**
	 * Gets the UnitOwner whose turn it is.
	 *
	 * @return a UnitOwner that is stored in currentTurn
	 */
	@Override
	public UnitOwner getCurrentTurn() {
		return null;
	}

	@Override
	public int getWinner() {
		return 0;
	}

	@Override
	public int getNumberTurns() {
		return 0;
	}

	@Override
	public void addObserver(Observer o) {

	}

	@Override
	public void setChanged() {

	}

	@Override
	public boolean hasChanged() {
		return false;
	}

	@Override
	public void notifyObservers(Object o) {

	}
}
