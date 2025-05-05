package pkTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Game1 {

    public static void startGame1Easy(Tree<String> treeMain) {
        Scanner scanner = new Scanner(System.in);
        Position<String> current = treeMain.getRoot();
        int iterations = 0;

        System.out.println("Think of an item, and I will try to guess it!");
        System.out.print("Please enter your guess: ");
        String userGuessed = scanner.nextLine(); // Capture user's guess

        while (iterations < 6 && current != null) {
            System.out.println("Is it a " + current.getData() + "? (true/false)");
            boolean answer = scanner.nextBoolean();
            iterations++;

            if (answer) {
                if (current.getData().equalsIgnoreCase(userGuessed)) {
                    System.out.println("System has guessed your thought!");
                    return;
                } else if (!current.getChildren().isEmpty()) {
                    current = current.getChildren().get(0); // Go to first child
                } else {
                    break; // No children to go deeper
                }
            } else {
                Position<String> parent = current.getParent();
                if (parent == null) {
                    System.out.println("System failed. No more options.");
                    return;
                }

                List<Position<String>> siblings = parent.getChildren();
                int index = siblings.indexOf(current);
                if (index + 1 < siblings.size()) {
                    current = siblings.get(index + 1); // Try next sibling
                } else {
                    // Backtrack up the tree to find next available sibling
                    while (parent != null) {
                        Position<String> grandParent = parent.getParent();
                        if (grandParent == null) {
                            current = null;
                            break;
                        }

                        List<Position<String>> parentSiblings = grandParent.getChildren();
                        int parentIndex = parentSiblings.indexOf(parent);
                        if (parentIndex + 1 < parentSiblings.size()) {
                            current = parentSiblings.get(parentIndex + 1);
                            break;
                        } else {
                            parent = grandParent;
                        }
                    }
                }
            }
        }

        // Final check after 6 questions
        if (current != null && current.getData().equalsIgnoreCase(userGuessed)) {
            System.out.println("System has guessed your thought!");
        } else {
            System.out.println("System failed to guess your thought.");
            System.out.println("You thought of: " + userGuessed);
        }
    }


    public static void startGame1Hard(Tree<String> treeMain) {
        Scanner scanner = new Scanner(System.in);
        String currentFocus = "inert entity"; // Start with correct case
        int iterations = 0;

        System.out.println("Think of an item, and I will try to guess it!");
        System.out.print("Please enter your guess: ");
        String userGuessed = scanner.nextLine(); // Capture user's guess


        while (iterations < 6 && currentFocus != null) {
            Position<String> currentNode = treeMain.search(treeMain.getRoot(), currentFocus);
            if (currentNode == null) {
                System.out.println("Error: Current node not found!");
                break;
            }

            // If no children, guess currentFocus
            if (currentNode.getChildren().isEmpty()) {
                System.out.println("Is it a " + currentFocus + "? (true/false)");
                boolean answer = scanner.nextBoolean();
                if (answer) {
                    System.out.println("System has guessed your thought!");
                } else {
                    System.out.println("System failed.");
                }
                return;
            }

            System.out.println("Is it a " + currentFocus + "? (true/false)");
            boolean answer = scanner.nextBoolean();

            if (answer) {
                // Keep only current node among siblings
                Position<String> parent = currentNode.getParent();
                if (parent != null) {
                    LinkedList<Position<String>> siblings = new LinkedList<>(parent.getChildren());
                    for (Position<String> sibling : siblings) {
                        if (!sibling.getData().equals(currentFocus)) {
                            treeMain.remove(sibling.getData());
                        }
                    }
                }

                // Move down to the child with the most children
                String child = maxChildren(treeMain, currentFocus);
                if (child.equals(currentFocus)) {
                    // No deeper children — user guessed
                    System.out.println("Is it a " + currentFocus + "? (true/false)");
                    answer = scanner.nextBoolean();
                    if (answer) {
                        System.out.println("System has guessed your thought!");
                    } else {
                        System.out.println("System failed.");
                    }
                    return;
                } else {
                    currentFocus = child;
                }

            } else {
                // Wrong guess — remove current and go up
                Position<String> parent = currentNode.getParent();
                treeMain.remove(currentFocus);
                if (parent != null) {
                    currentFocus = maxChildren(treeMain, parent.getData());
                } else {
                    currentFocus = null;
                }
            }

            iterations++;
        }


        System.out.println("Is it a " + currentFocus + "? (true/false)");
        boolean answer = scanner.nextBoolean();
        if(answer&&userGuessed.equals(currentFocus)){
            System.out.println("System has guessed your thought");
        }
        else{
            System.out.println("System failed");
        }


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
