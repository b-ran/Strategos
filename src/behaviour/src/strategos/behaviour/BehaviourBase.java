package strategos.behaviour;


import strategos.MapLocation;

class BehaviourBase implements Behaviour {

    private Behaviour behaviour;

    BehaviourBase(Behaviour behaviour) {
        this.behaviour = behaviour;
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

    @Override public int attack(Movable enemy) {
        return behaviour.attack(enemy);
    }

    @Override public int defend(Movable enemy) {
        return behaviour.defend(enemy);
    }

    @Override public int getStrength() {
        return behaviour.getStrength();
    }

    @Override public int getToughness() {
        return behaviour.getToughness();
    }
}
