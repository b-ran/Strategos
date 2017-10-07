package units;

import strategos.Graphical;
import strategos.GraphicalVisitor;
import strategos.UnitOwner;
import strategos.behaviour.Behaviour;
import strategos.units.Cavalry;

public class CavalryTestObj extends UnitTestObj implements Cavalry, Graphical {
    public CavalryTestObj(UnitOwner owner) {
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
