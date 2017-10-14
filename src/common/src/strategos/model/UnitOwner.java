package strategos.model;

import strategos.units.Unit;

import java.io.Serializable;
import java.util.List;

public interface UnitOwner extends Serializable {

	List<Unit> getUnits();

	void addUnit(Unit newUnit);

	void addVisibleTile(MapLocation newTile);

	void removeUnit(Unit toRemove);

	List<MapLocation> getVisibleTiles();

	boolean isNPC();

	void setUnits(List<Unit> newUnits);

	UnitOwner copy(GameState newState);
}
