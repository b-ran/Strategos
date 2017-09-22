package strategos.model.units;

import strategos.MapLocation;
import strategos.hexgrid.Hex;
import strategos.units.Unit;

public class NullUnitImpl extends UnitImpl implements Unit {

	public NullUnitImpl(int xPosition, int yPosition) {

	}

	@Override
	public Hex getPosition() {
		return null;
	}

	@Override
	public void setPosition(MapLocation position) {

	}

	@Override
	public void turnTick() {

	}

	@Override
	public void wary() {

	}

	@Override
	public void entrench() {

	}

	@Override
	public void charge() {

	}

	@Override
	public boolean move() {
		return false;
	}

	@Override
	public int attack(Unit enemy) {
		return 0;
	}

	@Override
	public int defend(Unit enemy) {
		return 0;
	}

	@Override
	public int getStrength() {
		return 0;
	}

	@Override
	public int getToughness() {
		return 0;
	}
}
