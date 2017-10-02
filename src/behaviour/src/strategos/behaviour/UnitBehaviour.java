package strategos.behaviour;


import strategos.*;
import strategos.behaviour.config.*;
import strategos.exception.*;
import strategos.terrain.*;
import strategos.units.*;


abstract class UnitBehaviour extends BaseBehaviour {

    //TODO: Where is your javadoc?

    private boolean entrench;
    private int     actionPoints;
    private boolean wary;
    private int     hitpoints;

    UnitBehaviour(GameState gameState) {
        super(gameState);

        hitpoints = BehaviourConfig.UNIT_HITPOINTS;
        actionPoints = 0;

        wary = false;
        entrench = false;
    }

    UnitBehaviour(UnitBehaviour behaviour) {
        super(behaviour);

        entrench = behaviour.entrench;
        actionPoints = behaviour.actionPoints;
        wary = behaviour.wary;
        hitpoints = behaviour.hitpoints;
    }

    @Override public void turnTick(Unit unit) {
        actionPoints = getMaxActionPoints();

        if (wary) {
            actionPoints--;
        }
    }

    @Override public void wary(Unit unit) {
        wary = !wary;
        entrench = false;
    }

    @Override public void entrench(Unit unit) {
        entrench = !entrench;
        wary = false;
    }

    @Override public void charge(Unit unit) {
        // TODO: charge
    }

    @Override final public boolean move(Unit unit, Direction direction) {
        if (direction == null) {
            throw new NullPointerException("Method move() requires a non-null direction");
        }

        if (getActionPoints(unit) <= 0) {
            return false;
        }
        else {
            setPosition(unit, getPosition(unit).getNeighbour(direction));
            actionPoints -= terrainMovementCost(unit);
            return true;
        }
    }

    @Override public int attack(Unit unit, Unit enemy) {
        if (enemy == null) {
            throw new NullPointerException("Method attack() requires a non-null enemy");
        }

        if (!isAlive(unit) || !enemy.isAlive()) {
            return 0;
        }

        // TODO: HP debuff
        int defence = terrainDamageBonus(enemy, enemy.getToughness(), false);

        enemy.defend(unit);

        hitpoints -= defence;

        if (enemy instanceof HealthPotion) {
            hitpoints = BehaviourConfig.UNIT_HITPOINTS;
        }

        return 0;
    }

    private int terrainDamageBonus(Unit unit, int damage, boolean attacking) {
        assert unit != null : "Method terrainDamageBonus() shouldn't be receiving a null unit";

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

    @Override public int defend(Unit unit, Unit enemy) {
        if (enemy == null) {
            throw new NullPointerException("Method defend() requires a non-null enemy");
        }

        int attack = terrainDamageBonus(enemy, enemy.getStrength(), true);

        if (wary) {
            attack -= 1;
        }
        else if (entrench) {
            attack -= 2;
        }

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

    int getMaxActionPoints() {
        return BehaviourConfig.INFANTRY_ACTION_POINTS;
    }

    void setActionPoints(int actionPoints) {
        this.actionPoints = actionPoints;
    }
}
