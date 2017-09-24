package strategos.model;

import strategos.Direction;
import strategos.GameState;
import strategos.MapLocation;
import strategos.hexgrid.Hex;
import strategos.terrain.Terrain;
import strategos.units.Unit;

import java.util.ArrayList;
import java.util.List;

public class Strategos implements GameState {
	private World world;
	private ArrayList<Player> players = new ArrayList<>();
	private Player turn;

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

		this.world = toRestore.world;
		this.players = toRestore.players;
		this.turn = toRestore.turn;
	}

	@Override
	public Unit getUnitAt(MapLocation location) {
		for (strategos.units.Unit u : world.getAllUnits()) {
			if (u.getPosition().getX() == location.getX() && u.getPosition().getY() == location.getY()) {
				return u;
			}
		}
		return null;
	}

	public void move(Unit unit, Direction direction, int amount) {
		amount = Math.min(amount, unit.getActionPoints());
		Hex currentPosition = world.getMap().get(unit.getPosition().getX(), unit.getPosition().getY());
		while (amount > 0) {
			if (!currentPosition.getNeighbour(direction).isInPlayArea() ||
					getUnitAt(unit.getPosition()) != null) {
				break;
			}
			currentPosition = currentPosition.getNeighbour(direction);
			unit.move(direction);
			amount--;
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

	public void wary(Unit unit) {

	}

	public void entrench(Unit unit) {

	}

	@Override
	public List<Unit> getUnitsInRange(MapLocation location, int range) {

		List<Unit> units = new ArrayList<>();

		if (location.getY() < 0 || location.getY() > world.getMap().getMap().length ||
				location.getX() < 0 || location.getY() > world.getMap().getMap().length) {
			return units;
		}

		Hex centre = world.getMap().get(location.getX(), location.getY());
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
	public Terrain getTerrainAt(MapLocation location) {
		return world.getMap().get(location.getX(), location.getY()).getTerrain();
	}

	@Override
	public void nextTurn() {

	}


}
