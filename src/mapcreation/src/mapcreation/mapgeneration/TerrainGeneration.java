package mapcreation.mapgeneration;

import mapcreation.mapgeneration.terrain.Forest;
import mapcreation.noisegeneration.NoiseGenerator;
import strategos.Paintable;
import strategos.terrain.Terrain;

/**
 * Created by Shaun Sinclair
 * Strategos
 * 28/07/2017.
 */
public class TerrainGeneration {

    /**
     * Takes a square 2D array of paintable objects and applies generated terrain to each
     *
     * @param hexMap 2D array(Square) of paintable objects
     * @return 2D array of painted objects
     */
    public Paintable[][] populateMap(Paintable[][] hexMap, int seed) {
        final int size = hexMap.length;
        //Calls the noise generation class to produce a field of noise
        //width is multiplied by 4 and y by 3 because i plan to sample every corner and the center of the hex

//        double[][] mapTopology =
        NoiseGenerator generatedNoise = new NoiseGenerator(8, 1 / 2, seed);
        //startX and y are 0 and end is size
        int resolution =1;
        int xPos,yPos;
        for (int x = 0; x < hexMap[0].length; x++) {
            for (int y = 0; y < hexMap[x].length; y++) {
                xPos = size*resolution;
                yPos = size*resolution;
                double noise =
            }
        }
        return hexMap;
    }
//    hexMap[x][y].setTerrain(getTerrain(x, y, size, mapTopology));
    /**
     * Samples the generated topology and sets the terrain of the paintable object
     *
     * @param x    X position
     * @param y    Y position
     * @param size Board size
     * @return The terrain type
     */
    public Terrain getTerrain(int x, int y, int size) {
        //TODO
        //may remove persistence(and octaves)
        return new Forest();
    }
}
