package noisegenerationtests;


import mapcreation.mapgeneration.TerrainGeneration;
import mapcreation.noisegeneration.NoiseGenerator;
import mapcreation.noisegeneration.partsNeedsToBeRenamed.Octave;
import mapcreation.noisegeneration.partsNeedsToBeRenamed.PrintMap;
import org.junit.jupiter.api.Test;


class NoiseTester {

    @Test
    void testOctave_1(){
        Octave testOctave = new Octave(10);
    }

    @Test
    void testOctave_2(){
        int size = 250;
        NoiseGenerator generatedNoise = new NoiseGenerator(7, 1 / 2, 200);
        double[][] mapTopology = new double[size][size];
        int resolution = 1;
        int xPos,yPos;
        for (int x = 0; x < mapTopology.length; x++) {
            for (int y = 0; y < mapTopology[0].length; y++) {
                xPos = size*resolution;
                yPos = size*resolution;
                double noise = 1+generatedNoise.getNoise(xPos,yPos);
                System.out.println(noise);
                mapTopology[x][y] = noise;
            }
        }
        PrintMap.greyImage(mapTopology);
    }

}
