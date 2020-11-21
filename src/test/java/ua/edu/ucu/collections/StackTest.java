package ua.edu.ucu.collections;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StackTest {
    private Stack stack;

    @Before
    public void setUp() {
        stack = new Stack();
        for (Object i : new Object[]{'0', 1, 'b', "top"}) {
            stack.push(i);
        }
    }

    @Test
    public void testPop() {
        assertEquals(stack.pop(), "top");
    }

    @Test
    public void testPeek() {
        assertEquals(stack.peek(), "top");
        stack.pop();
        assertEquals(stack.peek(), 'b');
    }

    @Test
    public void testPush() {
        stack.push("new");
        assertEquals(stack.peek(), "new");
    }

    @Test(expected = NullPointerException.class)
    public void testPopError() {
        for (int i = 0; i < 5; ++i) {
            stack.pop();
        }
    }

    @Test
    public void test1000PushPop() {
        for (int i = 0; i < 1000; i++) {
            stack.push(i);
        }
        for (int i = 0; i < 1000; i++) {
            stack.pop();
        }
    }
}