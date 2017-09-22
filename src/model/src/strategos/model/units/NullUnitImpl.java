package strategos.model.units;

import strategos.behaviour.Movable;
import strategos.hexgrid.Hex;

public class NullUnit extends UnitImpl implements Movable {

	public NullUnit(int xPosition, int yPosition) {

	}

	@Override
	public Hex getPosition() {
		return null;
	}

	@Override
	public int getStrength() {
		return 0;
	}

	@Override
	public int getToughness() {
		return 0;
	}
}
