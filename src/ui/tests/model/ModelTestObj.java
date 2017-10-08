package model;

import strategos.*;
import strategos.terrain.Terrain;
import strategos.units.Unit;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class ModelTestObj implements GameState {
    private GameCollections gameCollections;
    private  ArrayList<UnitOwner> unitOwners = new ArrayList<>();
    private  ArrayList<Unit> attackRange = new ArrayList<>();
    private  ArrayList<MapLocation> moveRange = new ArrayList<>();

    @Override
    public void save() {

    }

    public void setAttackRange(ArrayList<Unit> attackRange) {
        this.attackRange = attackRange;
    }

    public void setMoveRange(ArrayList<MapLocation> moveRange) {
        this.moveRange = moveRange;
    }

    @Override
    public void load(SaveInstance toRestore) {


    }

    @Override
    public Unit getUnitAt(MapLocation location) {
        for (Unit u : gameCollections.getAllUnits()) {
            if (u.getPosition().getX() == location.getX() && u.getPosition().getY() == location.getY()) {
                return u;
            }
        }
        return null;
    }

    @Override
    public void move(Unit unit, Direction direction, int amount) {

    }

    @Override
    public void move(Unit unit, MapLocation mapLocation) {
        unit.setPosition(mapLocation);
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
    public List<Unit> getUnitsInAttackRange(Unit unit) {
        return attackRange;
    }

    @Override
    public List<MapLocation> getTilesInMoveRange(Unit unit) {
        return moveRange;
    }

    @Override
    public SaveInstance export() {
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
    public void setThisInstancePlayer(UnitOwner thisInstancePlayer) {

    }

    @Override
    public UnitOwner getThisInstancePlayer() {
        return null;
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

    @Override
    public void addObserver(Observer o) {
        
    }

    @Override
    public void setChanged() {

    }

    @Override
    public boolean hasChanged() {
        return false;
    }

    @Override
    public void notifyObservers(Object o) {

    }
}
