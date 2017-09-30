package strategos.model.units;

import strategos.UnitOwner;
import strategos.behaviour.Behaviour;
import strategos.units.Bridge;
import strategos.units.Unit;

public class BridgeImpl extends UnitImpl implements Bridge {
	public BridgeImpl(UnitOwner owner) {
		super(owner);
	}

	public BridgeImpl(Behaviour behaviour, UnitOwner owner) {
		super(behaviour, owner);
	}

	@Override
	public Unit copyUnit() {
		Unit newUnit = new BridgeImpl(getBehaviour().copy(), getOwner());
		return newUnit;
	}
}
