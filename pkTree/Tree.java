
package pkTree;
//import java.util.Iterator;

import java.util.List;

public class Tree<T> {
    private Position<T> root_;
    private int ctPositions_;

    // Constructor initializes the tree with root as null and size as zero
    public Tree() {
        this.root_ = null;
        this.ctPositions_ = 0;
    }

    // Getter for root
    public Position<T> getRoot() {
        return root_;
    }

    // Setter for root
    public void setRoot(Position<T> node) {
        this.root_ = node;
        this.ctPositions_ += 1;
    }

    // Returns the number of nodes in the tree
    public int size() { return ctPositions_; }

    // Returns true if the tree is empty
    public boolean isEmpty() { return ctPositions_ == 0; }


    public void insert(T parentData, T newData) {

        if (ctPositions_ == 0) {
            Position<T> newRoot = new Position<>(parentData, null);
            setRoot(newRoot);
            ctPositions_++;

            Position<T> newNode = new Position<>(newData, newRoot);
            newRoot.addChild(newNode);
            ctPositions_++;

            return; // Stop after the first insertion
        }

        if (search(root_, newData) != null) {
            return; // Skip insertion if child already exists
        }

        Position<T> parentNode = search(root_, parentData);
        if (parentNode == null) {
            return; // Skip insertion if parent not found
        }

        Position<T> newNode = new Position<>(newData, parentNode);
        parentNode.addChild(newNode);
        ctPositions_++;
    }





    // Searches for a node containing the given data, returns the node or null
    // Searches for a node containing the given data, returns the node or null
    public Position<T> search(Position<T> node, T data) {
        // Empty tree or node is null
        if (node == null) {
            return null;
        }

        // We found the node
        if (node.getData().equals(data)) {
            return node;
        }

        // Recursive call on all the children of the current node
        for (Position<T> child : node.getChildren()) {
            Position<T> result = search(child, data);
            if (result != null) {
                return result; // Return the node if found in one of the children
            }
        }

        // Data not found in this node or its children
        return null;
    }

    public boolean remove(T data) {
        // Find the node to remove
        Position<T> nodeToRemove = search(root_, data);
        if (nodeToRemove == null) {
            return false; // Node not found
        }

        if (nodeToRemove == root_) {
            // Case 1: Remove the root (entire tree)
            root_ = null;
            ctPositions_ = 0;
        } else {
            // Case 2: Remove a non-root node (subtree)
            Position<T> parent = nodeToRemove.getParent();
            parent.getChildren().remove(nodeToRemove); // Detach from parent
            int removedNodes = countNodesInSubtree(nodeToRemove);
            ctPositions_ -= removedNodes;
        }

        return true;
    }

    /**
     * Helper method to count all nodes in a subtree.
     * @param node The root of the subtree.
     * @return Total number of nodes in the subtree.
     */
    private int countNodesInSubtree(Position<T> node) {
        if (node == null) return 0;
        int count = 1; // Include the current node
        for (Position<T> child : node.getChildren()) {
            count += countNodesInSubtree(child);
        }
        return count;
    }

    public void displayTree(){
        displayTreeHelper(root_,"");

    }


    private void displayTreeHelper(Position<T> node, String indent) {
        if (node == null) {
            return;
        }

        // Print the current node
        System.out.println(indent + node.getData());

        // Get the children of the current node
        List<Position<T>> children = node.getChildren();  // Assuming getChildren() is available

        // Recursively print each child
        for (int i = 0; i < children.size(); i++) {
            Position<T> child = children.get(i);  // Get the child at index i
            displayTreeHelper(child, indent + "  ");  // Call the helper for this child
        }
    }


}
