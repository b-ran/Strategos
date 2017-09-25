package strategos.behaviour;


import strategos.*;
import strategos.units.*;


class BehaviourElite extends UnitBehaviour {

    BehaviourElite(GameState gameState, Unit unit) {
        super(gameState, unit);
    }

    @Override public int getStrength() {
        return 20;
    }

    @Override public int getToughness() {
        return 35;
    }
}
