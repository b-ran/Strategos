package terrain;

import strategos.Graphical;
import strategos.GraphicalVisitor;
import strategos.terrain.Plains;

public class PlainsTestObj extends TerrainTestObj implements Plains, Graphical {
    @Override
    public void draw(GraphicalVisitor graphicalVisitor) {
        graphicalVisitor.visit(this);
    }
}
