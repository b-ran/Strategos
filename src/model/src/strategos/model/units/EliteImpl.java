package strategos.model.units;

import strategos.Graphical;
import strategos.GraphicalVisitor;
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
public class EliteImpl extends UnitImpl implements Elite, Graphical {
	private static final Image sprite = null;

	public EliteImpl(UnitOwner owner, MapLocation startLocation) {
		super(owner, startLocation);
	}

	public EliteImpl(Behaviour behaviour, UnitOwner owner, MapLocation startLocation) {
		super(behaviour, owner, startLocation);
	}


	@Override
	public Unit copy(UnitOwner newOwner) {
		return new EliteImpl(getBehaviour().copy(), newOwner, getPosition());
	}

	@Override
	public void draw(GraphicalVisitor graphicalVisitor) {
		graphicalVisitor.visit(this);
	}
}
