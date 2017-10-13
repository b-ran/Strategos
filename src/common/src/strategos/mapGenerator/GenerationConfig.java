package strategos.mapGenerator;

public class GenerationConfig {

    /**
     * Frequency of different terrain types
     * Highest value it occurs at
     */
    public static double PLAINS_FREQ = 0.465, HILL_FREQ = 0.27 + PLAINS_FREQ, MOUNTAIN_FREQ = 1;

    /**
     * Changes the average forest content of the map
     * Lower value = less forest on the map
     * Max 1.0
     * Min 0.0
     */
    public static double FOREST_FREQ = 0.47;

    /**
     * Changes the average elevation of the map
     * Higher value = falter map(more plains less mountains)
     * Max 100
     * Min 1
     */
    public static int MAP_ALTITUDE = 90;

    public static int NUM_OCTAVES = 32;

    public static int X_RES = 4, Y_RES = 4;

}
