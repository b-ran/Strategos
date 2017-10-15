package strategos.behaviour;


import strategos.Direction;
import strategos.exception.RuleViolationException;
import strategos.model.GameState;
import strategos.model.MapLocation;
import strategos.model.UnitOwner;
import strategos.units.Unit;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Function;
import java.util.logging.Logger;

import static java.lang.Math.abs;


class AiBehaviour extends BaseBehaviour {

    //TODO: Where is your javadoc?

    private static final Random random = new Random();
    private static Logger logger = Logger.getLogger("strategos.behaviour");
    private Behaviour behaviour;
    private int directionIndex;

    AiBehaviour(GameState gameState, Function<GameState, Behaviour> factoryMethod) {
        super(gameState);

        if (factoryMethod == null) {
            throw new NullPointerException("AiBehaviour constructor requires non-null factoryMethod");
        }

        this.behaviour = factoryMethod.apply(gameState);
        logger.fine(String.format("AI behaviour created inner behaviour %s", this.behaviour.getClass()));

        if (this.behaviour == null) {
            throw new NullPointerException("Behaviour factory method should not return null");
        }

        this.directionIndex = random.nextInt(Direction.values().length);
        logger.fine(String.format("%s AI selected direction %s",
                                  this.behaviour.getClass(),
                                  Direction.values()[this.directionIndex]
        ));
    }

    private AiBehaviour(AiBehaviour aiBehaviour, GameState newState) {
        super(aiBehaviour, newState);

        this.behaviour = aiBehaviour.behaviour.copy(newState);
        this.directionIndex = aiBehaviour.directionIndex;
    }

    @Override public void turnTick(Unit unit) {
        this.behaviour.turnTick(unit);

        Optional<Unit> nearest = getNearestUnit(unit);

        while (getActionPoints(unit) > 0) {
            if (nearest.isPresent()) {
                if (pursueOrAttackUnit(unit, nearest.get())) {
                    break;
                }
            }
            else {
                explore(unit);
            }
        }
    }

    private Optional<Unit> getNearestUnit(Unit unit) {
        UnitOwner owner = getGameState().getPlayers()
                .stream()
                .filter(p -> p.getUnits().contains(unit))
                .findAny()
                .orElseThrow(() -> new RuleViolationException("Unowned unit"));

        return getGameState().getUnitsInRange(getPosition(unit), getSightRadius(unit))
                .stream()
                .filter(u -> !owner.getUnits().contains(u))
                .min((a, b) -> compareUnitDistance(unit, a, b));
    }

    private boolean pursueOrAttackUnit(Unit unit, Unit nearest) {
        int range = unit.getAttackRange();
        List<Unit> adjacentUnits = getGameState().getUnitsInRange(getPosition(unit), range);
        if (adjacentUnits.contains(nearest)) {
            getGameState().attack(unit, nearest.getPosition());
            return true;
        }
        else {
            return !pursueUnit(unit, nearest);
        }
    }

    private void explore(Unit unit) {
        Direction[] values = Direction.values();
        this.directionIndex = (values.length + this.directionIndex + random.nextInt(2) - 1) % values.length;
        Direction direction = values[this.directionIndex];
        logger.fine(String.format("%s AI selected direction %s", this.behaviour.getClass(), direction));
        getGameState().move(unit, direction);
    }

    @Override public MapLocation getPosition(Unit unit) {
        MapLocation position = this.behaviour.getPosition(unit);
        assert position != null : "Method getPosition() shouldn't be returning null";
        return position;
    }

    private int compareUnitDistance(Unit unit, Unit a, Unit b) {
        int x = getPosition(unit).getX();
        int y = getPosition(unit).getY();

        int aX = a.getPosition().getX();
        int aY = a.getPosition().getY();
        int aD = abs(x - aX) + abs(x + y - aX - aY) + abs(x - aX);

        int bX = b.getPosition().getX();
        int bY = b.getPosition().getY();
        int bD = abs(x - bX) + abs(x + y - bX - bY) + abs(x - bX);

        return aD - bD;
    }

    private boolean pursueUnit(Unit unit, Unit nearest) {
        int dx = nearest.getPosition().getX() - getPosition(unit).getX();
        int dy = nearest.getPosition().getY() - getPosition(unit).getY();

        MapLocation previous = getPosition(unit);

        if (dx < 0) {
            getGameState().move(unit, Direction.WEST);
        }
        else if (dx > 0) {
            getGameState().move(unit, Direction.EAST);
        }
        else if (dy < 0) {
            getGameState().move(unit, Direction.NORTH_EAST);
        }
        else if (dy > 0) {
            getGameState().move(unit, Direction.NORTH_WEST);
        }

        return !previous.equals(getPosition(unit));
    }


    @Override public void setPosition(Unit unit, MapLocation position) {
        if (position == null) {
            throw new NullPointerException("Method setPosition() requires non-null position");
        }
        this.behaviour.setPosition(unit, position);
    }

    @Override public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + this.behaviour.hashCode();
        result = 31 * result + this.directionIndex;
        return result;
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        AiBehaviour that = (AiBehaviour) o;

        if (this.directionIndex != that.directionIndex) return false;
        return this.behaviour.equals(that.behaviour);
    }

    @Override public String toString() {
        return "AiBehaviour{" + "behaviour=" + this.behaviour + ", directionIndex=" + this.directionIndex + "} " +
               super.toString();
    }

    @Override public void wary(Unit unit) {
        this.behaviour.wary(unit);
    }

    @Override public boolean getWary(Unit unit) {
        return this.behaviour.getWary(unit);
    }

    @Override public void entrench(Unit unit) {
        this.behaviour.entrench(unit);
    }

    @Override public boolean getEntrench(Unit unit) {
        return this.behaviour.getEntrench(unit);
    }

    @Override public void charge(Unit unit) {
        this.behaviour.charge(unit);
    }

    @Override public boolean move(Unit unit, Direction direction) {
        if (direction == null) {
            throw new NullPointerException("Method move() requires a non-null direction");
        }
        return this.behaviour.move(unit, direction);
    }

    @Override public int attack(Unit unit, Unit enemy) {
        if (enemy == null) {
            throw new NullPointerException("Method attack() requires a non-null enemy");
        }
        return this.behaviour.attack(unit, enemy);
    }

    @Override public int defend(Unit unit, Unit enemy) {
        if (enemy == null) {
            throw new NullPointerException("Method defend() requires a non-null enemy");
        }
        return this.behaviour.defend(unit, enemy);
    }

    @Override public int getStrength(Unit unit) {
        return this.behaviour.getStrength(unit);
    }

    @Override public int getToughness(Unit unit) {
        return this.behaviour.getToughness(unit);
    }

    @Override public int getHitpoints(Unit unit) {
        return this.behaviour.getHitpoints(unit);
    }

    @Override public boolean isAlive(Unit unit) {
        return this.behaviour.isAlive(unit);
    }

    @Override public int getSightRadius(Unit unit) {
        return this.behaviour.getSightRadius(unit);
    }

    @Override public int getActionPoints(Unit unit) {
        return this.behaviour.getActionPoints(unit);
    }

    @Override public Behaviour copy(GameState newState) {
        return new AiBehaviour(this, newState);
    }

    @Override public int getAttackRange() {
        return this.behaviour.getAttackRange();
    }
}
