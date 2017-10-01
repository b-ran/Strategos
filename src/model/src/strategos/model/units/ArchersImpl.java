package strategos.model.units;

import strategos.Direction;
import strategos.MapLocation;
import strategos.UnitOwner;
import strategos.behaviour.Behaviour;
import strategos.hexgrid.Hex;
import strategos.units.Archers;
import strategos.units.Unit;

/**
 * Created as part of Strategos project
 * 30/07/2017.
 */
public class ArchersImpl extends UnitImpl implements Archers {

	public ArchersImpl(UnitOwner owner) {
		super(owner);
	}

	public ArchersImpl(Behaviour behaviour, UnitOwner owner) {
		super(behaviour, owner);
	}

	@Override
	public Unit copy() {
		Unit newUnit = new ArchersImpl(getBehaviour().copy(), getOwner());
		return newUnit;
	}
}
