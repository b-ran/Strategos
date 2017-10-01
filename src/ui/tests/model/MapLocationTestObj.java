package model;

import strategos.Direction;
import strategos.MapLocation;
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
}
