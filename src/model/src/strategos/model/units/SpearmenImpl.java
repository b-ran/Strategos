package strategos.model.units;

import strategos.*;
import strategos.behaviour.Behaviour;
import strategos.model.GameState;
import strategos.model.MapLocation;
import strategos.model.UnitOwner;
import strategos.units.Spearmen;
import strategos.units.Unit;

/**
 * An implementation of the Spearmen Unit.
 *
 * @author Daniel Pinfold
 */
public class SpearmenImpl extends UnitImpl implements Spearmen, GameObject {


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
	public void accept(GameObjectVisitor gameObjectVisitor) {
		gameObjectVisitor.visit(this);
	}
}
