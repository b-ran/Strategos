package strategos;

import strategos.units.Unit;

import java.util.ArrayList;
import java.util.List;

public interface GameCollections {

	public GameBoard getMap();

	public List<Unit> getAllUnits();

	public void setMap(GameBoard map);

	public void setAllUnits(List<Unit> allUnits);
}
