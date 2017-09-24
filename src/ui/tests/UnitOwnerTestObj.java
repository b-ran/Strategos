import strategos.MapLocation;
import strategos.UnitOwner;
import strategos.units.Unit;

import java.util.ArrayList;
import java.util.List;

public class UnitOwnerTestObj implements UnitOwner {

    private List<Unit> units = new ArrayList<>();

    public UnitOwnerTestObj() {
    }

    public void addUnit(Unit unit) {
        units.add(unit);
    }

    @Override
    public List<Unit> getUnits() {
        return units;
    }

    @Override
    public List<MapLocation> getVisibileTiles() {
        return null;
    }

    @Override
    public boolean isNPC() {
        return false;
    }
}
