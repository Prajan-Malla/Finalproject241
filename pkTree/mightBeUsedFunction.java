//package pkTree;
//
//import java.util.*;
//
//
//public class mightBeUsedFunction {
//
//    // Returns an iterable collection of all positions in the tree (pre-order traversal)
//    public Iterable<Position<T>> positions() {
//        List<Position<T>> allPositions = new LinkedList<>();
//        preOrderTraversalCollectPos(root_, allPositions);
//        return allPositions;
//    }
//
//    // Helper method for pre-order traversal to collect all positions
//    private void preOrderTraversalCollectPos(Position<T> node, List<Position<T>> allPositions) {
//        if (node == null) return; // the tree is empty
//        allPositions.add(node); // Add the current node: action of the visit
//        for (Position<T> child : node.getChildren()) {
//            preOrderTraversalCollectPos(child, allPositions);// recursive call
//        }
//    }
//
//
//    // Helper method for post-order traversal to delete all the positions in the tree
//    public void postOrderDelete(Position<T> node) {
//        if (node == null) return; // the tree is empty
//
//        List<Position<T>> lstChildren=node.getChildren();// we get the list of children once
//        int szList=lstChildren.size();
//
//        for(int i = 0; i < szList; i++) {
//            postOrderDelete(lstChildren.get(i));// recursive call
//        }
//        System.out.println("Deleting the node"+node.getData());
//        node=null;
//        ctPositions_--;
//    }
//
//    // Deletes the node with the given nodeData and attaches its children to its parent
//    public boolean deleteNode(T nodeData) {
//        Position<T> nodeToDelete = search(root_, nodeData);
//        if (nodeToDelete == null) {
//            return false; // node not found
//        }
//
//        Position<T> parentNode = nodeToDelete.getParent();
//        if (parentNode == null) { // The node to delete is the root
//            if (nodeToDelete.getChildren().isEmpty()) {
//                root_ = null; // No other nodes, tree is empty
//            } else {
//                Position<T> newRoot = nodeToDelete.getChildren().get(0); // Left-most child
//                root_ = newRoot; // The left-most child becomes the new root
//                // Attach all other children of the old root to the new root
//                for (int i = 1; i < nodeToDelete.getChildren().size(); i++) {
//                    newRoot.addChild(nodeToDelete.getChildren().get(i));
//                }
//            }
//        } else {
//            // Attach all children of the deleted node to its parent
//            for (Position<T> child : nodeToDelete.getChildren()) {
//                parentNode.addChild(child);
//            }
//            // Remove the deleted node from its parent's children
//            parentNode.removeChild(nodeToDelete);
//        }
//
//        ctPositions_--;
//        return true;
//    }
//
//    // Helper method for pre-order traversal to print all the positions
//    // it takes a number of empty spaces
//    public void preOrderTrPrint(Position<T> node, String emptySpaces) {
//        if (node == null) return; // the tree is empty
//        System.out.println(emptySpaces+node.getData());// we print the data of the node: action of the visit
//
//        List<Position<T>> lstChildren=node.getChildren();// we get the list of children once
//        int szList=lstChildren.size();
//
//        emptySpaces+='\t';
//        for(int i = 0; i < szList; i++) {
//            preOrderTrPrint(lstChildren.get(i), emptySpaces);// recursive call
//        }
//    }
//}
