package strategos.behaviour;


import strategos.model.units.*;


abstract class AiBehaviour extends BehaviourBase
        implements UnitBehaviour, HasBehaviour<UnitBehaviour>
{

    private UnitBehaviour behaviour;

    @Override public UnitBehaviour getBehaviour() {
        assert behaviour != null
                : "Behaviour should never be null on retrieval.";
        return behaviour;
    }

    @Override public void setBehaviour(UnitBehaviour behaviour) {
        assert behaviour != null : "Behaviour should never be set to null.";
        this.behaviour = behaviour;
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
}
