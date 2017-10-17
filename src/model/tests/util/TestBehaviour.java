package util;

import strategos.Direction;
import strategos.model.GameState;
import strategos.model.MapLocation;
import strategos.behaviour.Behaviour;
import strategos.units.Unit;

public class TestBehaviour implements Behaviour {

	public boolean     entrenched;
	private MapLocation position;
	public int         actionPoints = 2;
	public boolean     wary;
	public int         hitpoints;
	public boolean charging;
	public boolean moved;
	public boolean attacking;
	public GameState gameState;

	public TestBehaviour(GameState gameState) {
		this.gameState = gameState;
		hitpoints = 100;

		wary = false;
		entrenched = false;
	}

	@Override
	public MapLocation getPosition(Unit unit) {
		return position;
	}

	@Override
	public void setPosition(Unit unit, MapLocation position) {
		this.position = position;
	}

	@Override
	public void turnTick(Unit unit) {

	}

	@Override
	public void wary(Unit unit) {
		wary = true;
	}

	@Override
	public boolean getWary(Unit unit) {
		return false;
	}

	@Override
	public void entrench(Unit unit) {
		entrenched = true;
	}

	@Override
	public boolean getEntrench(Unit unit) {
		return false;
	}

	@Override
	public void charge(Unit unit) {
		charging = true;
	}

	@Override
	public boolean move(Unit unit, Direction direction) {
		moved = true;
		setPosition(unit, getPosition(unit).getNeighbour(direction));
		actionPoints--;
		return true;
	}

	@Override
	public int attack(Unit unit, Unit enemy) {
		attacking = true;
		return 0;
	}

	public void takeDamage(int damage) {
		hitpoints -= damage;
	}

	@Override
	public int defend(Unit unit, Unit enemy) {
		return 0;
	}

	@Override
	public int getStrength(Unit unit) {
		return 0;
	}

	@Override
	public int getToughness(Unit unit) {
		return 0;
	}

	@Override
	public int getHitpoints(Unit unit) {
		return 0;
	}

	@Override
	public boolean isAlive(Unit unit) {
		return true;
	}

	@Override
	public int getSightRadius(Unit unit) {
		return 2;
	}

	@Override
	public int getActionPoints(Unit unit) {
		return actionPoints;
	}

	@Override
	public Behaviour copy(GameState newState) {
		TestBehaviour behaviour = new TestBehaviour(null);
		behaviour.takeDamage(100 - hitpoints);
		behaviour.setPosition(null, getPosition(null));
		behaviour.attacking = attacking;
		behaviour.entrenched = entrenched;
		behaviour.wary = wary;
		behaviour.moved = moved;
		behaviour.charging = charging;
		behaviour.actionPoints = actionPoints;

		return behaviour;
	}

	@Override
	public int getAttackRange() {
		return 1;
	}
}
