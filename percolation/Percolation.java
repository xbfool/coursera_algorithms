/******************************************************************************
 *  Compilation:  javac Percolation.java
 *  Execution:    java Percolation n
 *  This program takes the grid size n as a command-line argument.
 *  Then, the user repeatedly clicks sites to open with the mouse.
 *  After each site is opened, it draws full sites in light blue,
 *  open sites (that aren't full) in white, and blocked sites in black.
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private SiteState[] data;
    private int numberOfOpen;
    private final int size;
    private final int start;
    private final int end;
    private final WeightedQuickUnionUF uf;

    private enum SiteState {
        eClose,
        eOpen,
    }

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        size = n;
        data = new SiteState[n * n + 2];
        for (int i = 0; i < n * n + 2; i++) {
            data[i] = SiteState.eClose;
        }
        numberOfOpen = 0;
        uf = new WeightedQuickUnionUF(n * n + 2);
        start = n * n;
        end = n * n + 1;
        data[start] = SiteState.eOpen;
        data[end] = SiteState.eOpen;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        int i = index(row, col);
        if (data[i] == SiteState.eClose) {
            data[i] = SiteState.eOpen;
            numberOfOpen++;
            if (row == 1) {
                uf.union(i, start);
            }
            if (row == size) {
                uf.union(i, end);
            }
            if (row > 1 && isOpen(row - 1, col)) {
                uf.union(i, i - size);
            }
            if (row < size && isOpen(row + 1, col)) {
                uf.union(i, i + size);
            }
            if (col > 1 && isOpen(row, col - 1)) {
                uf.union(i, i - 1);
            }
            if (col < size && isOpen(row, col + 1)) {
                uf.union(i, i + 1);
            }

        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        return data[index(row, col)] != SiteState.eClose;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        return uf.find(index(row, col)) == uf.find(start);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return numberOfOpen;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.find(start) == uf.find(end);
    }


    // test client (optional)
    public static void main(String[] args) {
        //empty
    }

    private int index(int row, int col) {
        if (row <= 0 || row > size || col <= 0 || col > size) {
            throw new IllegalArgumentException();
        }
        return (row - 1) * size + col - 1;
    }


}
