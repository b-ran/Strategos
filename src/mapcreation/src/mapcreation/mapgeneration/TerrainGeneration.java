package mapcreation.mapgeneration;

import mapcreation.mapgeneration.terrain.Forest;
import mapcreation.noisegeneration.NoiseGenerator;
import strategos.Paintable;
import strategos.terrain.Terrain;

import javax.imageio.ImageWriter;
import java.awt.*;

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


        NoiseGenerator generatedNoise = new NoiseGenerator(8, 1 / 2, seed);
        double[][] mapTopology = new double[size][size];
        int resolution = 1;
        int xPos, yPos;
        for (int x = 0; x < hexMap[0].length; x++) {
            for (int y = 0; y < hexMap[x].length; y++) {
                xPos = size * resolution;
                yPos = size * resolution;
                double noise = 1 + generatedNoise.getNoise(xPos, yPos);
                mapTopology[x][y] = noise;
            }
        }

        return setTerrain(mapTopology, hexMap);
    }
//    hexMap[x][y].setTerrain(getTerrain(x, y, size, mapTopology));

    private Paintable[][] setTerrain(double[][] mapTopology, Paintable[][] hexMap) {
        if (mapTopology.length != hexMap.length || mapTopology[0].length != hexMap[0].length)
            throw new RuntimeException("mapTopology resolution is incorrect.");
        for (int x = 0; x < hexMap.length; x++) {
            for (int y = 0; y < hexMap[0].length; y++) {
                hexMap[x][y].setTerrain(getTerrain(mapTopology[x][y]));
            }
        }
        return hexMap;
    }
    //Samples the generated topology and sets the terrain of the paintable object

    public Terrain getTerrain(double value) {
        //TODO
        //may remove persistence(and octaves)
        return new Forest();
    }

}
