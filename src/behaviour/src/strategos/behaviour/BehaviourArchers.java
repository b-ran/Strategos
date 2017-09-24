package strategos.behaviour;


import strategos.*;
import strategos.units.*;


public class BehaviourArchers extends UnitBehaviour {

    BehaviourArchers(GameState gameState, Unit unit) {
        super(gameState, unit);
    }

    @Override public int getStrength() {
        return 18;
    }

    @Override public int getToughness() {
        return 10;
    }

    @Override public void charge() {
        // Archers cannot charge
    }
}
