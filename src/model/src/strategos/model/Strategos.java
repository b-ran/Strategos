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
	
	public Strategos(World world) {
		
	}

	public void save() {

	}

	public void load() {

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
			unit.move();
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
		Hex centre = world.getMap().get(location.getX(), location.getY());
		return null;
	}

	@Override
	public Terrain getTerrainAt(MapLocation location) {
		return null;
	}

	@Override
	public void nextTurn() {

	}


}
