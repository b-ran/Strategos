package strategos.model.units;

import strategos.model.Hex;
import strategos.util.exception.RuleViolationException;

import java.awt.*;

import static strategos.util.Config.*;
/**
 * Created as part of Strategos project
 * 30/07/2017.
 */
public class Cavalry extends Unit {
	private static final Image sprite = null;

	private int health = 100;
	private double actionPoints = 4;

	private final int strength = 5;
	private int strengthMod = 0;

	private final int toughness = 3;
	private int toughnessMod = 0;

	private Hex position;

	@Override
	public boolean move() {
		//TODO
		return false;
	}

	@Override
	public int attack(Unit enemy) {
		//TODO
		//Check that enemy hex is one of your hexes neighbours
		//Must include a + 3 modifier when attacking
		return 0;
	}

	@Override
	public int defend(Unit enemy) {
		//TODO
		return 0;
	}

	public void wary() {
		if (actionPoints>=1) actionPoints-=1;
		else throw new RuleViolationException("This unit does not have enough action points to Wary");
		toughnessMod += WARY_VAL;
	}

	@Override
	public Hex getPosition() {
		return position;
	}

	@Override
	public int getStrength() {
		//TODO add terrain mod to this
		return toughness + toughnessMod;
	}

	@Override
	public int getToughness() {
		//TODO add terrain mod to this
		return strength + strengthMod;
	}

}
