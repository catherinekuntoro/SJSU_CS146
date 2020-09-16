import edu.princeton.cs.algs4.StdIn;

import java.util.ArrayList;
import java.util.Collections;

public class PostOrder {


    //Instance variable to keep track of the current pre-order index
    //Would not be messed up during recursion
    private int preOrderIndex = 0;

    //ArrayList to contain the nodes in post order traversal
    private ArrayList<String> everything = new ArrayList<>();


    //Helper method to search the index where "data" is in the array
    private int searchIndex(ArrayList<String> arr, String data) {
        int index;
        for (index = 0; index < arr.size(); index++) {
            if (data.equals(arr.get(index))) {
                return index;
            }
        }

        return index;
    }

    //Method to fill the everything Array List with post order iteration
    private void postOrderIteration(int start, int end, ArrayList<String> inOrder, ArrayList<String> preOrder) {

        //Base case
        if (start > end) {
            return;
        }

        //Get what index is the item at the current preOrder index inside the inOrder array, then increment it
        int indexAtInOrder = searchIndex(inOrder, preOrder.get(preOrderIndex++));


        //All nodes that are in the left subtree of the current preOrder node will be from
        //the start index to indexAtInOrder-1
        postOrderIteration(start, indexAtInOrder - 1, inOrder, preOrder);

        //All nodes that are in the right subtree of the current preOrder node will be from
        //indexAtInOrder+1 until the end index
        postOrderIteration(indexAtInOrder + 1, end, inOrder, preOrder);

        //Add the current item to the everything array
        everything.add(inOrder.get(indexAtInOrder));
    }

    public static void main(String[] args) {
        //Prompt. Just a reminder :D
        System.out.println("Please enter the order of in-order then pre-order traversal. Separate entries with commas.");


        //First line is inOrder
        ArrayList<String> inOrderInputs = new ArrayList<>();

        //Check for validity
        if (!StdIn.hasNextLine()) {

            throw new IllegalArgumentException("Please enter a line of in-order traversal separated by commas");

        } else {

            //Read the user input
            String inOrderAll = StdIn.readLine();

            //Insert the inputs inside the array list
            Collections.addAll(inOrderInputs, inOrderAll.split(","));
        }

        //Second line is preOrder
        ArrayList<String> preOrderInputs = new ArrayList<>();

        //Checking for validity
        if (!StdIn.hasNextLine()) {

            throw new IllegalArgumentException("Please enter another line of pre-order traversal separated by commas");

        } else {

            //Read the user input
            String preOrderAll = StdIn.readLine();

            //Insert the inputs inside the array list
            Collections.addAll(preOrderInputs, preOrderAll.split(","));
        }

        if (preOrderInputs.size() != inOrderInputs.size()) {
            throw new IllegalArgumentException("The input size of either the preOrder or inOrder arrays are not the same. Please enter valid entries");
        }

        PostOrder po = new PostOrder();

        //Call postOrder() to construct the post order iteration
        po.postOrderIteration(0, preOrderInputs.size() - 1, inOrderInputs, preOrderInputs);

        //Iterate through the array list with the post order iteration in it and print
        int lastIndex = po.everything.size() - 1;
        for (String s : po.everything) {
            if (s.equals(po.everything.get(lastIndex))) {
                System.out.print(s);
            } else {
                System.out.print(s + ", ");
            }

        }
    }


}
