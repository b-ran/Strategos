package terrain;

import strategos.Graphical;
import strategos.GraphicalVisitor;
import strategos.terrain.Mountain;

public class MountainTestObj extends TerrainTestObj implements Mountain, Graphical {
    @Override
    public void draw(GraphicalVisitor graphicalVisitor) {
        graphicalVisitor.visit(this);
    }
}
