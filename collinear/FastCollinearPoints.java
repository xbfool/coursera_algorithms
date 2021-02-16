/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {
    private ArrayList<LineSegment> segments;
    public FastCollinearPoints(Point[] points){
        if(points == null)
            throw new IllegalArgumentException();
        segments = new ArrayList<LineSegment>();
        for (int i = 0; i < points.length; i++) {
            if(points[i] == null){
                throw new IllegalArgumentException();
            }
        }
        for (int i = 0; i < points.length; i++) {
            Point p = points[i];
            Arrays.sort(points, p.slopeOrder());
            double slope = Double.NEGATIVE_INFINITY;
            int index = 0;
            int start = 0;
            int end = 0;
            for (int j = 0; j < points.length; j++) {
                if(p.slopeTo(points[j]) == slope){
                    index = index + 1;
                }else{
                    if(index >= 4){
                        LineSegment ls = new LineSegment(points[])
                    }
                }
            }
        }
    }    // finds all line segments containing 4 or more points
    public           int numberOfSegments()  {
        return segments.size();
    }      // the number of line segments
    public LineSegment[] segments(){
        return (LineSegment[]) segments.toArray();
    }
    public static void main(String[] args) {

    }
}
