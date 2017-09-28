package mapgenerationtests;

import mapcreation.mapgeneration.terrain.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MapTester {

    @Test
    public void testSetTerrain_1() {
        TestHex hex = new TestHex(true);
        assert (hex.isInPlayArea());
        hex.setTerrain(new ForestTile());
        assertEquals("ForestTile", hex.getTerrain());
    }

    @Test
    public void testSetTerrain_2() {
        TestHex hex = new TestHex(true);
        hex.setTerrain(new ForestTile());
        assertEquals("ForestTile", hex.getTerrain());
        hex.setTerrain(new RiverTile());
        assertEquals("ForestTile", hex.getTerrain());
    }

    @Test
    public void testSetTerrain_3() {
        TestHex[] hexes = {
                new TestHex(false),
                new TestHex(true),
                new TestHex(true),
                new TestHex(true),
                new TestHex(true),
                new TestHex(true),
                new TestHex(false)
        };

        hexes[1].setTerrain(new ForestTile());
        hexes[2].setTerrain(new HillTile());
        hexes[3].setTerrain(new PlainsTile());
        hexes[4].setTerrain(new MountainTile());
        hexes[5].setTerrain(new RiverTile());

        String[] names = new String[hexes.length];

        names[0] = "";
        for (int i = 1; i < hexes.length - 1; i++) {
            names[i] = hexes[i].getTerrain();
        }
        names[names.length - 1] = "";

        hexes[3].setTerrain(new RiverTile());
        hexes[2].setTerrain(new MountainTile());
        hexes[4].setTerrain(new PlainsTile());
        hexes[1].setTerrain(new HillTile());
        hexes[5].setTerrain(new ForestTile());
        for (int i = 0; i < names.length; i++) {
            assert (names[i].equals(hexes[i].getTerrain()));
        }

    }

    @Test
    public void testSetTerrain_4() {

    }

    /**
     * Used to check bits of code work properly while isolated
     */
    @Test
    public void genericTest() {

    }

}
