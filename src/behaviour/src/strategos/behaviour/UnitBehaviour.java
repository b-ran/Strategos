package strategos.behaviour;


import strategos.Config;
import strategos.Direction;
import strategos.exception.RuleViolationException;
import strategos.model.GameState;
import strategos.model.MapLocation;
import strategos.terrain.*;
import strategos.units.HealthPotion;
import strategos.units.Unit;

import java.util.logging.Logger;


abstract class UnitBehaviour extends BaseBehaviour {

    //TODO: Where is your javadoc?

    private static Logger logger = Logger.getLogger("strategos.behaviour");

    private boolean entrench;
    private int actionPoints;
    private boolean wary;
    private int hitpoints;
    private boolean hasAttacked;

    UnitBehaviour(GameState gameState) {
        super(gameState);

        this.hitpoints = Config.UNIT_HITPOINTS;
        this.actionPoints = 0;

        this.wary = false;
        this.entrench = false;

        this.hasAttacked = true;
    }

    UnitBehaviour(UnitBehaviour behaviour, GameState newState) {
        super(behaviour, newState);

        this.entrench = behaviour.entrench;
        this.actionPoints = behaviour.actionPoints;
        this.wary = behaviour.wary;
        this.hitpoints = behaviour.hitpoints;
    }

    @Override public void turnTick(Unit unit) {
        logger.fine(String.format("%s: turn tick", this.getClass()));

        this.actionPoints = getMaxActionPoints();
        this.hasAttacked = false;

        if (this.wary) {
            this.actionPoints--;
        }
    }

    @Override public void wary(Unit unit) {
        if (this.wary) {
            this.wary = false;
            logger.info(String.format("%s: left wary state", this.getClass()));
        }
        else if (this.actionPoints >= Config.WARY_COST) {
            this.actionPoints -= Config.WARY_COST;
            this.wary = true;
            if (this.entrench) {
                this.entrench = false;
                logger.fine(String.format("%s: left entrench state", this.getClass()));
            }
            logger.info(String.format("%s: entered wary state", this.getClass()));
        }
        else {
            logger.info(String.format("%s: could not wary", this.getClass()));
        }
    }

    @Override public boolean getWary(Unit unit) {
        return this.wary;
    }

    @Override public void entrench(Unit unit) {
        if (this.entrench) {
            this.entrench = false;
            logger.info(String.format("%s: left entrench state", this.getClass()));
        }
        else if (this.actionPoints >= Config.ENTRENCH_COST) {
            this.actionPoints -= Config.ENTRENCH_COST;
            this.entrench = true;
            if (this.wary) {
                this.wary = false;
                logger.fine(String.format("%s: left wary state", this.getClass()));
            }
            logger.info(String.format("%s: entered entrench state", this.getClass()));
        }
        else {
            logger.info(String.format("%s: could not entrench", this.getClass()));
        }
    }

    @Override public boolean getEntrench(Unit unit) {
        return this.entrench;
    }

    @Override public void charge(Unit unit) {
        // TODO: charge
    }

    @Override final public boolean move(Unit unit, Direction direction) {
        logger.info(String.format("%s: move %s", this.getClass(), direction));

        if (direction == null) {
            throw new NullPointerException("Method move() requires a non-null direction");
        }

        if (getEntrench(unit)) {
            entrench(unit);
        }

        MapLocation neighbour = getPosition(unit).getNeighbour(direction);
        int movementCost = terrainMovementCost(neighbour.getTerrain());

        if (getActionPoints(unit) < 1) {
            logger.info(String.format("%s: not enough action points for move", this.getClass()));
            return false;
        }
        else {
            setPosition(unit, neighbour);
            this.actionPoints -= movementCost;
            return true;
        }
    }

