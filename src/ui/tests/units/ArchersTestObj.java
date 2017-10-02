package units;

import strategos.UnitOwner;
import strategos.behaviour.Behaviour;
import strategos.units.Archers;

public class ArchersTestObj extends UnitTestObj implements Archers {
    public ArchersTestObj(UnitOwner owner) {
        super(owner);
    }

    @Override
    public void setBehaviour(Behaviour behaviour) {

    }
}
