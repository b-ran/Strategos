import strategos.Direction;
import strategos.GameState;
import strategos.MapLocation;
import strategos.terrain.Terrain;
import strategos.units.Unit;

import java.util.List;

public class ModelTestObj implements GameState {
    @Override
    public void save() {

    }

    @Override
    public void load(int saveIndex) {

    }

    @Override
    public Unit getUnitAt(MapLocation location) {
        return null;
    }

    @Override
    public void move(Unit unit, Direction direction, int amount) {

    }

    @Override
    public void attack(Unit unit, int targetX, int targetY) {

    }

    @Override
    public void attack(Unit unit, MapLocation location) {

    }

    @Override
    public void wary(Unit unit) {

    }

    @Override
    public void entrench(Unit unit) {

    }

    @Override
    public List<Unit> getUnitsInRange(MapLocation location, int range) {
        return null;
    }

    @Override
    public Terrain getTerrainAt(MapLocation location) {
        return null;
    }

    @Override
    public void nextTurn() {

    }
}
