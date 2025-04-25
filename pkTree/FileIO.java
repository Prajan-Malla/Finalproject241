package pkTree;

import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;

public class FileIO {

    public static void main(String[] args) {
        String path = "/Users/prajanmalla/InteeliJ Projects/final-project/src/pkTree/ entities_hierarchy.txt";


        readFile(path);

    }

    public static void readFile(String path) {
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader(path));
            String line = reader.readLine();

            while (line != null) { // we keep looping until we reach the last line
                System.out.println(line);
                System.out.println("--"); // separator line
                line = reader.readLine(); 		    // read next line
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

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

