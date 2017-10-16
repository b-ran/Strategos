package mapcreation.mapgeneration.terrain;

import strategos.GameObject;
import strategos.GameObjectVisitor;
import strategos.terrain.Mountain;

import static strategos.terrain.TerrainConfig.MOUNTAINS_PASSABLE;

public class MountainTile extends TerrainTile implements Mountain, GameObject {

    @Override
    public String toString() {
        return "MountainTile";
    }

    @Override
    public boolean isPassable() {
        return MOUNTAINS_PASSABLE;
    }

    @Override
    public void accept(GameObjectVisitor gameObjectVisitor) {
        gameObjectVisitor.visit(this);
    }
}
