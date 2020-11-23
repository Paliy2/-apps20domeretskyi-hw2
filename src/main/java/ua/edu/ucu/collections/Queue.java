package ua.edu.ucu.collections;

import ua.edu.ucu.collections.immutable.ImmutableLinkedList;

public class Queue {
    private ImmutableLinkedList lst;

    public Queue() {
        this.lst = new ImmutableLinkedList();
    }

    public Object peek() {
        return lst.getFirst();
    }

    public Object dequeue() {
        Object res = lst.getFirst();
        lst = lst.removeFirst();
        return res;
    }

    public void enqueue(Object e) {
        lst = lst.addLast(e);
    }
}