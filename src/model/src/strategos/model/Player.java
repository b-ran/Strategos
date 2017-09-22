package strategos.model;

import java.util.ArrayList;

import strategos.model.units.UnitImpl;

public class Player {
	
	private ArrayList<UnitImpl> units = new ArrayList<>();
	
	public Player() {
	}
	
	public ArrayList<UnitImpl> getUnits() {
		return units;
	}
}
