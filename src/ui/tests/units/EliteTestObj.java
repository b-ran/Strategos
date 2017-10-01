package units;

import strategos.UnitOwner;
import strategos.behaviour.Behaviour;
import strategos.units.Elite;

public class EliteTestObj extends UnitTestObj implements Elite {
    public EliteTestObj(UnitOwner owner) {
        super(owner);
    }

    @Override
    public void setBehaviour(Behaviour behaviour) {

    }
}
