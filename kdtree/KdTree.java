/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.ArrayList;

class Node {
    public Point2D point;
    public Node right;
    public Node left;
    public int depth;
    public Node parent;

    public Node(Point2D point, Node right, Node left, int depth, Node parent) {
        this.point = point;
        this.right = right;
        this.left = left;
        this.depth = depth;
        this.parent = parent;
    }

    public void draw() {
        this.point.draw();
        if (this.right != null) {
            this.right.draw();
        }
        if (this.left != null) {
            this.left.draw();
        }
    }
}

public class KdTree {
    private Node root;
    private int size;

    public KdTree() {
        root = null;
        size = 0;
    }                            // construct an empty set of points

    public boolean isEmpty() {
        return root == null;
    }                      // is the set empty?

    public int size() {
        return size;
    }                         // number of points in the set

    public void insert(
            Point2D p) {
        if (p == null)
            throw new IllegalArgumentException();
        Node r = root;
        if (r == null) {
            root = new Node(p, null, null, 0, null);
            size = size + 1;
            return;
        }
        while (r != null) {
            if (r.point.equals(p)) {
                return;
            }
            if (r.depth % 2 == 0) {
                if (p.x() > r.point.x()) {
                    if (r.right == null) {
                        r.right = new Node(p, null, null, r.depth + 1, r);
                        size = size + 1;
                        break;
                    }
                    else {
                        r = r.right;
                    }
                }
                else {
                    if (r.left == null) {
                        r.left = new Node(p, null, null, r.depth + 1, r);
                        size = size + 1;
                        break;
                    }
                    else {
                        r = r.left;
                    }
                }
            }
            else {
                if (p.y() > r.point.y()) {
                    if (r.right == null) {
                        r.right = new Node(p, null, null, r.depth + 1, r);
                        size = size + 1;
                        break;
                    }
                    else {
                        r = r.right;
                    }
                }
                else {
                    if (r.left == null) {
                        r.left = new Node(p, null, null, r.depth + 1, r);
                        size = size + 1;
                        break;
                    }
                    else {
                        r = r.left;
                    }
                }
            }

        }

    }            // add the point to the set (if it is not already in the set)

    public boolean contains(Point2D p) {
        if (p == null)
            throw new IllegalArgumentException();
        Node r = root;
        while (r != null) {
            if (r.point.equals(p)) {
                return true;
            }
            else {
                if (r.depth % 2 == 0) {
                    if (p.x() > r.point.x()) {
                        r = r.right;
                    }
                    else {
                        r = r.left;
                    }
                }
                else {
                    if (p.y() > r.point.y()) {
                        r = r.right;
                    }
                    else {
                        r = r.left;
                    }
                }
            }
        }
        return false;
    }            // does the set contain point p?

    public void draw() {
        if (root != null)
            root.draw();
    }                         // draw all points to standard draw

    private Iterable<Point2D> rec_range(RectHV rect, Node root) {
        ArrayList l = new ArrayList<Point2D>();
        if (root == null)
            return l;
        if (rect.contains(root.point))
            l.add(root.point);
        if (root.depth % 2 == 0) {
            if (root.point.x() >= rect.xmin() && root.left != null) {
                Iterable<Point2D> it = rec_range(rect, root.left);
                for (Point2D p : it) {
                    l.add(p);
                }
            }
            if (root.point.x() <= rect.xmax() && root.right != null) {
                Iterable<Point2D> it = rec_range(rect, root.right);
                for (Point2D p : it) {
                    l.add(p);
                }
            }
        }
        else {
            if (root.point.y() >= rect.ymin() && root.left != null) {
                Iterable<Point2D> it = rec_range(rect, root.left);
                for (Point2D p : it) {
                    l.add(p);
                }
            }
            if (root.point.y() <= rect.ymax() && root.right != null) {
                Iterable<Point2D> it = rec_range(rect, root.right);
                for (Point2D p : it) {
                    l.add(p);
                }
            }
        }
        return l;
    }

    public Iterable<Point2D> range(
            RectHV rect) {
        return rec_range(rect, root);
    }          // all points that are inside the rectangle (or on the boundary)

    private Node nearest1(Point2D p, Node root) {
        if (root.left == null && root.right == null)
            return root;
        Node r = root;
        while (r != null) {
            if (r.point.equals(p))
                return r;

            if (r.depth % 2 == 0) {
                if (p.x() > r.point.x()) {
                    if (r.right == null)
                        return r;
                    else {
                        r = r.right;
                    }
                }
                else {
                    if (r.left == null) {
                        return r;
                    }
                    else {
                        r = r.left;
                    }
                }
            }
            else {
                if (p.y() > r.point.y()) {
                    if (r.right == null) {
                        return r;
                    }
                    else {
                        r = r.right;
                    }
                }
                else {
                    if (r.left == null) {
                        return r;
                    }
                    else {
                        r = r.left;
                    }
                }
            }
        }
        return r;
    }

    private Node nearest2(Point2D p, double min_distance, Node current_root, Node search_root) {
        Node ret = current_root;
        double distanse = min_distance;
        if (search_root.point.distanceTo(p) < distanse) {
            ret = search_root;
            distanse = search_root.point.distanceTo(p);
        }
        if (search_root.depth % 2 == 0) {
            if (search_root.left != null && p.x() - min_distance <= search_root.point.x()) {
                ret = nearest2(p, distanse, ret, search_root.left);
                distanse = ret.point.distanceTo(p);
            }
            if (search_root.right != null && p.x() + min_distance >= search_root.point.x()) {
                ret = nearest2(p, distanse, ret, search_root.right);
                distanse = ret.point.distanceTo(p);
            }
        }
        else {
            if (search_root.left != null && p.y() - min_distance <= search_root.point.y()) {
                ret = nearest2(p, distanse, ret, search_root.left);
                distanse = ret.point.distanceTo(p);
            }
            if (search_root.right != null && p.y() + min_distance >= search_root.point.y()) {
                ret = nearest2(p, distanse, ret, search_root.right);
                distanse = ret.point.distanceTo(p);
            }
        }
        return ret;
    }

    public Point2D nearest(Point2D p) {
        Node r1 = nearest1(p, root);
        double distance = r1.point.distanceTo(p);
        return nearest2(p, distance, r1, root).point;
    }

    public static void main(String[] args) {

    }
}
