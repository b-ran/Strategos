package mapcreation.mapgeneration.terrain;

import strategos.Graphical;
import strategos.GraphicalVisitor;
import strategos.terrain.Hill;

import static strategos.terrain.TerrainConfig.HILLS_PASSABLE;

public class HillTile extends TerrainTile implements Hill, Graphical {

    @Override
    public String toString() {
        return "HillTile";
    }

    @Override
    public boolean isPassable() {
        return HILLS_PASSABLE;
    }

    @Override
    public void draw(GraphicalVisitor graphicalVisitor) {
        graphicalVisitor.visit(this);
    }
}
