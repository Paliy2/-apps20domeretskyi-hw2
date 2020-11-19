package ua.edu.ucu.collections.immutable;

public class Node {
    Object value;
    Node next;

    public Node(Object e) {
        this.value = e;
        this.next = null;
    }
}
