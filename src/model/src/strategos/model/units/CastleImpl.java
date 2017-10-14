package strategos.model.units;

import strategos.model.MapLocation;
import strategos.model.UnitOwner;
import strategos.behaviour.Behaviour;
import strategos.units.Castle;

/**
 * An unused implementation of the Castle Unit.
 *
 * @author Daniel Pinfold
 */
public class CastleImpl extends UnitImpl implements Castle {
	public CastleImpl(UnitOwner owner, MapLocation startLocation) {
		super(owner, startLocation);
	}

	public CastleImpl(Behaviour behaviour, UnitOwner owner, MapLocation startLocation) {
		super(behaviour, owner, startLocation);
	}
}
