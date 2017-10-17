package strategos.networking;

import strategos.*;
import strategos.model.*;
import strategos.terrain.Terrain;
import strategos.units.Unit;

import java.util.List;
import java.util.Observer;


/**
 * This class is used for testing purposes only
 */
public class TestGameState implements GameState {
	SaveInstance instance;
	private boolean changed = false;

	@Override
	public void newGame() {
	}

	@Override
	public void save() {

	}

	@Override
	public void load(SaveInstance toRestore) {
		instance = toRestore;
		setChanged();
	}

	@Override
	public Unit getUnitAt(MapLocation location) {
		return null;
	}

	@Override
	public void move(Unit unit, Direction direction) {

	}

	@Override
	public void move(Unit unit, MapLocation mapLocation) {

	}

	@Override
	public void attack(Unit unit, int targetX, int targetY) {

	}

	@Override
	public void attack(Unit unit, MapLocation location) {

	}

	@Override
	public void wary(Unit unit) {

	}

	@Override
	public void entrench(Unit unit) {

	}

	@Override
	public List<Unit> getUnitsInRange(MapLocation location, int range) {
		return null;
	}

	@Override
	public List<Unit> getUnitsInAttackRange(Unit unit) {
		return null;
	}

	@Override
	public List<MapLocation> getTilesInMoveRange(Unit unit) {
		return null;
	}

	@Override
	public SaveInstance export() {
		return null;
	}

	@Override
	public Terrain getTerrainAt(MapLocation location) {
		return null;
	}

	@Override
	public List<MapLocation> getTilesInRange(MapLocation location, int range) {
		return null;
	}

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
		return null;
	}

	@Override
	public List<UnitOwner> getPlayers() {
		return null;
	}

	@Override
	public List<SaveInstance> getSaves() {
		return null;
	}

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
		changed = true;
	}

	@Override
	public boolean hasChanged() {
		return changed;
	}

	@Override
	public void notifyObservers(Object o) {

	}
}
