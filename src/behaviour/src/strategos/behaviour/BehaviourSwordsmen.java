package strategos.behaviour;


import strategos.*;
import strategos.units.*;


class BehaviourSwordsmen extends UnitBehaviour {

    BehaviourSwordsmen(GameState gameState, Unit unit) {
        super(gameState, unit);
    }

    @Override public int getStrength() {
        return 25;
    }

    @Override public int getToughness() {
        return 18;
    }
}
