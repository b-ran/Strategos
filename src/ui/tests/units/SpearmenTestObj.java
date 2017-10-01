package units;

import strategos.UnitOwner;
import strategos.behaviour.Behaviour;
import strategos.units.Spearmen;

public class SpearmenTestObj extends UnitTestObj implements Spearmen {
    public SpearmenTestObj(UnitOwner owner) {
        super(owner);
    }

    @Override
    public void setBehaviour(Behaviour behaviour) {

    }
}
