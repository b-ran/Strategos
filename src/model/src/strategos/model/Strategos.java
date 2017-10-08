package strategos.model;

import strategos.*;
import strategos.hexgrid.Map;
import strategos.model.units.BridgeImpl;
import strategos.model.units.SwordsmenImpl;
import strategos.terrain.Terrain;
import strategos.units.Bridge;
import strategos.units.Unit;

import java.util.*;
import java.util.Observable;

/**
 * An implementation of GameState that handles the core running of the game. Does not interact with any of the other
 * 		libraries, but uses exposed interfaces to simulate commands on the model's aspects. Also contains implementations
 * 		of GameCollections and UnitOwners.
 */
public class Strategos implements GameState {
	private GameCollections world;
	private List<UnitOwner> players = new ArrayList<>();
	private UnitOwner turn;
	private List<Observer> observers = new ArrayList<>();
	private boolean changed = false;
	private UnitOwner thisInstancePlayer;

	private List<SaveInstance> saves = new ArrayList<>();

	public Strategos(World world, UnitOwner playerOne, UnitOwner playerTwo, UnitOwner barbarians) {
		this.world = world;
		players.add(playerOne);
		players.add(playerTwo);
		players.add(barbarians);
		turn = playerOne;
	}

	@Override
	public UnitOwner getThisInstancePlayer() {
		return thisInstancePlayer;
	}

	@Override
	public void setThisInstancePlayer(UnitOwner thisInstancePlayer) {
		this.thisInstancePlayer = thisInstancePlayer;
	}

	public void save() {
		if (saves.size() > 3) {
			saves.remove(saves.size() - 1);
		}
		saves.add(0, export());
	}

	@Override
	public SaveInstance export() {
		return new SaveState(world, players, turn);
	}

	public void load(SaveInstance toRestore) {

		this.world = toRestore.getWorld();
		this.players = toRestore.getPlayers();
		this.turn = toRestore.getTurn();
	}

	@Override
	public Unit getUnitAt(MapLocation location) {
		if (location == null) {
			return null;
		}
		for (Unit u : world.getAllUnits()) {
			if (u.getPosition().getX() == location.getX() && u.getPosition().getY() == location.getY()) {
				return u;
			}
		}
		return null;
	}

	@Override
	public void move(Unit unit, Direction direction, int amount) {
		amount = Math.min(amount, unit.getActionPoints());
		MapLocation currentPosition = unit.getPosition();
		while (amount != 0) {
			if (!currentPosition.getNeighbour(direction).isInPlayArea() || !canPassUnit(unit, currentPosition.getNeighbour(direction))) {
				break;
			}
			currentPosition = currentPosition.getNeighbour(direction);
			if (!unit.move(direction)) {
				break;
			}
			amount--;
			calculateVision(unit.getOwner());
		}
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
		if (getTilesInMoveRange(unit).contains(mapLocation)) {
			unit.move(directionFromNeighbour(unit.getPosition(), mapLocation));
			calculateVision(unit.getOwner());
		}
	}

	private Direction directionFromNeighbour(MapLocation origin, MapLocation neighbour) {
		for (java.util.Map.Entry<Direction, MapLocation> entry : origin.getNeighbours().entrySet()) {
			if (entry.getValue().equals(neighbour)) {
				return entry.getKey();
			}
		}
		return null;
	}

	private boolean canPassUnit(Unit mover, MapLocation moveTo) {
		Unit u = getUnitAt(moveTo);
		return u == null || (u instanceof Bridge && u.getOwner().equals(mover.getOwner()));
	}

	private void calculateVision(UnitOwner player) {
		for (Unit unit : player.getUnits()) {
			List<MapLocation> sightRange = getTilesInRange(unit.getPosition(), unit.getSightRadius());
			for (MapLocation tile : sightRange) {
				if (!player.getVisibleTiles().contains(tile)) {
					player.getVisibleTiles().add(tile);
				}
			}
		}
	}

	@Override
	public void attack(Unit unit, MapLocation location) {
		Unit target = getUnitAt(location);
		if (unit.getActionPoints() == 0) {
			return;
		}
		if (target == null) {
			return;
		}
		if (target.getOwner().equals(unit.getOwner())) {
			return;
		}
		unit.attack(target);
		cleanUp(unit, target);
		setChanged();
	}

