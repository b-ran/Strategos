package strategos.model.units;

import strategos.MapLocation;
import strategos.hexgrid.Hex;
import strategos.units.Archers;
import strategos.units.Unit;

/**
 * Created as part of Strategos project
 * 30/07/2017.
 */
public class ArchersImpl extends UnitImpl implements Archers {
	
	@Override
	public Hex getPosition() {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getToughness() {
		// TODO Auto-generated method stub
		return 0;
	}

}
