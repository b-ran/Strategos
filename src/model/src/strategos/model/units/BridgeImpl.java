package strategos.model.units;

import strategos.MapLocation;
import strategos.UnitOwner;
import strategos.behaviour.Behaviour;
import strategos.units.Bridge;
import strategos.units.Unit;

public class BridgeImpl extends UnitImpl implements Bridge {


	public BridgeImpl(UnitOwner owner, MapLocation startLocation) {
		super(owner, startLocation);
	}

	public BridgeImpl(Behaviour behaviour, UnitOwner owner, MapLocation startLocation) {
		super(behaviour, owner, startLocation);
	}

	@Override
	public Unit copy() {
		return new BridgeImpl(getBehaviour().copy(), getOwner(), getPosition());
	}
}
