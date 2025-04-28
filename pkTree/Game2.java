package pkTree;

import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class Game2 {
    public static void startGame2(Tree<String> treeMain){
        LinkedList<Position<String>> possibleQuestion = new LinkedList<>(); // Save the path nodes here

        Position<String> node = treeMain.getRoot(); // Start from root
        if (node == null) {
            System.out.println("The tree is empty!");
            return;
        }

        possibleQuestion.add(node); // Add the root first
        LinkedList<Position<String>> children = node.getChildren();
        Random random = new Random();

        while (!children.isEmpty()) {
            int randomIndex = random.nextInt(children.size());
            node = children.get(randomIndex); // Move to that child

            possibleQuestion.add(node); // Save this node into the path
            children = node.getChildren(); // Update children list
        }
        // node.getData());
        Scanner scanner = new Scanner(System.in);
        int checker=0;
        boolean answer=true;
        int linklistsSize=possibleQuestion.size();
        while(checker!=5 && answer){
            System.out.println("Is "+possibleQuestion.get(linklistsSize)+" a subtype of "+possibleQuestion.get(linklistsSize-1));
            answer = scanner.nextBoolean();
            linklistsSize--;
            checker++;
        }
        if(answer){
            System.out.println("You won");
        }
        else{
            System.out.println("You loose");
        }
    }
}
