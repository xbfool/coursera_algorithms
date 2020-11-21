/******************************************************************************
 *  Compilation:  javac PercolationStats.java
 *  Execution:    java PercolationStats n
 *  Dependencies: Percolation.java
 *  This program takes the grid size n as a command-line argument.
 *  Then, the user repeatedly clicks sites to open with the mouse.
 *  After each site is opened, it draws full sites in light blue,
 *  open sites (that aren't full) in white, and blocked sites in black.
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private static final double CONFIDENCE_95 = 1.96;
    private final double[] trailResults;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }
        trailResults = new double[trials];
        for (int i = 0; i < trials; i++) {
            Integer[] intArray = new Integer[n * n];

            for (int j = 0; j < n * n; j++) {
                intArray[j] = j;
            }
            StdRandom.shuffle(intArray);
            Percolation p = new Percolation(n);
            for (int k = 0; k < n * n; k++) {
                int col = intArray[k] % n + 1;
                int row = intArray[k] / n + 1;
                p.open(col, row);
                if (p.percolates()) {
                    trailResults[i] = (double) (k + 1) / ((double) n * n);
                    break;
                }
            }
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(trailResults);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(trailResults);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        double m = mean();
        double s = stddev();
        double r = m - CONFIDENCE_95 * s / trailResults.length;
        return r;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        double m = mean();
        double s = stddev();
        double r = m + CONFIDENCE_95 * s / trailResults.length;
        return r;
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trails = Integer.parseInt(args[1]);

        PercolationStats s = new PercolationStats(n, trails);
        System.out.println("mean                    = " + s.mean());
        System.out.println("stddev                  = " + s.stddev());
        System.out
                .println("95% confidence interval = [" + s.confidenceLo() + ", " + s.confidenceHi()
                                 + "]");


    }


}
