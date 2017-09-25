package strategos.behaviour;


import strategos.*;
import strategos.units.*;


class BehaviourSpearmen extends UnitBehaviour {

    BehaviourSpearmen(GameState gameState, Unit unit) {
        super(gameState, unit);
    }

    @Override public int getStrength() {
        return Config.SPEARMEN_STRENGTH;
    }

    @Override public int getToughness() {
        return Config.SPEARMEN_TOUGHNESS;
    }
}
