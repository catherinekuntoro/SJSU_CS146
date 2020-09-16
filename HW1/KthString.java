// KthString.java: Takes a command-line argument k and prints
// the kth string from the end found on standard input,
// assuming that standard input has k or more strings.

import edu.princeton.cs.algs4.Queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class KthString {
    /*
        The method kthWordFromEnd returns the String of the Kth word from the end, where K is a number from 0 until sentences.length - 1

        @param      Queue<String> sentences     A queue that contains all the words of the sentences
        @param      int k                       To determine the Kth word starting from 0 from the end that user wants to delete
        @return     String                      Returns the Kth string from the end
     */
    public String kthWordFromEnd(Queue<String> sentences, int k) {

        //Check input validity
        if (sentences.size() == 0) {
            System.out.println("Please insert a sentence with at least one word.");
            System.exit(0);
        }
        if (k >= sentences.size()) {
            System.out.println("Invalid K value. Please insert a K value ranging from 0 until the length of your sentence - 1");
            System.exit(0);
        }
        if (k < 0) {
            System.out.println("Invalid K value. Please insert a K value ranging from 0 until the length of your sentence - 1");
            System.exit(0);
        }

        //If the input wants to retrieve the last word from the end (meaning the first word from the beginning)
        //Return the first word of the sentence
        if (k == (sentences.size() - 1)) {
            return sentences.dequeue();
        }

        int deleteWordWithThisNumber = sentences.size() - k - 1;
        int counter = 0;

        //Remove the words until it is in index deleteWordWithThisNumber
        while (counter < deleteWordWithThisNumber) {
            sentences.dequeue();
            counter++;
        }

        return sentences.dequeue();
    }

    public static void main(String[] args) {

        //BufferedReader to read the input
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        //In case of IOExceptions
        try {
            System.out.println("Input format: $ java KthString [Kth word from 0 until sentence length - 1]");

            //Read the input line
            String line = reader.readLine();

            //Split the line into words by splitting the spaces in between
            String[] lineDivided = line.split(" ");
            int kthWordToBeDeleted = Integer.parseInt(lineDivided[3]);


            System.out.println("Insert a sentence here");

            //Read the sentence inputted and split it to words by splitting the spaces in between
            String sentence = reader.readLine();
            String[] sentenceDivided = sentence.split(" ");

            //Insert all the words into a queue
            Queue<String> words = new Queue<>();
            for (String s : sentenceDivided) {
                words.enqueue(s);
            }

            //The Kth word from the end
            KthString ks = new KthString();
            System.out.println(ks.kthWordFromEnd(words, kthWordToBeDeleted));

        } catch (IOException e) {
            e.getMessage();
        }
    }
}
