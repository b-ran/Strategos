package strategos.model.units;

import strategos.Graphical;
import strategos.GraphicalVisitor;
import strategos.MapLocation;
import strategos.UnitOwner;
import strategos.behaviour.Behaviour;
import strategos.hexgrid.Hex;
import strategos.units.Cavalry;
import strategos.units.Unit;

/**
 * Created as part of Strategos project
 * 30/07/2017.
 */
public class CavalryImpl extends UnitImpl implements Cavalry, Graphical {


	public CavalryImpl(UnitOwner owner, MapLocation startLocation) {
		super(owner, startLocation);
	}

	public CavalryImpl(Behaviour behaviour, UnitOwner owner, MapLocation startLocation) {
		super(behaviour, owner, startLocation);
	}

	@Override
	public Unit copy(UnitOwner newOwner) {
		return new CavalryImpl(getBehaviour().copy(), newOwner, getPosition());
	}

	@Override
	public void draw(GraphicalVisitor graphicalVisitor) {
		graphicalVisitor.visit(this);
	}
}
