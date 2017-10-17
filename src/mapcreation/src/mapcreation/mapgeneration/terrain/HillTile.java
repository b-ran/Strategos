package mapcreation.mapgeneration.terrain;

import strategos.GameObject;
import strategos.GameObjectVisitor;
import strategos.terrain.Hill;

import static strategos.terrain.TerrainConfig.HILLS_PASSABLE;

public class HillTile extends TerrainTile implements Hill, GameObject {

    @Override
    public String toString() {
        return "HillTile";
    }

    @Override
    public boolean isPassable() {
        return HILLS_PASSABLE;
    }

    @Override
    public void accept(GameObjectVisitor gameObjectVisitor) {
        gameObjectVisitor.visit(this);
    }
}
