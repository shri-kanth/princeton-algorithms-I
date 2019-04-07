package com.my.app;

import edu.princeton.cs.algs4.StdIn;

public class Permutation {

    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();
        while(StdIn.hasNextChar()){
            randomizedQueue.enqueue(StdIn.readString());
        }
        int count = 0;
        for(String s : randomizedQueue){
            if(count == k){
                break;
            }
            count++;
            System.out.println(s);
        }
    }
}
