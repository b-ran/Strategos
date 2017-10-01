package strategos.behaviour;


import strategos.*;
import strategos.exception.*;
import strategos.terrain.*;
import strategos.units.*;


abstract class UnitBehaviour extends BaseBehaviour {

    private boolean     entrench;
    private MapLocation position;
    private int         actionPoints;
    private boolean     wary;
    private int         hitpoints;

    UnitBehaviour(GameState gameState) {
        super(gameState);

        hitpoints = 100;
        actionPoints = 0;

        wary = false;
        entrench = false;
    }

    UnitBehaviour(UnitBehaviour behaviour) {
        super(behaviour);

        entrench = behaviour.entrench;
        position = behaviour.position;
        actionPoints = behaviour.actionPoints;
        wary = behaviour.wary;
        hitpoints = behaviour.hitpoints;
    }

    @Override final public MapLocation getPosition(Unit unit) {
        assert position != null
                : "Method getPosition() shouldn't be returning null";
        return position;
    }

    @Override final public void setPosition(Unit unit, MapLocation position) {
        if (position == null) {
            throw new NullPointerException(
                    "Method setPosition() requires non-null position");
        }
        this.position = position;
    }

    @Override public void turnTick(Unit unit) {
        actionPoints = getMaxActionPoints();

        if (wary) {
            actionPoints--;
        }
    }

    @Override public void wary(Unit unit) {
        // TODO: wary
    }

    @Override public void entrench(Unit unit) {
        // TODO: entrench
    }

    @Override public void charge(Unit unit) {
        // TODO: charge
    }

    @Override final public boolean move(Unit unit, Direction direction) {
        if (direction == null) {
            throw new NullPointerException(
                    "Method move() requires a non-null direction");
        }

        if (getActionPoints(unit) <= 0) {
            return false;
        }
        else {
            setPosition(unit, position.getNeighbour(direction));
            actionPoints -= terrainMovementCost(unit);
            return true;
        }
    }

    @Override public int attack(Unit unit, Unit enemy) {
        if (enemy == null) {
            throw new NullPointerException(
                    "Method attack() requires a non-null enemy");
        }

        if (!isAlive(unit) || !enemy.isAlive()) {
            return 0;
        }

        // TODO: HP debuff
        int defence = terrainDamageBonus(enemy, enemy.getToughness(), false);

        enemy.defend(unit);

        hitpoints -= defence;

        return 0;
    }

    private int terrainDamageBonus(Unit unit, int damage, boolean attacking) {
        assert unit != null
                : "Method terrainDamageBonus() shouldn't be receiving a null unit";

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
            throw new RuleViolationException("Unit must be on valid Terrain");
        }
    }

    private int terrainMovementCost(Unit unit) {
        Terrain terrain = getGameState().getTerrainAt(getPosition(unit));

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
            throw new RuleViolationException("Unit must be on valid Terrain");
        }
    }

    @Override public int defend(Unit unit, Unit enemy) {
        if (enemy == null) {
            throw new NullPointerException(
                    "Method defend() requires a non-null enemy");
        }

        int attack = terrainDamageBonus(enemy, enemy.getStrength(), true);

        hitpoints -= attack;

        return 0;
    }

    @Override public boolean isAlive(Unit unit) {
        return hitpoints > 0;
    }

    @Override public int getSightRadius(Unit unit) {
        return 2;
    }

    @Override public int getActionPoints(Unit unit) {
        return isAlive(unit) ? actionPoints : 0;
    }

    void setActionPoints(int actionPoints) {
        this.actionPoints = actionPoints;
    }

    int getMaxActionPoints() {
        return Config.INFANTRY_ACTION_POINTS;
    }
}
