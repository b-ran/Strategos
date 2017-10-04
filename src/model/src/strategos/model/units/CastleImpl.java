package strategos.model.units;

import strategos.MapLocation;
import strategos.UnitOwner;
import strategos.behaviour.Behaviour;
import strategos.units.Castle;

public class CastleImpl extends UnitImpl implements Castle {
	public CastleImpl(UnitOwner owner, MapLocation startLocation) {
		super(owner, startLocation);
	}

	public CastleImpl(Behaviour behaviour, UnitOwner owner, MapLocation startLocation) {
		super(behaviour, owner, startLocation);
	}
}
