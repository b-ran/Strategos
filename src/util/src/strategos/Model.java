package strategos;

import strategos.units.Unit;

public interface Model {

	public void save();

	public void load();

	public strategos.units.Unit getUnitAt(int xIndex, int yIndex);

	public boolean isNullUnit(Unit unit);

	public void move(Unit unit, Direction direction, int amount);

	public void attack(Unit unit, int targetX, int targetY);

	public void wary(Unit unit);

	public void entrench(Unit unit);
}
