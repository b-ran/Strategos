package strategos.model.units;

import strategos.behaviour.HasBehaviour;
import strategos.behaviour.UnitBehaviour;
import strategos.model.Hex;
import strategos.util.Entity;
import strategos.util.exception.RuleViolationException;

/**
 * Created as part of Strategos project
 * 28/07/2017.
 */

public abstract class Unit implements Entity, HasBehaviour<UnitBehaviour> {
	public enum Action {
		WARY, ENTRENCH, MOVE, 
	}

	public  void doAction(Action act){
		switch (act) {
			case WARY:
				break;
			case ENTRENCH:
				break;
			default:
				throw new IllegalArgumentException("No such action.");
		}
	}

	private UnitBehaviour behaviour;

	@Override
	public UnitBehaviour getBehaviour() {
		return behaviour;
	}

	@Override
	public void setBehaviour(UnitBehaviour behaviour) {
		this.behaviour = behaviour;
	}

	public abstract Hex getPosition();

	public abstract int getStrength();

	public abstract int getToughness();
}