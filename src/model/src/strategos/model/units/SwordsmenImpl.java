package strategos.model.units;

import strategos.MapLocation;
import strategos.UnitOwner;
import strategos.behaviour.Behaviour;
import strategos.hexgrid.Hex;
import strategos.units.Swordsmen;
import strategos.units.Unit;

/**
 * Created as part of Strategos project
 * 30/07/2017.
 */
public class SwordsmenImpl extends UnitImpl implements Swordsmen {

	public SwordsmenImpl(UnitOwner owner) {
		super(owner);
	}

	public SwordsmenImpl(Behaviour behaviour, UnitOwner owner) {
		super(behaviour, owner);
	}

	@Override
	public Unit copy() {
		// No need to create local variable here
		Unit newUnit = new SwordsmenImpl(getBehaviour().copy(), getOwner());
		return newUnit;
	}
}
