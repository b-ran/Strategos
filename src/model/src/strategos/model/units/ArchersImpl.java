package strategos.model.units;

import strategos.*;
import strategos.behaviour.Behaviour;
import strategos.hexgrid.Hex;
import strategos.units.Archers;
import strategos.units.Unit;

/**
 * Created as part of Strategos project
 * 30/07/2017.
 */
public class ArchersImpl extends UnitImpl implements Archers, Graphical {


	public ArchersImpl(UnitOwner owner, MapLocation startLocation) {
		super(owner, startLocation);
	}

	public ArchersImpl(Behaviour behaviour, UnitOwner owner, MapLocation startLocation) {
		super(behaviour, owner, startLocation);
	}

	@Override
	public Unit copy(UnitOwner newOwner, GameState newState) {
		return new ArchersImpl(getBehaviour().copy(newState), newOwner, getPosition());
	}

	@Override
	public void draw(GraphicalVisitor graphicalVisitor) {
		graphicalVisitor.visit(this);
	}
}
