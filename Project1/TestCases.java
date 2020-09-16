import org.junit.Test;

import static org.junit.Assert.*;

public class TestCases {
    @Test
    //Check size method
    public void test1() {
        LinkedDeque<Integer> q = new LinkedDeque<Integer>();
        q.addFirst(2);
        q.addLast(5);
        assertEquals(2, q.size());
    }

    @Test
    //check isEmpty() method
    public void test2() {
        LinkedDeque<Integer> empty = new LinkedDeque<Integer>();
        assertTrue(empty.isEmpty());
    }

    //Check trying to insert a null object
    @Test(expected = NullPointerException.class)
    public void test3() {
        LinkedDeque<Integer> nullInsert = new LinkedDeque<Integer>();
        nullInsert.addFirst(null);
    }

    @Test
    //return the value of the deleted node at the front
    public void test4() {
        LinkedDeque<Integer> q = new LinkedDeque<Integer>();
        q.addFirst(2);
        q.addFirst(1);
        q.addLast(3);
        q.addLast(4);

        for (int i = 1; i < 5; i++) {
            assertEquals(i, q.removeFirst().intValue());
        }
    }

    @Test
    //testing removeLast method
    public void test5() {
        LinkedDeque<Integer> q = new LinkedDeque<Integer>();
        q.addFirst(2);
        q.addFirst(1);
        q.addLast(3);
        q.addLast(4);

        for (int i = 4; i > 0; i--) {
            assertEquals(i, q.removeLast().intValue());
        }
    }

    @Test
    //Testing what the head, tail, next, and previous of a single node
    public void test6() {
        LinkedDeque<Integer> q = new LinkedDeque<Integer>();
        q.addFirst(2);
        assertEquals(q.getHeadValue().intValue(), 2);
        assertEquals(q.getTailValue().intValue(), 2);
        assertNull(q.getNextValue());
        assertNull(q.getPrevValue());
    }

    @Test
    public void test7() {
        ResizingArrayRandomQueue<Integer> empty = new ResizingArrayRandomQueue<Integer>();
        assertTrue(empty.isEmpty());
    }

}
