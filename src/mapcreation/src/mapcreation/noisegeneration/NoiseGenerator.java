package mapcreation.noisegeneration;

import mapcreation.noisegeneration.partsNeedsToBeRenamed.Octave;

import java.util.Random;

/**
 * Created by Shaun Sinclair
 * Strategos
 * 28/08/2017.
 */
public class NoiseGenerator {

    Octave[] octaves;
    double[] frequencies, amplitudes;


    public NoiseGenerator(int numOctaves, double persistence, int seed) {
        Random random = new Random(seed);
        octaves = new Octave[numOctaves];
        frequencies = new double[numOctaves];
        amplitudes = new double[numOctaves];

        for (int i = 0; i < numOctaves; i++) {
            octaves[i] = new Octave(random.nextInt());
            frequencies[i] = Math.pow(2, i);
            amplitudes[i] = Math.pow(persistence, numOctaves - i);
        }
    }


    public double getNoise(int x, int y){
        double result = 0;
        for (int i = 0; i < octaves.length; i++) {
            result+=octaves[i].noise(x/frequencies[i], y/frequencies[i]*amplitudes[i]);
        }
        return result;
    }

    /**
     * Generates a field of noise with the parameters set by width and height
     *
     * @param width  Size * 5(sampling for flat top hexes)
     * @param height Size * 3(sampling for flat top hexes)
     * @return A field of generated noise(Topology)
     */
    public double[][] generateNoise(int width, int height) {
        return new double[1][1];
    }


}
