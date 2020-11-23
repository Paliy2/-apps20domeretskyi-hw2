package ua.edu.ucu.collections;

import ua.edu.ucu.collections.immutable.ImmutableLinkedList;

public class Stack {
    private ImmutableLinkedList list;

    public Stack() {
        list = new ImmutableLinkedList();
    }

    Object peek() {
        return list.getFirst();
    }

    Object pop() {
        Object resultElement = list.getFirst();
        list = list.removeFirst();
        return resultElement;
    }

    void push(Object e) {
        list = list.addFirst(e);
    }


}