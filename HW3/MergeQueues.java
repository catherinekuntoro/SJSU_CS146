import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class MergeQueues {
    // Return true if v is less than w and false otherwise.
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }


    //since merge static, this class also static
    private static class ArrayInputs {
        private Comparable object;
        private int arrayThisObjectBelongsTo;

        private ArrayInputs(Comparable object, Integer arrayThisObjectBelongsTo) {
            this.object = object;
            this.arrayThisObjectBelongsTo = arrayThisObjectBelongsTo;
        }

        private Comparable getObject() {
            return object;
        }

        private int getArrayThisObjectBelongsTo() {
            return arrayThisObjectBelongsTo;
        }
    }

    // Merge and return the two sorted queues as a single sorted queue.
    private static Queue<Comparable> merge(Queue<Comparable> q1,
                                           Queue<Comparable> q2) {

        MinPQ<ArrayInputs> minPQ = new MinPQ<>(2, new Comparator<ArrayInputs>() {
            @Override
            public int compare(ArrayInputs o1, ArrayInputs o2) {
                return o1.getObject().compareTo(o2.getObject());
            }
        });

        ArrayList<Iterator<Comparable>> listOfIterators = new ArrayList<>(2); //size of two bc only 2 queues
        listOfIterators.add(q1.iterator());
        listOfIterators.add(q2.iterator());

        for (int i = 0; i < listOfIterators.size(); i++) {
            if (listOfIterators.get(i).hasNext()) {
                minPQ.insert(new ArrayInputs(listOfIterators.get(i).next(), i));
            }

        }
        Queue<Comparable> result = new Queue<>();
        while (!minPQ.isEmpty()) {
            ArrayInputs smallestObjectInformation = minPQ.delMin();
            result.enqueue(smallestObjectInformation.getObject());

            int arrayOfSmallestObj = smallestObjectInformation.getArrayThisObjectBelongsTo();

            if (listOfIterators.get(arrayOfSmallestObj).hasNext()) {
                minPQ.insert(new ArrayInputs(listOfIterators.get(arrayOfSmallestObj).next(), arrayOfSmallestObj));
            }
        }
        return result;

    }

    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
//        String[] a = {"A", "B", "C", "D", "E", "F", "G", "H", "I",
//                "J", "K", "L", "M", "N", "O", "P", "Q", "R",
//                "S", "T", "U", "V", "W", "X", "Y", "Z"};
        String[] b = {"B", "B", "C", "D"};
        String[] c = {"A", "B", "C"};
        Queue<Comparable> q1 = new Queue<Comparable>();
        Queue<Comparable> q2 = new Queue<Comparable>();

//        for (String s : a) {
//            if (StdRandom.bernoulli(0.5)) {
//                q1.enqueue(s);
//            } else {
//                q2.enqueue(s);
//            }
//        }
        for (String s : b) {
            q1.enqueue(s);
        }
        for (String s : c) {
            q2.enqueue(s);
        }
        int s1 = q1.size(), s2 = q2.size();
        StdOut.println(merge(q1, q2));
        //assert q1.size() == s1 && q2.size() == s2;
    }
}
