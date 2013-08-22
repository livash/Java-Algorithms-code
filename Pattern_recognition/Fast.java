import java.io.File;
import java.util.Arrays;

/**
 * @author Olena Ivashyna
 * @date march 2, 2013
 */
public class Fast {

    public static void main(String[] args) {
        StdDraw.setCanvasSize(800, 800);
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.setPenRadius(.05);
        In read = new In(new File(args[0]));
        int N = read.readInt();
        Point[] points = new Point[N];
        Point[] aux; // auxilary array
        double[] slopes;
        int count = 0;
        for (int i = 0; i < N; i++) {
            int x = read.readInt();
            int y = read.readInt();
            points[i] = new Point(x, y);
            Point p = points[0];
        }
        //draw all points
        for (int foo = 0; foo < N; foo++) {
            points[foo].draw();
        }
        //find the smallest point of them all
        Arrays.sort(points);
        for (int i = 0; i < N; i++) {
            aux = new Point[N - i - 1];
            for (int g = i + 1; g < N; g++) {
                int ind = g - i - 1;
                aux[ind] = points[g];
            }
            Arrays.sort(aux, points[i].SLOPE_ORDER);
            slopes = new double[aux.length];
            for (int j = 0; j < aux.length; j++) // fill in slopes array
            {
                slopes[j] = points[i].slopeTo(aux[j]);
            }

            //now loop through the slopes array to find identical slope values
            for (int k = 0; k < aux.length; k++) {
                if (slopes[k] != Double.NEGATIVE_INFINITY) {
                    for (int m = k + 1; m < aux.length; m++) {
                        if (slopes[k] == slopes[m]) {
                            count++;
                        }
                    }
                    if (count >= 2) {// atleast 3 points have same slope with p
                        StdOut.print(points[i].toString());
                        for (int f = 0; f <= count; f++) {
                            StdOut.print(" -> " + aux[k + f].toString());
                            StdDraw.setPenRadius(.005);
                            //aux[k + f].drawTo(points[i]);
                        }
                        StdOut.println();
                        points[i].drawTo(aux[k+count]);
                    }
                }
                k = k + count;
                count = 0;
            }
        }
    }
}
