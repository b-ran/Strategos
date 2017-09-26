package units;

import strategos.Direction;
import strategos.MapLocation;
import strategos.UnitOwner;
import strategos.units.Unit;

public class UnitTestObj implements Unit{

    private UnitOwner owner;
    private MapLocation mapLocation;

    public UnitTestObj (UnitOwner owner) {
        this.owner = owner;
    }

    @Override
    public MapLocation getPosition() {
        return mapLocation;
    }

    @Override
    public void setPosition(MapLocation position) {
        mapLocation = position;
    }

    @Override
    public void turnTick() {

    }

    @Override
    public void wary() {

    }

    @Override
    public void entrench() {

    }

    @Override
    public void charge() {

    }

    @Override
    public boolean move(Direction direction) {
        return false;
    }

    @Override
    public int attack(Unit enemy) {
        return 0;
    }

    @Override
    public int defend(Unit enemy) {
        return 0;
    }

    @Override
    public int getStrength() {
        return 0;
    }

    @Override
    public int getToughness() {
        return 0;
    }

    @Override
    public UnitOwner getOwner() {
        return owner;
    }

    @Override
    public boolean isAlive() {
        return false;
    }

    @Override
    public int getSightRadius() {
        return 0;
    }

    @Override
    public int getActionPoints() {
        return 0;
    }
}
