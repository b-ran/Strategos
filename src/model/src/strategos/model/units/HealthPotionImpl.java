package strategos.model.units;

import strategos.GameObjectVisitor;
import strategos.behaviour.Behaviour;
import strategos.model.GameState;
import strategos.model.MapLocation;
import strategos.model.UnitOwner;
import strategos.units.HealthPotion;
import strategos.units.Unit;

/**
 * An implementation of the Health Potion Unit.
 *
 * @author Daniel Pinfold
 */
public class HealthPotionImpl extends UnitImpl implements HealthPotion {
	public HealthPotionImpl(UnitOwner owner, MapLocation startLocation) {
		super(owner, startLocation);
	}

	public HealthPotionImpl(Behaviour behaviour, UnitOwner owner, MapLocation startLocation) {
		super(behaviour, owner, startLocation);
	}

	@Override
	public Unit copy(UnitOwner newOwner, GameState newState) {
		return new HealthPotionImpl(getBehaviour().copy(newState), newOwner, getPosition());
	}

	@Override
	public void accept(GameObjectVisitor gameObjectVisitor) {
		gameObjectVisitor.visit(this);
	}
}
