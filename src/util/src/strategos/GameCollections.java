package strategos;

import strategos.units.Unit;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public interface GameCollections extends Serializable{

	public GameBoard getMap();

	public List<Unit> getAllUnits();

	public void setMap(GameBoard map);

	public void setAllUnits(List<Unit> allUnits);
}
