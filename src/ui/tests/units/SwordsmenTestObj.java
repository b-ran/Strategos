package units;

import strategos.UnitOwner;
import strategos.behaviour.Behaviour;
import strategos.units.Swordsmen;

public class SwordsmenTestObj extends UnitTestObj implements Swordsmen {
    public SwordsmenTestObj(UnitOwner owner) {
        super(owner);
    }

    @Override
    public void setBehaviour(Behaviour behaviour) {

    }
}
