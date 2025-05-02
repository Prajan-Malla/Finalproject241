package pkTree;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String path = "C:\\Users\\mallap894\\IdeaProjects\\Finalproject241\\pkTree\\ entities_hierarchy.txt";
        Tree<String> treeMain = new Tree<>();
        String stopper = "end";  // Initialize stopper to control loop exit
        int lopper = 0;  // To store the choice for which game to play
        Scanner scanner = new Scanner(System.in);

        while (stopper.equals("end")) {  // Use equals() to compare strings
            // Read the file and build the tree
            FileIO.readFile(path, treeMain);
            treeMain.displayTree();  // Display the tree

            // Ask the user if they want to play Game 1 or Game 2
            System.out.println("Do you want to play Game 1 or Game 2? (1/2): ");

            lopper = scanner.nextInt();  // Read the user's choice

            if (lopper == 1) {
                System.out.println("Do you want to play easy level or advance level  ;(easy->1  advance->2)");
                lopper = scanner.nextInt();
                if(lopper==1){
                    System.out.println("You selected Game 1 Easy mode.");
                    System.out.println("Think of the element in the tree");
                    treeMain.displayTree(); // to display for the user
                    Game1.startGame1Easy(treeMain);
                }
                else{
                    System.out.println("You selected Game 1 Advance level.");
                    System.out.println("Think of the element in the tree");
                    treeMain.displayTree(); // to display for the user
                    Game1.startGame1Hard(treeMain);
                }

            } else if (lopper == 2) {
                System.out.println("You selected Game 2.");
                System.out.println("Answer these 5 questions to win the game. ");
                Game2.startGame2(treeMain);  // Start Game 2
            } else {
                System.out.println("Invalid choice. Please select 1 or 2.");
                continue;  // Skip the rest of the loop if input is invalid
            }

            // Ask the user if they want to play again
            System.out.println("Do you want to play again? Type 'end' to play again or any other key to exit.");
            stopper = scanner.next();  // Take user input to decide whether to continue or stop
        }

        System.out.println("Thank you for playing!");
        scanner.close();  // Close the scanner to avoid resource leak
    }
}
