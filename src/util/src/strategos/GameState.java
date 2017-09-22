package strategos;

import strategos.units.Unit;

public interface GameState {

	public void save();

	public void load();

	/**
	 * Finds the Unit (if such a Unit exists) at a given index on the Hex board. If no unit is found at the index, it
	 * 		will return null.
	 *
	 * @param xIndex the x-position of the desired unit (i.e. the x-index of the selected Hex in the 2D array).
	 * @param yIndex the y-position of the desired unit (i.e. the y-index of the selected Hex in the 2D array).
	 * @return the Unit at the position, if one exists. If no unit is found, it returns null.
	 */
	public Unit getUnitAt(int xIndex, int yIndex);

	public void move(Unit unit, Direction direction, int amount);

	public void attack(Unit unit, int targetX, int targetY);

	public void wary(Unit unit);

	public void entrench(Unit unit);
}
