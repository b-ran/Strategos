package mapcreation.noisegeneration;

/**
 * Created by Shaun Sinclair
 * Strategos
 * 28/08/2017.
 */
public class NoiseGenerator {

    /**
     * Generates a field of noise with the parameters set by width and height
     *
     * @param width Size * 5(sampling for flat top hexes)
     * @param height Size * 3(sampling for flat top hexes)
     * @return A field of generated noise(Topology)
     */
    public double[][] generateNoise(int width, int height) {
        //TODO write Perlin
        return new double[width][height];
    }
}
