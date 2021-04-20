import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Comparator;

/* *****************************************************************************
 *  Name: xbfool
 *  Date: 2021-04-06
 *  Description: algorithm
 **************************************************************************** */
class BoardNode {
    public BoardNode(Board board, BoardNode parent, int move) {
        this.board = board;
        this.parent = parent;
        this.move = move;
    }

    public Board board;
    public int move;
    public BoardNode parent;
}


class HammingComparator
        implements Comparator<BoardNode> {

    @Override
    public int compare(BoardNode node1,
                       BoardNode node2) {
        if (node1.move + node1.board.hamming() < node2.move + node2.board.hamming()) {
            return -1;
        }
        else if (node1.move + node1.board.hamming() > node2.move + node2.board.hamming()) {
            return 1;
        }
        else {
            return 0;
        }
    }
}

class ManhattanComparator
        implements Comparator<BoardNode> {

    @Override
    public int compare(BoardNode node1,
                       BoardNode node2) {
        if (node1.move + node1.board.manhattan() < node2.move + node2.board.manhattan()) {
            return -1;
        }
        else if (node1.move + node1.board.manhattan() > node2.move + node2.board.manhattan()) {
            return 1;
        }
        else {
            return 0;
        }
    }
}


public class Solver {
    Board initial;
    MinPQ<BoardNode> minpq;
    int moves = -1;
    BoardNode solutionBoard = null;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        this.initial = initial;
        minpq = new MinPQ<BoardNode>();
        minpq.insert(new BoardNode(initial, null, 0));
        while (!minpq.isEmpty()) {
            BoardNode b = minpq.delMin();
            if (b.board.hamming() == 0) {
                solutionBoard = b;
                moves = b.move;
            }
            else {
                for (Board tmp : b.board.neighbors()) {
                    if (b.parent != null && !b.parent.board.equals(tmp))
                        minpq.insert(new BoardNode(tmp, b, b.move + 1));
                }
            }
        }
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return moves() == -1;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        return moves;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        if (solutionBoard == null)
            return null;
        ArrayList<Board> l = new ArrayList<Board>();
        BoardNode b = solutionBoard;
        while (b != null) {
            l.add(0, b.board);
            b = b.parent;
        }
        return l;
    }

    // test client (see below)
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
            Solver solver = new Solver(initial);
            StdOut.println(initial.toString());
            StdOut.println(initial.dimension());
            StdOut.println(initial.hamming());
            StdOut.println(initial.manhattan());
            StdOut.println(initial.isGoal());
            StdOut.println(filename + ": " + solver.moves());
        }
    }


}
