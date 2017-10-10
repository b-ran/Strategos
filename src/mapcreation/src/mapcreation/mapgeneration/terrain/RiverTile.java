package mapcreation.mapgeneration.terrain;

import strategos.Graphical;
import strategos.GraphicalVisitor;
import strategos.terrain.River;

import static strategos.terrain.TerrainConfig.riversPassable;

public class RiverTile extends TerrainTile implements River, Graphical {

    @Override
    public String toString() {
        return "RiverTile";
    }

    @Override
    public boolean isPassable() {
        return riversPassable;
    }

    @Override
    public void draw(GraphicalVisitor graphicalVisitor) {
        graphicalVisitor.visit(this);
    }
}