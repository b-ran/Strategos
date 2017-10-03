package strategos.model;

import strategos.*;
import strategos.terrain.Terrain;
import strategos.units.Bridge;
import strategos.units.Unit;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

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

	private List<SaveInstance> saves = new ArrayList<>();

	public Strategos(World world, UnitOwner playerOne, UnitOwner playerTwo, UnitOwner barbarians) {
		this.world = world;
		players.add(playerOne);
		players.add(playerTwo);
		players.add(barbarians);
		turn = playerOne;
	}

	public void save() {
		if (saves.size() > 3) {
			return;
		}
		saves.add(new SaveState(world, players, turn));
	}

	public void load(SaveInstance toRestore) {

		this.world = toRestore.getWorld();
		this.players = toRestore.getPlayers();
		this.turn = toRestore.getTurn();
	}

	@Override
	public Unit getUnitAt(MapLocation location) {
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

	private boolean canPassUnit(Unit mover, MapLocation location, Direction direction) {
		Unit u = getUnitAt(location.getNeighbour(direction));
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
		if (target == null) {
			return;
		}
		if (target.getOwner().equals(unit.getOwner())) {
			return;
		}
		unit.attack(target);
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
