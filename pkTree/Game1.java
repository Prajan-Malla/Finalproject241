package pkTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Game1 {

    /**
     * Easy mode guessing game.
     * Navigates the tree level-by-level using user input (yes/no questions).
     * If the guess is correct, it moves to the first child; otherwise, checks siblings or backtracks.
     */
    public static void startGame1Easy(Tree<String> treeMain) {

        Scanner scanner = new Scanner(System.in);
        Position<String> current = treeMain.getRoot(); // Start at root of the tree

        while (current != null) {
            System.out.println("Is it a " + current.getData() + "? (true/false)");
            boolean answer = scanner.nextBoolean();

            if (answer) {
                if (current.getChildren().isEmpty()) {
                    // No children means this is a guess
                    System.out.println("System has guessed your thought!");
                    return;
                } else {
                    // Move to first child node and keep guessing deeper
                    current = current.getChildren().get(0);
                }
            } else {
                // Wrong guess — look for next sibling
                Position<String> parent = current.getParent();
                if (parent == null) {
                    // No parent = root = no siblings left
                    System.out.println("System failed. No more options.");
                    return;
                }

                List<Position<String>> siblings = parent.getChildren();
                int index = siblings.indexOf(current);

                if (index + 1 < siblings.size()) {
                    // Try next sibling
                    current = siblings.get(index + 1);
                } else {
                    // No siblings left — backtrack up the tree
                    while (parent != null) {
                        Position<String> grandParent = parent.getParent();
                        if (grandParent == null) {
                            System.out.println("System failed. No more options.");
                            return;
                        }

                        // Try next sibling of parent
                        List<Position<String>> parentSiblings = grandParent.getChildren();
                        int parentIndex = parentSiblings.indexOf(parent);
                        if (parentIndex + 1 < parentSiblings.size()) {
                            current = parentSiblings.get(parentIndex + 1);
                            break;
                        } else {
                            parent = grandParent; // Keep backtracking
                        }
                    }
                }
            }
        }

        System.out.println("System failed.");
    }

    /**
     * Hard mode guessing game.
     * Uses tree pruning and heuristic to always follow the branch with the most children.
     * Attempts to narrow down options quickly.
     */
    public static void startGame1Hard(Tree<String> treeMain) {
        Scanner scanner = new Scanner(System.in);
        String currentFocus = "inert entity"; // Starting point for guessing
        int iterations = 0; // Limit questions to prevent infinite loops

        while (iterations < 6 && currentFocus != null) {
            Position<String> currentNode = treeMain.search(treeMain.getRoot(), currentFocus);

            if (currentNode == null) {
                // Safety check: if node not found
                System.out.println("Error: Current node not found!");
                break;
            }

            // If no children, this is a leaf guess
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

            // Ask user about the current focus
            System.out.println("Is it a " + currentFocus + "? (true/false)");
            boolean answer = scanner.nextBoolean();

            if (answer) {
                // Prune siblings (remove incorrect branches at this level)
                Position<String> parent = currentNode.getParent();
                if (parent != null) {
                    LinkedList<Position<String>> siblings = new LinkedList<>(parent.getChildren());
                    for (Position<String> sibling : siblings) {
                        if (!sibling.getData().equals(currentFocus)) {
                            treeMain.remove(sibling.getData()); // Remove non-matching siblings
                        }
                    }
                }

                // Pick the child with the most children (broadest branch)
                String child = maxChildren(treeMain, currentFocus);

                if (child.equals(currentFocus)) {
                    // No deeper path, final guess
                    System.out.println("Is it a " + currentFocus + "? (true/false)");
                    answer = scanner.nextBoolean();
                    if (answer) {
                        System.out.println("System has guessed your thought!");
                    } else {
                        System.out.println("System failed.");
                    }
                    return;
                } else {
                    // Move focus deeper
                    currentFocus = child;
                }

            } else {
                // If incorrect, remove current focus node and go up one level
                Position<String> parent = currentNode.getParent();
                treeMain.remove(currentFocus);

                if (parent != null) {
                    currentFocus = maxChildren(treeMain, parent.getData()); // Pick best sibling or cousin
                } else {
                    currentFocus = null;
                }
            }

            iterations++; // Count question
        }

        // Final guess after reaching max attempts
        System.out.println("Is it a " + currentFocus + "? (true/false)");
        boolean answer = scanner.nextBoolean();
        if (answer) {
            System.out.println("System has guessed your thought");
        } else {
            System.out.println("System failed");
        }

        scanner.close();
    }

    /**
     * Helper function to return the child of a node with the most children.
     * Used to guide the guessing path to the most informative branch.
     */
    public static String maxChildren(Tree<String> treeMain, String parentData) {
        Position<String> parentNode = treeMain.search(treeMain.getRoot(), parentData);
        if (parentNode == null) return parentData;

        LinkedList<Position<String>> children = new LinkedList<>(parentNode.getChildren());
        Position<String> maxChild = null;
        int maxCount = -1;

        // Find child with the most descendants
        for (Position<String> child : children) {
            int childCount = child.getChildren().size();
            if (childCount > maxCount) {
                maxCount = childCount;
                maxChild = child;
            }
        }

        // Return most promising child or current if none found
        return (maxChild != null) ? maxChild.getData() : parentData;
    }
}
