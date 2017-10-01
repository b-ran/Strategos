package strategos.model.units;

import strategos.Direction;
import strategos.Graphical;
import strategos.MapLocation;
import strategos.UnitOwner;
import strategos.behaviour.Behaviour;
import strategos.units.Unit;

public class UnitImpl implements Graphical, Unit {

	private Behaviour behaviour;
	private UnitOwner owner;

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
		return behaviour.getPosition(this);
	}

	@Override
	public void setPosition(MapLocation position) {
		behaviour.setPosition(this, position);
	}

	@Override
	public void turnTick() {
		behaviour.turnTick(this);
	}

	@Override
	public void wary() {
		behaviour.wary(this);
	}

	@Override
	public void entrench() {
		behaviour.entrench(this);
	}

	@Override
	public void charge() {
		behaviour.charge(this);
	}

	@Override
	public boolean move(Direction direction) {
		return behaviour.move(this, direction);
	}

	@Override
	public int attack(Unit enemy) {
		return behaviour.attack(this, enemy);
	}

	@Override
	public int defend(Unit enemy) {
		return behaviour.defend(this, enemy);
	}

	@Override
	public int getStrength() {
		return behaviour.getStrength(this);
	}

	@Override
	public int getToughness() {
		return behaviour.getToughness(this);
	}

	@Override
	public UnitOwner getOwner() {
		return owner;
	}

	@Override
	public boolean isAlive() {
		return behaviour.isAlive(this);
	}

	@Override
	public int getSightRadius() {
		return behaviour.getSightRadius(this);
	}

	@Override
	public int getActionPoints() {
		return behaviour.getActionPoints(this);
	}

	@Override
	public Unit copy() {
		Unit newUnit = new UnitImpl(getOwner());
		newUnit.setBehaviour(getBehaviour().copy());
		return newUnit;
	}
}