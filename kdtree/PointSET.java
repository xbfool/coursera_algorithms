/* *****************************************************************************
 *  Name: xbfool
 *  Date: 2021-04-22
 *  Description: algorithm
 **************************************************************************** */

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;

import java.util.ArrayList;
import java.util.Iterator;

public class PointSET {
    private SET<Point2D> set;

    public PointSET() {
        set = new SET<Point2D>();
    }                          // construct an empty set of points

    public boolean isEmpty() {
        return set.isEmpty();
    }                     // is the set empty?

    public int size() {
        return set.size();
    }                   // number of points in the set

    public void insert(
            Point2D p) {
        if (p == null)
            throw new IllegalArgumentException();
        set.add(p);
    }           // add the point to the set (if it is not already in the set)

    public boolean contains(Point2D p) {
        if (p == null)
            throw new IllegalArgumentException();
        return set.contains(p);
    }           // does the set contain point p?

    public void draw() {
        Iterator it = set.iterator();
        while (it.hasNext()) {
            Point2D p = (Point2D) it.next();
            p.draw();
        }
    }                    // draw all points to standard draw

    public Iterable<Point2D> range(
            RectHV rect) {
        if (rect == null)
            throw new IllegalArgumentException();
        ArrayList<Point2D> l = new ArrayList<Point2D>();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            Point2D p = (Point2D) it.next();
            if (rect.contains(p)) {
                l.add(p);
            }
        }
        return l;
    }        // all points that are inside the rectangle (or on the boundary)

    public Point2D nearest(
            Point2D p) {
        if (p == null)
            throw new IllegalArgumentException();
        Point2D ret = null;
        Double distant = Double.MAX_VALUE;
        Iterator it = set.iterator();
        while (it.hasNext()) {
            Point2D t = (Point2D) it.next();
            Double distance = p.distanceTo(t);
            if (distance < distant) {
                distant = distance;
                ret = p;
            }
        }
        return ret;
    }          // a nearest neighbor in the set to point p; null if the set is empty

    public static void main(
            String[] args) {

    }         // unit testing of the methods (optional)
}
