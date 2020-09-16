// Josephus.java: Takes N and M from the command line and prints out the order
// in which people are eliminated (and thus would show Josephus where to sit in
// the circle).

import edu.princeton.cs.algs4.Queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Josephus {

    /*
       The method determinePeopleToEliminate returns a N-sized Queue that is filled in order of
       eliminated for every Mth person. Two queues are used, in which the queues will pass the
       integers back and forth until every integer is inside the Queue that contains the order
       of person to be eliminated

       @param       int n               N number of people, starting from 0 until N-1
       @param       int m               Every Mth person in the circle will be deleted
       @return      Queue<Integer>      A queue where its contents are in order of elimination
     */
    public Queue<Integer> determinePeopleToEliminate(int n, int m) {

        //Check for input validity
        if (m > n) {
            System.out.println("Input Mth person to be deleted is greater than the N number of people to be deleted.");
            System.exit(0);
        } else if (m == 0) {
            System.out.println("Invalid M input. Please insert a number M from 1 to N");
            System.exit(0);
        }

        Queue<Integer> firstQueue = new Queue<>();

        //Boolean to keep track whether or not everything in firstQueue has been traversed before traversing the second queue
        boolean firstQueueTurn = true;

        Queue<Integer> secondQueue = new Queue<>();

        Queue<Integer> eliminatedPeople = new Queue<>();

        int counterToDelete = 1;

        //Fill the first queue from integers from 0 until N-1
        for (int i = 0; i < n; i++) {
            firstQueue.enqueue(i);
        }

        //When completed, the eliminatedPeople queue size should be equal to N
        while (eliminatedPeople.size() != n) {

            //If this is the Mth person, delete the person from the queue
            if (firstQueueTurn) {
                if (counterToDelete == m) {

                    //Insert the deleted person into the eliminatedPeople queue
                    eliminatedPeople.enqueue(firstQueue.dequeue());

                    counterToDelete = 1;

                    //If through this action there is no Integers left in the first queue, move on to the second queue
                    if (firstQueue.size() == 0) {
                        firstQueueTurn = false;
                    }

                } else {

                    counterToDelete++;

                    //If this is not the person to be eliminated, give them to the second queue
                    secondQueue.enqueue(firstQueue.dequeue());

                    //If through this action there is no Integers left in the first queue, move on to the second queue
                    if (firstQueue.size() == 0) {
                        firstQueueTurn = false;
                    }
                }
            } else {
                if (counterToDelete == m) {
                    eliminatedPeople.enqueue(secondQueue.dequeue());

                    counterToDelete = 1;

                    //If through this action there is no Integers left in the second queue, move on to the first queue
                    if (secondQueue.size() == 0) {
                        firstQueueTurn = true;
                    }

                } else {
                    counterToDelete++;

                    //If this is not the person to be eliminated, give them to the first queue
                    firstQueue.enqueue(secondQueue.dequeue());

                    //If through this action there is no Integers left in the second queue, move on to the first queue
                    if (secondQueue.size() == 0) {
                        firstQueueTurn = true;
                    }
                }
            }
        }
        return eliminatedPeople;
    }

    /*
        The printPeopleToBeEliminated() method receives a Queue, and then will print the contents of the Queue
        in FIFO (First In First Out) order

        @param      Queue<Integer>toBeEliminated      A Queue that contains the people arranged in order of elimination
     */
    public void printPeopleToBeEliminated(Queue<Integer> toBeEliminated) {
        while (!toBeEliminated.isEmpty()) {
            System.out.print(toBeEliminated.dequeue() + " ");
        }
    }

    public static void main(String[] args) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        //In case of IOExceptions
        try {
            System.out.println("Input format: $ java Josephus [N amount people, ordered 0 to N-1] [Eliminate every Mth person]");

            //Read the input line
            String line = reader.readLine();

            //Split the line into words by splitting the spaces in between
            String[] lineDivided = line.split(" ");
            int amountOfPeople = Integer.parseInt(lineDivided[3]);
            int mthPerson = Integer.parseInt(lineDivided[4]);

            Josephus j = new Josephus();

            Queue<Integer> peopleToBeEliminated = j.determinePeopleToEliminate(amountOfPeople, mthPerson);
            j.printPeopleToBeEliminated(peopleToBeEliminated);

        } catch (IOException e) {
            e.getMessage();
        }
    }
}
