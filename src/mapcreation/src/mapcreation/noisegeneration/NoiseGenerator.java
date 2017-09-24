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
    private int NUM_OCTAVES;
    double[] frequencies, amplitudes;
    private double PERSISTENCE;


    public NoiseGenerator(int numOctaves, double persistence, int seed) {
        NUM_OCTAVES = numOctaves;
        PERSISTENCE = persistence;
        Random random = new Random(seed);
        octaves = new Octave[NUM_OCTAVES];
        frequencies = new double[NUM_OCTAVES];
        amplitudes = new double[NUM_OCTAVES];
        for (int i = 0; i < NUM_OCTAVES; i++) {
            octaves[i] = new Octave(random.nextInt());
            frequencies[i] = Math.pow(2, i);
            amplitudes[i] = Math.pow(PERSISTENCE, NUM_OCTAVES - i);
        }
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
