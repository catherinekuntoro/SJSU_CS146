// AvgGPA.java: Reads from standard input a list of letter grades and computes
// and prints the average GPA (the average of the numbers corresponding to
// the grades).

import edu.princeton.cs.algs4.StdIn;

import java.util.InputMismatchException;

public class AvgGPA {
    public static void main(String[] args) {
        double totalScore = 0;
        double count = 0;
        String letterGrade;

        AvgGPA avgGPA = new AvgGPA();
        ArrayST<String, Double> letterToNum = avgGPA.mappingLetterGradeToScores();

        while (!StdIn.isEmpty()) {
            letterGrade = StdIn.readString();

            //Check if the letter grade exists
            if (letterToNum.get(letterGrade) == null) {
                throw new InputMismatchException("Invalid input for a letter grade. Please enter a valid letter grade.");

                //If it is a valid letter grade input, add the corresponding grade point to the total score
            } else {
                totalScore += letterToNum.get(letterGrade);
            }

            count++;
        }

        //Take the average score
        System.out.println(totalScore / count);

    }

    //Method to map letter grades with their respective grade point value with a symbol table
    private ArrayST<String, Double> mappingLetterGradeToScores() {
        ArrayST<String, Double> letterToNum = new ArrayST<>(11);
        letterToNum.put("A+", 4.33);
        letterToNum.put("A", 4.0);
        letterToNum.put("A-", 3.67);
        letterToNum.put("B+", 3.33);
        letterToNum.put("B", 3.0);
        letterToNum.put("B-", 2.67);
        letterToNum.put("C+", 2.33);
        letterToNum.put("C", 2.0);
        letterToNum.put("C-", 1.67);
        letterToNum.put("D", 1.0);
        letterToNum.put("F", 0.0);

        return letterToNum;
    }

}
