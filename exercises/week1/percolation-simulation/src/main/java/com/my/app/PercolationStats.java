package com.my.app;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private final int n;
    private final int T;
    private final double[] percolationThresholdArray;
    private double mean;
    private double stdDev;
    private double confidenceLo;
    private double confidenceHi;

    public PercolationStats(int n, int T){
        if(n <= 0 || T <= 0){
            throw new IllegalArgumentException();
        }
        this.n = n;
        this.T = T;
        this.percolationThresholdArray = new double[T];
        this.performExperiments();
    }

    public double mean(){
        return this.mean;
    }

    public double stddev(){
        return this.stdDev;
    }

    public double confidenceLo(){
        return this.confidenceLo;
    }

    public double confidenceHi(){
        return this.confidenceHi;
    }

    private void performExperiments(){
        for(int i = 0; i < T; ++i){
            Percolation percolation = new Percolation(n);
            while (!percolation.percolates()){
                int row = StdRandom.uniform(n)+1;
                int col = StdRandom.uniform(n)+1;
                percolation.open(row,col);
            }
            double threshold = ((double) percolation.numberOfOpenSites()/(n*n));
            this.percolationThresholdArray[i] = threshold;
        }

        this.mean = StdStats.mean(percolationThresholdArray);
        this.stdDev = StdStats.stddev(percolationThresholdArray);
        double sqrtOfT = Math.sqrt(this.T);
        this.confidenceLo = (mean - ((1.96* stdDev)/sqrtOfT));
        this.confidenceHi = (mean + ((1.96* stdDev)/sqrtOfT));
    }

    public static void main(String[] args){
        int n = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        PercolationStats percolationStats = new PercolationStats(n,T);
        StdOut.printf("mean                    = %f\n",percolationStats.mean());
        StdOut.printf("stddev                  = %f\n",percolationStats.stddev());
        StdOut.printf("95%% confidence interval = [%f, %f]\n",percolationStats.confidenceLo(),percolationStats.confidenceHi());
    }
}
