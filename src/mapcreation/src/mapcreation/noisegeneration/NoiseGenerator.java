package mapcreation.noisegeneration;

import java.util.Random;

/**
 * Created by Shaun Sinclair
 * Strategos
 * 28/08/2017.
 */
public class NoiseGenerator {
    /**
     * Array of octaves
     */
    private Octave[] octaves;
    private double[] frequencies;

    /**
     * Produces and collates A number of octaves of noise into a map
     *
     * @param numOctaves  Number of octaves to create
     * @param seed        Seed for the randomisation so that the same map will be made of the same seed
     *                    Have removed Persistence
     */
    public NoiseGenerator(int numOctaves, int seed) {
        Random random = new Random(seed);
        octaves = new Octave[numOctaves];
        frequencies = new double[numOctaves];

        for (int i = 0; i < numOctaves; i++) {
            octaves[i] = new Octave(random.nextInt());
            frequencies[i] = Math.pow(2, i);
        }
    }

    /**
     * Returns the value from  that position
     *
     * @param x X position to sample
     * @param y Y position to sample
     * @return Noise value from map
     */
    public double getNoise(int x, int y) {
        double result = 0;
        for (int i = 0; i < octaves.length; i++) {
            result += octaves[i].noise(x / frequencies[i], y / frequencies[i]);
        }
        return result;
    }
}
