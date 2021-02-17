/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.Queue;
public class FastCollinearPoints {
    private Queue<LineSegment> segments;
    public FastCollinearPoints(Point[] points){
        // if(points == null)
        //     throw new IllegalArgumentException();
        // segments = new Queue<LineSegment>();
        // for (int i = 0; i < points.length; i++) {
        //     if(points[i] == null){
        //         throw new IllegalArgumentException();
        //     }
        // }
        // Arrays.sort(points);
        // for (int i = 0; i < points.length; i++) {
        //     Point p = points[i];
        //     Point[] ps1 =
        //     Arrays.sort(points, p.slopeOrder());
        //     double slope = Double.NEGATIVE_INFINITY;
        //     int index = 0;
        //     int start = 0;
        //     int end = 0;
        //     for (int j = 0; j < points.length; j++) {
        //         if(p.slopeTo(points[j]) == slope){
        //             index = index + 1;
        //         }else{
        //             if(index >= 4){
        //                 LineSegment ls = new LineSegment(points[])
        //             }
        //         }
        //     }
        // }
    }    // finds all line segments containing 4 or more points
    public           int numberOfSegments()  {
        return segments.size();
    }      // the number of line segments
    public LineSegment[] segments(){
        LineSegment[] ss = new LineSegment[segments.size()];
        for (int i = 0; i < segments.size(); i++) {
            ss[i] = segments.dequeue();
        }
        return ss;
     }
    public static void main(String[] args) {

    }
}
