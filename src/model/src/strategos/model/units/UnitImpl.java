package strategos.model.units;

import strategos.Direction;
import strategos.Graphical;
import strategos.MapLocation;
import strategos.UnitOwner;
import strategos.behaviour.Behaviour;
import strategos.units.Unit;

public class UnitImpl implements Graphical, Unit {

	private Behaviour behaviour;
	private final UnitOwner owner;

	public UnitImpl(UnitOwner owner) {
		this.owner = owner;
	}

	public UnitImpl(Behaviour behaviour, UnitOwner owner) {
		setBehaviour(behaviour);
		this.owner = owner;
	}

	@Override
	public void setBehaviour(Behaviour behaviour) {
		this.behaviour = behaviour;
	}

	@Override
	public Behaviour getBehaviour() {
		return behaviour;
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
		return owner;
	}

	@Override
	public boolean isAlive() {
		return behaviour.isAlive();
	}

	@Override
	public int getSightRadius() {
		return behaviour.getSightRadius();
	}

	@Override
	public int getActionPoints() {
		return behaviour.getActionPoints();
	}

	@Override
	public Unit copy() {
		Unit newUnit = new UnitImpl(getOwner());
		newUnit.setBehaviour(getBehaviour().copy());
		return newUnit;
	}
}