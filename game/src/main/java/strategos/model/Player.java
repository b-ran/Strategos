package strategos.model;

import java.util.ArrayList;

import strategos.model.units.Unit;

public class Player {
	
	private ArrayList<Unit> units = new ArrayList<>();
	
	public Player() {
	}
	
	public ArrayList<Unit> getUnits() {
		return units;
	}
}
