package strategos.behaviour;


import strategos.*;
import strategos.exception.*;
import strategos.terrain.*;
import strategos.units.*;


abstract class UnitBehaviour extends BaseBehaviour {

    private final boolean     entrench;
    private       MapLocation position;
    private       int         actionPoints;
    private       boolean     wary;
    private       int         hitpoints;

    UnitBehaviour(GameState gameState, Unit unit) {
        super(gameState, unit);

        hitpoints = 100;
        actionPoints = 0;

        wary = false;
        entrench = false;
    }

    @Override final public MapLocation getPosition() {
        return position;
    }

    @Override final public void setPosition(MapLocation position) {
        this.position = position;
    }

    @Override public void turnTick() {
        actionPoints = getMaxActionPoints();

        if (wary) {
            actionPoints--;
        }
    }

    @Override public void wary() {
        // TODO: wary
    }

    @Override public void entrench() {
        // TODO: entrench
    }

    @Override public void charge() {
        // TODO: charge
    }

    @Override final public boolean move(Direction direction) {
        if (getActionPoints() <= 0) {
            return false;
        }
        else {
            setPosition(position.getNeighbour(direction));
            actionPoints -= terrainMovementCost();
            return true;
        }
    }

    @Override public int attack(Unit enemy) {
        if (!isAlive() || !enemy.isAlive()) {
            return 0;
        }

        // TODO: HP debuff
        int defence = terrainDamageBonus(enemy, enemy.getToughness(), false);

        enemy.defend(getUnit());

        hitpoints -= defence;

        return 0;
    }

    private int terrainDamageBonus(Unit unit, int damage, boolean attacking) {
        Terrain terrain = getGameState().getTerrainAt(unit.getPosition());
        if (terrain instanceof Plains) {
            return damage;
        }
        else if (terrain instanceof Forest) {
            return attacking ? (int) (damage * 0.85) : (int) (damage * 1.15);
        }
        else if (terrain instanceof Hill) {
            return attacking ? (int) (damage * 1.1) : (int) (damage * 1.25);
        }
        else if (terrain instanceof River) {
            return attacking ? (int) (damage * 0.9) : damage;
        }
        else {
            throw new RuleViolationException("Unit must not be on Mountain");
        }
    }

    private int terrainMovementCost() {
        Terrain terrain = getGameState().getTerrainAt(getPosition());
        if (terrain instanceof Plains) {
            return -1;
        }
        else if (terrain instanceof Forest) {
            return -2;
        }
        else if (terrain instanceof Hill) {
            return -2;
        }
        else if (terrain instanceof River) {
            return -2;
        }
        else {
            throw new RuleViolationException("Unit must not be on Mountain");
        }
    }

    @Override public int defend(Unit enemy) {
        int attack = terrainDamageBonus(enemy, enemy.getStrength(), true);

        hitpoints -= attack;

        return 0;
    }

    @Override public boolean isAlive() {
        return hitpoints > 0;
    }

    @Override public int getSightRadius() {
        return 2;
    }

    @Override public int getActionPoints() {
        return isAlive() ? actionPoints : 0;
    }

    protected void setActionPoints(int actionPoints) {
        this.actionPoints = actionPoints;
    }

    int getMaxActionPoints() {
        return 2;
    }
}
