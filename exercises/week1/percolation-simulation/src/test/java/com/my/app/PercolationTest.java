package com.my.app;

import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


import static org.junit.Assert.*;


public class PercolationTest
{
    private static final String TEST_INPUT_FILE_PATH = "/percolation/input50.txt";

    @Test
    public void initializationShouldCreateClosedGrid()
    {
        Percolation percolation = new Percolation(10);
        assertFalse( percolation.isOpen(2,2) );
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwExceptionForInvalidArgumentInConstructor(){
        Percolation percolation = new Percolation(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwExceptionForInvalidArgumentInOpen(){
        Percolation percolation = new Percolation(10);
        percolation.open(0,3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwExceptionForInvalidArgumentInIsOpen(){
        Percolation percolation = new Percolation(10);
        percolation.isOpen(5,0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwExceptionForInvalidArgumentInIsFull(){
        Percolation percolation = new Percolation(10);
        percolation.isFull(-1,-3);
    }


    @Test
    public void allTheMethodsShouldWork()
    {
        Percolation percolation = new Percolation(10);

        assertFalse( percolation.isOpen(2,2) );

        assertFalse(percolation.isFull(2, 2));

        assertEquals(percolation.numberOfOpenSites(), 0);

        percolation.open(3, 5);

        assertEquals(percolation.numberOfOpenSites(), 1);

        assertTrue(percolation.isOpen(3, 5));

    }

    @Test
    public void testInputFile() throws Exception{
        Path inputFilePath = Paths.get(this.getClass().getResource(TEST_INPUT_FILE_PATH).toURI());
        int n = Integer.parseInt(Files.lines(inputFilePath).limit(1).findFirst().get());

        Percolation percolation = new Percolation(n);

        Files.lines(inputFilePath).skip(1).forEach(line -> {
            String[] args = line.split(" ");
            int row = Integer.parseInt(args[0]);
            int col = Integer.parseInt(args[1]);
            percolation.open(row,col);
        });

        System.out.println(percolation.percolates());

        print(percolation,n);


    }

    private void print(Percolation percolation, int n){
        System.out.println("\n------------------------------------------------");
        for(int i = 0; i < n; ++i){
            for(int j = 0; j < n; ++j){
                if(percolation.isOpen(i+1,j+1)){
                    if(percolation.isFull(i+1,j+1)){
                        System.out.print(" # ");
                    }else{
                        System.out.print(" - ");
                    }
                }else{
                    System.out.print("   ");
                }
            }
            System.out.println();
        }
        System.out.println("\n------------------------------------------------");
    }



}
