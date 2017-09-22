package strategos.model.units;

import strategos.Graphical;
import strategos.behaviour.Behaviour;
import strategos.behaviour.Movable;
import strategos.hexgrid.Hex;

/**
 * Created as part of Strategos project
 * 28/07/2017.
 */

public abstract class Unit implements Graphical, strategos.units.Unit {

	private Behaviour behaviour;

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

	public abstract Hex getPosition();

	public abstract int getStrength();

	public abstract int getToughness();
}