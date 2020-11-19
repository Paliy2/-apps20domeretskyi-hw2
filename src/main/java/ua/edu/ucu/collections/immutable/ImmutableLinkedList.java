package ua.edu.ucu.collections.immutable;

public class ImmutableLinkedList implements ImmutableList {
    Node head;
    int size;

    public ImmutableLinkedList() {
        this.head = null;
        this.size = 0;
    }

    private ImmutableLinkedList(Node n) {
        head = n;
    }

    ImmutableLinkedList(Object[] c) {
        if (c.length > 0) {
            this.head = new Node(c[0]);
            Node current = this.head;
            for (int i = 1; i < c.length; i++) {
                current.next = new Node(c[i]);
                current = current.next;
            }
        }
        this.size = c.length;
    }


    private ImmutableLinkedList copy() {

        if (this.head == null) {
            return new ImmutableLinkedList();
        }

        ImmutableLinkedList resList = new ImmutableLinkedList();
        resList.size = size;
        resList.head = new Node(this.head.value);
        Node nodeOriginal = this.head.next;
        Node nodeNew = resList.head;

        while (nodeOriginal != null) {
            nodeNew.next = new Node(nodeOriginal.value);
            nodeNew = nodeNew.next;
            nodeOriginal = nodeOriginal.next;
        }

        return resList;
    }

    public ImmutableLinkedList add(Object e) {
        return add(this.size, e);
    }

    public ImmutableLinkedList add(int index, Object e) {
        checkIndexBounds(index);

        int counter = 0;
        ImmutableLinkedList resList = copy();
        Node nodeTemp = resList.head;
        Node nodeNew = new Node(e);

        // if the original list is empty
        if (nodeTemp == null) {
            resList.head = new Node(e);
        }
        // special case
        else if (index == 0) {
            nodeNew.next = nodeTemp;
            ImmutableLinkedList lst = new ImmutableLinkedList(nodeNew);
            lst.size = this.size + 1;
            return lst;
        }
        // normal case
        else {
            while (nodeTemp != null) {
                // till last index
                if (counter == index - 1) {
                    Node current = nodeTemp.next;
                    nodeTemp.next = nodeNew;
                    nodeTemp.next.next = current;
                }

                nodeTemp = nodeTemp.next;
                counter++;
            }
        }

        resList.size++;
        return resList;
    }

    public ImmutableLinkedList addAll(Object[] c) {
        return addAll(this.size, c);
    }

    public ImmutableLinkedList addAll(int index, Object[] c) {
        ImmutableLinkedList resList = copy();

        for (int i = 0; i < c.length; i++) {
            resList = resList.add(index + i, c[i]);
        }

        return resList;
    }

    public Object get(int index) {
        checkIndexBounds(index);

        ImmutableLinkedList resList = copy();

        Node current = resList.head;
        while (index != 0) {
            current = current.next;
            index--;  //!!
        }
        return current.value;
    }


    public ImmutableLinkedList remove(int index) {
        checkIndexBounds(index);

        ImmutableLinkedList resList = copy();
        Node current = resList.head;
        int counter = 0;

        if (index == 0) {
            ImmutableLinkedList lst = new ImmutableLinkedList(current.next);
            lst.size = this.size - 1;
            return lst;
        }

        while (counter != index - 1) {
            current = current.next;
            counter++;
        }
        // remove
        Node prev = current;
        prev.next = current.next.next;
        resList.size--;
        return resList;
    }

    public ImmutableLinkedList set(int index, Object e) {
        checkIndexBounds(index);

        ImmutableLinkedList resList = copy();
        Node current = resList.head;

        while (index != 0) {
            current = current.next;
            index--;
        }

        current.value = e;
        return resList;
    }

    public int indexOf(Object e) {
        ImmutableLinkedList resList = copy();
        Node current = resList.head;
        int counter = 0;

        // get object with index
        while (current != null && !current.value.equals(e)) {
            current = current.next;
            counter++;
        }

        if (counter == resList.size && resList.get(this.size - 1) != e) {
            return -1;
        }

        return counter;
    }

    public int size() {
        return this.size;
    }

    public ImmutableLinkedList clear() {
        return new ImmutableLinkedList();
    }

    public boolean isEmpty() {
        // in case sth wrong happens
        if (this.size < 0) {
            this.size = 0;
        }
        return this.size == 0;
    }

    public Object[] toArray() {
        Object[] array = new Object[this.size];
        ImmutableLinkedList resList = copy();
        Node current = resList.head;

        int i = 0;
        while (current != null) {
            array[i] = current.value;
            current = current.next;
            i++;
        }

        return array;
    }

    @Override
    public String toString() {
        ImmutableLinkedList resList = copy();
        Node current = resList.head;

        if (current == null) {
            return "[]";
        }

        StringBuilder resText = new StringBuilder();
        resText.append("[");
        while (current != null) {
            resText.append(current.value.toString()).append(", ");
            current = current.next;
        }

        return resText.substring(0, resText.length() - 2) + "]";
    }

    private void checkIndexBounds(int index) {
        if (index > this.size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
    }
}