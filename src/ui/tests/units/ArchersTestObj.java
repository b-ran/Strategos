package units;

import strategos.GameObject;
import strategos.GameObjectVisitor;
import strategos.UnitOwner;
import strategos.behaviour.Behaviour;
import strategos.units.Archers;

public class ArchersTestObj extends UnitTestObj implements Archers, GameObject {
    public ArchersTestObj(UnitOwner owner) {
        super(owner);
    }

    @Override
    public void setBehaviour(Behaviour behaviour) {

    }

    @Override
    public void accept(GameObjectVisitor gameObjectVisitor) {
        gameObjectVisitor.visit(this);
    }
}
