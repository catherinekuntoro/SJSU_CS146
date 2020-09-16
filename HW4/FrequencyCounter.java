// FrequencyCounter.java: Reads in a command-line integer and sequence of words
// from standard input and prints out all the words (whose length exceeds the
// threshold) that occur most frequently to standard output. It also prints out
// the number of words whose length exceeds the threshold and the number of
// distinct such words.

import edu.princeton.cs.algs4.StdIn;

import java.util.ArrayList;

public class FrequencyCounter {
    public static void main(String[] args) {

        //Reads the argument.
        //Any word whose length is less than inputWordLength will be ignored later on
        int inputWordLength = Integer.parseInt(args[0]);

        //Throws an exception if user input is not at least 1 for the length of the word
        if (inputWordLength < 1) {
            throw new IllegalArgumentException("Invalid word length. Please enter an integer that is greater than or equal to one.");
        }

        //Symbol table that accepts a String as its key, where the input word will be the key
        //Accepts an Integer as its value, where it would represent the word's frequency
        ArrayST<String, Integer> symbolTableWords = new ArrayST<>();

        while (!StdIn.isEmpty()) {

            //Reads input
            String word = StdIn.readString();

            //Ignore the words that has length that is less than inputWord Length
            if (word.length() >= inputWordLength) {

                //If the symbol table already contains word, increment  word's frequency
                if (symbolTableWords.contains(word)) {
                    symbolTableWords.put(word, symbolTableWords.get(word) + 1);
                }

                //Otherwise, add one as the value to show that it's the first occurrence of the word
                else {
                    symbolTableWords.put(word, 1);
                }
            }
        }

        //After there are no more inputs, print the final stats

        //The highest frequency must at least be one
        int highestFrequency = 1;


        //Get the highest frequency of words
        ArrayList<String> wordsWithHighestFrequency = new ArrayList<>();

        for (String current : symbolTableWords.keys()) {

            //There is a possibility that the current key being visited right now is null, since the symbol table
            //may have expanded its size, which could lead to some unoccupied spot
            //Therefore, once the current key is null, that means there are no more actual keys to be seen
            if (current == null) {
                break;
            }

            //If the current word has the same frequency as highestFrequency, add that word to the array list
            if (symbolTableWords.get(current) == highestFrequency) {

                wordsWithHighestFrequency.add(current);

            }

            //However, if the current word's frequency is greater than highestFrequency, clear the array list first
            else if (symbolTableWords.get(current) > highestFrequency) {

                //Everything else before this current word has a lower frequency, so clear the array list
                wordsWithHighestFrequency.clear();

                //Add this current word with the highest frequency into the array list
                wordsWithHighestFrequency.add(current);

                //Update the number of highest frequency
                highestFrequency = symbolTableWords.get(current);
            }
        }
        int totalWords = 0;
        for (String s : symbolTableWords.keys()) {
            totalWords += symbolTableWords.get(s);
        }

        System.out.println("WORD(S) WITH THE HIGHEST FREQUENCY:");

        for (String current : wordsWithHighestFrequency) {
            System.out.println(current + " " + highestFrequency);
        }

        System.out.println("distinct words =    " + symbolTableWords.size());
        System.out.println("total words    =    " + totalWords);

    }
}
