package mapgenerationtests;

import mapcreation.mapgeneration.terrain.TerrainTile;
import strategos.Paintable;
import strategos.terrain.Terrain;

public class TestHex implements Paintable {
    private boolean isInPlayArea;
    private TerrainTile terrain;

    TestHex(boolean isInPlayArea) {
        this.isInPlayArea = isInPlayArea;
    }

    @Override
    public boolean isInPlayArea() {
        return isInPlayArea;
    }

    String getTerrain() {
        return isInPlayArea ? terrain.toString() : "";
    }

    public void setTerrain(TerrainTile terrain) {
        if (isInPlayArea && this.terrain == null) {
            this.terrain = terrain;
        }
    }

    @Override
    public void setTerrain(Terrain t) {

    }
}
