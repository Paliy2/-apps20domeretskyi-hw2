package ua.edu.ucu.collections;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class QueueTest {
    private Queue queue;

    @Before
    public void setUp() {
        queue = new Queue();
        for (Object i : new Object[]{"top", 'b', 1, '0'}) {
            queue.enqueue(i);
        }
    }

    @Test
    public void testPeek() {
        assertEquals(queue.peek(), "top");
    }

    @Test
    public void testDequeue() {
        assertEquals(queue.dequeue(), "top");
    }

    @Test
    public void testEnqueue() {
        queue.enqueue("new");
        assertNotEquals(queue.peek(), "new");
    }

    @Test(expected = NullPointerException.class)
    public void testDequeError() {
        for (int i = 0; i < 5; ++i) {
            queue.dequeue();
        }
    }

    @Test
    public void test1000EnqueueDeque() {
        for (int i = 0; i < 1000; i++) {
            queue.enqueue(i);
        }
        for (int i = 0; i < 1000; i++) {
            queue.dequeue();
        }
    }
}