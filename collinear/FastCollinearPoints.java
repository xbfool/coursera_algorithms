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

public class FastCollinearPoints {
    private Queue<LineSegment> segments;
    public FastCollinearPoints(Point[] points){
         if(points == null)
             throw new IllegalArgumentException();
         segments = new Queue<LineSegment>();
         for (int i = 0; i < points.length; i++) {
             if(points[i] == null){
                 throw new IllegalArgumentException();
             }
         }
         Arrays.sort(points);

//         StdOut.println("start print points");
//         for(int kk = 0; kk < points.length; kk++)
//         {
//             StdOut.println(points[kk]);
//         }
//         StdOut.println("end print points");
         for (int i = 0; i < points.length-3; i++) {
             Point[] points2 = Arrays.copyOfRange(points, i+1, points.length);

             //StdOut.println("start print points2");
//             for(int kk = 0; kk < points2.length; kk++)
//             {
//                 StdOut.println(points2[kk]);
//             }
             //StdOut.println("end print points2");

             Point p = points[i];
             //StdOut.println(p);
             Arrays.sort(points2, p.slopeOrder());
             double slope = Double.NEGATIVE_INFINITY;
             int index = 0;
             int start = 0;
             for (int j = 0; j < points2.length; j++) {
                 if(p == points2[j]){
                     //StdOut.println("continue");
                     continue;
                 }

                 if(p.slopeTo(points2[j]) == slope){
                     //StdOut.println("index+1");
                     index = index + 1;
                     //StdOut.println(index);
                 }else{
                     //StdOut.println("index end");
                     //StdOut.println(index);
                     if(index >= 2){
                         LineSegment ls = new LineSegment(p, points2[j-1]);
                         segments.enqueue(ls);
                         //StdOut.println(ls);
                     }
                     start = j;
                     index = 0;
                     slope = p.slopeTo(points2[j]);
                     //StdOut.println("slope");
                     //StdOut.println(slope);
                 }
             }
             if(index >= 2){
                 //StdOut.println("index en2");
                 LineSegment ls = new LineSegment(p, points2[points2.length-1]);
                 segments.enqueue(ls);
                 //StdOut.println(ls);
             }
         }
    }    // finds all line segments containing 4 or more points
    public int numberOfSegments()  {
        return segments.size();
    }      // the number of line segments
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
            StdOut.println(p);
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
