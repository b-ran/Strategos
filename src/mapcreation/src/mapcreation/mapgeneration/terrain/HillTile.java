package mapcreation.mapgeneration.terrain;

import strategos.Graphical;
import strategos.GraphicalVisitor;
import strategos.terrain.Hill;

public class HillTile extends TerrainTile implements Hill, Graphical {

    private final boolean passable = true;

    @Override
    public boolean isPassable() {
        return passable;
    }

    @Override
    public String toString() {
        return "HillTile";
    }

    @Override
    public void draw(GraphicalVisitor graphicalVisitor) {
        graphicalVisitor.visit(this);
    }
}
