package mapcreation.mapgeneration.terrain;

import strategos.Graphical;
import strategos.GraphicalVisitor;
import strategos.terrain.River;

public class RiverTile extends TerrainTile implements River, Graphical {

    private final boolean passable = false;

    @Override
    public String toString() {
        return "RiverTile";
    }

    @Override
    public boolean isPassable() {
        return passable;
    }

    @Override
    public void draw(GraphicalVisitor graphicalVisitor) {
        graphicalVisitor.visit(this);
    }
}