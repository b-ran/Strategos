package model;

import strategos.Direction;
import strategos.model.MapLocation;
import strategos.terrain.Terrain;

import java.util.Map;

public class MapLocationTestObj implements MapLocation{

    private int y;
    private int x;
    private Terrain terrain;

    public MapLocationTestObj(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public MapLocation getNeighbour(Direction direction) {
        return null;
    }

    @Override
    public void addNeighbour(Direction direction, MapLocation location) {

    }

    @Override
    public boolean isInPlayArea() {
        return true;
    }

    @Override
    public Terrain getTerrain() {
        return terrain;
    }

    @Override
    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }

    @Override
    public Map<Direction, MapLocation> getNeighbours() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MapLocationTestObj hex = (MapLocationTestObj) o;

        if (x != hex.getX()) return false;
        if (y != hex.getY()) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        result = 31 * result + (terrain != null ? terrain.hashCode() : 0);
        return result;
    }
    
}
