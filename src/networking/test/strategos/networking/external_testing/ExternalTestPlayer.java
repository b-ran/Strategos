package strategos.networking.external_testing;

import strategos.model.GameState;
import strategos.model.MapLocation;
import strategos.model.UnitOwner;
import strategos.units.Unit;

import java.util.List;

public class ExternalTestPlayer implements UnitOwner {
	@Override
	public List<Unit> getUnits() {
		return null;
	}

	@Override
	public void addUnit(Unit newUnit) {

	}

	@Override
	public void addVisibleTile(MapLocation newTile) {

	}

	@Override
	public void removeUnit(Unit toRemove) {

	}

	@Override
	public List<MapLocation> getVisibleTiles() {
		return null;
	}

	@Override
	public boolean isNPC() {
		return false;
	}

	@Override
	public void setUnits(List<Unit> newUnits) {

	}

	@Override
	public UnitOwner copy(GameState newState) {
		return null;
	}

}
