package mapcreation.noisegeneration;

import java.awt.*;
import java.util.Random;

public class Octave {

    private Point[] corners = {
            new Point(1, 1),
            new Point(-1, 1),
            new Point(1, -1),
            new Point(-1, -1)
    };

    //TODO: rename
    //TODO: describe
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
    private short[] perm = new short[512];
    private short[] permMod4 = new short[512];

    public Octave(int seed) {
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

    //rename
    private final double F2 = 0.5 * (Math.sqrt(3.0) - 1);
    private final double G2 = (3.0 - Math.sqrt(3.0)) / 6.0;

    //rename to x, y

    /**
     * @param xin
     * @param yin
     * @return
     */
    public double noise(double xin, double yin) {
        double s = (xin + yin) * F2;
        int i = fastFloor(xin + s);
        int j = fastFloor(yin + s);
        double t = (i + j) * G2;
        double X0 = i - t;
        double Y0 = j - t;
        double x0 = xin - X0;
        double y0 = yin - Y0;

        int i1 = 0, j1 = 0;
        if (x0 > y0) {
            i1 = 1;
        } else {
            j1 = 1;
        }
        double[] x = {
                x0,
                x0 - i1 + G2,
                x0 - 1.0 + 2.0 * G2
        };
        double[] y = {
                y0,
                y0 - j1 + G2,
                y0 - 1.0 + 2.0 * G2
        };

        //Reduce i and j to less than 255 if necessary
        i &= 255;
        j &= 255;

        int gi[] = {permMod4[i + perm[j]],
                permMod4[i + i1 + perm[j + j1]],
                permMod4[i + 1 + perm[j + 1]]};
        double n[] = new double[3];
        //may be able to use arrays
        double[] tArray = {
                0.5 - x[0] * x[0] - y[0] * y[0],
                0.5 - x[1] * x[1] - y[1] * y[1],
                0.5 - x[2] * x[2] - y[2] * y[2]
        };

        for (int k = 0; k < tArray.length; k++) {
            if (tArray[k] < 0) {
                n[k] = 0;
            } else {
                tArray[k] *= tArray[k];
                n[k] = tArray[k] * tArray[k] * dot(corners[gi[k]], x[k], y[k]);
            }
        }
//        System.out.println((n[0] + n[1] + n[2]));
        return 70 * (n[0] + n[1] + n[2]);
    }

    /**
     * @param point
     * @param x
     * @param y
     * @return
     */
    private double dot(Point point, double x, double y) {
        return point.x * x + point.y * y;
    }

    /**
     * "Faster" version of floor
     *
     * @return Integer value of x
     */
    private int fastFloor(double x) {
        return x > 0 ? (int) x : (int) x - 1;
    }

}
