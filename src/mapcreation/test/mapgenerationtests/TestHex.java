package mapgenerationtests;


import strategos.Paintable;
import strategos.terrain.Terrain;

public class TestHex implements Paintable {
    private boolean isInPlayArea;
    private Terrain terrain;

    TestHex(boolean isInPlayArea) {
        this.isInPlayArea = isInPlayArea;
    }

    @Override
    public boolean isInPlayArea() {
        return isInPlayArea;
    }

    String getTerrain() {
        return terrain.toString();
    }

    @Override
    public void setTerrain(Terrain terrain) {
        if (isInPlayArea && this.terrain == null) {
            System.out.println("1");
            this.terrain = terrain;
        }
    }
}
