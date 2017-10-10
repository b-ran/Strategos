package mapcreation.mapgeneration.terrain;

import strategos.Graphical;
import strategos.GraphicalVisitor;
import strategos.terrain.Plains;

import static strategos.terrain.TerrainConfig.plainsPassable;

public class PlainsTile extends TerrainTile implements Plains, Graphical {

    @Override
    public String toString() {
        return "PlainsTile";
    }

    @Override
    public boolean isPassable() {
        return plainsPassable;
    }

    @Override
    public void draw(GraphicalVisitor graphicalVisitor) {
        graphicalVisitor.visit(this);
    }
}
