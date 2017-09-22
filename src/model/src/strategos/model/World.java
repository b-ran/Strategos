package strategos.model;

import java.util.ArrayList;
import java.util.List;

import strategos.hexgrid.Map;
import strategos.model.units.UnitImpl;

public class World {
	private Map map;
	private ArrayList<UnitImpl> allUnits = new ArrayList<>();
	
	public World() {
		
	}
	
	public Map getMap() {
		return map;
	}
	
	public List<UnitImpl> getAllUnits() {
		return allUnits;
	}

}
