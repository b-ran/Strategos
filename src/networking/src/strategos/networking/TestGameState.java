package strategos.networking;

import strategos.*;
import strategos.terrain.Terrain;
import strategos.units.Unit;

import java.util.List;


/**
 * This class is used for testing purposes only
 */
public class TestGameState implements GameState {
	public static TestGameState instance;

	static {
		instance = new TestGameState();
	}

	@Override
	public void save() {

	}

	@Override
	public void load(SaveInstance toRestore) {

	}

	@Override
	public Unit getUnitAt(MapLocation location) {
		return null;
	}

	@Override
	public void move(Unit unit, Direction direction, int amount) {

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
	public GameCollections getWorld() {
		return null;
	}
}
