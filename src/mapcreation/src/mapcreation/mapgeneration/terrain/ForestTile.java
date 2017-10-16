package mapcreation.mapgeneration.terrain;

import strategos.GameObject;
import strategos.GameObjectVisitor;
import strategos.terrain.Forest;

import static strategos.terrain.TerrainConfig.FORESTS_PASSABLE;

public class ForestTile extends TerrainTile implements Forest, GameObject {

    @Override
    public String toString() {
        return "ForestTile";
    }

    @Override
    public boolean isPassable() {
        return FORESTS_PASSABLE;
    }

    @Override
    public void accept(GameObjectVisitor gameObjectVisitor) {
        gameObjectVisitor.visit(this);
    }
}
