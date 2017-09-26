package strategos.model.units;

import strategos.MapLocation;
import strategos.UnitOwner;
import strategos.behaviour.Behaviour;
import strategos.hexgrid.Hex;
import strategos.units.Unit;

public class NullUnitImpl extends UnitImpl implements Unit {
	public NullUnitImpl(UnitOwner owner) {
		super(owner);
	}
}
