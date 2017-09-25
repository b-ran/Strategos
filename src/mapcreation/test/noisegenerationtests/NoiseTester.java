package noisegenerationtests;


import mapcreation.noisegeneration.NoiseGenerator;
import mapcreation.noisegeneration.partsNeedsToBeRenamed.Octave;
import mapcreation.noisegeneration.partsNeedsToBeRenamed.PrintMap;
import org.junit.Test;


public class NoiseTester {

    @Test
    public void testOctave_1() {
        Octave testOctave = new Octave(10);
    }

    @Test
    public void testOctave_2() {
        int size = 50;
        NoiseGenerator generatedNoise = new NoiseGenerator(128, 1/4, Integer.MAX_VALUE);
        double[][] mapTopology = new double[size][size];
        int resolution = size;
        int xPos, yPos;
        double max = Double.MIN_VALUE, min = Double.MAX_VALUE;
        for (int x = 0; x < mapTopology.length; x++) {
            for (int y = 0; y < mapTopology[0].length; y++) {
                xPos = x * resolution;
                yPos = y * resolution;
                double noise = 1+generatedNoise.getNoise(xPos, yPos);
                noise = (noise + 9) / 20;
//                if (noise>255)noise = 255;
//                System.out.println(noise);
                mapTopology[x][y] = noise;
                if (max < noise) max = noise;
                if (min > noise) min = noise;
            }
        }
        System.out.println("Max: " + max + "\nMin: " + min);
        PrintMap.greyImage(mapTopology);
    }

}
