package mapcreation.mapgeneration;

import mapcreation.mapgeneration.terrain.*;
import mapcreation.noisegeneration.NoiseGenerator;
import strategos.Paintable;
import strategos.mapGenerator.Generator;
import strategos.terrain.Terrain;

import java.util.Random;

import static strategos.mapGenerator.GenerationConfig.*;

/**
 * Created by Shaun Sinclair
 * Strategos
 * 28/07/2017.
 */
public class TerrainGeneration implements Generator {


    /**
     * Change the resolution if sampling areas to produce map instead of individual pixels(wont be used in this version)
     */
    private int xRes = X_RES, yRes = Y_RES;

    //Following vars will eventually be pulled from settings that the user can change(set statically for now)

    /**
     * Number of octaves used by the generator(currently way to high)
     */
    private int octaves = NUM_OCTAVES;

    /**
     * Changes the average elevation of the map
     * Lower value = falter map(more plains less mountains)
     * Max 100
     * Min 1
     */
    private int mapAltitude = MAP_ALTITUDE;

    /**
     * Changes the average forest content of the map
     * Higher value = more forest on the map
     * Max 1.0
     * Min 0.0
     */
    private double forestFreq = FOREST_FREQ;

    /**
     * Percentage of the map that has to be hills and forests
     */
    private final float percentage = PERCENTAGE;

    /**
     * Takes a square 2D array of paintable objects and applies generated terrain to each
     *
     * @param map  2D array(Square) of paintable objects
     * @param seed Seed for the maps random generator so a map can be played more than once
     * @return 2D array of painted objects
     */
    public Paintable[][] populateMap(Paintable[][] map, int seed) {
        //Map must be min of 15 in size, square
        if (map.length != map[0].length || map.length < 15) {
            throw new RuntimeException("Map to small");
        }

        return setTerrain(seed, map);
    }

    /**
     * Takes a square 2D array of paintable objects and applies generated terrain to each
     *
     * @param map 2D array(Square) of paintable objects
     * @return 2D array of painted objects
     */
    public Paintable[][] populateMap(Paintable[][] map) {
        Random rand = new Random();
        int seed = rand.nextInt();
        //Map must be min of 15 in size, square
        if (map.length != map[0].length || map.length < 15) {
            throw new RuntimeException("Map too small");
        }

        return setTerrain(seed, map);
    }


    /**
     * This method fills the Paintable tiles with terrain based of data from mapTopology
     *
     * @param seed Seed for the maps random generator
     * @param map  The map of Printable objects(tiles)
     * @return map with terrain filled in
     */
    private Paintable[][] setTerrain(int seed, Paintable[][] map) {
        //Dimensions for noise map
        int width = map[0].length, height = map.length;
        for (int i = 0; i < 20 && !isValid(map); i++) {
            //Create map and fills it with noise values
            double[][] mapTopology = fillMap(width, height, seed);
            boolean[][] forestMap = fillForest(width, height, seed);

            if (mapTopology.length != map.length || mapTopology[0].length != map[0].length)
                throw new RuntimeException("mapTopology resolution is incorrect.");

            for (int x = 0; x < map.length; x++) {
                for (int y = 0; y < map[0].length; y++) {
                    if (map[x][y].isInPlayArea()) {
                        map[x][y].setTerrain(getTerrain(mapTopology[x][y], forestMap[x][y]));
                    } else {
                        map[x][y].setTerrain(new MountainTile());
                    }
                }
            }
        }

        map = generateRiver(map);

        return map;
    }

    /**
     * Checks that the map is valid for normal play
     *
     * @param map Map to be checked
     * @return If the map is valid for play
     */
    private boolean isValid(Paintable[][] map) {
        if (map[0][0].getTerrain() == null) return false;
        int num = 0;
        for (Paintable[] mapRow : map) {
            for (Paintable Tile : mapRow) {
                if (Tile.getTerrain().toString().equals("HillTile") || Tile.getTerrain().toString().equals("ForestTile")) {
                    num++;
                }
            }
        }
        return num > ((map.length * map[0].length) / 100) * percentage;
    }


    /**
     * Fills a map with noise values
     *
     * @param width  Width of Map to be filled
     * @param height Height of Map to be filled
     * @param seed   The seed the map is being produced from
     * @return Map of noise values
     */
    private double[][] fillMap(int width, int height, int seed) {
        //Calls the noise generation class to produce a field of noise
        NoiseGenerator generatedNoise = new NoiseGenerator(octaves, seed);
        //Create a topology map to fill
        double[][] mapTopology = new double[width][height];
        double noise;
        //Fill mapTopology with noise values
        for (int x = 0; x < mapTopology.length * xRes; x += xRes) {
            for (int y = 0; y < mapTopology[0].length * yRes; y += yRes) {
                noise = generatedNoise.getNoise(x, y);
                noise = (noise + 10) / 20;
                //Shifts the values of map
                noise = (noise / 100) * mapAltitude;
                mapTopology[x / xRes][y / yRes] = noise;
            }
        }
        return mapTopology;
    }

    /**
     * For testing fillMap() with a specific value of FOREST_FREQ
     *
     * @return fillMap()
     */
    public double[][] testFillMap(int width, int height, int seed, int flatness, int octaves) {
        this.mapAltitude = flatness;
        this.octaves = octaves;
        return fillMap(width, height, seed);
    }

    /**
     * Fills the map with forests
     *
     * @param width  Width of Map to be filled
     * @param height Height of Map to be filled
     * @param seed   The seed the map is being produced from
     * @return If the tile is FOREST_FREQ or not
     */
    private boolean[][] fillForest(int width, int height, int seed) {
        //Calls the noise generation class to produce a field of noise(seed incremented to provide some deviation from the topologyMap)
        NoiseGenerator generatedNoise = new NoiseGenerator(512, seed + 1);
        boolean[][] forestMap = new boolean[width][height];
        double noise;
        //Fill forestMap with noise
        for (int x = 0; x < forestMap.length; x++) {
            for (int y = 0; y < forestMap[0].length; y++) {
                noise = generatedNoise.getNoise(x, y);
                //noise values are ~ between -10 and 10 but need to be scaled to between ~ 0 and 1 for drawing an image to work in testing
                noise = (noise + 10) / 20;
                forestMap[x][y] = noise <= forestFreq;
            }
        }
        return forestMap;

    }

    /**
     * For testing fillForest() with a specific value of FOREST_FREQ
     *
     * @return fillForest()
     */
    public boolean[][] testFillForest(int width, int height, int seed, double forested) {
        this.forestFreq = forested;
        return fillForest(width, height, seed);
    }

    /**
     * Samples the generated topology and sets the terrain of the paintable object
     *
     * @param value Noise at that point
     * @return TerrainTile specific to that tile
     */
    private Terrain getTerrain(double value, boolean forest) {

        if (value < PLAINS_FREQ) {
            return forest ? new ForestTile() : new PlainsTile();
        } else if (value < HILL_FREQ) {
            return new HillTile();
        } else if (value < MOUNTAIN_FREQ) {
            return new MountainTile();
        }
        if (value > 1 || value < 0) {
            System.out.println(value);
        }
        return new PlainsTile();
    }

    /**
     * Generates a river going from top left to bottom right with some variation
     *
     * @param map Map to put rivers into
     * @return ap with river
     */
    private Paintable[][] generateRiver(Paintable[][] map) {
        int width = map.length, height = map[0].length;
        int x = 0, y = 0;
        for (int i = 0; i < width + height - 2; i++) {
            if (i % 2 == 0) x++;
            else y++;
            map[x][y].setTerrain(new RiverTile());
        }
        return map;
    }

}
