package com.my.app;

import org.junit.Test;

import static org.junit.Assert.*;


public class RandomizedQueueTest
{

    @Test
    public void basicQueue(){
        RandomizedQueue<Integer> randomQueue = new RandomizedQueue<>();
        randomQueue.enqueue(1);
        randomQueue.enqueue(2);
        randomQueue.enqueue(3);
        assertEquals(randomQueue.size(),3);
        System.out.println(randomQueue.dequeue());
        System.out.println(randomQueue.dequeue());
        System.out.println(randomQueue.dequeue());
        System.out.println();
        assertEquals(randomQueue.size(),0);
        assertTrue(randomQueue.isEmpty());
    }

    @Test
    public void basicIterator(){
        RandomizedQueue<Integer> randomQueue = new RandomizedQueue<>();
        randomQueue.enqueue(1);
        randomQueue.enqueue(8);
        randomQueue.enqueue(3);
        randomQueue.enqueue(6);
        randomQueue.enqueue(123);
        for(Integer i : randomQueue){
            System.out.println(i);
        }
        assertFalse(randomQueue.isEmpty());
        assertEquals(randomQueue.size(),5);
    }
}
