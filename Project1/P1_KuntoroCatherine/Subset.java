import edu.princeton.cs.algs4.StdIn;

// Takes a command-line integer k; reads in a sequence of strings from
// standard input; and prints out exactly k of them, uniformly at random.
// Note that each item from the sequence is printed out at most once.
public class Subset {
    public static void main(String[] args) {

        //Reads the argument.
        //Input k assumed to be a valid input, as per the instructions.
        int inputKthValue = Integer.parseInt(args[0]);

        //Creates a ResizingArrayRandomQueue object to store all the String inputs then randomize it
        ResizingArrayRandomQueue<String> queue = new ResizingArrayRandomQueue<>();

        //Prompt for string input
        System.out.println("Please enter a string");

        //" For an extra challenge, limit the maximum size (of ResizingArrayRandomQueue) to k. "

        //Add the string inputs inside the queue
        while (!StdIn.isEmpty()) {
            //If the size of the queue equates to inputKthValue, dequeue a random element
            //to make room for another String input read
            if (queue.size() == inputKthValue) {
                queue.dequeue();
            }
            //Since the size of the queue is still within inputKthValue size, add the string input into the queue.
            //This ensures the queue still reads every string input while making
            //sure that the size of the queue is inputKthValue-size
            queue.enqueue(StdIn.readString());

        }

        //Prints the queue objects with ResizingArrayRandomQueue's dequeue method
        while (!queue.isEmpty()) {
            //The dequeue method will dequeue in a random order
            System.out.println(queue.dequeue());
        }

    }
}
