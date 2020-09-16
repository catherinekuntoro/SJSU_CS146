import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class CertifyHeap {
    // Return true of v is less than w and false otherwise.
    private static boolean less(Comparable v, Comparable w) {
        return (v.compareTo(w) < 0);
    }

    // Return true if a[] represents a maximum-ordered heap and false
    // otherwise. Remember to index from 1.
    private static boolean maxOrderedHeap(Comparable[] a) {
        //Check for input validity, if given an empty array throw an exception
        //Array a of length 1 would be invalid, since heap construction ignores a[0], so if an array consists only of a[0],
        //one cannot construct a heap with that array
        if (a.length == 0 || a.length == 1) {
            throw new IllegalArgumentException("Entered an empty Comparable[] array. Please enter a valid array.");
        }

        //The first node begins at a[1], since usually heaps has a[0] as a placeholder value
        int keyIndex = 1;

        //if there's a child to be checked
        while ((keyIndex * 2) < a.length) {

            //Calculate the index for the node at keyIndex's children
            int firstChildIndex = keyIndex * 2;
            int secondChildIndex = (keyIndex * 2) + 1;

            //There is an other child within the bound
            if ((secondChildIndex) < a.length) {

                //Checking for null input
                if (a[secondChildIndex] == null || a[keyIndex] == null) {
                    throw new NullPointerException("Detected a null input. Please enter a valid input.");
                }

                //If parent's value is lesser than the second child's value, return false
                if (less(a[keyIndex], a[secondChildIndex])) {
                    return false;
                }

            }

            //Checking for null input
            if (a[firstChildIndex] == null || a[keyIndex] == null) {
                throw new NullPointerException("Detected a null input. Please enter a valid input.");
            }

            //If parent's value is lesser than the first child's value, return false
            if (less(a[keyIndex], a[firstChildIndex])) {
                return false;
            }

            //Increment the keyIndex of parent if the parent has valid children
            ++keyIndex;

        }

        //If the loop has successfully been completed, a[] is a valid max-ordered heap
        return true;
    }

    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        StdOut.println(maxOrderedHeap(a));
    }
}
