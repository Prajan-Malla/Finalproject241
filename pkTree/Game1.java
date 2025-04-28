package pkTree;

import java.util.LinkedList;
import java.util.Scanner;

public class Game1 {

    public static void startGame1(Tree<String> treeMain) {
        Scanner scanner = new Scanner(System.in);
        String currentFocus = "Inert entity"; // Start with correct case
        int iterations = 0;

        while (iterations < 6 && currentFocus != null) {
            System.out.println("Is it a " + currentFocus + "? (true/false)");
            boolean answer = scanner.nextBoolean();

            Position<String> currentNode = treeMain.search(treeMain.getRoot(), currentFocus);
            if (currentNode == null) {
                System.out.println("Error: Current node not found!");
                break;
            }

            if (answer) {
                // Remove all siblings except current node
                Position<String> parent = currentNode.getParent();
                if (parent != null) {
                    LinkedList<Position<String>> siblings = new LinkedList<>(parent.getChildren());
                    for (Position<String> sibling : siblings) {
                        if (!sibling.getData().equals(currentFocus)) {
                            treeMain.remove(sibling.getData());
                        }
                    }
                }
                // Move down to child with most branches
                String child = maxChildren(treeMain, currentFocus);
                currentFocus = child.equals(currentFocus) ? null : child;
            } else {
                // Remove current branch and move up to parent
                Position<String> parent = currentNode.getParent();
                treeMain.remove(currentFocus);
                if (parent != null) {
                    currentFocus = maxChildren(treeMain, parent.getData());
                } else {
                    currentFocus = null; // Tree is empty
                }
            }
            iterations++;
        }

        System.out.println("Is it a " + currentFocus + "? (true/false)");
        boolean answer = scanner.nextBoolean();
        if(answer){
            System.out.println("System has guessed your thought");
        }
        else{
            System.out.println("System failed");
        }

        scanner.close();
    }

    public static String maxChildren(Tree<String> treeMain, String parentData) {
        Position<String> parentNode = treeMain.search(treeMain.getRoot(), parentData);
        if (parentNode == null) return parentData;

        LinkedList<Position<String>> children = new LinkedList<>(parentNode.getChildren());
        Position<String> maxChild = null;
        int maxCount = -1;

        for (Position<String> child : children) {
            int childCount = child.getChildren().size();
            if (childCount > maxCount) {
                maxCount = childCount;
                maxChild = child;
            }
        }

        return (maxChild != null) ? maxChild.getData() : parentData;
    }
}