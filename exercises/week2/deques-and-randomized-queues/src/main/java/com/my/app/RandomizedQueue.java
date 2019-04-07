package com.my.app;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] q;
    private int size;

    private class RandomizedQueueIterator implements Iterator<Item> {

        private int current = 0;

        public boolean hasNext(){
            return current < size;
        }

        public void remove(){
            throw new UnsupportedOperationException();
        }

        public Item next(){
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            if(current < size-1){
                int x = StdRandom.uniform(current,size);
                int y = StdRandom.uniform(current,size);
                Item temp = q[x];
                q[x] = q[y];
                q[y] = temp;
            }
            Item item = q[current];
            current++;
            return item;
        }
    }

    public RandomizedQueue(){
        q = (Item[]) new Object[1];
    }
    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return this.size;
    }

    public void enqueue(Item item){
        if(item == null){
            throw new IllegalArgumentException();
        }
        adjustCapacity();
        q[size] = item;
        size++;
    }

    public Item dequeue()  {
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        randomize();
        Item item = q[size-1];
        q[size-1] = null;
        size--;
        adjustCapacity();
        return item;
    }

    public Item sample() {
        int index = StdRandom.uniform(size);
        return q[index];
    }

    private void randomize(){
        int x = StdRandom.uniform(size);
        int y = StdRandom.uniform(size);
        Item temp = q[x];
        q[x] = q[y];
        q[y] = temp;
    }

    private void adjustCapacity(){
        if(isFull()){
            reSize(q.length*2);
        }else if(isQuater()){
            reSize(q.length/2);
        }
    }

    private boolean isFull(){
        return this.q.length == size;
    }

    private boolean isQuater(){
        return this.q.length == 4*size;
    }

    private void reSize(int capacity){
        Item[] copy = (Item [])new Object[capacity];
        for (int i = 0; i < size; i++){
            copy[i] = q[i];
        }
        q = copy;
    }

    public Iterator<Item> iterator(){
        return new RandomizedQueueIterator();
    }

}
