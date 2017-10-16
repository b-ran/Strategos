package strategos.model.units;

import strategos.*;
import strategos.behaviour.Behaviour;
import strategos.model.GameState;
import strategos.model.MapLocation;
import strategos.model.UnitOwner;
import strategos.units.Archers;
import strategos.units.Unit;

/**
 * An implementation of the Archer Unit.
 *
 * @author Daniel Pinfold - pinfoldani
 */
public class ArchersImpl extends UnitImpl implements Archers, GameObject {


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
	public void accept(GameObjectVisitor gameObjectVisitor) {
		gameObjectVisitor.visit(this);
	}

	@Override
	public String toString() {
		return "Archers";
	}
}
