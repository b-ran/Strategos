package units;

import strategos.Graphical;
import strategos.GraphicalVisitor;
import strategos.UnitOwner;
import strategos.behaviour.Behaviour;
import strategos.units.Spearmen;

public class SpearmenTestObj extends UnitTestObj implements Spearmen, Graphical {
    public SpearmenTestObj(UnitOwner owner) {
        super(owner);
    }

    @Override
    public void setBehaviour(Behaviour behaviour) {

    }

    @Override
    public void draw(GraphicalVisitor graphicalVisitor) {
        graphicalVisitor.visit(this);
    }
}
