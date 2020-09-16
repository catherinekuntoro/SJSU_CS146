import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class ArrayST<Key, Value> {
    private static final int INIT_CAPACITY = 2;
    private Key[] keys;
    private Value[] values;
    private int N; //Number of unique key-value pairs in the symbol table

    // Create a symbol table with INIT_CAPACITY.
    public ArrayST() {
        N = 0;
        keys = (Key[]) new Object[INIT_CAPACITY];
        values = (Value[]) new Object[INIT_CAPACITY];
    }

    // Create a symbol table with the given capacity.
    public ArrayST(int capacity) {
        N = 0;
        keys = (Key[]) new Object[capacity];
        values = (Value[]) new Object[capacity];
    }

    // Return the number of current unique key-value pairs in the table.
    public int size() {
        return N;
    }

    // Return true if the table is empty and false otherwise.
    public boolean isEmpty() {
        return size() == 0;
    }

    // Return true if the table contains key and false otherwise.
    public boolean contains(Key key) {

        //Go through every key in the keys array. If there is a matching key, return true
        for (Key checking : keys) {
            if (key.equals(checking)) {
                return true;
            }
        }

        //Otherwise, return false
        return false;
    }

    // Return the value associated with key, or null.
    public Value get(Key key) {

        //Only until N since the resize method might make the keys[] array larger than necessary
        //Therefore N contains a more accurate representation of how many unique key-value pairs there are
        for (int i = 0; i < N; i++) {
            if (key.equals(keys[i]) && (keys[i] != null)) {

                return values[i];
            }
        }
        return null;
    }

    // Put the kev-value pair into the table; remove key from table if value is null.
    public void put(Key key, Value value) {

        //If value is null, delete the key
        if (value == null) {
            delete(key);
        }

        //Check for duplicates. If there is a duplicate, delete the key first then add it later
        if (contains(key)) {
            delete(key);
        }


        //Reach this point, that means key a new, unique key. Double the size of values[] and keys[] if necessary
        //if the number of key-value pair is exactly or more than the size of the keys[] array
        if (N >= keys.length) {
            resize(2 * N);
        }

        //Add the new key and value at N index in the arrays
        keys[N] = key;
        values[N] = value;

        //Increment N number of key-value pairs
        N++;
    }


    // Remove key (and its value) from table.
    public void delete(Key key) {

        //Cannot delete anything from an empty symbol table
        if (isEmpty()) {
            return;
        }

        //Only until N since the resize method might make the keys[] array larger than necessary
        //Therefore N contains a more accurate representation of how many unique key-value pairs there are

        for (int i = 0; i < N; i++) {

            //To avoid adding a null key
            if (keys[i] != null) {

                //If it is trying to delete a key at the end of the array, set key and value to null
                if (keys[i].equals(key) && (i == N - 1)) {

                    keys[i] = null;

                    values[i] = null;

                    //Decrement N
                    N--;

                    //If the number of unique key-value pairs are a fourth of the length of the keys[] after deletion, then make the size
                    //of the keys[] and values[] arrays smaller
                    if ((N > 0) && (N == (keys.length / 4))) {
                        resize(keys.length / 2);
                    }

                } else if (keys[i].equals(key)) {
                    //Set the value of the next key to the current key
                    keys[i] = keys[i + 1];

                    //Set the value of the next value to the current value
                    values[i] = values[i + 1];


                    //Since key[i+1]'s key has been moved to key[i], set key[i+1] to null
                    keys[i + 1] = null;

                    //Since values[i+1]'s key has been moved to values[i], set values[i+1] to null
                    values[i + 1] = null;


                    //Reduce the size of the unique key-value pairs
                    N--;

                    //If the number of unique key-value pairs are a fourth of the length of the keys[] after deletion, then make the size
                    //of the keys[] and values[] arrays smaller
                    if ((N > 0) && (N == (keys.length / 4))) {
                        resize(keys.length / 2);
                    }

                    //Exit loop once an item has been deleted
                    break;
                }
            }

        }
    }

    // Return all the keys in the table.
    public Iterable<Key> keys() {
        //Since there is an import of the queue class, then one can use the Queue class to iterate the keys
        Queue<Key> keyQueue = new Queue<>();
        for (Key current : keys) {

            //To avoid enqueuing a null key or a key with an empty value
            if (current != null && get(current) != null) {
                keyQueue.enqueue(current);
            }
        }
        return keyQueue;
    }

    // Resize the internal arrays to capacity.
    private void resize(int capacity) {
        Key[] tempk = (Key[]) new Object[capacity];
        Value[] tempv = (Value[]) new Object[capacity];
        for (int i = 0; i < N; i++) {

            //Only add to the temporary array if the key at index i is not null
            if (keys[i] != null) {
                tempk[i] = keys[i];
                tempv[i] = values[i];
            }

        }
        values = tempv;
        keys = tempk;
    }

    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        ArrayST<String, Integer> st = new ArrayST<String, Integer>();
        int count = 0;
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            st.put(s, ++count);
        }


        for (String s : args) {

            st.delete(s);
        }

        for (String s : st.keys()) {
            StdOut.println(s + " " + st.get(s));
        }
    }
}
