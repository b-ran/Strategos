package strategos.behaviour;


import strategos.*;
import strategos.units.*;

import java.util.*;
import java.util.function.*;

import static java.lang.Math.*;


class AiBehaviour extends BaseBehaviour {

    private Behaviour behaviour;

    AiBehaviour(
            GameState gameState,
            Unit unit,
            BiFunction<GameState, Unit, Behaviour> factoryMethod
    )
    {
        super(gameState, unit);

        if (factoryMethod == null) {
            throw new NullPointerException(
                    "AiBehaviour constructor requires non-null factoryMethod");
        }

        this.behaviour = factoryMethod.apply(gameState, unit);

        if (this.behaviour == null) {
            throw new NullPointerException(
                    "Behaviour factory method should not return null");
        }
    }

    @Override public MapLocation getPosition() {
        MapLocation position = behaviour.getPosition();
        assert position != null
                : "Method getPosition() shouldn't be returning null";
        return position;
    }

    @Override public void setPosition(MapLocation position) {
        if (position == null) {
            throw new NullPointerException(
                    "Method setPosition() requires non-null position");
        }
        behaviour.setPosition(position);
    }

    @Override public void turnTick() {
        behaviour.turnTick();

        Optional<Unit> nearest =
                getGameState().getUnitsInRange(getPosition(), getSightRadius())
                        .stream()
                        .min((a, b) -> {
                            double aX = getPosition().getX() -
                                        a.getPosition().getX();
                            double aY = getPosition().getY() -
                                        a.getPosition().getY();
                            double bX = getPosition().getX() -
                                        b.getPosition().getX();
                            double bY = getPosition().getY() -
                                        b.getPosition().getY();

                            return (int) (hypot(aX, aY) - hypot(bX, bY));
                        });

        if (nearest.isPresent()) {
            if (getGameState().getUnitsInRange(getPosition(), 1)
                    .contains(nearest.get()))
            {
                getGameState().attack(getUnit(), nearest.get().getPosition());
            }
            else {
                // TODO: Approach unit
            }
        }
        else {
            // TODO: Explore
        }
    }

    @Override public void wary() {
        behaviour.wary();
    }

    @Override public void entrench() {
        behaviour.entrench();
    }

    @Override public void charge() {
        behaviour.charge();
    }

    @Override public boolean move(Direction direction) {
        if (direction == null) {
            throw new NullPointerException(
                    "Method move() requires a non-null direction");
        }
        return behaviour.move(direction);
    }

    @Override public int attack(Unit enemy) {
        if (enemy == null) {
            throw new NullPointerException(
                    "Method attack() requires a non-null enemy");
        }
        return behaviour.attack(enemy);
    }

    @Override public int defend(Unit enemy) {
        if (enemy == null) {
            throw new NullPointerException(
                    "Method defend() requires a non-null enemy");
        }
        return behaviour.defend(enemy);
    }

    @Override public int getStrength() {
        return behaviour.getStrength();
    }

    @Override public int getToughness() {
        return behaviour.getToughness();
    }

    @Override public boolean isAlive() {
        return behaviour.isAlive();
    }

    @Override public int getSightRadius() {
        return behaviour.getSightRadius();
    }

    @Override public int getActionPoints() {
        return behaviour.getActionPoints();
    }
}
