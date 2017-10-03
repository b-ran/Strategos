package model;

import strategos.GameBoard;
import strategos.MapLocation;
import strategos.terrain.Terrain;

public class GameBoardTestObj implements GameBoard {

    private MapLocation[][] map;

    @Override
    public MapLocation[][] getData() {
        return map;
    }

    @Override
    public MapLocation get(int x, int y) {
        return map[y][x];
    }

    @Override
    public void set(int x, int y, MapLocation location) {

    }

    @Override
    public int getDiameter() {
        return 0;
    }

    @Override
    public Terrain getTerrainAt(MapLocation location) {
        return null;
    }

    public void setData(MapLocation[][] map) {
        this.map = map;
    }
}
