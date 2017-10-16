package units;

import strategos.GameObject;
import strategos.GameObjectVisitor;
import strategos.model.UnitOwner;
import strategos.behaviour.Behaviour;
import strategos.units.Spearmen;

public class SpearmenTestObj extends UnitTestObj implements Spearmen, GameObject {
    public SpearmenTestObj(UnitOwner owner) {
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
