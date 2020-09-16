import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The {@code ResizingArrayRandomQueue} class represents a queue of generic items in random
 * order taht utilizes a resizing array. Supports .enqueue and .dequeue,
 * operations and methods for retrieving a random sample from the queue.
 */
public class ResizingArrayRandomQueue<Item> implements Iterable<Item> {
    private Item[] q;   //Queue elements
    private int N;      //Number of elements on queue


    /**
     * Construct an empty queue.
     */
    public ResizingArrayRandomQueue() {
        q = (Item[]) new Object[2];
        N = 0;
    }

    /**
     * Is the queue empty?
     *
     * @return true if this que is empty; false otherwise
     */
    public boolean isEmpty() {
        return (N == 0);
    }


    /**
     * The number of items on the queue.
     *
     * @return the number of items in the queue
     */
    public int size() {
        return N;
    }


    /**
     * Add item to the queue.
     *
     * @param item the item to add
     */
    public void enqueue(Item item) {
        if (item == null)
            throw new NullPointerException("Invalid input. Attempting to add \"null\" item");

        //Doubles size of array if necessary
        if (N == q.length)
            resize(N * 2);
        q[N] = item;
        N++;
    }

    /**
     * Remove and return a random item from the queue.
     *
     * @return a random item from this queue
     * @throws java.util.NoSuchElementException if queue is empty
     */
    public Item dequeue() {
        if (isEmpty())
            throw new NoSuchElementException("Queue is empty");

        //Selects random element from array and removes it
        int index = StdRandom.uniform(N);   //index of random queue element
        Item item = q[index];
        q[index] = q[N - 1]; //Moves last item in queue to the index of the removed item
        q[N - 1] = null;

        //Cuts array size in half if N is 1/4 the size of the array
        if (N > 0 && N == q.length / 4)
            resize(q.length / 2);
        N--;
        return item;

    }

    /**
     * Return a random item from the queue, but do not remove it.
     *
     * @return item returns a random item from queue
     * @throws java.util.NoSuchElementException if queue is empty
     */
    public Item sample() {
        if (isEmpty())
            throw new NoSuchElementException("Queue is empty");

        //Selects random element from array
        int index = StdRandom.uniform(N);   //index of random queue element
        return q[index];
    }

    /**
     * An independent iterator over items in the queue in random order.
     *
     * @return An independent iterator that iterates over items in the queue in random order.
     */
    public Iterator<Item> iterator() {
        return new RandomQueueIterator();
    }

    // An iterator, doesn't implement remove() since it's optional.
    private class RandomQueueIterator implements Iterator<Item> {
        Item[] randQ;   //Queue elements in random order
        int current;    //Index of the current item in the queue

        //Initializes a RandomQueueIterator
        RandomQueueIterator() {

            //Copies values from this queue into the iterator
            randQ = (Item[]) new Object[N];
            for (int i = 0; i < N; i++) {
                randQ[i] = q[i];
            }

            //Randomizes elements
            StdRandom.shuffle(randQ);
            current = 0;
        }

        public boolean hasNext() {
            return current < N;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException("No more items to return");
            Item currentItem = randQ[current];
            current++;
            return currentItem;
        }
    }

    // A string representation of the queue.
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item + " ");
        }
        return s.toString().substring(0, s.length() - 1);
    }

    // Helper method for resizing the underlying array.
    private void resize(int max) {
        Item[] temp = (Item[]) new Object[max];

        for (int i = 0; i < N; i++) {
            if (q[i] != null) {
                temp[i] = q[i];
            }
        }
        q = temp;
    }

    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        ResizingArrayRandomQueue<Integer> q = new ResizingArrayRandomQueue<Integer>();
        while (!StdIn.isEmpty()) {
            q.enqueue(StdIn.readInt());
        }
        int sum1 = 0;
        for (int x : q) {
            sum1 += x;
        }
        int sum2 = sum1;
        for (int x : q) {
            sum2 -= x;
        }
        int sum3 = 0;
        while (q.size() > 0) {
            sum3 += q.dequeue();
        }
        StdOut.println(sum1);
        StdOut.println(sum2);
        StdOut.println(sum3);
        StdOut.println(q.isEmpty());
    }
}
