package strategos.model.units;

import strategos.*;
import strategos.behaviour.Behaviour;
import strategos.model.GameState;
import strategos.model.MapLocation;
import strategos.model.UnitOwner;
import strategos.units.Bridge;
import strategos.units.Unit;

/**
 * An implementation of the Bridge Unit.
 *
 * @author Daniel Pinfold - pinfoldani
 */
public class BridgeImpl extends UnitImpl implements Bridge, GameObject {


	public BridgeImpl(UnitOwner owner, MapLocation startLocation) {
		super(owner, startLocation);
	}

	public BridgeImpl(Behaviour behaviour, UnitOwner owner, MapLocation startLocation) {
		super(behaviour, owner, startLocation);
	}

	@Override
	public Unit copy(UnitOwner newOwner, GameState newState) {
		return new BridgeImpl(getBehaviour().copy(newState), newOwner, getPosition());
	}

	@Override
	public void accept(GameObjectVisitor gameObjectVisitor) {
		gameObjectVisitor.visit(this);
	}


	@Override
	public String toString() {
		return "Bridge";
	}
}
