package strategos.model.units;

import strategos.*;
import strategos.behaviour.Behaviour;
import strategos.model.GameState;
import strategos.model.MapLocation;
import strategos.model.UnitOwner;
import strategos.units.Elite;
import strategos.units.Unit;

import java.awt.*;

/**
 * Created as part of Strategos project
 * 30/07/2017.
 */
public class EliteImpl extends UnitImpl implements Elite, GameObject {
	private static final Image sprite = null;

	public EliteImpl(UnitOwner owner, MapLocation startLocation) {
		super(owner, startLocation);
	}

	public EliteImpl(Behaviour behaviour, UnitOwner owner, MapLocation startLocation) {
		super(behaviour, owner, startLocation);
	}


	@Override
	public Unit copy(UnitOwner newOwner, GameState newState) {
		return new EliteImpl(getBehaviour().copy(newState), newOwner, getPosition());
	}

	@Override
	public void accept(GameObjectVisitor gameObjectVisitor) {
		gameObjectVisitor.visit(this);
	}
}
