/**
 * Creates Binary search tree using BSTNode class and SortedCollections interface.
 * @param <T>
 */
public class BinarySearchTree<T extends Comparable<T>> implements SortedCollection<T> {

    // Reference to the root node of the binary search tree
    protected BSTNode<T> root;

    /**
     * Constructor that initializes an empty tree.
     */
    public BinarySearchTree() {
        root = null;
    }

    /**
     * Inserts a new element into the binary search tree.
     *
     * @param data the data to be inserted
     * @throws NullPointerException if data is null
     */
    @Override
    public void insert(T data) throws NullPointerException {
        if (data == null) {
            throw new NullPointerException("Cannot insert null data.");
        }
        BSTNode<T> newNode = new BSTNode<>(data);
        if (root == null) {
            root = newNode;
        } else {
            insertHelper(newNode, root);
        }
    }

    /**
     * Recursively inserts a new node into the correct position in the subtree.
     *
     * @param newNode the new node to insert
     * @param subtree the current subtree being inspected
     */
    protected void insertHelper(BSTNode<T> newNode, BSTNode<T> subtree) {
        // Compare the new node's data with the subtree's data
        if (newNode.getData().compareTo(subtree.getData()) < 0) {
            // Left subtree insertion
            if (subtree.getLeft() == null) {
                subtree.setLeft(newNode);
                newNode.setUp(subtree);
            } else {
                insertHelper(newNode, subtree.getLeft());
            }
        } else {
            // Right subtree insertion
            if (subtree.getRight() == null) {
                subtree.setRight(newNode);
                newNode.setUp(subtree);
            } else {
                insertHelper(newNode, subtree.getRight());
            }
        }
    }

    /**
     * Checks if the tree contains the specified data.
     *
     * @param data the data to search for
     * @return true if the data is found, false otherwise
     */
    @Override
    public boolean contains(Comparable<T> data) {
        return containsRecursively(root, data);
    }

    /**
     * Recursively searches for data in the subtree.
     *
     * @param node the current node being inspected
     * @param data the data to search for
     * @return true if the data is found, false otherwise
     */
    private boolean containsRecursively(BSTNode<T> node, Comparable<T> data) {
        if (node == null) {
            return false;
        }
        int comparison = data.compareTo(node.getData());
        if (comparison == 0) {
            return true;
        } else if (comparison < 0) {
            return containsRecursively(node.getLeft(), data);
        } else {
            return containsRecursively(node.getRight(), data);
        }
    }

    /**
     * Returns the number of elements in the tree.
     *
     * @return the size of the tree
     */
    @Override
    public int size() {
        return calculateSize(root);
    }

    /**
     * Recursively calculates the size of the subtree.
     *
     * @param node the current node being inspected
     * @return the size of the subtree
     */
    private int calculateSize(BSTNode<T> node) {
        if (node == null) {
            return 0;
        }
        return 1 + calculateSize(node.getLeft()) + calculateSize(node.getRight());
    }

    /**
     * Returns whether the tree is empty.
     *
     * @return true if the tree is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Clears the tree, making it empty.
     */
    @Override
    public void clear() {
        root = null;
    }

    /**
     * Test 1: Inserts multiple integer values and verifies the tree structure and size.
     *
     * @return true if the test passes, false otherwise
     */
    public boolean test1() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.insert(10);
        bst.insert(5);
        bst.insert(15);
        bst.insert(2);
        bst.insert(7);

        // Expect tree structure with 5 as left child of 10, and 15 as right child
        return bst.contains(10) && bst.contains(5) && bst.contains(15) &&
                bst.contains(2) && bst.contains(7) && bst.size() == 5;
    }

    /**
     * Test 2: Inserts multiple string values and ensures they can be found in the correct order.
     *
     * @return true if the test passes, false otherwise
     */
    public boolean test2() {
        BinarySearchTree<String> bst = new BinarySearchTree<>();
        bst.insert("banana");
        bst.insert("apple");
        bst.insert("cherry");

        // Expect apple < banana < cherry order
        return bst.contains("banana") && bst.contains("apple") &&
                bst.contains("cherry") && bst.size() == 3;
    }

    /**
     * Test 3: Tests the clear method and checks if the tree size is zero after clearing.
     *
     * @return true if the test passes, false otherwise
     */
    public boolean test3() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.insert(10);
        bst.insert(20);
        bst.insert(30);

        if (bst.size() != 3) return false;  // Tree should contain 3 nodes
        bst.clear();  // Clear the tree
        return bst.isEmpty();  // Ensure the tree is empty
    }

    /**
     * Main method that runs the tests and prints out the results.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        System.out.println("Test 1: " + (bst.test1() ? "Passed" : "Failed"));
        System.out.println("Test 2: " + (bst.test2() ? "Passed" : "Failed"));
        System.out.println("Test 3: " + (bst.test3() ? "Passed" : "Failed"));
    }
}
