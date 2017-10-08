package mapgenerationtests;

import mapcreation.mapgeneration.TerrainGeneration;
import mapcreation.mapgeneration.terrain.*;
import org.junit.Test;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MapTester {

    @Test
    public void testSetTerrain_1() {
        TestHex hex = new TestHex(true);
        assert (hex.isInPlayArea());
        hex.setTerrain(new ForestTile());
        assertEquals("ForestTile", hex.getTerrainName());
    }

    @Test
    public void testSetTerrain_2() {
        TestHex hex = new TestHex(true);
        hex.setTerrain(new ForestTile());
        assertEquals("ForestTile", hex.getTerrainName());
        hex.setTerrain(new RiverTile());
        assertEquals("ForestTile", hex.getTerrainName());
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
            names[i] = hexes[i].getTerrainName();
        }
        names[names.length - 1] = "";

        hexes[3].setTerrain(new RiverTile());
        hexes[2].setTerrain(new MountainTile());
        hexes[4].setTerrain(new PlainsTile());
        hexes[1].setTerrain(new HillTile());
        hexes[5].setTerrain(new ForestTile());
        for (int i = 0; i < names.length; i++) {
            assert (names[i].equals(hexes[i].getTerrainName()));
        }

    }

    @Test
    public void testSetTerrain_4() {
        TerrainGeneration terrainGeneration = new TerrainGeneration();
        TestMap testMap = new TestMap();
        terrainGeneration.populateMap(testMap.constructMap(20));

    }

    @Test
    public void test_settingTerrain_hill_05() {
        TestHex hex = new TestHex(true);
        assert (hex.isInPlayArea());
        hex.setTerrain(new HillTile());
        assertEquals("HillTile", hex.getTerrainName());
    }

    @Test
    public void test_settingTerrain_mountain_06() {
        TestHex hex = new TestHex(true);
        assert (hex.isInPlayArea());
        hex.setTerrain(new MountainTile());
        assertEquals("MountainTile", hex.getTerrainName());
    }

    @Test
    public void test_settingTerrain_plains_07() {
        TestHex hex = new TestHex(true);
        assert (hex.isInPlayArea());
        hex.setTerrain(new PlainsTile());
        assertEquals("PlainsTile", hex.getTerrainName());
    }

    @Test
    public void test_settingTerrain_river_08() {
        TestHex hex = new TestHex(true);
        assert (hex.isInPlayArea());
        hex.setTerrain(new RiverTile());
        assertEquals("RiverTile", hex.getTerrainName());
    }


    @Test
    public void test_settingTerrain_repeative_09() {
        TestHex hex = new TestHex(true);
        assert (hex.isInPlayArea());
        for (int i = 0; i < 100; i++) {
            hex.setTerrain(new ForestTile());
            hex.setTerrain(new HillTile());
            hex.setTerrain(new MountainTile());
            hex.setTerrain(new PlainsTile());
            hex.setTerrain(new RiverTile());
        }
        assertEquals("ForestTile", hex.getTerrainName());
    }

    @Test
    public void test_settingTerrain_null_10() {
        TestHex hex = new TestHex(true);
        try {
            hex.setTerrain(null);
            fail("Exception not thrown");
        } catch (IllegalArgumentException e) {
            return;
        }
    }

    /**
     * Used to check bits of code work properly while isolated
     */
    @Test
    public void genericTest() {

    }

}
