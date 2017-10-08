package terrain;

import strategos.Graphical;
import strategos.GraphicalVisitor;
import strategos.terrain.River;

public class RiverTestObj extends TerrainTestObj implements River, Graphical {
    @Override
    public void draw(GraphicalVisitor graphicalVisitor) {
        graphicalVisitor.visit(this);
    }
}
