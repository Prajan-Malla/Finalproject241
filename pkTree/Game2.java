package pkTree;

import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class Game2 {
    public static void startGame2(Tree<String> treeMain) {
        LinkedList<Position<String>> possibleQuestion = new LinkedList<>();
        Random random = new Random();
        Position<String> node = treeMain.getRoot();
        if (node == null) {
            System.out.println("The tree is empty!");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        boolean answer = true;

        // Retry the process until we can ask at least 5 questions
        while (possibleQuestion.size() < 6) {
            possibleQuestion.clear(); // Reset possible questions list
            node = treeMain.getRoot();
            possibleQuestion.add(node);

            // Traverse down the tree until we have at least 6 nodes in the path
            while (possibleQuestion.size() < 6) {
                LinkedList<Position<String>> children = node.getChildren();
                if (children == null || children.isEmpty()) {
                    break; // If there are no children, break out of the loop
                }

                int randomIndex = random.nextInt(children.size());
                node = children.get(randomIndex);
                possibleQuestion.add(node);
            }

            // Check if the tree depth is sufficient (6 nodes)
            if (possibleQuestion.size() >= 6) {
                break; // We can proceed with the game
            } else {
                System.out.println("Tree is not deep enough for 5 questions. Retrying...");
            }
        }

        // Ask 5 questions from leaf to root
        int maxQuestions = Math.min(5, possibleQuestion.size() - 1);
        for (int i = possibleQuestion.size() - 1; i > 0 && maxQuestions > 0 && answer; i--, maxQuestions--) {
            System.out.println("Is " + possibleQuestion.get(i).getData() +
                    " a subtype of " + possibleQuestion.get(i - 1).getData() + "? (true/false)");
            answer = scanner.nextBoolean();
        }

        if (answer) {
            System.out.println("You won");
        } else {
            System.out.println("You lose");
        }
    }
}
