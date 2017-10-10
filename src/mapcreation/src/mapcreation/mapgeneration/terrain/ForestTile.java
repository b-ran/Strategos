package mapcreation.mapgeneration.terrain;

import strategos.Graphical;
import strategos.GraphicalVisitor;
import strategos.terrain.Forest;

public class ForestTile extends TerrainTile implements Forest, Graphical {

    private final boolean passable = true;

    @Override
    public boolean isPassable() {
        return passable;
    }

    @Override
    public String toString() {
        return "ForestTile";
    }

    @Override
    public void draw(GraphicalVisitor graphicalVisitor) {
        graphicalVisitor.visit(this);
    }
}
