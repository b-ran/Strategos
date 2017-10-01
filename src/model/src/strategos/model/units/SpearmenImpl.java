package strategos.model.units;

import strategos.MapLocation;
import strategos.UnitOwner;
import strategos.behaviour.Behaviour;
import strategos.hexgrid.Hex;
import strategos.units.Spearmen;
import strategos.units.Unit;

/**
 * Created as part of Strategos project
 * 28/07/2017.
 */
public class SpearmenImpl extends UnitImpl implements Spearmen {

	public SpearmenImpl(UnitOwner owner) {
		super(owner);
	}

	public SpearmenImpl(Behaviour behaviour, UnitOwner owner) {
		super(behaviour, owner);
	}

	@Override
	public Unit copy() {
		// No need to create local variable here
		Unit newUnit = new SpearmenImpl(getBehaviour().copy(), getOwner());
		return newUnit;
	}
}
