package mapcreation.mapgeneration.terrain;

import strategos.Graphical;
import strategos.GraphicalVisitor;
import strategos.terrain.Plains;

public class PlainsTile extends TerrainTile implements Plains, Graphical {

    private final boolean passable =  true;

    @Override
    public boolean isPassable() {
        return passable;
    }

    @Override
    public String toString() {
        return "PlainsTile";
    }

    @Override
    public void draw(GraphicalVisitor graphicalVisitor) {
        graphicalVisitor.visit(this);
    }
}
