package strategos.model.units;

import strategos.*;
import strategos.behaviour.Behaviour;
import strategos.hexgrid.Hex;
import strategos.units.Spearmen;
import strategos.units.Unit;

/**
 * Created as part of Strategos project
 * 28/07/2017.
 */
public class SpearmenImpl extends UnitImpl implements Spearmen, Graphical {


	public SpearmenImpl(UnitOwner owner, MapLocation startLocation) {
		super(owner, startLocation);
	}

	public SpearmenImpl(Behaviour behaviour, UnitOwner owner, MapLocation startLocation) {
		super(behaviour, owner, startLocation);
	}

	@Override
	public Unit copy(UnitOwner newOwner, GameState newState) {
		return new SpearmenImpl(getBehaviour().copy(newState), newOwner, getPosition());
	}

	@Override
	public void draw(GraphicalVisitor graphicalVisitor) {
		graphicalVisitor.visit(this);
	}
}
