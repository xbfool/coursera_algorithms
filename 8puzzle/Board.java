/* *****************************************************************************
 *  Name: xbfu
 *  Date: 2021-04-06
 *  Description: algorithm
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;

public class Board {
    private int[][] tiles;
    private int size;
    private int hamming = -1;
    private int manhattan = -1;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        size = tiles.length;
        this.tiles = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.tiles[i][j] = tiles[i][j];
            }
        }
    }

    // string representation of this board
    public String toString() {
        String s1 = String.format("%d\n", size);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                s1 += String.format("%d ", tiles[i][j]);
            }
            s1 += "\n";
        }
        return s1;
    }

    // board dimension n
    public int dimension() {
        return size;
    }

    // number of tiles out of place
    public int hamming() {
        if (hamming != -1)
            return hamming;
        int x = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (tiles[i][j] != 0 && tiles[i][j] != i * size + j + 1) {
                    x++;
                }
            }
        }
        hamming = x;
        return hamming;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        if (manhattan != -1) {
            return manhattan;
        }
        int x = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int tmp = tiles[i][j];
                if (tmp == 0)
                    continue;
                int tmi = (tmp - 1) / size;
                int tmj = (tmp - 1) % size;
                x += Math.abs(tmi - i) + Math.abs(tmj - j);
            }
        }
        manhattan = x;
        return manhattan;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return hamming() == 0;
    }


    // does this board equal y?
    public boolean equals(Object y) {
        Board yy = (Board) y;
        if (yy.size != size) {
            return false;
        }
        else {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (tiles[i][j] != yy.tiles[i][j]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        ArrayList<Board> ret = new ArrayList<Board>();
        int x = 0;
        int y = 0;
        Boolean found = false;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (tiles[i][j] == 0) {
                    y = i;
                    x = j;
                    break;
                }
            }
            if (found) {
                break;
            }
        }
        //up
        if (y > 0) {
            Board b = new Board(this.tiles);
            b.tiles[y - 1][x] = 0;
            b.tiles[y][x] = tiles[y - 1][x];
            ret.add(b);
        }
        if (y < size - 1) {
            Board b = new Board(this.tiles);
            b.tiles[y + 1][x] = 0;
            b.tiles[y][x] = tiles[y + 1][x];
            ret.add(b);
        }
        if (x > 0) {
            Board b = new Board(this.tiles);
            b.tiles[y][x - 1] = 0;
            b.tiles[y][x] = tiles[y][x - 1];
            ret.add(b);
        }
        if (x < size - 1) {
            Board b = new Board(this.tiles);
            b.tiles[y][x + 1] = 0;
            b.tiles[y][x] = tiles[y][x + 1];
            ret.add(b);
        }
        return ret;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        int x1 = 0;
        int y1 = 0;
        int x2 = 0;
        int y2 = 0;
        int found = 0;
        Board b = new Board(tiles);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (tiles[i][j] != 0) {
                    if (found >= 2) {
                        break;
                    }
                    if (found == 0) {
                        x1 = j;
                        y1 = i;
                        found++;
                    }
                    else {
                        x2 = j;
                        y2 = i;
                        found++;
                    }
                }

            }

        }
        b.tiles[y1][x1] = tiles[y2][x2];
        b.tiles[y2][x2] = tiles[y1][x1];
        return b;
    }

    // unit testing (not graded)
    public static void main(String[] args) {
        for (String filename : args) {

            // read in the board specified in the filename
            In in = new In(filename);
            int n = in.readInt();
            int[][] tiles = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    tiles[i][j] = in.readInt();
                }
            }

            // solve the slider puzzle
            Board initial = new Board(tiles);
            //Solver solver = new Solver(initial);
            StdOut.println(initial.toString());
            StdOut.println(initial.dimension());
            StdOut.println(initial.hamming());
            StdOut.println(initial.manhattan());
            StdOut.println(initial.isGoal());
            StdOut.println(initial.twin().toString());
            //StdOut.println(filename + ": " + solver.moves());
        }
    }
}
