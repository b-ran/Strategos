package terrain;

import strategos.GameObject;
import strategos.GameObjectVisitor;
import strategos.terrain.Plains;

public class PlainsTestObj extends TerrainTestObj implements Plains, GameObject {
    @Override
    public void accept(GameObjectVisitor gameObjectVisitor) {
        gameObjectVisitor.visit(this);
    }
}
