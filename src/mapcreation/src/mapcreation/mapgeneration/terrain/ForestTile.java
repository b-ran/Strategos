package mapcreation.mapgeneration.terrain;

import strategos.Graphical;
import strategos.GraphicalVisitor;
import strategos.terrain.Forest;

import static strategos.terrain.TerrainConfig.FORESTS_PASSABLE;

public class ForestTile extends TerrainTile implements Forest, Graphical {

    @Override
    public String toString() {
        return "ForestTile";
    }

    @Override
    public boolean isPassable() {
        return FORESTS_PASSABLE;
    }

    @Override
    public void draw(GraphicalVisitor graphicalVisitor) {
        graphicalVisitor.visit(this);
    }
}
