package mapcreation.mapgeneration;

import mapcreation.mapgeneration.terrain.*;
import mapcreation.noisegeneration.NoiseGenerator;
import strategos.Paintable;
import strategos.terrain.Terrain;

import java.util.Random;

/**
 * Created by Shaun Sinclair
 * Strategos
 * 28/07/2017.
 */
public class TerrainGeneration {

    //Following vars will eventually be pulled from settings that the user can change(set statically for now)
    /**
     * Change the resolution if sampling areas to produce map instead of individual pixels(wont be used in this version)
     */
    private int xRes = 1, yRes = 1;

    /**
     * Changes the average elevation of the map
     * Higher value = falter map(more plains less mountains)
     * Max 100
     * Min 1
     */
    private int flatness = 90;

    /**
     * Changes the average forest content of the map
     * Higher value = less forest on the map
     * Max 100
     * Min 1
     */
    private int forested = 90;

    private int octaves = 512;
    private double persistence = 0.01;

    /**
     * Frequency of different terrain types
     * Highest value it occurs at
     */
    private double plainsFreq = 0.5, hillFreq = 0.2 + plainsFreq;//, mountainFreq = 0.3 + hillFreq + plainsFreq;

    /**
     * Takes a square 2D array of paintable objects and applies generated terrain to each
     *
     * @param hexMap 2D array(Square) of paintable objects
     * @param seed   Seed for maps random so a map can be played more than once
     * @return 2D array of painted objects
     */
    public Paintable[][] populateMap(Paintable[][] hexMap, int seed) {
        //Map must be min of 15 in size, square
        if (hexMap.length != hexMap[0].length || hexMap.length < 15) {
            throw new RuntimeException("Map to small");
        }
        //Dimensions for noise map
        int width = hexMap[0].length, height = hexMap.length;
        //Create map and fills it with noise values
        double[][] mapTopology = fillMap(width, height, seed);
        boolean[][] forestMap = fillForest(width, height, seed);

        return setTerrain(mapTopology, forestMap, hexMap);
    }

    /**
     * Takes a square 2D array of paintable objects and applies generated terrain to each
     *
     * @param hexMap 2D array(Square) of paintable objects
     * @return 2D array of painted objects
     */
    public Paintable[][] populateMap(Paintable[][] hexMap) {
        Random rand = new Random();
        int seed = rand.nextInt();
        //Map must be min of 15 in size, square
        if (hexMap.length != hexMap[0].length || hexMap.length < 15) {
            throw new RuntimeException("Map to small");
        }
        //Dimensions for noise map
        int width = hexMap[0].length, height = hexMap.length;
        //Create map and fills it with noise values
        double[][] mapTopology = fillMap(width, height, seed);
        boolean[][] forestMap = fillForest(width, height, seed);

        return setTerrain(mapTopology, forestMap, hexMap);
    }

    /**
     * Fills a map with noise values
     *
     * @param width  Width of hexMap to be filled
     * @param height Height of hexMap to be filled
     * @param seed   The seed the map is being produced from
     * @return Map of noise values
     */
    private double[][] fillMap(int width, int height, int seed) {
        //Calls the noise generation class to produce a field of noise
        NoiseGenerator generatedNoise = new NoiseGenerator(octaves, persistence, seed);
        //Create a topology map to fill
        double[][] mapTopology = new double[width * xRes][height * yRes];
        double noise;
        //Fill mapTopology with noise values
        for (int x = 0; x < mapTopology.length; x++) {
            for (int y = 0; y < mapTopology[0].length; y++) {
                noise = generatedNoise.getNoise(x, y);
                noise = (noise + 10) / 20;
                //Shifts the values of map
                noise = (noise / 100) * flatness;
                mapTopology[x][y] = noise;
            }
        }
        return mapTopology;
    }

    /**
     * For testing fillMap() with a specific value of forested
     *
     * @return fillMap()
     */
    public double[][] testFillMap(int width, int height, int seed, int flatness) {
        this.flatness = flatness;
        return fillMap(width, height, seed);
    }

    /**
     * Fills the map with forests
     *
     * @param width  Width of hexMap to be filled
     * @param height Height of hexMap to be filled
     * @param seed   The seed the map is being produced from
     * @return If the tile is forested or not
     */
    private boolean[][] fillForest(int width, int height, int seed) {
        //Calls the noise generation class to produce a field of noise(seed incremented to provide some deviation from the topologyMap)
        NoiseGenerator generatedNoise = new NoiseGenerator(512, 0.01, seed + 1);
        boolean[][] forestMap = new boolean[width * xRes][height * yRes];
        double noise;
        //Fill forestMap with noise
        for (int x = 0; x < forestMap.length; x++) {
            for (int y = 0; y < forestMap[0].length; y++) {
                noise = generatedNoise.getNoise(x, y);
                //noise values are ~ between -10 and 10 but need to be scaled to between ~ 0 and 1 for drawing an image to work in testing
                noise = (noise + 10) / 20;
                //Shifts the values of map
                noise = (noise / 100) * forested;
                forestMap[x][y] = noise >= 0.5;
            }
        }
        return forestMap;
    }

    /**
     * For testing fillForest() with a specific value of forested
     *
     * @return fillForest()
     */
    public boolean[][] testFillForest(int width, int height, int seed, int forested) {
        this.forested = forested;
        return fillForest(width, height, seed);
    }

    /**
     * This method fills the Paintable hexes with terrain based of data from mapTopology
     *
     * @param mapTopology The map of values produced as noise
     * @param hexMap      The map of Printable objects(Hexes)
     * @return hexMap with terrain filled in
     */
    private Paintable[][] setTerrain(double[][] mapTopology, boolean[][] forestMap, Paintable[][] hexMap) {
        //TODO check if this will work as the hexes are off by a margin
        if (mapTopology.length != hexMap.length || mapTopology[0].length != hexMap[0].length)
            throw new RuntimeException("mapTopology resolution is incorrect.");
        for (int x = 0; x < hexMap.length; x++) {
            for (int y = 0; y < hexMap[0].length; y++) {
                if (hexMap[x][y].isInPlayArea()) {
                    hexMap[x][y].setTerrain(getTerrain(mapTopology[x][y], forestMap[x][y]));
                } else {
                    hexMap[x][y].setTerrain(new MountainTile());
                }
            }
        }
        hexMap = generateRiver(hexMap);
        return hexMap;
    }

    /**
     * Samples the generated topology and sets the terrain of the paintable object
     *
     * @param value Noise at that point
     * @return TerrainTile specific to that hex
     */
    private Terrain getTerrain(double value, boolean forest) {
        if (value < plainsFreq) {
            return forest ? new ForestTile() : new PlainsTile();
        } else if (value < hillFreq) {
            return new HillTile();
        } else {
            return new MountainTile();
        }
    }

    /**
     * Generates a river going from top left to bottom right with some variation
     *
     * @param hexMap Map to put rivers into
     * @return hexMap with river
     */
    private Paintable[][] generateRiver(Paintable[][] hexMap) {
        int width = hexMap.length, height = hexMap[0].length;
        int x = 0, y = 0;
        for (int i = 0; i < width + height - 2; i++) {
            if (i % 2 == 0) x++;
            else y++;
            hexMap[x][y].setTerrain(new RiverTile());
        }
        return hexMap;
    }

}
