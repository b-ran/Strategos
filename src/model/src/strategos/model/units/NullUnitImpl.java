package strategos.model.units;

import strategos.Graphical;
import strategos.GraphicalVisitor;
import strategos.MapLocation;
import strategos.UnitOwner;
import strategos.behaviour.Behaviour;
import strategos.hexgrid.Hex;
import strategos.units.Unit;

public class NullUnitImpl extends UnitImpl implements Unit, Graphical {

	public NullUnitImpl(UnitOwner owner, MapLocation startLocation) {
		super(owner, startLocation);
	}

	public NullUnitImpl(Behaviour behaviour, UnitOwner owner, MapLocation startLocation) {
		super(behaviour, owner, startLocation);
	}

	@Override
	public void draw(GraphicalVisitor graphicalVisitor) {
		return;
	}
}
