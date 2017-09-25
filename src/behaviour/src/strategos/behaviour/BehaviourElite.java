package strategos.behaviour;


import strategos.*;
import strategos.units.*;


class BehaviourElite extends UnitBehaviour {

    BehaviourElite(GameState gameState, Unit unit) {
        super(gameState, unit);
    }

    @Override public int getStrength() {
        return Config.ELITE_STRENGTH;
    }

    @Override public int getToughness() {
        return Config.ELITE_TOUGHNESS;
    }
}
