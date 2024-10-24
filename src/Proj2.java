/********************************************************************
 * @file: Proj2.java
 * @description: This program implements the main method to run the project, adding the Dog Data into arraylists and sorting shuffling the arraylist to insert and search in BSTs and AVL trees in order to compare their runtimes and running rates.
 * @author: June Bernstein
 * @date: October 23, 2024
 ******************************************************************/

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class Proj2 {
    public static void main(String[] args) throws IOException {
        // Use command line arguments to specify the input file
        if (args.length != 2) {
            System.err.println("Usage: java TestAvl <input file> <number of lines>");
            System.exit(1);
        }

        String inputFileName = args[0];
        int numLines = Integer.parseInt(args[1]);

        // For file input
        FileInputStream inputFileNameStream = null;
        Scanner inputFileNameScanner = null;

        // Open the input file
        inputFileNameStream = new FileInputStream(inputFileName);
        inputFileNameScanner = new Scanner(inputFileNameStream);

        // ignore first line
        inputFileNameScanner.nextLine();

        // FINISH ME
        //implements arraylist and adds all the data for each dog data line
        ArrayList<DogData> dogArrayList = new ArrayList<>();
        Scanner scnr = new Scanner(new File("./src/dogbreeds.csv"));
        Scanner inputscnr = new Scanner(inputFileNameStream);
        //remove header in file
        if (scnr.hasNext()) {
            scnr.nextLine();
        }
        while (scnr.hasNext() && dogArrayList.size()<numLines) {
            String line = scnr.nextLine();
            String[] parts = line.split(","); //split the string into multiple parts
            //Check if the dogs match
            //if (parts[0].equals("Breed")) {
            //Create new DogData object
            DogData data = new DogData(
                    parts[0], //breed
                    parts[1], //originCountry
                    parts[2], //furColor
                    parts[3], //height
                    parts[4], //eyeColor
                    parts[5]  //longevity
            );
            dogArrayList.add(data);//add the data onto the ArrayList
            //}
        }


        File file = new File("./output.txt");
        //performs collections.sort on arraylsit
        Collections.sort(dogArrayList);

        //implements BST for sorted
        BST<DogData> sortedBST = new BST<>();
        //takes the time and adds the arraylist to the BST
        long startTime = System.nanoTime();
        for (DogData data : dogArrayList) {
            sortedBST.insert(data);
        }
        long endTime = System.nanoTime();
        writeToFile("Inserting sorted BST took:  " + (endTime - startTime) + " ns", "./output.txt");

        //implements AVL for sorted
        AvlTree<DogData> sortedAvlTree = new AvlTree<>();
        //takes the time and adds the arraylist to the AVL
        startTime = System.nanoTime();
        for (DogData data : dogArrayList) {
            sortedAvlTree.insert(data);
        }
        endTime = System.nanoTime();
        writeToFile("Inserting sorted AVL tree:  " + (endTime - startTime) + " ns", "./output.txt");

        //search sortedBST
        startTime = System.nanoTime();
        for (DogData data : dogArrayList) {
            sortedBST.search(data);
        }
        endTime = System.nanoTime();
        writeToFile("Searching sorted BST took:  " + (endTime - startTime) + " ns", "./output.txt");

        //search sortedAvlTree
        startTime = System.nanoTime();
        for (DogData data : dogArrayList) {
            sortedAvlTree.contains(data);
        }
        endTime = System.nanoTime();
        writeToFile("Searching sorted AVL took:  " + (endTime - startTime) + " ns", "./output.txt");


        //shuffle arraylist
        Collections.shuffle(dogArrayList);
        //implement BST for shuffled
        startTime = System.nanoTime();
        BST<DogData> randomBST = new BST<>();
        for (DogData data : dogArrayList) {
            randomBST.insert(data);
        }
        endTime = System.nanoTime();
        writeToFile("Inserting shuffled BST took:  " + (endTime - startTime) + " ns", "./output.txt");


        //implement AVL for shuffled
        startTime = System.nanoTime();
        AvlTree<DogData> randomAvlTree = new AvlTree<>();
        for (DogData data : dogArrayList) {
            randomAvlTree.insert(data);
        }
        endTime = System.nanoTime();
        writeToFile("Inserting shuffled AVL tree:  " + (endTime - startTime) + " ns", "./output.txt");

        //search for shuffled BST
        startTime = System.nanoTime();
        for (DogData data : dogArrayList) {
            randomBST.search(data);
        }
        endTime = System.nanoTime();
        writeToFile("Searching shuffled BST took:  " + (endTime - startTime) + " ns", "./output.txt");

        //search for shuffled AVL
        startTime = System.nanoTime();
        for (DogData data : dogArrayList) {
            randomAvlTree.contains(data);
        }
        endTime = System.nanoTime();
        writeToFile("Searching shuffled AVL took:  " + (endTime - startTime) + " ns", "./output.txt");

        writeToFile("", "./output.txt");
    }

    //writeToFile is used to write the nanoseconds onto the output file
    public static void writeToFile(String content, String filePath) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath, true));
            boolean emptyFile = new File(filePath).length() == 0;
            if (!emptyFile) {
                bufferedWriter.newLine();
            }
            bufferedWriter.write(content);
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/*
I used the dog_breeds.csv file and I removed the columns that contained fur color, character traits, and common health problems
I did this because these files had a variety of different string lengths seperated by commas
 */

