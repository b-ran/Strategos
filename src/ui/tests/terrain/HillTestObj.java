package terrain;

import strategos.Graphical;
import strategos.GraphicalVisitor;
import strategos.terrain.Hill;

public class HillTestObj extends TerrainTestObj implements Hill, Graphical {
    @Override
    public void draw(GraphicalVisitor graphicalVisitor) {
        graphicalVisitor.visit(this);
    }
}
