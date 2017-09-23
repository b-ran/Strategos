package strategos.model;

import strategos.Direction;
import strategos.GameState;
import strategos.model.units.NullUnitImpl;
import strategos.units.Unit;

public class Strategos implements GameState {
	private World world;
	
	public Strategos(World world) {
		
	}

	public void save() {

	}

	public void load() {

	}

	@Override
	public strategos.units.Unit getUnitAt(int xIndex, int yIndex) {
		for (strategos.units.Unit u : world.getAllUnits()) {
			if (u.getPosition().getX() == xIndex && u.getPosition().getY() == yIndex) {
				return u;
			}
		}
		return null;
	}

	public void move(Unit unit, Direction direction, int amount) {

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
		Unit target = getUnitAt(new MapLocation() {
			@Override
			public int getX() {
				return targetX;
			}

			@Override
			public int getY() {
				return targetY;
			}

			@Override
			public MapLocation getNeighbour(Direction direction) {
				return null;
			}
		});
		if (target == null) {
			return;
		}
		unit.attack(target);
	}

	public void wary(Unit unit) {

	}

	public void entrench(Unit unit) {

	}


	
}
