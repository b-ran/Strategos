package noisegenerationtests;


import mapcreation.mapgeneration.TerrainGeneration;
import mapcreation.noisegeneration.NoiseGenerator;
import mapcreation.noisegeneration.noiseutil.PrintMap;
import org.junit.Test;

public class NoiseTester {
    /**
     * There was a point during testing where a pretty obscure bug came up
     * that was to do with errors in floating point number calculations and
     * finding it took running intensive tests like this. this was because of the
     * likely hood of the errors occurring being low as the values that caused
     * them where at the max and min points in the noise generation.
     * RUN ONLY IF YOU HAVE A BIT(read a lot) OF TIME AND REALLY WANT TO CHECK MY NOISE VALUES
     * ARE 99.999% PERFECT.
     */
//    @Test
//    public void testNoiseGeneration_0() {
//        int width = 200, height = 200;
//        int xPos, yPos;
//        double[][] mapTopology;
//        double max = Double.MIN_VALUE, min = Double.MAX_VALUE;
//        double noise;
//        int seed;
//
//        for (int i = 0; i < 1000; i++) {
//            seed = (int) (Math.random() * 255);
//            NoiseGenerator generatedNoise = new NoiseGenerator(64, 0.01, seed);
//            mapTopology = new double[width][height];
//            for (int x = 0; x < mapTopology.length; x++) {
//                for (int y = 0; y < mapTopology[0].length; y++) {
//                    xPos = x * width;
//                    yPos = y * height;
//                    noise = generatedNoise.getNoise(xPos, yPos);
//                    noise = (noise + 12) / 24;
//                    mapTopology[x][y] = noise;
//                    if (max < noise) max = noise;
//                    if (min > noise) min = noise;
//                }
//            }
//        }
//        //Includes a little leeway due to float rounding being weird
//        assert (max < 1.02);
//        assert (min > -0.02);
//
//    }


    @Test
    public void testNoiseGeneration_1() {
        int width = 200, height = 200;
        int xPos, yPos;
        double[][] mapTopology;
        double max = Double.MIN_VALUE, min = Double.MAX_VALUE;
        double noise;
        int seed;

        for (int i = 0; i < 100; i++) {
            seed = (int) (Math.random() * 255);
            NoiseGenerator generatedNoise = new NoiseGenerator(64, seed);
            mapTopology = new double[width][height];
            for (int x = 0; x < mapTopology.length; x++) {
                for (int y = 0; y < mapTopology[0].length; y++) {
                    xPos = x * width;
                    yPos = y * height;
                    noise = generatedNoise.getNoise(xPos, yPos);
                    noise = (noise + 12) / 24;
                    mapTopology[x][y] = noise;
                    if (max < noise) max = noise;
                    if (min > noise) min = noise;
                }
            }
        }
        //Includes a little leeway due to float rounding being weird
        assert (max < 1.02);
        assert (min > -0.02);

    }


    /*@Test
    public void mapTest_1() {
        TerrainGeneration TG = new TerrainGeneration();
        double[][] map = TG.testFillMap(50, 50, 1, 90);
        //To physically compare to correctTopology
        PrintMap.greyImage(map);
        double[][] testMap = noisegenerationtests.TestResources.testMap;
        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[0].length; y++) {
                assert (map[x][y] == testMap[x][y]);
            }
        }
    }

    @Test
    public void forestTest_1() {
        TerrainGeneration TG = new TerrainGeneration();
        boolean[][] forest = TG.testFillForest(50, 50, 1, 90);
        //To physically compare to correctForest
        PrintMap.greyImage(forest);
        boolean[][] testForest = noisegenerationtests.TestResources.testForest;
        for (int x = 0; x < forest.length; x++) {
            for (int y = 0; y < forest[0].length; y++) {
                assert (forest[x][y] == testForest[x][y]);
            }
        }
    }*/




}
