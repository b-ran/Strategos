package strategos.model;

import java.util.ArrayList;
import java.util.List;

import strategos.GameBoard;
import strategos.GameCollections;
import strategos.hexgrid.Map;
import strategos.model.units.UnitImpl;
import strategos.units.Unit;

public class World implements GameCollections {
	private GameBoard map;
	private List<Unit> allUnits = new ArrayList<>();
	
	public World(Map map, ArrayList<Unit> units) {
		this.map = map;
		this.allUnits = units;
	}

	@Override
	public GameBoard getMap() {
		return map;
	}

	@Override
	public List<Unit> getAllUnits() {
		return allUnits;
	}

	@Override
	public void setMap(GameBoard map) {
		this.map = map;
	}

	@Override
	public void setAllUnits(List<Unit> allUnits) {
		this.allUnits = allUnits;
	}
}
