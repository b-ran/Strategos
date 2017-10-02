package units;

import strategos.UnitOwner;
import strategos.behaviour.Behaviour;
import strategos.units.Cavalry;

public class CavalryTestObj extends UnitTestObj implements Cavalry {
    public CavalryTestObj(UnitOwner owner) {
        super(owner);
    }

    @Override
    public void setBehaviour(Behaviour behaviour) {

    }
}
