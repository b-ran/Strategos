package terrain;

import strategos.GameObject;
import strategos.GameObjectVisitor;
import strategos.terrain.Mountain;

public class MountainTestObj extends TerrainTestObj implements Mountain, GameObject {
    @Override
    public void accept(GameObjectVisitor gameObjectVisitor) {
        gameObjectVisitor.visit(this);
    }
}
