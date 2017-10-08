package strategos.networking.external_testing;

import strategos.MapLocation;
import strategos.UnitOwner;
import strategos.units.Unit;

import java.util.List;

public class ExternalTestPlayer implements UnitOwner {
	@Override
	public List<Unit> getUnits() {
		return null;
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
	public UnitOwner copy() {
		return null;
	}
}
