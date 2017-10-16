package model;

import strategos.model.GameState;
import strategos.model.MapLocation;
import strategos.model.UnitOwner;
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
    public void addVisibleTile(MapLocation newTile) {

    }

    @Override
    public void removeUnit(Unit toRemove) {

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

    @Override
    public UnitOwner copy(GameState newState) {
        return null;
    }

    public void setAsNpc() {
        npc = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UnitOwnerTestObj that = (UnitOwnerTestObj) o;

        if (npc != that.npc) return false;
        return units != null ? units.equals(that.units) : that.units == null;
    }

    @Override
    public int hashCode() {
        int result = units != null ? units.hashCode() : 0;
        result = 31 * result + (npc ? 1 : 0);
        return result;
    }
}
