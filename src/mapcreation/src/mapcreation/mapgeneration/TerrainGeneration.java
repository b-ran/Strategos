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
     * @param map 2D array(Square) of paintable objects
     * @return 2D array of painted objects
     */
    public Paintable[][] generateSquareMap(Paintable[][] map) {
        final int size = map.length;
        //Calls the noise generation class to produce a field of noise
        //width is multiplied by 4 and y by 3 because i plan to sample every corner and the center of the hex
        double[][] mapTopology = new NoiseGenerator().generateNoise(size * 5, size * 3);
        for (int x = 0; x < map[0].length; x++) {
            for (int y = 0; y < map[x].length; y++) {
                map[x][y].setTerrain(getTerrain(x, y, size, mapTopology));
            }
        }
        return map;
    }

    /**
     * Samples the generated topology and sets the terrain of the paintable object
     *
     * @param x           X position
     * @param y           Y position
     * @param size        Board size
     * @param mapTopology Generated topology
     * @return The terrain type
     */
    public Terrain getTerrain(int x, int y, int size, double[][] mapTopology) {
        //TODO
        return new Forest();
    }
}
