package com.my.app;

import edu.princeton.cs.algs4.StdIn;

public class Permutation {

    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();
        int count = 0;
        while(!StdIn.isEmpty()){
            if(count == k && !randomizedQueue.isEmpty()){
                randomizedQueue.dequeue();
                count--;
            }
            count++;
            randomizedQueue.enqueue(StdIn.readString());
        }

        for(String s : randomizedQueue){
            if(count == 0){
                break;
            }
            System.out.println(s);
            count--;
        }
    }
}
