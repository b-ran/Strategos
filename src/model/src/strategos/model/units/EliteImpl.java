package strategos.model.units;

import strategos.MapLocation;
import strategos.UnitOwner;
import strategos.behaviour.Behaviour;
import strategos.hexgrid.Hex;
import strategos.units.Elite;
import strategos.units.Unit;

import java.awt.*;

/**
 * Created as part of Strategos project
 * 30/07/2017.
 */
public class EliteImpl extends UnitImpl implements Elite {
	private static final Image sprite = null;

	public EliteImpl(UnitOwner owner, MapLocation startLocation) {
		super(owner, startLocation);
	}

	public EliteImpl(Behaviour behaviour, UnitOwner owner, MapLocation startLocation) {
		super(behaviour, owner, startLocation);
	}


	@Override
	public Unit copy() {
		return new EliteImpl(getBehaviour().copy(), getOwner(), getPosition());
	}
}
