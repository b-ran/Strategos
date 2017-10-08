package terrain;

import strategos.Graphical;
import strategos.GraphicalVisitor;
import strategos.terrain.Forest;

public class ForestTestObj extends TerrainTestObj implements Forest, Graphical {
    @Override
    public void draw(GraphicalVisitor graphicalVisitor) {
        graphicalVisitor.visit(this);
    }
}
