package strategos.behaviour;


import strategos.*;
import strategos.units.*;


class BehaviourSwordsmen extends UnitBehaviour {

    BehaviourSwordsmen(GameState gameState, Unit unit) {
        super(gameState, unit);
    }

    @Override public int getStrength() {
        return Config.SWORDSMEN_STRENGTH;
    }

    @Override public int getToughness() {
        return Config.SWORDSMEN_TOUGHNESS;
    }
}
