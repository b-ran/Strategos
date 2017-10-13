package strategos.behaviour;


import strategos.*;
import strategos.exception.*;
import strategos.model.GameState;
import strategos.model.MapLocation;
import strategos.terrain.*;
import strategos.units.*;

import java.util.logging.*;


abstract class UnitBehaviour extends BaseBehaviour {

    //TODO: Where is your javadoc?

   private static Logger logger = Logger.getLogger("strategos.behaviour");

    private boolean entrench;
    private int     actionPoints;
    private boolean wary;
    private int     hitpoints;
    private boolean hasAttacked;

    @Override
    public String toString() {
        return "UnitBehaviour{" +
                "entrench=" + entrench +
                ", actionPoints=" + actionPoints +
                ", wary=" + wary +
                ", hitpoints=" + hitpoints +
                ", hasAttacked=" + hasAttacked +
                "} " + super.toString();
    }

    UnitBehaviour(GameState gameState) {
        super(gameState);

        hitpoints = Config.UNIT_HITPOINTS;
        actionPoints = 0;

        wary = false;
        entrench = false;

        hasAttacked = true;
    }

    UnitBehaviour(UnitBehaviour behaviour, GameState newState) {
        super(behaviour, newState);

        entrench = behaviour.entrench;
        actionPoints = behaviour.actionPoints;
        wary = behaviour.wary;
        hitpoints = behaviour.hitpoints;
    }

    @Override public void turnTick(Unit unit) {
        logger.fine(String.format("%s: turn tick", this.getClass()));

        actionPoints = getMaxActionPoints();
        hasAttacked = false;

        if (wary) {
            actionPoints--;
        }
    }

    @Override public void wary(Unit unit) {
        if (wary) {
            wary = false;
            logger.info(String.format("%s: left wary state", this.getClass()));
        }
        else if (actionPoints >= Config.WARY_COST) {
            actionPoints -= Config.WARY_COST;
            wary = true;
            if (entrench) {
                entrench = false;
                logger.fine(String.format("%s: left entrench state", this.getClass()));
            }
            logger.info(String.format("%s: entered wary state", this.getClass()));
        }
        else {
            logger.info(String.format("%s: could not wary", this.getClass()));
        }
    }

    @Override public boolean getWary(Unit unit) {
        return wary;
    }

    @Override public void entrench(Unit unit) {
        if (entrench) {
            entrench = false;
            logger.info(String.format("%s: left entrench state", this.getClass()));
        }
        else if (actionPoints >= Config.ENTRENCH_COST) {
            actionPoints -= Config.ENTRENCH_COST;
            entrench = true;
            if (wary) {
                wary = false;
                logger.fine(String.format("%s: left wary state", this.getClass()));
            }
            logger.info(String.format("%s: entered entrench state", this.getClass()));
        }
        else {
            logger.info(String.format("%s: could not entrench", this.getClass()));
        }
    }

    @Override public boolean getEntrench(Unit unit) {
        return entrench;
    }

    @Override public void charge(Unit unit) {
        // TODO: charge
    }

    @Override final public boolean move(Unit unit, Direction direction) {
        logger.info(String.format("%s: move %s", this.getClass(), direction));

        if (direction == null) {
            throw new NullPointerException("Method move() requires a non-null direction");
        }

        MapLocation neighbour = getPosition(unit).getNeighbour(direction);
        int movementCost = terrainMovementCost(neighbour.getTerrain());

        if (getActionPoints(unit) < 1) {
            logger.info(String.format("%s: not enough action points for move", this.getClass()));
            return false;
        }
        else {
            setPosition(unit, neighbour);
            actionPoints -= movementCost;
            return true;
        }
    }

    @Override public int attack(Unit unit, Unit enemy) {
        logger.info(String.format("%s: attack %s", this.getClass(), enemy));

        if (enemy == null) {
            throw new NullPointerException("Method attack() requires a non-null enemy");
        }

        if (!isAlive(unit) || !enemy.isAlive() || hasAttacked) {
            logger.info(String.format("%s: cannot attack", this.getClass()));
            return 0;
        }

        int defence = enemy.getToughness();
        defence += enemy.getWary() ? Config.WARY_MODIFIER : 0;
        defence += enemy.getEntrench() ? Config.ENTRENCH_MODIFIER : 0;
        defence *= 0.8 + (enemy.getHitpoints() / 500.0);
        defence = terrainDamageBonus(enemy, defence, false);

        enemy.defend(unit);

        hitpoints -= defence;
        hasAttacked = true;

        if (enemy instanceof HealthPotion) {
            logger.info(String.format("%s: use health potion", this.getClass()));
            hitpoints = Config.UNIT_HITPOINTS;
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
        logger.fine(String.format("%s: defend against %s", this.getClass(), enemy));

        if (enemy == null) {
            throw new NullPointerException("Method defend() requires a non-null enemy");
        }

        int attack = enemy.getStrength();
        attack -= getWary(unit) ? 1 : 0;
        attack -= getEntrench(unit) ? 2 : 0;
        attack *= 0.8 + (enemy.getHitpoints() / 500.0);
        attack = terrainDamageBonus(enemy, attack, true);

        hitpoints -= attack;

        return 0;
    }

    @Override public int getHitpoints(Unit unit) {
        return hitpoints > 0 ? hitpoints : 0;
    }

    @Override public boolean isAlive(Unit unit) {
        return hitpoints > 0;
    }

    @Override public int getSightRadius(Unit unit) {
        return 2;
    }

    @Override public int getActionPoints(Unit unit) {
        return isAlive(unit) && actionPoints > 0 ? actionPoints : 0;
    }

    @Override
    public int getAttackRange() {
        return Config.MELEE_RANGE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        UnitBehaviour that = (UnitBehaviour) o;

        if (entrench != that.entrench) return false;
        if (actionPoints != that.actionPoints) return false;
        if (wary != that.wary) return false;
        if (hitpoints != that.hitpoints) return false;
        return hasAttacked == that.hasAttacked;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (entrench ? 1 : 0);
        result = 31 * result + actionPoints;
        result = 31 * result + (wary ? 1 : 0);
        result = 31 * result + hitpoints;
        result = 31 * result + (hasAttacked ? 1 : 0);
        return result;
    }

    private int terrainMovementCost(Terrain terrain) {
        if (terrain instanceof Plains) {
            return 1;
        }
        else if (terrain instanceof Forest) {
            return 2;
        }
        else if (terrain instanceof Hill) {
            return 2;
        }
        else if (terrain instanceof River) {
            return 2;
        }
        else {
            throw new RuleViolationException("Unit must be on valid Terrain");
        }
    }

    int getMaxActionPoints() {
        return Config.INFANTRY_ACTION_POINTS;
    }

    void setActionPoints(int actionPoints) {
        this.actionPoints = actionPoints;
    }
}
