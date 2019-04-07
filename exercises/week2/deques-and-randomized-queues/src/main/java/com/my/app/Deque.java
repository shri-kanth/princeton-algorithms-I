package com.my.app;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    private Node first;
    private Node last;
    private int size;

    private class DequeIterator implements Iterator<Item>{

        private Node current = first;

        public boolean hasNext(){
            return current != null;
        }

        public void remove(){
            throw new UnsupportedOperationException();
        }

        public Item next(){
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public Deque(){
    }

    public boolean isEmpty(){
        return this.first == null;
    }

    public int size(){
        return this.size;
    }

    public void addFirst(Item item){
        if(item == null){
            throw new IllegalArgumentException();
        }
        Node node = new Node();
        node.item = item;
        Node lastFirst = this.first;
        this.first = node;
        this.first.next = lastFirst;
        if(lastFirst == null){
            this.last = this.first;
        }else{
            lastFirst.prev = this.first;
        }
        this.size++;
    }

    public void addLast(Item item) {
        if(item == null){
            throw new IllegalArgumentException();
        }
        Node node = new Node();
        node.item = item;
        Node lastLast = this.last;
        this.last = node;
        this.last.prev = lastLast;
        if(lastLast == null){
            this.first = this.last;
        }else{
            lastLast.next = this.last;
        }
        this.size++;
    }

    public Item removeFirst(){
        if(this.isEmpty()){
            throw new NoSuchElementException();
        }
        Node presentFirst = this.first;
        Node newFirst = this.first.next;
        this.first = newFirst;
        if(this.first == null){
            this.last = null;
        }else{
            this.first.prev = null;
        }
        size--;
        presentFirst.next = null;
        return presentFirst.item;
    }

    public Item removeLast(){
        if(this.isEmpty()){
            throw new NoSuchElementException();
        }
        Node presentLast = this.last;
        Node newLast = this.last.prev;
        this.last = newLast;
        if(this.last == null){
            this.first = null;
        }else{
            this.last.next = null;
        }
        size--;
        presentLast.prev = null;
        return presentLast.item;

    }

    public Iterator<Item> iterator(){
      return new DequeIterator();
    }

    public static void main(String[] args) {}

}
