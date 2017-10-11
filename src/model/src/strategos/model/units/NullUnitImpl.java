package strategos.model.units;

import strategos.GameObject;
import strategos.GameObjectVisitor;
import strategos.model.MapLocation;
import strategos.model.UnitOwner;
import strategos.behaviour.Behaviour;
import strategos.units.Unit;

public class NullUnitImpl extends UnitImpl implements Unit, GameObject {

	public NullUnitImpl(UnitOwner owner, MapLocation startLocation) {
		super(owner, startLocation);
	}

	public NullUnitImpl(Behaviour behaviour, UnitOwner owner, MapLocation startLocation) {
		super(behaviour, owner, startLocation);
	}

	@Override
	public void accept(GameObjectVisitor gameObjectVisitor) {
		return;
	}
}
