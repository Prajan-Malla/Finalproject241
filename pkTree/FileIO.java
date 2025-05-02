package pkTree;

import java.util.*;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;

public class FileIO {

    // Reads a file and builds a tree from its contents
    public static void readFile(String path, Tree<String> tree) {
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader(path));
            String line = reader.readLine();

            // Loop through each line of the file
            while (line != null) {

                // Check for valid line format
                if (line.contains(":")) {
                    String[] parts = line.split(":");
                    String parent = parts[0].trim();
                    String[] children = parts[1].split(",");

                    // Insert each child into the tree
                    for (int i = 0; i < children.length; i++) {
                        String childrenTrimed = children[i].trim();
                        insertTreeData(parent.toLowerCase(), childrenTrimed.toLowerCase(), tree);
                    }
                }

                line = reader.readLine(); // Move to next line
            }

            reader.close(); // Close the reader
        } catch (IOException e) {
            e.printStackTrace(); // Handle errors
        }
    }

    // Helper method to insert parent-child relationship into the tree
    public static void insertTreeData(String parent, String child, Tree<String> tree) {
        tree.insert(parent, child);
    }

    /*
    // Method to write user input to a file (currently commented out)
    public static void writeFile(String path) {
        System.out.println("begg of writing.");
        String inputUser;
        try {
            FileWriter myWriter = new FileWriter(path);
            Scanner scanner = new Scanner(System.in);

            System.out.println("New line?");
            inputUser = scanner.nextLine(); // Get user input
            myWriter.write(inputUser);      // Write to file

            scanner.close(); // Close scanner
            myWriter.close(); // Close writer
            System.out.println("End of writing.");
        } catch (IOException e) {
            System.out.println("Error--------------");
            e.printStackTrace(); // Handle errors
        }
    }
    */

}
