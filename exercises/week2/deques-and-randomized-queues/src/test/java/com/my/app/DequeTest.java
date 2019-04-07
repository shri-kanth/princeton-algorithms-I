package com.my.app;

import org.junit.Test;

import static org.junit.Assert.*;


public class DequeTest
{

    @Test
    public void basicStack(){
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
        assertEquals(deque.size(),3);
        assertEquals(deque.removeFirst(),Integer.valueOf(3));
        assertEquals(deque.removeFirst(),Integer.valueOf(2));
        assertEquals(deque.removeFirst(),Integer.valueOf(1));
        assertEquals(deque.size(),0);
        assertTrue(deque.isEmpty());
    }

    @Test
    public void basicQueue(){
        Deque<Integer> deque = new Deque<>();
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        assertEquals(deque.size(),3);
        assertEquals(deque.removeFirst(),Integer.valueOf(1));
        assertEquals(deque.removeFirst(),Integer.valueOf(2));
        assertEquals(deque.removeFirst(),Integer.valueOf(3));
        assertEquals(deque.size(),0);
        assertTrue(deque.isEmpty());
    }

    @Test
    public void basicDeque(){
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addLast(3);
        deque.addLast(4);
        assertEquals(deque.size(),4);
        assertEquals(deque.removeLast(),Integer.valueOf(4));
        assertEquals(deque.removeFirst(),Integer.valueOf(2));
        assertEquals(deque.removeLast(),Integer.valueOf(3));
        assertEquals(deque.removeLast(),Integer.valueOf(1));
        assertEquals(deque.size(),0);
        assertTrue(deque.isEmpty());
    }

    @Test
    public void basicIterator(){
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addLast(3);
        deque.addLast(4);
        for(Integer i : deque){
            System.out.println(i);
        }
        assertFalse(deque.isEmpty());
        assertEquals(deque.size(),4);
    }
}
