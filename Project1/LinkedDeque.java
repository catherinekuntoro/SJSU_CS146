import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

// Deque implementation using a linked list.
public class LinkedDeque<Item> implements Iterable<Item> {

    //Instance variables
    private Node head;
    private Node tail;
    private int size;

    // Helper doubly-linked list class.
    private class Node {
        private Item item;
        private Node next;
        private Node prev;

        //Constructs a Node with 'item' inside it
        private Node(Item item) {
            this.item = item;
        }

        /*
            Sets the next node of this node

            @param  Node n      'n' node to be set as the next node
                                of this node
         */
        void setNext(Node n) {
            next = n;
        }

        /*
            Sets the previous node of this node

            @param  Node p      'p' node to be set as the previous node
                                of this node
         */
        void setPrev(Node p) {
            prev = p;
        }

        /*
            Returns the next node of this node

            @return  Node      Returns the next node of this node
         */
        Node getNext() {
            return next;
        }

        /*
            Returns the previous node of this node

            @return  Node      Returns the previous node of this node
         */
        Node getPrev() {
            return prev;
        }

        /*
            Returns the item contained within this node

            @return  Item      Returns the item of this node
         */
        Item getItem() {
            return item;
        }

    }

    // Construct an empty deque.
    public LinkedDeque() {

        //An empty Deque has nothing in it; it has an empty (null) head and tail
        head = null;
        tail = null;

        //Set size to zero for an empty Deque
        size = 0;
    }

    //Mainly for testing purposes; no real use in actual program
    public Item getHeadValue() {
        return head.getItem();
    }

    //Mainly for testing purposes; no real use in actual program
    public Item getTailValue() {
        return tail.getItem();
    }

    //Mainly for testing purposes; no real use in actual program
    public Item getNextValue() {
        return head.getNext().getItem();
    }

    //Mainly for testing purposes; no real use in actual program
    public Item getPrevValue() {
        return head.getPrev().getItem();
    }

    // Is the dequeue empty?
    public boolean isEmpty() {

        //If size is zero, it is empty
        return size == 0;
    }

    // The number of items on the deque.
    public int size() {
        return size;
    }

    // Add item to the front of the deque.
    public void addFirst(Item item) {

        //Checks for a null item input. Throws a NullPointerException if so
        if (item == null) {
            throw new NullPointerException("Unable to add a null Item object.");
        }

        //Creates a new node with the specified 'item' value that would be the new head of deque
        Node newHead = new Node(item);

        //If newHead would be the one and only Node of the Deque
        if (size() == 0) {

            //If newHead is the only thing in the Deque, newHead is its own head and tail
            head = newHead;
            tail = newHead;

            //Sets the previous and next node of newHead to be null
            //since it is the only Node in the deque
            newHead.setNext(null);
            newHead.setPrev(null);

        } else {

            //Save the current node of the head so it could be the next of newHead
            Node nextOfNewHead = head;

            //To ensure newHead has the properties of a head;
            //The next node of the newHead is the old head
            newHead.setNext(nextOfNewHead);
            //the previous node of a head should be null (nothing)
            newHead.setPrev(null);

            //Now, the old head (nextOfNewHead)'s previous node is the newHead
            nextOfNewHead.setPrev(newHead);

            //Sets newHead as the new head of the deque
            head = newHead;
        }

        //Since a newHead is added, the size of Deque increases
        size++;

    }

    // Add item to the end of the deque.
    public void addLast(Item item) {

        //Checks for a null item input. Throws a NullPointerException if so
        if (item == null) {
            throw new NullPointerException("Unable to add a null Item object.");
        }

        //Creates a new node with the specified 'item' value that would be the new tail of Deque
        Node newTail = new Node(item);

        //If newTail would be the only Node in the Deque
        if (size() == 0) {

            //Set head and tail to newTail
            head = newTail;
            tail = newTail;

            //Set newTail's next and previous nodes to null since newTail is the only Node in the Deque
            newTail.setNext(null);
            newTail.setPrev(null);

        } else {
            //Save the current node of the tail so it could be
            //the previous node of the new tail
            Node prevOfNewTail = tail;

            //Set the next node of the tail a null
            newTail.setNext(null);

            //Set the previous node of the new tail as the old tail (prevOfNewTail)
            newTail.setPrev(prevOfNewTail);

            //Set the old tail(prevOfNewTail)'s next node as the new tail
            prevOfNewTail.setNext(newTail);

            //Set the tail node of this Deque as the new tail
            tail = newTail;
        }

        //Increase the size of the Deque
        size++;
    }

