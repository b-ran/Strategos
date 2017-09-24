package strategos.behaviour;


import strategos.*;
import strategos.units.*;


public class BehaviourSpearmen extends UnitBehaviour {

    BehaviourSpearmen(GameState gameState, Unit unit) {
        super(gameState, unit);
    }

    @Override public int getStrength() {
        return 15;
    }

    @Override public int getToughness() {
        return 25;
    }
}
