import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class IndirectSort {
    // Is v < w?
    private static boolean less(Comparable v, Comparable w) {
        return (v.compareTo(w) < 0);
    }

    // Exchange a[i] and a[j] (for indirect sort)
    private static void exch(int[] a, int i, int j) {
        int swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    // Indirectly sort a[] using insertion sort, ie, not by rearranging a[],
    // but by returning an array perm[] such that perm[i] is the index of
    // the ith smallest entry in a[].
    public static int[] indexSort(Comparable[] a) {

        //Created array perm[] to store the indexes
        int[] perm = new int[a.length];

        //Check for validity. If a[] is an empty or null array, throw an Exception
        if (a.length == 0 || a[0] == null) {
            throw new IllegalArgumentException("Entered an empty or null Comparable[] array. Please enter a valid array");
        }

        //Fill the perm[] array with indexes
        for (int i = 0; i < perm.length; i++) {
            perm[i] = i;
        }

        //Iterate through the values in perm[]
        for (int i = 1; i < perm.length; i++) {

            //Value in perm[] (that corresponds to an index in a[]) that is being checked now
            int indexCheckingNow = perm[i];

            //Index before indexCheckingNow in perm
            int beforeIndexCheckingNow = indexCheckingNow - 1;


            //Checking for input validity; whether or not there's a null value
            if ((a[perm[indexCheckingNow]] == null) || (a[perm[beforeIndexCheckingNow]] == null)) {
                throw new NullPointerException("Detected a null value in the inputted array. " +
                        "Please enter an array with valid inputs");
            }

            //While there are valid indexes behind indexCheckingNow, and the values in a[] that corresponds
            //to indexCheckingNow is less than the value in a[] that corresponds to beforeIndexCheckingNow
            while ((beforeIndexCheckingNow >= 0) && less((a[perm[indexCheckingNow]]), (a[perm[beforeIndexCheckingNow]]))) {

                //Exchange the indexes
                exch(perm, indexCheckingNow, beforeIndexCheckingNow);

                //Decrement the indexes
                indexCheckingNow--;
                beforeIndexCheckingNow--;

            }

        }

        //Return the array perm the order of indexes from the smallest to largest value of a[]
        return perm;
    }

    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        int[] perm = indexSort(a);

        int i;
        for (i = 0; i < perm.length - 1; i++) {
            StdOut.print(a[perm[i]] + " ");
        }
        StdOut.println(a[perm[i]]);
    }
}
