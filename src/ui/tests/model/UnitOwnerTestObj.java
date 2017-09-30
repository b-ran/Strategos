package model;

import strategos.MapLocation;
import strategos.UnitOwner;
import strategos.units.Unit;

import java.util.ArrayList;
import java.util.List;

public class UnitOwnerTestObj implements UnitOwner {

    private List<Unit> units = new ArrayList<>();
    private boolean npc = false;

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
    public List<MapLocation> getVisibleTiles() {
        return null;
    }

    @Override
    public boolean isNPC() {
        return npc;
    }

    @Override
    public void setUnits(List<Unit> newUnits) {

    }

    public void setAsNpc() {
        npc = true;
    }


}
