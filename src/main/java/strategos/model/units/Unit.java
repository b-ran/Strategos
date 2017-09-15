package strategos.model.units;

import strategos.model.Hex;
import strategos.util.Entity;
import strategos.util.exception.RuleViolationException;

/**
 * Created as part of Strategos project
 * 28/07/2017.
 */

//This is a interface for all strategos.units
public abstract class Unit implements Entity {
	//for use in other files
	public enum Action {
		WARY, ENTRENCH
	}

	public  void doAction(Action act){
		switch (act) {
			case WARY:
				wary();
				break;
			case ENTRENCH:
				entrench();
				break;
			default:
				throw new IllegalArgumentException("No such action.");
		}
	}

	public abstract boolean move();

	public abstract int attack(Unit enemy);

	public abstract int defend(Unit enemy);

	public abstract void wary();

	public void entrench(){
		throw new RuleViolationException("Unit cannot entrench");
	}

	public void charge(){
		throw new RuleViolationException("Unit cannot Charge");
	}

	public abstract Hex getPosition();

	public abstract int getStrength();

	public abstract int getToughness();
}