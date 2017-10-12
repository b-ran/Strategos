package strategos.model;

import strategos.*;
import strategos.model.units.BridgeImpl;
import strategos.model.units.StateCreator;
import strategos.terrain.Terrain;
import strategos.units.Bridge;
import strategos.units.Unit;

import java.util.*;

/**
 * An implementation of GameState that handles the core running of the game. Does not interact with any of the other
 * libraries, but uses exposed interfaces to simulate commands on the model's aspects. Also contains implementations
 * of GameCollections and UnitOwners.
 */
public class Strategos implements GameState {
	private GameCollections world;
	private List<UnitOwner> players = new ArrayList<>();
	private UnitOwner turn;
	private List<Observer> observers = new ArrayList<>();
	private boolean changed = false;
	private UnitOwner thisInstancePlayer;
	private boolean synced = false;

	private StateCreator stateCreator;

	private int turns = 0;

	private List<SaveInstance> saves = new ArrayList<>();

	public Strategos(StateCreator stateCreator, World world, UnitOwner playerOne, UnitOwner playerTwo, UnitOwner barbarians) {
		this.world = world;
		players.add(playerOne);
		players.add(playerTwo);
		players.add(barbarians);
		turn = playerOne;
		this.stateCreator = stateCreator;
	}

	@Override
	public UnitOwner getThisInstancePlayer() {
		return thisInstancePlayer;
	}

	@Override
	public void setThisInstancePlayer(UnitOwner thisInstancePlayer) {
		this.thisInstancePlayer = thisInstancePlayer;
	}

	@Override
	public GameState newGame() {
		return stateCreator.createNewState();
	}

	public void save() {
		if (saves.size() > 3) {
			saves.remove(saves.size() - 1);
		}
		saves.add(0, export());
	}

	@Override
	public SaveInstance export() {
		return new SaveState(this, world, players, turn);
	}

	public void load(SaveInstance toRestore) {
		int index = players.indexOf(getThisInstancePlayer());
		this.world = toRestore.getWorld();
		this.players = toRestore.getPlayers();
		this.turn = toRestore.getTurn();
		setThisInstancePlayer(players.get(index));
		calculateVision(thisInstancePlayer);

		for (Unit u : world.getAllUnits()) {
			u.setBehaviour(u.getBehaviour().copy(this));
		}

		setChanged();
		if (synced) {
			turn.getUnits().forEach(Unit::turnTick);
		}
		notifyObservers(null);
		synced = true;
	}

	@Override
	public Unit getUnitAt(MapLocation location) {
		if (location == null) {
			return null;
		}
		Unit potentialUnit = null;
		for (Unit u : world.getAllUnits()) {
			if (u.getPosition().getX() == location.getX() && u.getPosition().getY() == location.getY()) {
				potentialUnit = u;
				if (!(potentialUnit instanceof Bridge)) {
					return potentialUnit;
				}
			}
		}
		return potentialUnit;
	}

	@Override
	public void move(Unit unit, Direction direction) {
		move(unit, unit.getPosition().getNeighbour(direction));
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
		MapLocation newLocation = world.getMap().get(mapLocation.getX(), mapLocation.getY());
		if (getTilesInMoveRange(unit).contains(newLocation)) {
			unit.move(directionFromNeighbour(unit.getPosition(), newLocation));
			calculateVision(unit.getOwner());
		}
		notifyObservers(null);
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
					player.addVisibleTile(tile);
				}
			}
		}
	}

	@Override
	public void attack(Unit unit, MapLocation location) {
		Unit target = getUnitAt(location);
		// If a unit lacks the AP to attack
		if (unit.getActionPoints() <= 0) {
			return;
		}
		// If the unit is attacking an empty tile
		if (target == null) {
			return;
		}
		// If the unit is attacking an ally
		if (target.getOwner().equals(unit.getOwner())) {
			return;
		}
		int defenderHP = target.getHitpoints();
		int attackerHP = unit.getHitpoints();
		unit.attack(target);
		if (!(attackerHP == unit.getHitpoints() && defenderHP == target.getHitpoints())) {
			System.out.println(unit + " attacked " + target + " {");
			System.out.println("	attacker took " + (attackerHP - unit.getHitpoints()) + " damage");
			System.out.println("	defender took " + (defenderHP - target.getHitpoints()) + " damage");
		}
		cleanUp(unit, target);
		setChanged();
	}

	/**
	 * Handles the result of the combat once two units have fought. If the defender was a bridge, it is captured by
	 * 		the attacker. If either attacker or defender died, they are removed from the game.
	 * @param unitA the attacking unit.
	 * @param unitB the defending unit.
	 */
	private void cleanUp(Unit unitA, Unit unitB) {
		if (unitB instanceof Bridge) {
			unitB.getOwner().removeUnit(unitB);
			world.getAllUnits().remove(unitB);
			BridgeImpl newBridge = new BridgeImpl(unitB.getBehaviour(), unitA.getOwner(), unitB.getPosition());

			// Bridges must be inserted at the start of the list of units, otherwise they will be drawn on top of
			// 		units that are standing on them.
			unitA.getOwner().getUnits().add(0, newBridge);
			world.getAllUnits().add(0, newBridge);
			System.out.println("	bridge was captured by Player " + (getPlayers().indexOf(unitA.getOwner()) + 1));
		}
		if (!unitB.isAlive()) {
			unitB.getOwner().removeUnit(unitB);
			world.getAllUnits().remove(unitB);
			System.out.println("	defender was killed");
		}
		if (!unitA.isAlive()) {
			unitA.getOwner().removeUnit(unitA);
			world.getAllUnits().remove(unitA);
			System.out.println("	attacker was killed");
		}
		System.out.println("}\n");
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
		if (unit.getActionPoints() <= 0) {
			return actualUnits;
		}
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
		if (unit.getActionPoints() <= 0) {
			return actualTiles;
		}

		for (MapLocation tile : potentialTiles) {
			if (tile.isInPlayArea()) {
				if (canPassUnit(unit, tile)) {
					actualTiles.add(tile);
				}
			} else if (getUnitAt(tile) instanceof Bridge && canPassUnit(unit, tile)){
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
		turns++;
		for (int i = 0; i < turn.getUnits().size(); i++) {
			turn.getUnits().get(i).turnTick();
		}

		getPlayers().forEach(this::calculateVision);

		int turnIndex = players.indexOf(turn);
		turnIndex = (turnIndex + 1) % players.size();
		turn = players.get(turnIndex);
		if (turnIndex == 2) {
			nextTurn();
		}
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
	public int getWinner() {
		for (int i = 0; i < 3; i++) {
			if (players.get(i).getUnits().isEmpty()) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public void addObserver(Observer o) {
		if (!observers.contains(o)) {
			observers.add(o);
		}
	}

	@Override
	public int getNumberTurns() {
		return turns / 3;
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
		for (Observer obs : observers) {
			obs.update(null, o);
		}
		changed = false;
	}
}