    @Override public int attack(Unit unit, Unit enemy) {
        logger.info(String.format("%s: attack %s", this.getClass(), enemy));

        if (enemy == null) {
            throw new NullPointerException("Method attack() requires a non-null enemy");
        }

        if (!isAlive(unit) || !enemy.isAlive() || this.hasAttacked) {
            logger.info(String.format("%s: cannot attack", this.getClass()));
            return 0;
        }

        if (getActionPoints(unit) < 1) {
            logger.info(String.format("%s: not enough action points for attack", this.getClass()));
            return 0;
        }

        this.actionPoints -= 1;

        int defence = enemy.getToughness();
        defence += enemy.getWary() ? Config.WARY_MODIFIER : 0;
        defence += enemy.getEntrench() ? Config.ENTRENCH_MODIFIER : 0;
        defence *= 0.8 + (enemy.getHitpoints() / 500.0);
        defence = terrainDamageBonus(enemy, defence, false);

        enemy.defend(unit);

        this.hitpoints -= defence;
        this.hasAttacked = true;

        if (enemy instanceof HealthPotion) {
            logger.info(String.format("%s: use health potion", this.getClass()));
            this.hitpoints = Config.UNIT_HITPOINTS;
        }

        return 0;
    }

    private int terrainDamageBonus(Unit unit, int damage, boolean attacking) {
        assert unit != null : "Method terrainDamageBonus() shouldn't be receiving a null unit";

        Terrain terrain = getGameState().getTerrainAt(unit.getPosition());

        if (terrain instanceof Plains) {
            return attacking
                    ? (int) (damage * Config.PLAINS_STRENGTH_BONUS)
                    : (int) (damage * Config.PLAINS_TOUGHNESS_BONUS);
        }
        else if (terrain instanceof Forest) {
            return attacking
                    ? (int) (damage * Config.FOREST_STRENGTH_BONUS)
                    : (int) (damage * Config.FOREST_TOUGHNESS_BONUS);
        }
        else if (terrain instanceof Hill) {
            return attacking
                    ? (int) (damage * Config.HILL_STRENGTH_BONUS)
                    : (int) (damage * Config.HILL_TOUGHNESS_BONUS);
        }
        else if (terrain instanceof River) {
            return attacking
                    ? (int) (damage * Config.RIVER_STRENGTH_BONUS)
                    : (int) (damage * Config.RIVER_TOUGHNESS_BONUS);
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

        this.hitpoints -= attack;

        return 0;
    }

    @Override public int getHitpoints(Unit unit) {
        return this.hitpoints > 0 ? this.hitpoints : 0;
    }

    @Override public boolean isAlive(Unit unit) {
        return this.hitpoints > 0;
    }

    @Override public int getSightRadius(Unit unit) {
        return 2;
    }

    @Override public int getActionPoints(Unit unit) {
        return isAlive(unit) && this.actionPoints > 0 ? this.actionPoints : 0;
    }

    @Override public int getAttackRange() {
        return Config.MELEE_RANGE;
    }

    private int terrainMovementCost(Terrain terrain) {
        if (terrain instanceof Plains) {
            return Config.PLAINS_MOVEMENT_COST;
        }
        else if (terrain instanceof Forest) {
            return Config.FOREST_MOVEMENT_COST;
        }
        else if (terrain instanceof Hill) {
            return Config.HILL_MOVEMENT_COST;
        }
        else if (terrain instanceof River) {
            return Config.RIVER_MOVEMENT_COST;
        }
        else {
            throw new RuleViolationException("Unit must be on valid Terrain");
        }
    }

    int getMaxActionPoints() {
        return Config.INFANTRY_ACTION_POINTS;
    }

    @Override public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (this.entrench ? 1 : 0);
        result = 31 * result + this.actionPoints;
        result = 31 * result + (this.wary ? 1 : 0);
        result = 31 * result + this.hitpoints;
        result = 31 * result + (this.hasAttacked ? 1 : 0);
        return result;
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        UnitBehaviour that = (UnitBehaviour) o;

        if (this.entrench != that.entrench) return false;
        if (this.actionPoints != that.actionPoints) return false;
        if (this.wary != that.wary) return false;
        if (this.hitpoints != that.hitpoints) return false;
        return this.hasAttacked == that.hasAttacked;
    }

    @Override public String toString() {
        return "UnitBehaviour{" + "entrench=" + this.entrench + ", actionPoints=" + this.actionPoints + ", wary=" +
               this.wary + ", hitpoints=" + this.hitpoints + ", hasAttacked=" + this.hasAttacked + "} " +
               super.toString();
    }

    void setActionPoints(int actionPoints) {
        this.actionPoints = actionPoints;
    }
}
