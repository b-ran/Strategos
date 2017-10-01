package strategos.behaviour;


import strategos.*;
import strategos.units.*;

import java.util.*;
import java.util.function.*;

import static java.lang.Math.*;


class AiBehaviour extends BaseBehaviour {

    //TODO: Where is your javadoc?

    private Behaviour behaviour;

    //TODO: change to AiBehaviour(GameState gameState, Function<GameState, Behaviour> factoryMethod) {
    //TODO: change precondition to assertion (up to you though it thinks better with how others have done precondition)
    AiBehaviour(
            GameState gameState, Function<GameState, Behaviour> factoryMethod
    )
    {
        //TODO: maybe want to assert gameState even if it's internal
        super(gameState);

        //TODO: assert factoryMethod != null : "AiBehaviour constructor requires non-null factoryMethod";
        if (factoryMethod == null) {
            throw new NullPointerException(
                    "AiBehaviour constructor requires non-null factoryMethod");
        }

        this.behaviour = factoryMethod.apply(gameState);

        //TODO: assert this.behaviour != null : "Behaviour factory method should not return null"; ";
        if (this.behaviour == null) {
            throw new NullPointerException(
                    "Behaviour factory method should not return null");
        }
    }

    //TODO: @Override public MapLocation getMapLocation(Unit unit) {
    @Override public MapLocation getPosition(Unit unit) {
        //TODO: MapLocation mapLocation = behaviour.getPosition(unit);
        MapLocation position = behaviour.getPosition(unit);
        assert position != null
                //TODO: : "Method getMapLocation() shouldn't be returning null";
                : "Method getPosition() shouldn't be returning null";
        //TODO: return mapLocation;
        return position;
    }

    //TODO: @Override public void setMapLocation(Unit unit, MapLocation mapLocation) {
    @Override public void setPosition(Unit unit, MapLocation position) {
        //TODO: assert mapLocation != null : "Method setMapLocation() requires non-null mapLocation"
        if (position == null) {
            throw new NullPointerException(
                    "Method setPosition() requires non-null position");
        }
        //TODO: behaviour.setMapLocation(unit, mapLocation);
        behaviour.setPosition(unit, position);
    }

    @Override public void turnTick(Unit unit) {
        behaviour.turnTick(unit);

        Optional<Unit> nearest = getGameState().getUnitsInRange(
                getPosition(unit),
                getSightRadius(unit)
        ).stream().min((a, b) -> {
            //TODO: double aX = getMapLocation(unit).getX() - a.getMapLocation().getX();
            //TODO: double aY = getMapLocation(unit).getY() - a.getMapLocation().getY();
            //TODO: double bX = getMapLocation(unit).getX() - b.getMapLocation().getX();
            //TODO: double bY = getMapLocation(unit).getY() - b.getMapLocation().getY();
            double aX = getPosition(unit).getX() - a.getPosition().getX();
            double aY = getPosition(unit).getY() - a.getPosition().getY();
            double bX = getPosition(unit).getX() - b.getPosition().getX();
            double bY = getPosition(unit).getY() - b.getPosition().getY();

            return (int) (hypot(aX, aY) - hypot(bX, bY));
        });

        //TODO: too much indentation
        /*
            if (nearest.isPresent()) {
                if (getGameState().getUnitsInRange(getPosition(unit), 1).contains(nearest.get())) {
                    getGameState().attack(unit, nearest.get().getPosition());
                } else {
                   // TODO: Approach unit
                }
            } else {
            // TODO: Explore
            }
         */

        if (nearest.isPresent()) {
            if (getGameState().getUnitsInRange(getPosition(unit), 1)
                    .contains(nearest.get()))
            {
                getGameState().attack(unit, nearest.get().getPosition());
            }
            else {
                // TODO: Approach unit
            }
        }
        else {
            // TODO: Explore
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
        //TODO: assert direction != null : "Method move() requires a non-null direction";
        if (direction == null) {
            throw new NullPointerException(
                    "Method move() requires a non-null direction");
        }
        return behaviour.move(unit, direction);
    }

    @Override public int attack(Unit unit, Unit enemy) {
        //TODO: assert enemy != null : "Method attack() requires a non-null enemy";
        if (enemy == null) {
            throw new NullPointerException(
                    "Method attack() requires a non-null enemy");
        }
        return behaviour.attack(unit, enemy);
    }

    @Override public int defend(Unit unit, Unit enemy) {
        //TODO: assert enemy != null : "Method attack() requires a non-null enemy";
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
