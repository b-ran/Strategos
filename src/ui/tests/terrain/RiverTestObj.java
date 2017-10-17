package terrain;

import strategos.GameObject;
import strategos.GameObjectVisitor;
import strategos.terrain.River;

public class RiverTestObj extends TerrainTestObj implements River, GameObject {
    @Override
    public void accept(GameObjectVisitor gameObjectVisitor) {
        gameObjectVisitor.visit(this);
    }
}
