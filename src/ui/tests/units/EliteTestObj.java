package units;

import strategos.Graphical;
import strategos.GraphicalVisitor;
import strategos.UnitOwner;
import strategos.behaviour.Behaviour;
import strategos.units.Elite;

public class EliteTestObj extends UnitTestObj implements Elite, Graphical {
    public EliteTestObj(UnitOwner owner) {
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
