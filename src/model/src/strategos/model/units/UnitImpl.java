package strategos.model.units;

import strategos.*;
import strategos.behaviour.Behaviour;
import strategos.units.Unit;

public class UnitImpl implements Unit, Graphical {

	private Behaviour behaviour;
	private UnitOwner owner;

	public UnitImpl(UnitOwner owner, MapLocation startLocation) {
		this.owner = owner;
	}

	public UnitImpl(Behaviour behaviour, UnitOwner owner, MapLocation startLocation) {
		setBehaviour(behaviour);
		setPosition(startLocation);
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
	public boolean getWary() {
		return behaviour.getWary(this);
	}

	@Override
	public void entrench() {
		behaviour.entrench(this);
	}

	@Override
	public boolean getEntrench() {
		return behaviour.getEntrench(this);
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
	public int getHitpoints() {
		return behaviour.getHitpoints(this);
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
	public int getAttackRange() {
		return behaviour.getAttackRange();
	}

	@Override
	public Unit copy(UnitOwner newOwner) {
		return new UnitImpl(getBehaviour().copy(), newOwner, getPosition());
	}

	@Override
	public void draw(GraphicalVisitor graphicalVisitor) {

	}
}