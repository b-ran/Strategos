package mapcreation.mapgeneration.terrain;

import strategos.GameObject;
import strategos.GameObjectVisitor;
import strategos.terrain.River;

import static strategos.terrain.TerrainConfig.RIVERS_PASSABLE;

public class RiverTile extends TerrainTile implements River, GameObject {

    @Override
    public String toString() {
        return "RiverTile";
    }

    @Override
    public boolean isPassable() {
        return RIVERS_PASSABLE;
    }

    @Override
    public void accept(GameObjectVisitor gameObjectVisitor) {
        gameObjectVisitor.visit(this);
    }
}