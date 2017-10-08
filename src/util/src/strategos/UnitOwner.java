package strategos;

import strategos.units.Unit;

import java.io.Serializable;
import java.util.List;

public interface UnitOwner extends Serializable {

	public List<Unit> getUnits();

	public List<MapLocation> getVisibleTiles();

	public boolean isNPC();

	public void setUnits(List<Unit> newUnits);

	public UnitOwner copy();
}
