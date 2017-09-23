package strategos.behaviour;


import strategos.*;
import strategos.units.*;

import java.util.function.*;


class BehaviourBase implements Behaviour {

    private final GameState gameState;
    private final Unit unit;
    private Behaviour behaviour;

    BehaviourBase(GameState gameState, Unit unit, BiFunction<GameState, Unit, Behaviour> factoryMethod) {
        this.gameState = gameState;
        this.unit = unit;
        this.behaviour = factoryMethod.apply(gameState, unit);
    }

    @Override public MapLocation getPosition() {
        return behaviour.getPosition();
    }

    @Override public void setPosition(MapLocation position) {
        behaviour.setPosition(position);
    }

    @Override public void turnTick() {
        behaviour.turnTick();
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

    @Override public boolean move() {
        return behaviour.move();
    }

    @Override public int attack(Unit enemy) {
        return behaviour.attack(enemy);
    }

    @Override public int defend(Unit enemy) {
        return behaviour.defend(enemy);
    }

    @Override public int getStrength() {
        return behaviour.getStrength();
    }

    @Override public int getToughness() {
        return behaviour.getToughness();
    }

    final GameState getGameState() {
        return gameState;
    }

    final Unit getUnit() {
        return unit;
    }
}
