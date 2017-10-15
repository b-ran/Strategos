package strategos.model.units;

import strategos.*;
import strategos.behaviour.Behaviour;
import strategos.model.GameState;
import strategos.model.MapLocation;
import strategos.model.UnitOwner;
import strategos.units.Swordsmen;
import strategos.units.Unit;
/**
 * An implementation of the Swordsmen Unit.
 *
 * @author Daniel Pinfold - pinfoldani
 */
public class SwordsmenImpl extends UnitImpl implements Swordsmen, GameObject {


	public SwordsmenImpl(UnitOwner owner, MapLocation startLocation) {
		super(owner, startLocation);
	}

	public SwordsmenImpl(Behaviour behaviour, UnitOwner owner, MapLocation startLocation) {
		super(behaviour, owner, startLocation);
	}

	@Override
	public Unit copy(UnitOwner newOwner, GameState newState) {
		return new SwordsmenImpl(getBehaviour().copy(newState), newOwner, getPosition());
	}

	@Override
	public void accept(GameObjectVisitor gameObjectVisitor) {
		gameObjectVisitor.visit(this);
	}
}
