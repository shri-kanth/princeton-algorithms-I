package com.my.app;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private int n;
    private int T;
    private double[] percolationThresholdArray;

    public PercolationStats(int n, int T){
        if(n <= 0 || T <= 0){
            throw new IllegalArgumentException();
        }
        this.n = n;
        this.T = T;
        this.percolationThresholdArray = new double[T];
    }
    public double mean(){
        return StdStats.mean(percolationThresholdArray);
    }

    public double stddev(){
        return StdStats.stddev(percolationThresholdArray);
    }

    public double confidenceLo(){
        double mean = this.mean();
        double stdDev = this.stddev();
        double sqrtOfT = Math.sqrt(this.T);
        return (mean - ((1.96*stdDev)/sqrtOfT));
    }

    public double confidenceHi(){
        double mean = this.mean();
        double stdDev = this.stddev();
        double sqrtOfT = Math.sqrt(this.T);
        return (mean + ((1.96*stdDev)/sqrtOfT));
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
    }

    public static void main(String[] args){
        int n = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        PercolationStats percolationStats = new PercolationStats(n,T);
        percolationStats.performExperiments();
        StdOut.printf("mean                    = %f\n",percolationStats.mean());
        StdOut.printf("stddev                  = %f\n",percolationStats.stddev());
        StdOut.printf("95%% confidence interval = [%f, %f]\n",percolationStats.confidenceLo(),percolationStats.confidenceHi());
    }
}
