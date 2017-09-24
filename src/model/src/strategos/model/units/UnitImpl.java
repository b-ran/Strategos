package strategos.model.units;

import strategos.Direction;
import strategos.Graphical;
import strategos.MapLocation;
import strategos.UnitOwner;
import strategos.behaviour.Behaviour;
import strategos.hexgrid.Hex;
import strategos.units.Unit;

public class UnitImpl implements Graphical, Unit, Behaviour {

	private final Behaviour behaviour;
	private final UnitOwner owner;
	private int actionPoints;

	public UnitImpl(Behaviour behaviour, UnitOwner owner) {
		this.behaviour = behaviour;
		this.owner = owner;
	}

	@Override
	public MapLocation getPosition() {
		return behaviour.getPosition();
	}

	@Override
	public void setPosition(MapLocation position) {
		behaviour.setPosition(position);
	}

	@Override
	public void turnTick() {
		behaviour.turnTick();
	}

	@Override
	public void wary() {
		behaviour.wary();
	}

	@Override
	public void entrench() {
		behaviour.entrench();
	}

	@Override
	public void charge() {
		behaviour.charge();
	}

	@Override
	public boolean move(Direction direction) {
		return behaviour.move(direction);
	}

	@Override
	public int attack(Unit enemy) {
		return behaviour.attack(enemy);
	}

	@Override
	public int defend(Unit enemy) {
		return behaviour.defend(enemy);
	}

	@Override
	public int getStrength() {
		return behaviour.getStrength();
	}

	@Override
	public int getToughness() {
		return behaviour.getToughness();
	}

	@Override
	public UnitOwner getOwner() {
		return null;
	}

	@Override
	public boolean isAlive() {
		return false;
	}

	@Override
	public int getSightRadius() {
		return 0;
	}

	@Override
	public int getActionPoints() {
		return actionPoints;
	}

	public void setActionPoints(int actionPoints) {
		this.actionPoints = actionPoints;
	}
}