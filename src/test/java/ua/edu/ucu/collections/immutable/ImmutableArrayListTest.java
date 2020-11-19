package ua.edu.ucu.collections.immutable;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ImmutableArrayListTest {
    private ImmutableArrayList emptyArray;
    private ImmutableArrayList smallArray;
    private ImmutableArrayList largeArray;

    @Before
    public void setUp() throws Exception {
        smallArray = new ImmutableArrayList(new Object[]{1, 2, 3});
        largeArray = new ImmutableArrayList(new Object[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, "...", 100});
        emptyArray = new ImmutableArrayList();

    }

    @Test
    public void testToString() {
        assertEquals(smallArray.toString(), "[1, 2, 3]");
    }

    @Test
    public void testAddWithExisting() {
        ImmutableArrayList arrayList = new ImmutableArrayList(new Object[]{"My", "name", 5});
        ImmutableList list = arrayList.add('c');
        assertEquals(list.toString(), "[My, name, 5, c]");
    }

    @Test
    public void testAddToEmpty() {
        assertEquals(emptyArray.add("Hello!").toString(), "[Hello!]");
    }

    @Test
    public void testAddAtIndex() {
        ImmutableList lst;
        lst = largeArray.add(0, 0);
        assertEquals(lst.toString(), "[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, ..., 100]");
        lst = largeArray.add(7, 12);
        assertEquals(lst.toString(), "[1, 2, 3, 4, 5, 6, 7, 12, 8, 9, 10, ..., 100]");
        lst = largeArray.add(12, 14f);
        assertEquals(lst.toString(), "[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, ..., 100, 14.0]");
    }

    @Test
    public void testAddAtIndexEmpty() {
        assertEquals(emptyArray.add(1).size(), 1);
        assertEquals(emptyArray.add(1).toString(), "[1]");
        assertEquals(emptyArray.add(0, 1).toString(), "[1]");
    }

    @Test
    public void testAddAllEmpty() {
        Object[] arrToAdd = new Object[]{1, 4, 7};
        ImmutableList result = emptyArray.addAll(arrToAdd);
        assertEquals(result.toString(), "[1, 4, 7]");
    }

    @Test
    public void testAddAllToSmallArray() {
        Object[] arrToAdd = new Object[]{1, 4, 7};
        ImmutableList result = smallArray.addAll(arrToAdd);
        assertEquals(result.toString(), "[1, 2, 3, 1, 4, 7]");
    }

    @Test
    public void testAddAllByIndex() {
        Object[] arrToAdd = new Object[]{1, 4, 7};
        ImmutableList result = smallArray.addAll(2, arrToAdd);
        assertEquals(result.toString(), "[1, 2, 1, 4, 7, 3]");
    }

    @Test
    public void testSizeAfterAdding() {
        ImmutableList resArr = new ImmutableArrayList();
        assertEquals(resArr.add(1).size(), 1);
        assertEquals(resArr.add(new Object[]{1, 2, 3}).size(), 1);
        resArr = new ImmutableArrayList(new Object[]{1, 2, 3});
        resArr = resArr.add(6);
        assertEquals(resArr.size(), 4);
        assertEquals(largeArray.add(1).size(), 13);
    }

    @Test
    public void testIsEmptyTrue() {
        assertTrue(emptyArray.isEmpty());
    }

    @Test
    public void testIsEmptyFalse() {
        assertFalse(smallArray.isEmpty());
        assertFalse(largeArray.isEmpty());
    }

    @Test
    public void testInit() {
        ImmutableList emptyArr = new ImmutableArrayList();
        Object[] expected = new Object[]{};
        assertArrayEquals(expected, emptyArr.toArray());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddAtIndexError() {
        emptyArray.add(1, 3);
        largeArray.add(12, 3);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddAllIndexError() {
        emptyArray.addAll(-1, new Object[]{1, 2});
        emptyArray.addAll(1, new Object[]{1, 2, 3});
        emptyArray.addAll(10, new Object[]{1, 2, 4});
        emptyArray.addAll(100, new Object[]{1, 2, 3, 4, 5});
        largeArray.addAll(100, new Object[]{1});
        largeArray.addAll(-100, new Object[]{1});
        largeArray.addAll(12, new Object[]{1});
        smallArray.addAll(-10, new Object[]{"asd", "asd"});
        smallArray.addAll(10, new Object[]{"asd", "asd"});
    }

    @Test
    public void testGet() {
        assertEquals(largeArray.get(0), 1);
        assertEquals(largeArray.get(11), 100);
        assertEquals(largeArray.get(10), "...");
        assertEquals(smallArray.get(1), 2);
        assertEquals(smallArray.get(2), 3);
        assertEquals(smallArray.get(0), 1);
    }

    @Test
    public void testRemove() {
        ImmutableList newArr = new ImmutableArrayList(new Object[]{1, 2, 3});
        newArr = newArr.remove(2);
        newArr = newArr.remove(1);
        newArr = newArr.remove(0);
        assertEquals(newArr.toString(), "[]");
        assertArrayEquals(newArr.toArray(), new Object[0]);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveError() {
        emptyArray.remove(-1);
        emptyArray.remove(1);
        largeArray.remove(-1);
        largeArray.remove(12);
        smallArray.remove(6);
        smallArray.remove(-10);
    }

    @Test
    public void testSet() {
        ImmutableList newArr = new ImmutableArrayList(new Object[]{1, 2, 3});
        newArr = newArr.set(0, 3);
        newArr = newArr.set(1, 2);
        newArr = newArr.set(2, 1);
        assertEquals(newArr.toString(), "[3, 2, 1]");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSetError() {
        emptyArray.set(-1, 0);
        emptyArray.set(1, 0);
        emptyArray.set(10, 0);
        smallArray.set(7, "asd");
        smallArray.set(-1, "wrong!");
        largeArray.set(-1, "wrong!");
        largeArray.set(-10, "wrong!");
        largeArray.set(13, "wrong!");
    }

    @Test
    public void testIndexOf() {
        assertEquals(largeArray.indexOf(0), -1);
        assertEquals(largeArray.indexOf(1), 0);
        assertEquals(largeArray.indexOf(100), 11);
        assertEquals(smallArray.indexOf(3), 2);
        assertEquals(emptyArray.indexOf(0), -1);
    }

    @Test
    public void testSize() {
        assertEquals(largeArray.size(), 12);
        assertEquals(smallArray.size(), 3);
    }

    @Test
    public void testClear() {
        assertArrayEquals(largeArray.clear().toArray(), new Object[0]);
        assertArrayEquals(smallArray.clear().toArray(), new Object[0]);
    }

    @Test
    public void testToArray() {
        ImmutableList newArr = new ImmutableArrayList();
        newArr = newArr.addAll(new Object[]{1, 3, 2});
        Object[] expected = new Object[]{1, 3, 2};
        assertArrayEquals(expected, newArr.toArray());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testListAddIndexOutOfRange() {
        smallArray.add(10, 5);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testListGetError() {
        largeArray.get(12);
    }

    @Test
    public void testListClearEmpty() {
        ImmutableList result = emptyArray.clear();
        assertArrayEquals(result.toArray(), new Object[0]);
    }

    @Test
    public void testToStringEmpty() {
        assertEquals(emptyArray.toString(), "[]");
    }
}
