package units;

import strategos.*;
import strategos.behaviour.Behaviour;
import strategos.units.Unit;

public class UnitTestObj implements Unit, Graphical{

    private UnitOwner owner;
    private MapLocation mapLocation;

    public UnitTestObj (UnitOwner owner) {
        this.owner = owner;
    }

    @Override
    public void setBehaviour(Behaviour behaviour) {

    }

    @Override
    public Behaviour getBehaviour() {
        return null;
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
    public boolean getWary() {
        return false;
    }

    @Override
    public void entrench() {

    }

    @Override
    public boolean getEntrench() {
        return false;
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
    public int getHitpoints() {
        return 0;
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

    @Override
    public int getAttackRange() {
        return 0;
    }

    @Override
    public Unit copy(UnitOwner newOwner, GameState newState) {
        return null;
    }

    @Override
    public void draw(GraphicalVisitor graphicalVisitor) {
    }
}