	private void cleanUp(Unit unitA, Unit unitB) {
		if (unitB.getHitpoints() <= 0) {
			unitB.getOwner().getUnits().remove(unitB);
			world.getAllUnits().remove(unitB);
			if (unitB instanceof Bridge) {
				BridgeImpl newBridge = new BridgeImpl(unitB.getBehaviour(), unitA.getOwner(), unitB.getPosition());
				unitA.getOwner().getUnits().add(newBridge);
				world.getAllUnits().add(newBridge);
			}
		}
		if (unitA.getHitpoints() <= 0) {
			unitA.getOwner().getUnits().remove(unitA);
			world.getAllUnits().remove(unitA);
		}
	}

	@Override
	public void attack(Unit unit, int targetX, int targetY) {
		attack(unit, world.getMap().get(targetX, targetY));
	}

	@Override
	public void wary(Unit unit) {
		unit.wary();
	}

	@Override
	public void entrench(Unit unit) {
		unit.entrench();
	}

	@Override
	public List<Unit> getUnitsInRange(MapLocation location, int range) {

		List<Unit> units = new ArrayList<>();

		if (location.getY() < 0 || location.getY() > world.getMap().getData().length ||
				location.getX() < 0 || location.getY() > world.getMap().getData().length) {
			return units;
		}

		MapLocation centre = world.getMap().get(location.getX(), location.getY());
		for (int dX = -range; dX <= range; dX++) {

			int minValue = Math.max(-range, -dX - range);
			int maxValue = Math.min(range, -dX + range);

			for (int dY = minValue; dY <= maxValue; dY++) {
				int dZ = -dX - dY;

				int x = centre.getX() + dX;
				int y = centre.getY() + dZ;

				Unit potentialUnit = getUnitAt(world.getMap().get(x, y));
				if (!units.contains(potentialUnit) && potentialUnit != null) {
					units.add(potentialUnit);
				}
			}
		}
		return units;
	}

	@Override
	public List<Unit> getUnitsInAttackRange(Unit unit) {
		List<Unit> units = getUnitsInRange(unit.getPosition(), unit.getAttackRange());
		List<Unit> actualUnits = new ArrayList<>();
		for (Unit other : units) {
			if (other.equals(unit)) {
				continue;
			}
			if (other.getOwner() != unit.getOwner()) {
				actualUnits.add(other);
			}
		}
		return actualUnits;
	}

	@Override
	public List<MapLocation> getTilesInMoveRange(Unit unit) {
		List<MapLocation> potentialTiles = getTilesInRange(unit.getPosition(), 1);
		List<MapLocation> actualTiles = new ArrayList<>();
		if (unit.getActionPoints() == 0) {
			return actualTiles;
		}

		for (MapLocation tile : potentialTiles) {
			if (tile.isInPlayArea() && canPassUnit(unit, tile)) {
				actualTiles.add(tile);
			}
		}

		return actualTiles;
	}

	public List<MapLocation> getTilesInRange(MapLocation location, int range) {

		List<MapLocation> tiles = new ArrayList<>();

		MapLocation centre = world.getMap().get(location.getX(), location.getY());
		for (int dX = -range; dX <= range; dX++) {

			int minValue = Math.max(-range, -dX - range);
			int maxValue = Math.min(range, -dX + range);

			for (int dY = minValue; dY <= maxValue; dY++) {
				int dZ = -dX - dY;

				int x = centre.getX() + dX;
				int y = centre.getY() + dZ;

				MapLocation potentialTile = world.getMap().get(x, y);
				if (!tiles.contains(potentialTile) && potentialTile != null) {
					tiles.add(potentialTile);
				}
			}
		}
		return tiles;
	}

	@Override
	public Terrain getTerrainAt(MapLocation location) {
		return world.getMap().getTerrainAt(location);
	}

	@Override
	public void nextTurn() {
		turn.getUnits().removeIf(u -> !u.isAlive());

		turn.getUnits().forEach(Unit::turnTick);

		getPlayers().forEach(this::calculateVision);

		int turnIndex = players.indexOf(turn);
		turnIndex = (turnIndex + 1) % players.size();
		turn = players.get(turnIndex);
	}

	@Override
	public GameCollections getWorld() {
		for (UnitOwner player : players) {
			calculateVision(player);
		}
		return world;
	}

	@Override
	public List<UnitOwner> getPlayers() {
		return players;
	}

	@Override
	public List<SaveInstance> getSaves() {
		return saves;
	}

	@Override
	public UnitOwner getCurrentTurn() {
		return turn;
	}


	@Override
	public void addObserver(Observer o) {
		if (!observers.contains(o)) {
			observers.add(o);
		}
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
		observers.forEach(Observer::notify);
		changed = false;
	}
}
