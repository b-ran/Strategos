package mapcreation.mapgeneration.terrain;

import strategos.Graphical;
import strategos.GraphicalVisitor;
import strategos.terrain.Hill;

import static strategos.terrain.TerrainConfig.hillsPassable;

public class HillTile extends TerrainTile implements Hill, Graphical {

    @Override
    public String toString() {
        return "HillTile";
    }

    @Override
    public boolean isPassable() {
        return hillsPassable;
    }

    @Override
    public void draw(GraphicalVisitor graphicalVisitor) {
        graphicalVisitor.visit(this);
    }
}
