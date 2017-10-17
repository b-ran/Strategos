package mapcreation.mapgeneration.terrain;

import strategos.GameObject;
import strategos.GameObjectVisitor;
import strategos.terrain.Plains;

import static strategos.terrain.TerrainConfig.PLAINS_PASSABLE;

public class PlainsTile extends TerrainTile implements Plains, GameObject {

    @Override
    public String toString() {
        return "PlainsTile";
    }

    @Override
    public boolean isPassable() {
        return PLAINS_PASSABLE;
    }

    @Override
    public void accept(GameObjectVisitor gameObjectVisitor) {
        gameObjectVisitor.visit(this);
    }
}
