package mapcreation.noisegeneration;

import java.awt.*;
import java.util.Random;

class Octave {
    /**
     * This is a list of random numbers from 0 - 255 that are sampled to add extra randomness to the noise(predictably)
     */
    private static short randomSupply[] = {
            146, 225, 186, 223, 234, 58, 113, 176, 151, 231, 203, 11, 43, 13, 150, 74, 249, 187, 110, 44, 221, 33, 241
            , 7, 204, 237, 14, 252, 96, 200, 130, 84, 251, 211, 21, 34, 224, 10, 114, 41, 49, 215, 68, 189, 47, 0, 191
            , 166, 80, 69, 83, 9, 208, 238, 170, 65, 162, 180, 16, 85, 18, 167, 141, 228, 132, 28, 248, 250, 152, 75
            , 161, 163, 253, 218, 20, 89, 23, 147, 45, 99, 101, 245, 32, 121, 4, 54, 15, 144, 154, 88, 135, 67, 205
            , 93, 240, 244, 159, 97, 143, 36, 92, 117, 102, 22, 2, 12, 1, 100, 128, 229, 35, 79, 227, 217, 210, 8, 235
            , 207, 118, 105, 87, 40, 226, 123, 155, 129, 30, 138, 199, 91, 174, 56, 106, 90, 77, 172, 134, 201, 109
            , 94, 60, 153, 158, 82, 214, 242, 53, 148, 73, 173, 116, 107, 216, 51, 202, 57, 52, 104, 37, 122, 111, 171
            , 29, 42, 178, 48, 24, 108, 124, 139, 25, 137, 72, 133, 71, 239, 156, 3, 230, 179, 165, 149, 182, 183, 247
            , 76, 190, 184, 192, 157, 127, 185, 126, 195, 194, 142, 196, 50, 198, 136, 206, 219, 39, 145, 222, 115
            , 131, 177, 59, 188, 168, 112, 193, 213, 232, 169, 27, 160, 31, 246, 254, 55, 61, 255, 62, 209, 103, 66
            , 175, 38, 6, 120, 212, 233, 19, 197, 236, 86, 46, 63, 243, 98, 26, 78, 95, 164, 81, 17, 64, 70, 119, 125
            , 220, 5, 181, 140
    };

    /**
     * Corners of the graph
     */
    private Point[] corners = {
            new Point(1, 1),
            new Point(-1, 1),
            new Point(1, -1),
            new Point(-1, -1)
    };


    private short[] perm = new short[512];
    private short[] permMod4 = new short[512];

    /**
     * Initialises the lookup tables that noise is created from later
     *
     * @param seed Allows for predictable randomisation
     */
    Octave(int seed) {
        short[] p = randomSupply.clone();
        Random random = new Random(seed);
        short temp;
        int to, from;
        int numberOfSwaps = 400;
        for (int i = 0; i < numberOfSwaps; i++) {
            from = random.nextInt(p.length);
            to = random.nextInt(p.length);
            temp = p[from];
            p[from] = p[to];
            p[to] = temp;
        }

        for (int i = 0; i < p.length; i++) {
            //Does binary "and" on the binary values of i and 255
            //eg. 0000 0000 1111 1111
            //  & 0000 0011 1001 0010
            //  = 0000 0000 1001 0010
            perm[i] = p[i & 255];
            permMod4[i] = (short) (perm[i] % 4);
        }

    }

    private final double F2 = 0.5 * (Math.sqrt(3.0) - 1);//0.3660254037844386
    private final double G2 = (3.0 - Math.sqrt(3.0)) / 6.0;//0.21132486540518713

    /**
     * @param xin X position to to sample
     * @param yin Y position to sample
     * @return Noise value at xin, yin
     */
    double noise(double xin, double yin) {
        int xSquashed = fastFloor(xin + (xin + yin) * F2);
        int ySquashed = fastFloor(yin + (xin + yin) * F2);

        double x = xin - (xSquashed - ((xSquashed + ySquashed) * G2));
        double y = yin - (ySquashed - ((xSquashed + ySquashed) * G2));

        int i1 = 0, j1 = 0;
        if (x > y) i1 = 1;
        else j1 = 1;

        double[] xArray = {
                x,
                x - i1 + G2,
                x - 1.0 + 2.0 * G2
        };
        double[] yArray = {
                y,
                y - j1 + G2,
                y - 1.0 + 2.0 * G2
        };

        //Reduce xSquashed and ySquashed to less than 255 if necessary
        xSquashed &= 255;
        ySquashed &= 255;

        int gi[] = {
                permMod4[xSquashed + perm[ySquashed]],
                permMod4[xSquashed + i1 + perm[ySquashed + j1]],
                permMod4[xSquashed + 1 + perm[ySquashed + 1]]
        };

        double nArray[] = new double[3];

        //may be able to use arrays
        double[] tArray = {
                0.5 - xArray[0] * xArray[0] - yArray[0] * yArray[0],
                0.5 - xArray[1] * xArray[1] - yArray[1] * yArray[1],
                0.5 - xArray[2] * xArray[2] - yArray[2] * yArray[2]
        };

        for (int k = 0; k < tArray.length; k++) {
            if (tArray[k] < 0) {
                nArray[k] = 0;
            } else {
                tArray[k] *= tArray[k];
                nArray[k] = tArray[k] * tArray[k] * dot(corners[gi[k]], xArray[k], yArray[k]);
            }
        }
//        System.out.println((nArray[0] + nArray[1] + nArray[2]));
        return 70 * (nArray[0] + nArray[1] + nArray[2]);
    }

    /**
     * Dot product
     *
     * @param point Corner started from
     * @param x     X position to to sample
     * @param y     Y position to sample
     * @return Scaled vector
     */
    private double dot(Point point, double x, double y) {
        return point.x * x + point.y * y;
    }

    /**
     * "Faster" version of floor, rounds values down
     *
     * @return Integer value of x
     */
    private int fastFloor(double x) {
        return x > 0 ? (int) x : (int) x - 1;
    }

}
