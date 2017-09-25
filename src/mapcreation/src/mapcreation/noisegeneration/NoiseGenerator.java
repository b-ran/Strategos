package mapcreation.noisegeneration;

import mapcreation.noisegeneration.partsNeedsToBeRenamed.Octave;

import java.util.Random;

/**
 * Created by Shaun Sinclair
 * Strategos
 * 28/08/2017.
 */
public class NoiseGenerator {

    private Octave[] octaves;
    private double[] frequencies, amplitudes;


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

    /**
     *
     * @param x
     * @param y
     * @return
     */
    public double getNoise(int x, int y) {
        double result = 0;
        for (int i = 0; i < octaves.length; i++) {
            result += octaves[i].noise(x / frequencies[i], y / frequencies[i]);
        }
        return result;
    }
}
