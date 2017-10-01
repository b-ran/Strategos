package strategos.model.units;

import strategos.MapLocation;
import strategos.UnitOwner;
import strategos.behaviour.Behaviour;
import strategos.hexgrid.Hex;
import strategos.units.Cavalry;
import strategos.units.Unit;

/**
 * Created as part of Strategos project
 * 30/07/2017.
 */
public class CavalryImpl extends UnitImpl implements Cavalry {

	public CavalryImpl(UnitOwner owner) {
		super(owner);
	}

	public CavalryImpl(Behaviour behaviour, UnitOwner owner) {
		super(behaviour, owner);
	}

	@Override
	public Unit copy() {
		Unit newUnit = new CavalryImpl(getBehaviour().copy(), getOwner());
		return newUnit;
	}
}
