package mapcreation.mapgeneration.terrain;

import strategos.Graphical;
import strategos.GraphicalVisitor;
import strategos.terrain.Forest;

import static strategos.terrain.TerrainConfig.forestsPassable;

public class ForestTile extends TerrainTile implements Forest, Graphical {

    @Override
    public String toString() {
        return "ForestTile";
    }

    @Override
    public boolean isPassable() {
        return forestsPassable;
    }

    @Override
    public void draw(GraphicalVisitor graphicalVisitor) {
        graphicalVisitor.visit(this);
    }
}
