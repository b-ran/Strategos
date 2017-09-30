package model;

import strategos.*;
import strategos.terrain.Terrain;
import strategos.units.Unit;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class ModelTestObj extends Observable implements GameState {
    private GameCollections gameCollections;
    private  ArrayList<UnitOwner> unitOwners = new ArrayList<>();

    @Override
    public void save() {

    }

    @Override
    public void load(SaveInstance toRestore) {

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
    public List<MapLocation> getTilesInRange(MapLocation location, int range) {
        return null;
    }

    @Override
    public void nextTurn() {

    }

    @Override
    public GameCollections getWorld() {
        return gameCollections;
    }

    @Override
    public ArrayList<UnitOwner> getPlayers() {
        return unitOwners;
    }

    @Override
    public List<SaveInstance> getSaves() {
        return null;
    }

    @Override
    public UnitOwner getCurrentTurn() {
        return null;
    }


    public void setWorld(GameCollections gameCollections) {
        this.gameCollections = gameCollections;
    }

    public void setPlayers(ArrayList<UnitOwner> unitOwners) {
        this.unitOwners = unitOwners;
    }

}
