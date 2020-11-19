package ua.edu.ucu.collections.immutable;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ImmutableLinkedListTest {
    private ImmutableLinkedList stringList;
    private ImmutableLinkedList emptyList;

    @Before
    public void setUp() {
        stringList = new ImmutableLinkedList(new Object[]{"Never", "stop", "learning"});
        emptyList = new ImmutableLinkedList();
    }


    @Test
    public void testToString() {
        assertEquals(stringList.toString(), "[Never, stop, learning]");
    }

    @Test
    public void testAdd() {
        ImmutableLinkedList newList = stringList.add("Because");
        assertEquals(newList.toString(), "[Never, stop, learning, Because]");
    }

    @Test
    public void testAddEmpty() {
        ImmutableLinkedList newList = emptyList.add(1);
        newList = newList.add('a');
        newList = newList.add("hello");
        assertEquals(newList.toString(), "[1, a, hello]");
    }

    @Test
    public void testAddZeroIndex() {
        ImmutableLinkedList newList = emptyList.add(0, "Here");
        assertEquals(newList.toString(), "[Here]");
    }

    @Test
    public void testAddAt() {
        ImmutableLinkedList newList = stringList.add(3, '!');
        System.out.println(newList);
        assertEquals(newList.toString(), "[Never, stop, learning, !]");
    }

    @Test
    public void testAddAtEmpty() {
        ImmutableLinkedList newList = emptyList.add(0, "Lose");
        newList = newList.add(1, "yourself");
        newList = newList.add(2, ":( ");
        assertEquals(newList.toString(), "[Lose, yourself, :( ]");
    }

    @Test
    public void testAddAll() {
        ImmutableLinkedList list = stringList.addAll(new Object[]{"never", "stops", "teaching"});
        assertEquals(list.toString(), "[Never, stop, learning, never, stops, teaching]");
    }

    @Test
    public void testAddAllEmpty() {
        ImmutableLinkedList empty = emptyList.addAll(new Object[]{1, 2, 3});
        assertEquals(empty.toString(), "[1, 2, 3]");
    }

    @Test
    public void testAddAllUsingIndex() {
        ImmutableLinkedList list = stringList.addAll(1, new Object[]{5});
        assertEquals(list.toString(), "[Never, 5, stop, learning]");
    }

    @Test
    public void testAddAllEmptyUsingIndex() {
        ImmutableLinkedList empty = emptyList.addAll(0, new Object[]{3, "Eugene"});
        assertEquals(empty.toString(), "[3, Eugene]");
    }

    @Test
    public void testGet() {
        assertEquals(stringList.get(2), "learning");
    }

    @Test
    public void testRemove() {
        ImmutableLinkedList list = stringList.remove(2);
        assertEquals(list.toString(), "[Never, stop]");
    }

    @Test
    public void testSet() {
        ImmutableLinkedList list = stringList.set(2, "drinking");
        assertEquals(list.toString(), "[Never, stop, drinking]");
    }

    @Test
    public void testIndexOf() {
        int index = stringList.indexOf("Never");
        assertEquals(index, 0);
        index = stringList.indexOf("life");
        assertEquals(index, -1);

    }

    @Test
    public void testSize() {
        int size = stringList.size();
        assertEquals(size, 3);
    }

    @Test
    public void testAddAndSize() {
        ImmutableLinkedList list = stringList.add(1);
        assertEquals(list.size(), 4);
        list = list.addAll(new Object[]{1, 2, 3});
        int size = list.size();
        assertEquals(size, 7);
    }

    @Test
    public void testAddAndRemoveAndSize() {
        ImmutableLinkedList list = stringList.addAll(new Object[]{1, 2, 3});
        list = list.remove(1);
        list = list.remove(2);
        list = list.remove(3);
        assertEquals(list.size(), 3);
    }

    @Test
    public void testClear() {
        ImmutableLinkedList list = stringList.clear();
        int size = list.size();
        assertEquals(size, 0);
        assertEquals(list.toString(), "[]");
    }

    @Test
    public void testIsEmptyFalse() {
        boolean empty = stringList.isEmpty();
        assertFalse(empty);
    }

    @Test
    public void testIsEmptyTrue() {
        boolean empty = emptyList.isEmpty();
        assertTrue(empty);
    }

    @Test
    public void testToArray() {
        Object[] arr = stringList.toArray();
        assertArrayEquals(arr, new Object[]{"Never", "stop", "learning"});
    }

    @Test
    public void testSetSize() {
        emptyList.setSize(0);
        assertEquals(emptyList.size(), 0);
        assertEquals(emptyList.getSize(), 0);
    }

    @Test
    public void testSetHead() {
        emptyList.setHead(null);
        assertNull(emptyList.getHead());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void checkIndexBoundsError() {
        emptyList.get(1);
    }

    @Test
    public void checkIndexBounds() {
        Object a = stringList.get(1);
        assertEquals(a, "stop");
    }

    @Test
    public void testInitWithNode() {
        Node newHead = new Node(10);
        ImmutableLinkedList resList = new ImmutableLinkedList(newHead);
        assertEquals(resList.toString(), "[10]");
        assertEquals(resList.getHead().getValue(), 10);
        assertNull(resList.getHead().getNext());
    }

    @Test
    public void testInitWithNull() {
        Object[] toAdd = new Object[0];
        ImmutableList resList = new ImmutableLinkedList(toAdd);
    }
}

