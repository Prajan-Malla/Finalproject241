package pkTree;

import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class Game2 {

    /**
     * Starts a game where the system randomly navigates the tree and asks a series of questions.
     * The game continues until 5 questions can be asked, and the user responds with true/false answers.
     */
    public static void startGame2(Tree<String> treeMain) {

        LinkedList<Position<String>> possibleQuestion = new LinkedList<>(); // List to hold the nodes for questioning
        Random random = new Random(); // Randomizer to choose child nodes
        Position<String> node = treeMain.getRoot(); // Start from the root of the tree

        // Check if the tree is empty
        if (node == null) {
            System.out.println("The tree is empty!"); // Exit if the tree has no data
            return;
        }

        Scanner scanner = new Scanner(System.in); // Input scanner to get user responses
        boolean answer = true; // Track if the user has answered all questions correctly

        // Retry the process until we can ask at least 5 questions (depth of the tree >= 6)
        while (possibleQuestion.size() < 6) {
            possibleQuestion.clear(); // Reset the list of possible questions
            node = treeMain.getRoot(); // Start over from the root node
            possibleQuestion.add(node); // Add root as the first question node

            // Traverse down the tree to collect at least 6 nodes in the path
            while (possibleQuestion.size() < 6) {
                LinkedList<Position<String>> children = node.getChildren(); // Get the children of the current node

                // If the current node has no children, stop traversing
                if (children == null || children.isEmpty()) {
                    break;
                }

                // Randomly pick a child node to move down the tree
                int randomIndex = random.nextInt(children.size());
                node = children.get(randomIndex);
                possibleQuestion.add(node); // Add this node to the list of questions
            }

            // If we gathered 6 nodes, we're ready to proceed with the game
            if (possibleQuestion.size() >= 6) {
                break;
            } else {
                // If not, the tree isn't deep enough — retry
                System.out.println("Tree is not deep enough for 5 questions. Retrying...");
            }
        }

        // Ask 5 questions from the leaf to the root node
        int maxQuestions = Math.min(5, possibleQuestion.size() - 1); // Ensure we ask at most 5 questions
        for (int i = possibleQuestion.size() - 1; i > 0 && maxQuestions > 0 && answer; i--, maxQuestions--) {
            // Ask if the current node is a subtype of its parent
            System.out.println("Is " + possibleQuestion.get(i).getData() +
                    " a subtype of " + possibleQuestion.get(i - 1).getData() + "? (true/false)");
            answer = scanner.nextBoolean(); // Get user's answer (true/false)
        }

        // Output result based on user's answers
        if (answer) {
            System.out.println("You won"); // User answered all questions correctly
        } else {
            System.out.println("You lose"); // User made an incorrect answer
        }
    }
}
