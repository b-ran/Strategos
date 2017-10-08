package strategos.networking.external_testing;

import strategos.GameBoard;
import strategos.GameCollections;
import strategos.units.Unit;

import java.util.List;

public class ExternalTestWorld implements GameCollections {
	private GameBoard map;
	private List<Unit> allUnits;

	public ExternalTestWorld(GameBoard map, List<Unit> allUnits) {
		this.allUnits = allUnits;
		this.map = map;
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

	}

	@Override
	public void setAllUnits(List<Unit> allUnits) {

	}
}
