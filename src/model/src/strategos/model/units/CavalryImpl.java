package strategos.model.units;

import strategos.*;
import strategos.behaviour.Behaviour;
import strategos.model.GameState;
import strategos.model.MapLocation;
import strategos.model.UnitOwner;
import strategos.units.Cavalry;
import strategos.units.Unit;

/**
 * An implementation of the Cavalry Unit.
 *
 * @author Daniel Pinfold - pinfoldani
 */
public class CavalryImpl extends UnitImpl implements Cavalry, GameObject {


	public CavalryImpl(UnitOwner owner, MapLocation startLocation) {
		super(owner, startLocation);
	}

	public CavalryImpl(Behaviour behaviour, UnitOwner owner, MapLocation startLocation) {
		super(behaviour, owner, startLocation);
	}

	@Override
 	public Unit copy(UnitOwner newOwner, GameState newState) {
		return new CavalryImpl(getBehaviour().copy(newState), newOwner, getPosition());
	}

	@Override
	public void accept(GameObjectVisitor gameObjectVisitor) {
		gameObjectVisitor.visit(this);
	}


	@Override
	public String toString() {
		return "Cavalry";
	}
}
