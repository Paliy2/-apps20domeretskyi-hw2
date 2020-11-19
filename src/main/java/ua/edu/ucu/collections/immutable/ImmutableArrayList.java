package ua.edu.ucu.collections.immutable;

import java.util.Arrays;

public class ImmutableArrayList implements ImmutableList {

    private final Object[] array;

    public ImmutableArrayList() {
        this.array = new Object[0];
    }

    ImmutableArrayList(Object[] elements) {
        this.array = new Object[elements.length];

        for (int i = 0; i < elements.length; i++) {
            if (elements[i] == null) {
                break;
            }
            this.array[i] = elements[i];
        }
    }

    @Override
    public ImmutableList add(Object e) {
        return add(this.size(), e);
    }

    @Override
    public ImmutableList add(int index, Object e) {
        return this.addAll(index, new Object[]{e});
    }

    @Override
    public ImmutableList addAll(Object[] c) {
        return addAll(this.size(), c);
    }

    @Override
    public ImmutableList addAll(int index, Object[] c) {
        checkIndexBounds(index);
        Object[] newArray = new Object[this.size() + c.length];

        System.arraycopy(this.array, 0, newArray, 0, index);
        System.arraycopy(c, 0, newArray, index, c.length);
        System.arraycopy(this.array, index, newArray, index + c.length,
                this.size() - index);
        System.out.println("My size: " + size());
        return new ImmutableArrayList(newArray);
    }

    @Override
    public Object get(int index) {
        checkIndexBounds(index);
        return this.array[index];
    }

    @Override
    public ImmutableList remove(int index) {
        checkIndexBounds(index);
        Object[] newArray = new Object[this.size() - 1];
        System.arraycopy(this.array, 0, newArray, 0, index);
        System.arraycopy(this.array, index + 1, newArray, index, this.size() - 1 - index);
        return new ImmutableArrayList(newArray);
    }

    @Override
    public ImmutableList set(int index, Object e) {
        checkIndexBounds(index);

        Object[] newArray = Arrays.copyOf(this.array, this.size());
        newArray[index] = e;
        return new ImmutableArrayList(newArray);
    }

    @Override
    public int indexOf(Object e) {
        for (int i = 0; i < this.size(); i++) {
            if (array[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return this.array.length;
    }

    @Override
    public ImmutableList clear() {
        return new ImmutableArrayList();
    }

    @Override
    public boolean isEmpty() {
        return this.size() < 1;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOfRange(array, 0, this.size());
    }

    private void checkIndexBounds(int index) {
        if (index > this.size() || index < 0) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(toArray());
    }
}

