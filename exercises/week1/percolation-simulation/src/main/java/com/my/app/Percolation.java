package com.my.app;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation
{
    private final int n;
    private final Boolean[][] grid;
    private int numberOfOpenSites;
    private final WeightedQuickUnionUF connectionManager;
    private final int TOP_NODE;
    private final int BOTTOM_NODE;

    public Percolation(int n){
        if(n <= 0){
            throw new IllegalArgumentException();
        }
        this.n = n;
        this.grid = new Boolean[n][n];
        this.numberOfOpenSites = 0;

        this.connectionManager = new WeightedQuickUnionUF((n*n)+2);
        this.TOP_NODE = 0;
        this.BOTTOM_NODE = (n*n)+1;
    }

    public void open(int row, int col){
        validateRowAndCol(row,col);
        if(this.grid[row-1][col-1] == null){
            this.grid[row-1][col-1] = Boolean.FALSE;
            this.numberOfOpenSites++;
            connectWithNeighbours(row, col);
        }
    }

    private void connectWithNeighbours(int row, int col){

        // Top Layer auto connect to Top Node
        if(row == 1){
            this.connectionManager.union(TOP_NODE,getIndex(row,col));
        }

        // Bottom Layer auto connect to Bottom Node
        if(row == n){
            this.connectionManager.union(BOTTOM_NODE,getIndex(row, col));
        }

        int[] neighbours = getNeighbours(row, col);

        for(int neighbour : neighbours){
            if(neighbour > -1){
                this.connectionManager.union(getIndex(row, col),neighbour);
            }
        }

        updateIsFullRecursively(row, col);

    }

    public boolean isOpen(int row, int col){
        validateRowAndCol(row,col);
        return this.grid[row-1][col-1] != null;
    }

    public boolean isFull(int row, int col){
        validateRowAndCol(row,col);
        return Boolean.TRUE.equals(this.grid[row-1][col-1]);
    }

    private void updateIsFullRecursively(int row,int col){
        if(!Boolean.TRUE.equals(this.grid[row-1][col-1])){
            if(this.connectionManager.connected(TOP_NODE,getIndex(row, col))){
                this.grid[row-1][col-1] = Boolean.TRUE;
                int[] neighbours = getNeighbours(row, col);

                for(int neighbour : neighbours){
                    if(neighbour > -1){
                        int[] position = getRowAndCol(neighbour);
                        updateIsFullRecursively(position[0],position[1]);
                    }
                }
            }
        }
    }

    public int numberOfOpenSites(){
        return this.numberOfOpenSites;
    }


    public boolean percolates(){
        return this.connectionManager.connected(TOP_NODE,BOTTOM_NODE);
    }

    // Return -1 When no valid neighbour present
    private int[] getNeighbours(int row, int col){

        int[] neighbours = {-1, -1, -1, -1};

        if(row-1 > 0 && isOpen(row-1,col)){
            neighbours[0] = getIndex(row-1,col);
        }
        if(row+1 <= n && isOpen(row+1,col)){
            neighbours[1] = getIndex((row+1),col);
        }
        if(col-1 > 0 && isOpen(row,col-1)){
            neighbours[2] = getIndex(row, col-1);
        }
        if(col+1 <= n && isOpen(row,col+1)){
            neighbours[3] = getIndex(row,col+1);
        }

        return neighbours;
    }

    private int getIndex(int row, int col){
        return ((row-1)*n+col);
    }

    private int[] getRowAndCol(int index){
        int row = index%n == 0 ? (index/n) : (index/n)+1;
        int col = index%n == 0 ? n : index%n;
        int[] position = {row,col};
        return position;
    }

    private void validateRowAndCol(int row,int col){
        if(row <= 0 || col <= 0 || row > this.n || col > this.n){
            throw new IllegalArgumentException();
        }
    }

}
