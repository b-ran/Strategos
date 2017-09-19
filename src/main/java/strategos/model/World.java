package strategos.model;

import java.util.ArrayList;
import java.util.List;

import strategos.model.units.Unit;

public class World {
	private Map map;
	private ArrayList<Unit> allUnits = new ArrayList<>();
	
	public World() {
		
	}
	
	public Map getMap() {
		return map;
	}
	
	public List<Unit> getAllUnits() {
		return allUnits;
	}

}
