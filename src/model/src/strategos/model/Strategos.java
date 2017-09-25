package strategos.model;

import strategos.*;
import strategos.hexgrid.Hex;
import strategos.terrain.Terrain;
import strategos.units.Unit;

import java.util.ArrayList;
import java.util.List;

public class Strategos implements GameState {
	private GameCollections world;
	private ArrayList<UnitOwner> players = new ArrayList<>();
	private UnitOwner turn;

	private List<SaveState> saves = new ArrayList<>();


	public Strategos(World world) {
		this.world = world;
	}

	public void save() {
		if (saves.size() > 3) {
			return;
		}
		saves.add(new SaveState(world, players, turn));
	}

	public void load(int saveIndex) {
		if (saves.size() <= saveIndex) {
			return;
		}
		SaveState toRestore = saves.get(saveIndex);

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
		MapLocation currentPosition = world.getMap().get(unit.getPosition().getX(), unit.getPosition().getY());
		while (amount > 0) {
			if (!currentPosition.getNeighbour(direction).isInPlayArea() ||
					getUnitAt(unit.getPosition()) != null) {
				break;
			}
			currentPosition = currentPosition.getNeighbour(direction);
			unit.move(direction);
			amount--;
			calculateVision(unit.getOwner());
		}
	}

	private void calculateVision(UnitOwner player) {
		for (Unit unit : player.getUnits()) {
			List<MapLocation> sightRange = getTilesInRange(unit.getPosition(), 3);
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
		/*for (int i = 0; i < turn.getUnits().size(); i++) {
			if (!turn.getUnits().get(i).isAlive()) {
				turn.getUnits().remove(i);
			}
		}*/
		for (Unit unit : turn.getUnits()) {
			// TODO: reset unit action points
			// TODO: set "moved" to false
		}

		int turnIndex = players.indexOf(turn);
		turnIndex = (turnIndex + 1) % players.size();
		turn = players.get(turnIndex);
	}

	@Override
	public GameCollections getWorld() {
		return world;
	}


}
