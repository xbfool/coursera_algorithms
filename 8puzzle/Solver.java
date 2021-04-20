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
    private Board initial;
    private MinPQ<BoardNode> minpq;
    private int moves = -1;
    private BoardNode solutionBoard = null;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        this.initial = initial;
        Board c = initial.twin();
        minpq = new MinPQ<BoardNode>(new ManhattanComparator());
        MinPQ<BoardNode> c_minpq = new MinPQ<BoardNode>(new ManhattanComparator());
        BoardNode iNode = new BoardNode(initial, null, 0);
        BoardNode ciNode = new BoardNode(c, null, 0);
        minpq.insert(iNode);
        c_minpq.insert(ciNode);
        Boolean c_has_moves = false;
        //StdOut.println(initial.toString());
        //int i = 0;
        while (!minpq.isEmpty() && !c_has_moves) {
            BoardNode b = minpq.delMin();
            if (b.board.isGoal() && (b.move < moves || moves == -1)) {
                solutionBoard = b;
                moves = b.move;
            }
            else {
                if (b.move > moves && moves > 0)
                    continue;
                for (Board tmp : b.board.neighbors()) {
                    BoardNode inode = new BoardNode(tmp, b, b.move + 1);
                    if (b.parent == null || b.parent.board.equals(tmp))
                        minpq.insert(inode);
                }
            }
            if (moves >= 0 || !c_has_moves) {
                if (c_minpq.isEmpty())
                    continue;

                BoardNode cb = c_minpq.delMin();
                if (cb.board.isGoal())
                    break;
                else {
                    for (Board tmp : cb.board.neighbors()) {
                        BoardNode inode = new BoardNode(tmp, b, b.move + 1);
                        if (cb.parent == null || cb.parent.board.equals(tmp))
                            c_minpq.insert(inode);
                    }
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
            //   StdOut.println(initial.toString());
            //   StdOut.println(initial.dimension());
            //   StdOut.println(initial.hamming());
            //   StdOut.println(initial.manhattan());
            //   StdOut.println(initial.isGoal());
            StdOut.println(filename + ": " + solver.moves());
        }
    }


}
