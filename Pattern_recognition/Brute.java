import java.io.File;
import java.util.Arrays;

/**
 * @author Olena Ivashyna
 * @date march 2, 2013
 */
public class Brute {

    public static void main(String[] args) {
        In read = new In(new File(args[0]));
        int N = read.readInt();
        Point[] points = new Point[N];
        for (int i = 0; i < N; i++) {
            int x = read.readInt();
            int y = read.readInt();
            points[i] = new Point(x, y);
        }
        Point p, q, r, s;
        StdDraw.setCanvasSize(800, 800);
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.setPenRadius(.05);
        Arrays.sort(points);
        //draw all points
        for(int foo=0; foo<N; foo++)
            points[foo].draw();
        
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                for (int k = j + 1; k < N; k++) {
                    for (int l = k + 1; l < N; l++) {
                        p = points[i]; //point p
                        q = points[j]; //point q
                        r = points[k]; //point r
                        s = points[l]; //point s
                        //Arrays.sort(a, p.SLOPE_ORDER);
                        double slopePQ = p.slopeTo(q);
                        double slopePR = p.slopeTo(r);
                        double slopePS = p.slopeTo(s);
                        if (slopePQ == slopePR && slopePR == slopePS) {
                            // print out point set
                            StdOut.println(p.toString() + " -> " + q.toString() + " -> " + r.toString() + " -> " + s.toString());
                            //draw line segments
                            StdDraw.setPenRadius(.005);
                            //p.drawTo(q);
                            //p.drawTo(r);
                            p.drawTo(s);
                        }
                    }
                }
            }
        }
    }
}