    // Remove and return item from the front of the deque.
    public Item removeFirst() {
        //If user attempts to remove a node from an empty Deque, throw an exception
        if (isEmpty()) {
            throw new NoSuchElementException("Unable to remove an Item from an empty Deque");
        }
        if (size() == 1) {

            //If there's only one node in Deque, it means that node is both the head and tail of Deque
            Item itemOfOnlyNode = head.getItem();
            head = null;
            tail = null;

            //Decrease the size of the Deque and returns the item within the deleted Node
            size--;
            return itemOfOnlyNode;

        } else {
            //The new head would be the next node of the current head
            Node newHead = head.getNext();

            //Save the current head tht will be removed later
            Node oldHead = head;

            //Set the previous node of the new head node to null so it could be a proper head node
            newHead.setPrev(null);

            //Set the next node of the old head to null so it could sever its ties with its next node
            oldHead.setNext(null);

            //Set the new head as Deque's new head
            head = newHead;

            //Decrease the size of Deque and return the Item inside the just deleted node
            size--;
            return oldHead.getItem();
        }

    }

    // Remove and return item from the end of the deque.
    public Item removeLast() {
        //If user attempts to remove a node from an empty Deque, throw an exception
        if (isEmpty()) {
            throw new NoSuchElementException("Unable to remove an Item from an empty Deque");
        }
        if (size() == 1) {
            //If there's only one node in Deque, it means that node is both the head and tail of Deque
            Item itemOfOnlyNode = head.getItem();
            head = null;
            tail = null;

            //Decrease the size of the Deque and returns the item within the deleted Node
            size--;
            return itemOfOnlyNode;
        } else {
            //The new tail would be the previous node of the current tail
            Node newTail = tail.getPrev();

            //Save the current tail that will be removed
            Node oldTail = tail;

            //Set the next node of the new tail as null so it could be a proper tail node
            newTail.setNext(null);

            //Set the previous node of the old Tail to null to sever the ties with its previous node
            oldTail.setPrev(null);

            tail = newTail;

            //Decrease the size of the Deque and return the Item inside the deleted Node
            size--;
            return oldTail.getItem();
        }


    }

    // An iterator over items in the queue in order from front to end.
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    // An iterator, doesn't implement remove() since it's optional.
    private class DequeIterator implements Iterator<Item> {

        Node currentNode;

        DequeIterator() {
            currentNode = head;
        }

        public boolean hasNext() {
            //If currentNode's next is not null, then it means currentNode has a node next to it
            return currentNode.getNext() != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            //Check if there are any nodes next to this node
            if (!hasNext()) {
                throw new NoSuchElementException("There are no Items next to this node");
            }

            //Return the item of the next node
            currentNode = currentNode.getNext();
            return currentNode.getItem();
        }
    }

    // A string representation of the deque.
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item + " ");
        }
        return s.toString().substring(0, s.length() - 1);
    }

    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        LinkedDeque<Character> deque = new LinkedDeque<Character>();
        String quote = "There is grandeur in this view of life, with its "
                + "several powers, having been originally breathed into a few "
                + "forms or into one; and that, whilst this planet has gone "
                + "cycling on according to the fixed law of gravity, from so "
                + "simple a beginning endless forms most beautiful and most "
                + "wonderful have been, and are being, evolved. ~ "
                + "Charles Darwin, The Origin of Species";
        int r = StdRandom.uniform(0, quote.length());
        for (int i = quote.substring(0, r).length() - 1; i >= 0; i--) {
            deque.addFirst(quote.charAt(i));
        }

        for (int i = 0; i < quote.substring(r).length(); i++) {
            deque.addLast(quote.charAt(r + i));
        }

        StdOut.println(deque.isEmpty());
        StdOut.printf("(%d characters) ", deque.size());
        for (char c : deque) {
            StdOut.print(c);
        }
        StdOut.println();
        double s = StdRandom.uniform();
        for (int i = 0; i < quote.length(); i++) {
            if (StdRandom.bernoulli(s)) {
                deque.removeFirst();
            } else {
                deque.removeLast();
            }
        }
        StdOut.println(deque.isEmpty());
    }
}
