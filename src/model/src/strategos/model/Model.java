package strategos.model;

import strategos.Direction;
import strategos.behaviour.Movable;
import strategos.model.units.NullUnit;
import strategos.model.units.Unit;

public class Model {
	private World world;
	
	public Model(World world) {
		
	}

	/**
	 * Saves the game state, preserving the collections (units, map, etc) and the game turn.
	 */
	public void save() {

	}

	public void load() {

	}

	/**
	 * Finds the Unit (if such a Unit exists) at a given index on the Hex board. 
	 * @param xIndex
	 * @param yIndex
	 * @return
	 */
	public Movable getUnitAt(int xIndex, int yIndex) {
		for (Unit u : world.getAllUnits()) {
			if (u.getPosition().getX() == xIndex && u.getPosition().getY() == yIndex) {
				return u;
			}
		}
		return new NullUnit(xIndex, yIndex);
	}

	public void move(Movable unit, Direction direction, int amount) {

	}

	public void attack(Movable unit, int targetX, int targetY) {

	}

	public void wary(Movable unit) {

	}

	public void entrench(Movable unit) {

	}


	
}
