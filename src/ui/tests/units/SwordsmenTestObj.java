package units;

import strategos.Graphical;
import strategos.GraphicalVisitor;
import strategos.UnitOwner;
import strategos.behaviour.Behaviour;
import strategos.units.Swordsmen;

public class SwordsmenTestObj extends UnitTestObj implements Swordsmen, Graphical {
    public SwordsmenTestObj(UnitOwner owner) {
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
