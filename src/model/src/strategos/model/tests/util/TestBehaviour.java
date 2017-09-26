package strategos.model.tests.util;

import strategos.Direction;
import strategos.GameState;
import strategos.MapLocation;
import strategos.behaviour.Behaviour;
import strategos.units.Unit;

public class TestBehaviour implements Behaviour {

	private MapLocation position;
	public boolean moved = false;
	public boolean attacking = false;
	public boolean wary = false;
	public boolean entrenched = false;
	public boolean charging = false;

	public TestBehaviour(GameState g, Unit u) {

	}

	@Override
	public MapLocation getPosition() {
		return position;
	}

	@Override
	public void setPosition(MapLocation position) {
		this.position = position;
	}

	@Override
	public void turnTick() {

	}

	@Override
	public void wary() {
		wary = true;
	}

	@Override
	public void entrench() {
		entrenched = true;
	}

	@Override
	public void charge() {
		charging = true;
	}

	@Override
	public boolean move(Direction direction) {
		moved = true;
		return true;
	}

	@Override
	public int attack(Unit enemy) {
		attacking = true;
		return 0;
	}

	@Override
	public int defend(Unit enemy) {
		return 0;
	}

	@Override
	public int getStrength() {
		return 0;
	}

	@Override
	public int getToughness() {
		return 0;
	}

	@Override
	public boolean isAlive() {
		return true;
	}

	@Override
	public int getSightRadius() {
		return 2;
	}

	@Override
	public int getActionPoints() {
		return 2;
	}
}
