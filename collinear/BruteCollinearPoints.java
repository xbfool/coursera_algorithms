/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    ArrayList<LineSegment> segments;
    public BruteCollinearPoints(Point[] points){
        if(points == null)
            throw new IllegalArgumentException();
        segments = new ArrayList<LineSegment>();
        for (int i = 0; i < points.length; i++) {
            if(points[i] == null){
                throw new IllegalArgumentException();
            }
        }
        for (int i = 0; i < points.length - 3; i++) {
            for (int j = i + 1; j < points.length - 2; j++) {
                for (int k = j + 1; k < points.length - 1; k++) {
                    for (int l = k + 1; l < points.length; l++) {
                        Point [] ps= new Point[] {points[0], points[1], points[2], points[3]};
                        Arrays.sort(ps);
                        if( ps[0].slopeTo(ps[1]) == ps[0].slopeTo(ps[2]) && ps[0].slopeTo(ps[1]) == ps[0].slopeTo(ps[2])){
                            LineSegment ls = new LineSegment(ps[0], ps[3]);
                            segments.add(ls);
                        }
                    }
                }
            }
        }
    }  // finds all line segments containing 4 points
    public           int numberOfSegments(){
        return segments.size();
    }        // the number of line segments
    public LineSegment[] segments(){
        return (LineSegment[]) segments.toArray();
    }
    public static void main(String[] args) {

    }
}
