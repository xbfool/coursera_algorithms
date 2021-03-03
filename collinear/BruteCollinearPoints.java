/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

public class BruteCollinearPoints {
    private Queue<LineSegment> segments;
    public BruteCollinearPoints(Point[] points){
        if(points == null)
            throw new IllegalArgumentException();
        segments = new Queue<LineSegment>();
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) {
                throw new IllegalArgumentException();
            }
        }
        Arrays.sort(points);
        for (int i = 0; i < points.length - 3; i++) {
            for (int j = i + 1; j < points.length - 2; j++) {
                for (int k = j + 1; k < points.length - 1; k++) {
                    for (int l = k + 1; l < points.length; l++) {
                        Point [] ps= new Point[] {points[i], points[j], points[k], points[l]};
/*                        Arrays.sort(ps);
                        StdOut.println("---------");
                        StdOut.println(ps[0]);
                        StdOut.println(ps[1]);
                        StdOut.println(ps[2]);
                        StdOut.println(ps[3]);
                        StdOut.println("---------");*/
                        if( ps[0].slopeTo(ps[1]) == ps[0].slopeTo(ps[2]) && ps[0].slopeTo(ps[1]) == ps[0].slopeTo(ps[3])){
                            LineSegment ls = new LineSegment(ps[0], ps[3]);
                            segments.enqueue(ls);
                        }
                    }
                }
            }
        }
    }  // finds all line segments containing 4 points
    public int numberOfSegments(){
        return segments.size();
    }        // the number of line segments
    public LineSegment[] segments(){
        int l = segments.size();
        LineSegment[] s = new LineSegment[l];
        for (int i = 0; i < l; i++) {
            s[i] = segments.dequeue();
        }
        return s;
    }
    public static void main(String[] args) {
        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            //System.out.println("test");
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
