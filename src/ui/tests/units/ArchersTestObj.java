package units;

import strategos.Graphical;
import strategos.GraphicalVisitor;
import strategos.UnitOwner;
import strategos.behaviour.Behaviour;
import strategos.units.Archers;

public class ArchersTestObj extends UnitTestObj implements Archers, Graphical {
    public ArchersTestObj(UnitOwner owner) {
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
