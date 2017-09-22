package strategos.model;

import strategos.Direction;
import strategos.model.units.NullUnitImpl;
import strategos.units.Unit;

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
	 * Finds the Unit (if such a Unit exists) at a given index on the Hex board. If no unit is found at the index, it
	 * 		will return a NullUnit. NullUnits can be detected using the isNullUnit(Unit) method.
	 *
	 * @param xIndex the x-position of the desired unit (i.e. the x-index of the selected Hex in the 2D array).
	 * @param yIndex the y-position of the desired unit (i.e. the y-index of the selected Hex in the 2D array).
	 * @return the Unit at the position, if one exists. If no unit is found, it returns a new NullUnit.
	 */
	public strategos.units.Unit getUnitAt(int xIndex, int yIndex) {
		for (strategos.units.Unit u : world.getAllUnits()) {
			if (u.getPosition().getX() == xIndex && u.getPosition().getY() == yIndex) {
				return u;
			}
		}
		return new NullUnitImpl(xIndex, yIndex);
	}

	public boolean isNullUnit(Unit unit) {
		return false;
	}

	public void move(Unit unit, Direction direction, int amount) {

	}

	public void attack(Unit unit, int targetX, int targetY) {

	}

	public void wary(Unit unit) {

	}

	public void entrench(Unit unit) {

	}


	
}
