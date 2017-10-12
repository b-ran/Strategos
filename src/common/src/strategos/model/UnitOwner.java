package strategos.model;

import strategos.units.Unit;

import java.io.Serializable;
import java.util.List;

public interface UnitOwner extends Serializable {

	public List<Unit> getUnits();

	void addUnit(Unit newUnit);

	void addVisibleTile(MapLocation newTile);

	void removeUnit(Unit toRemove);

	public List<MapLocation> getVisibleTiles();

	public boolean isNPC();

	public void setUnits(List<Unit> newUnits);

	public UnitOwner copy(GameState newState);
}
