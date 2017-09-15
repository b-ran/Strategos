package strategos.units;

import strategos.mapGeneration.map.board.Hex;
import strategos.util.exception.RuleViolationException;

import java.awt.*;

import static strategos.StrategosGame.ENTRENCH_VAL;
import static strategos.StrategosGame.WARY_VAL;

/**
 * Created as part of Strategos project
 * 28/07/2017.
 */
public class Spearmen extends Unit {
	private static final Image sprite = null;

	private int health = 100;
	private double actionPoints = 2;

	private final int strength = 4;
	private int strengthMod = 0;

	private final int toughness = 5;
	private int toughnessMod = 0;

	private Hex position;

	public void wary() {
		if (actionPoints>=1) actionPoints-=1;
		else throw new RuleViolationException("This unit does not have enough action points to Wary");
		toughnessMod += WARY_VAL;
	}

	@Override
	public void entrench() {
		if (actionPoints>=2) actionPoints-=2;
		else throw new RuleViolationException("This unit does not have enough action points to Entrench");
		toughnessMod += ENTRENCH_VAL;
	}

	@Override
	public boolean move() {
		//TODO
		return false;
	}

	@Override
	public int attack(Unit enemy) {
		//TODO
		//Check that enemy hex is one of your hexes neighbours
		//+6 mod against mounted(ie: if unit.instanceOf(Cavalry))
		return 0;
	}

	@Override
	public int defend(Unit enemy) {
		//TODO
		//+6 mod against mounted(ie: if unit.instaceOf(Cavalry))
		return 0;
	}

	@Override
	public Hex getPosition() {
		return position;
	}

	@Override
	public int getStrength() {
		//TODO add terrain mod to this
		return strength + strengthMod;
	}

	@Override
	public int getToughness() {
		//TODO add terrain mod to this
		return toughness + toughnessMod;
	}

}
