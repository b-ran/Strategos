package units;

import strategos.GameObject;
import strategos.GameObjectVisitor;
import strategos.UnitOwner;
import strategos.behaviour.Behaviour;
import strategos.units.Cavalry;

public class CavalryTestObj extends UnitTestObj implements Cavalry, GameObject {
    public CavalryTestObj(UnitOwner owner) {
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
