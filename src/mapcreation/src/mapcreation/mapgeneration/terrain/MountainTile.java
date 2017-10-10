package mapcreation.mapgeneration.terrain;

import strategos.Graphical;
import strategos.GraphicalVisitor;
import strategos.terrain.Mountain;

public class MountainTile extends TerrainTile implements Mountain, Graphical {


    private final boolean passable = false;

    @Override
    public boolean isPassable() {
        return passable;
    }

    @Override
    public String toString() {
        return "MountainTile";
    }

    @Override
    public void draw(GraphicalVisitor graphicalVisitor) {
        graphicalVisitor.visit(this);
    }
}
