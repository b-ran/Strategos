package mapcreation.mapgeneration.terrain;

import strategos.Graphical;
import strategos.GraphicalVisitor;
import strategos.terrain.Mountain;

import static strategos.terrain.TerrainConfig.mountainsPassable;

public class MountainTile extends TerrainTile implements Mountain, Graphical {

    @Override
    public String toString() {
        return "MountainTile";
    }

    @Override
    public boolean isPassable() {
        return mountainsPassable;
    }

    @Override
    public void draw(GraphicalVisitor graphicalVisitor) {
        graphicalVisitor.visit(this);
    }
}
