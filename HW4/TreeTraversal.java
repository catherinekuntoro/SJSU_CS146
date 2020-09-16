import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class TreeTraversal {
    private Node root; // root of the binary search tree

    // Representation of a binary search tree
    private class Node {
        private String item;      // node item
        private Node left, right; // left and right subtrees

        // Construct a Node given its item.
        Node(String item) {
            this.item = item;
        }

        // Return a string representation of the node.
        public String toString() {
            return item;
        }
    }

    // Put the item into the tree.
    public void put(String item) {
        root = put(root, item);
    }

    // Helper for put(String item).
    private Node put(Node x, String item) {
        if (x == null) return new Node(item);
        int cmp = item.compareTo(x.item);
        if (cmp < 0) x.left = put(x.left, item);
        else if (cmp > 0) x.right = put(x.right, item);
        return x;
    }

    // Return the nodes of the tree traversed pre-order.
    public Iterable<Node> preOrder() {
        Queue<Node> q = new Queue<>();

        //Pass the queue into the preOrder helper method
        preOrder(root, q);

        return q;

    }

    // Helper for preOrder().
    private void preOrder(Node x, Queue<Node> q) {

        //If the current node is a null node, there are
        //no more nodes left to traverse in the current
        //node's direction
        if (x == null) {
            return;
        } else {

            //Enqueue the node first
            q.enqueue(x);

            //Traverse the left tree next
            preOrder(x.left, q);

            //Traverse the right tree last
            preOrder(x.right, q);
        }
    }

    // Return the nodes of the tree traversed in-order.
    public Iterable<Node> inOrder() {
        Queue<Node> q = new Queue<>();

        //Pass the queue and root node
        inOrder(root, q);

        return q;
    }

    // Helper for inOrder().
    private void inOrder(Node x, Queue<Node> q) {

        //If the current node is a null node, there are
        //no more nodes left to traverse in the current
        //node's direction
        if (x == null) {
            return;

        } else {

            //Travel left tree first
            inOrder(x.left, q);

            //Queue the key
            q.enqueue(x);

            //Travel right tree last
            inOrder(x.right, q);
        }
    }

    // Return the nodes of the tree traversed post-order.
    public Iterable<Node> postOrder() {
        Queue<Node> q = new Queue<>();

        //Pass the queue and the root node
        postOrder(root, q);
        return q;
    }

    // Helper for postOrder().
    private void postOrder(Node x, Queue<Node> q) {

        //If the current node is a null node, there are
        //no more nodes left to traverse in the current
        //node's direction
        if (x == null) {
            return;
        } else {

            //Traverse left tree first
            postOrder(x.left, q);

            //Traverse right tree
            postOrder(x.right, q);

            //Queue item
            q.enqueue(x);
        }
    }

    // Return the nodes of the tree traversed level-order.
    public Iterable<Node> levelOrder() {
        Queue<Node> q = new Queue<>();

        //The level starts with one, which will be the root node's level
        int level = 1;

        while (nodesInLevel(root, level, q)) {

            //If the program reaches inside this while loop,
            //that means there is at least one node in the current level
            //that the helper method nodesInLevel() has evaluated

            //Increment the level to go deeper into the tree
            level++;
        }

        //Return the queue containing the nodes in level order
        return q;

    }

    private boolean nodesInLevel(Node x, int level, Queue<Node> q) {

        if (x == null) {
            //There are no nodes in this level; end the level order traversal
            return false;
        }

        //If the level is equal to one, enqueue the node. This means we have reached the target level
        if (level == 1) {
            q.enqueue(x);

            return true; //There is a node in this level
        }


        //Traverse the left tree first. Since the level indicates the target level that we want to explore
        //and x always starts with the root node, explore the left tree of x first and decrement the level
        //by one to go deeper into the tree with every recursive call
        boolean leftNode = nodesInLevel(x.left, level - 1, q);

        //Next, traverse the right tree with the same logic as traversing the left tree
        boolean rightNode = nodesInLevel(x.right, level - 1, q);

        //If there are any left or right children in the given level (which means either leftNode or rightNode is true),
        //the "true" boolean value of this expression will be passed on to levelOrder()

        //If there are no left or right children in the given level, the "false" boolean value of this expression
        //will be passed on to levelOrder(), which would stop the traversal
        return (leftNode || rightNode);
    }

    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        String[] items = StdIn.readAllStrings();
        TreeTraversal tree = new TreeTraversal();
        for (String item : items) {
            tree.put(item);
        }
        StdOut.println("Pre-order:   " + tree.preOrder());
        StdOut.println("In-order:    " + tree.inOrder());
        StdOut.println("Post-order:  " + tree.postOrder());
        StdOut.println("Level-order: " + tree.levelOrder());
    }
}
