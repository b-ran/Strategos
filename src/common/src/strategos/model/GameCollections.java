package strategos.model;

import strategos.units.Unit;

import java.io.Serializable;
import java.util.List;

public interface GameCollections extends Serializable{

	GameBoard getMap();

	List<Unit> getAllUnits();

	void setMap(GameBoard map);

	void setAllUnits(List<Unit> allUnits);
}
