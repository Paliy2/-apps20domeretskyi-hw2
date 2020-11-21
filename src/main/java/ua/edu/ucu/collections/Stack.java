package ua.edu.ucu.collections;

import ua.edu.ucu.collections.immutable.ImmutableLinkedList;

public class Stack {
    private ImmutableLinkedList list;

    public Stack() {
        list = new ImmutableLinkedList();
    }

    Object peek() {
        // get first
        return list.get(0);
    }

    Object pop() {
        Object resultElement = list.get(0);
        // remove first
        list = list.remove(0);
        return resultElement;
    }

    void push(Object e) {
        // add first
        list = list.add(0, e);
    }


}