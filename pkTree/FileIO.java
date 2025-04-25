package pkTree;

import java.util.*;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;

public class FileIO {


    public static void readFile(String path,Tree tree) {
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader(path));
            String line = reader.readLine();

            while (line != null) { // we keep looping until we reach the last line

                if (line.contains(":")) {
                    String[] parts = line.split(":");
                    String parent = parts[0].trim();
                    String[] children = parts[1].split(",");

                    for (int i=0;i<(children.length);i++) {
                        insertTreeData(parent, children[i].trim(),tree);
                    }
                }

                line = reader.readLine(); 		    // read next line
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void insertTreeData(String parent, String child,Tree tree){
        tree.insert(parent,child);
    }

    // insert the tree funtion
/*
   public static void writeFile(String path) {
        System.out.println("begg of writing.");
        String inputUser;
        try {
            FileWriter myWriter = new FileWriter(path);

            Scanner scanner = new Scanner(System.in);

            System.out.println("New line?");

            inputUser = scanner.nextLine();
            myWriter.write(inputUser);

            scanner.close(); // Close the scanner when finished
            myWriter.close();
            System.out.println("End of writing.");
        } catch (IOException e) {
            System.out.println("Error--------------");
            e.printStackTrace();
        }
    }*/

}

