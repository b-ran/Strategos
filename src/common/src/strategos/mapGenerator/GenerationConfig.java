package strategos.mapGenerator;

public class GenerationConfig {

    /**
     * Frequency of different terrain types
     * Highest value it occurs at
     */
    public static double plainsFreq = 0.5, hillFreq = 0.11;// + plainsFreq;//, mountainFreq = 0.3 + hillFreq + plainsFreq;

    /**
     * Changes the average forest content of the map
     * Higher value = less forest on the map
     * Max 100
     * Min 1
     */
    public static int FOREST_FREQ = 90;

    /**
     * Changes the average elevation of the map
     * Higher value = falter map(more plains less mountains)
     * Max 100
     * Min 1
     */
    public static int MAP_ALTITUDE = 75;


    public static int NUM_OCTAVES = 512;

}
