package units;

import strategos.Direction;
import strategos.MapLocation;
import strategos.UnitOwner;
import strategos.units.Unit;

public class UnitTestObj implements Unit{
    @Override
    public MapLocation getPosition() {
        return null;
    }

    @Override
    public void setPosition(MapLocation position) {

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
        return null;
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
