package strategos.behaviour;


import strategos.*;
import strategos.units.*;


class BehaviourCavalry extends UnitBehaviour {

    BehaviourCavalry(GameState gameState, Unit unit) {
        super(gameState, unit);
    }

    @Override int getMaxActionPoints() {
        return 4;
    }

    @Override public int getStrength() {
        return 30;
    }

    @Override public int getToughness() {
        return 15;
    }
}
