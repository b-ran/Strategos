package terrain;

import strategos.GameObject;
import strategos.GameObjectVisitor;
import strategos.terrain.Forest;

public class ForestTestObj extends TerrainTestObj implements Forest, GameObject {
    @Override
    public void accept(GameObjectVisitor gameObjectVisitor) {
        gameObjectVisitor.visit(this);
    }
}
