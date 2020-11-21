package ua.edu.ucu.collections;

import ua.edu.ucu.collections.immutable.ImmutableLinkedList;

public class Queue {
    private ImmutableLinkedList lst;

    public Queue() {
        this.lst = new ImmutableLinkedList();
    }

    public Object peek() {
        // get first
        return lst.get(0);
    }

    public Object dequeue() {
        // del first
        Object res = lst.get(0);
        lst = lst.remove(0);
        return res;
    }

    public void enqueue(Object e) {
        // add last
        lst = lst.add(e);
    }
}