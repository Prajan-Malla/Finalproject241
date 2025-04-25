
package pkTree;
//import java.util.Iterator;
import java.util.LinkedList;
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



    public boolean inertNode(T parentData, T newData) {

        // Ensure newData is not already in the tree
        // we start with the root of the whole tree
        if (search(root_, newData) != null) {
            return false; // newData already exists in the tree
        }

        // Search for the parent node
        Position<T> parentNode = search(root_, parentData);
        if (parentNode == null) {
            return false; // parentData doesn't exist
        }

        // Create the new node and add it to the parent's children
        Position<T> newNode = new Position<>(newData, parentNode);
        parentNode.addChild(newNode);
        ctPositions_++;
        return true;
    }


    // Searches for a node containing the given data, returns the node or null
    private Position<T> search(Position<T> node, T data) {
        //empty tree
        if (node == null) {
            return null;
        }
        // we found the node
        if (node.getData().equals(data)) {
            return node;
        }
        // recursive call on all the children of the current node
        for (Position<T> child : node.getChildren()) {
            Position<T> result = search(child, data);
            if (result != null) {
                return result;
            }
        }
        return null;
    }

}
