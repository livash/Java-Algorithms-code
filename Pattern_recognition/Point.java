import java.util.Arrays;
import java.util.Comparator;

/**
 * @author Olena Ivashyna
 * @date march 2, 2013
 */
public class Point implements Comparable<Point> {

    public final Comparator<Point> SLOPE_ORDER = new SlopeOrder();  
    private int x;
    private int y;    

    public Point(int x, int y) // construct the point (x, y)
    {
        this.x = x;
        this.y = y;
    }

    public void draw() {
        StdDraw.point(x, y);
    }

    // draw the line segment from this point to that point
    public void drawTo(Point that) 
    {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    @Override
    public String toString() // string representation
    {
        return "(" + x + ", " + y + ")";
    }

    @Override
    // is this point lexicographically smaller than that point?
    public int compareTo(Point that) 
    {
        if (this.y < that.y) {
            return -1;
        }
        if (this.y > that.y) {
            return +1;
        }
        if (this.x < that.x) {
            return -1;
        }
        if (this.x > that.x) {
            return +1;
        }
        return 0;
    }

    // the slope between this point and that point
    public double slopeTo(Point that) 
    {
        if (y==that.y && x!=that.x) return 0;
        else if (y!=that.y && x==that.x) return Double.POSITIVE_INFINITY;
        else if (y==that.y && x==that.x) return Double.NEGATIVE_INFINITY;
        else return (double) (that.y - y) / (double) (that.x - x);
    }

    private class SlopeOrder implements Comparator<Point> {

        @Override
        public int compare(Point o1, Point o2) {
            double slope1 = slopeTo(o1);
            double slope2 = slopeTo(o2);
            if (slope1 > slope2) {
                return +1;
            } else if (slope1 < slope2) {
                return -1;
            } else {
               /* if      (o1.x>o2.x && o1.y==o2.y) return +1;
                else if (o1.x<o2.x && o1.y==o2.y) return -1;
                else if (o1.x==o2.x && o1.y>o2.y) return +1;
                else if (o1.x==o2.x && o1.y<o2.y) return -1;*/
                return 0;
            }
        }
    }
}