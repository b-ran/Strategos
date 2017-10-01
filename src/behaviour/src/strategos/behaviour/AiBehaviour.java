package strategos.behaviour;


import strategos.*;
import strategos.units.*;

import java.util.*;
import java.util.function.*;

import static java.lang.Math.*;


class AiBehaviour extends BaseBehaviour {

    //TODO: Where is your javadoc?

    private Behaviour behaviour;

    AiBehaviour(GameState gameState, Function<GameState, Behaviour> factoryMethod) {
        super(gameState);

        if (factoryMethod == null) {
            throw new NullPointerException(
                    "AiBehaviour constructor requires non-null factoryMethod");
        }

        this.behaviour = factoryMethod.apply(gameState);

        if (this.behaviour == null) {
            throw new NullPointerException(
                    "Behaviour factory method should not return null");
        }
    }

    @Override public MapLocation getPosition(Unit unit) {
        MapLocation position = behaviour.getPosition(unit);
        assert position != null
                : "Method getPosition() shouldn't be returning null";
        return position;
    }

    @Override public void setPosition(Unit unit, MapLocation position) {
        if (position == null) {
            throw new NullPointerException(
                    "Method setPosition() requires non-null position");
        }
        behaviour.setPosition(unit, position);
    }

    @Override public void turnTick(Unit unit) {
        behaviour.turnTick(unit);

        Optional<Unit> nearest = getGameState().getUnitsInRange(
                getPosition(unit),
                getSightRadius(unit)
        ).stream().min((a, b) -> {
            double aX = getPosition(unit).getX() - a.getPosition().getX();
            double aY = getPosition(unit).getY() - a.getPosition().getY();
            double bX = getPosition(unit).getX() - b.getPosition().getX();
            double bY = getPosition(unit).getY() - b.getPosition().getY();

            return (int) (hypot(aX, aY) - hypot(bX, bY));
        });

        //TODO: too much indentation
        if (nearest.isPresent()) {
            pursueUnit(unit, nearest.get());
        }
        else {
            // TODO: Explore
        }
    }

    private void pursueUnit(Unit unit, Unit nearest) {
        List<Unit> adjacentUnits =
                getGameState().getUnitsInRange(getPosition(unit), 1);
        if (adjacentUnits.contains(nearest)) {
            getGameState().attack(unit, nearest.getPosition());
        }
        else {
            // TODO: Approach unit
        }
    }

    @Override public void wary(Unit unit) {
        behaviour.wary(unit);
    }

    @Override public void entrench(Unit unit) {
        behaviour.entrench(unit);
    }

    @Override public void charge(Unit unit) {
        behaviour.charge(unit);
    }

    @Override public boolean move(Unit unit, Direction direction) {
        if (direction == null) {
            throw new NullPointerException(
                    "Method move() requires a non-null direction");
        }
        return behaviour.move(unit, direction);
    }

    @Override public int attack(Unit unit, Unit enemy) {
        if (enemy == null) {
            throw new NullPointerException(
                    "Method attack() requires a non-null enemy");
        }
        return behaviour.attack(unit, enemy);
    }

    @Override public int defend(Unit unit, Unit enemy) {
        if (enemy == null) {
            throw new NullPointerException(
                    "Method defend() requires a non-null enemy");
        }
        return behaviour.defend(unit, enemy);
    }

    @Override public int getStrength(Unit unit) {
        return behaviour.getStrength(unit);
    }

    @Override public int getToughness(Unit unit) {
        return behaviour.getToughness(unit);
    }

    @Override public boolean isAlive(Unit unit) {
        return behaviour.isAlive(unit);
    }

    @Override public int getSightRadius(Unit unit) {
        return behaviour.getSightRadius(unit);
    }

    @Override public int getActionPoints(Unit unit) {
        return behaviour.getActionPoints(unit);
    }

    @Override public Behaviour copy() {
        return new AiBehaviour(getGameState(), s -> behaviour.copy());
    }
}
