package strategos.model.units;

import strategos.UnitOwner;
import strategos.behaviour.Behaviour;
import strategos.units.Castle;

public class CastleImpl extends UnitImpl implements Castle {
	public CastleImpl(UnitOwner owner) {
		super(owner);
	}

	public CastleImpl(Behaviour behaviour, UnitOwner owner) {
		super(behaviour, owner);
	}
}
