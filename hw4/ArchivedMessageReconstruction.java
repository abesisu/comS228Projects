package edu.iastate.cs228.hw4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This is the main class for Homework 4.
 * This class reads in the input file and calls the necessary functions to deliver the correct output.
 * @author Abe Scheideman
 */
public class ArchivedMessageReconstruction {
    public static void main(String[] args) throws FileNotFoundException { //do something fancy with a try catch block for FileNotFoundException
        //First set up scanners, strings, and files to read the correct arch file.
        Scanner filenameInput = new Scanner(System.in);
        Scanner archFileScanner = null;
        String fileToDecode;
        boolean goodFile = false;
        while (!goodFile) {
            System.out.print("Please enter filename to decode: ");
            fileToDecode = filenameInput.next();
            File archFile = new File(fileToDecode);
            try {
                archFileScanner = new Scanner(archFile);
                goodFile = true;
            } catch (FileNotFoundException e) {
                System.out.println("INVALID FILE");
            }
        }

        //Scan each line of the file and store it as a string to be parsed
        //for building the tree and decoding the console output.

        String firstLine = "";
        String secondLine = "";
        String thirdLine = "";
        int currLine = 1;
        while (archFileScanner.hasNextLine()) {
            switch (currLine) {
                case 1: firstLine = archFileScanner.nextLine(); break;
                case 2: secondLine = archFileScanner.nextLine(); break;
                case 3: thirdLine = archFileScanner.nextLine();
                default: break;
            }
            currLine++;
        }
        archFileScanner.close();

        //After storing each line as a string, build the character tree.
        //If the new line character is part of the tree,
        //append it and the first two lines to use as the encoding scheme.
        //The encoding scheme will either be in the first and/or second lines so the third line must be checked.
        try {
            MsgTree encodedTree;
            if (thirdLine.length() == 0) {
                encodedTree = new MsgTree(firstLine);
            } else {
                encodedTree = new MsgTree(firstLine + '\n' + secondLine);
            }
            //Print out the characters and their encoding scheme.
            System.out.println("character  code");
            System.out.println("-------------------------");
            //A NullPointerException may be thrown
            encodedTree.printCodes(encodedTree, "");

            //Decode the line that holds the binary encoded message and print the message.
            System.out.println("MESSAGE:");
            if (thirdLine.length() == 0) {
                encodedTree.decode(encodedTree, secondLine);
            } else {
                encodedTree.decode(encodedTree, thirdLine);
            }

            //For extra credit, print the specified statistics.
            encodedTree.printStatistics();

        } catch (IndexOutOfBoundsException e) {
            System.out.println("ERROR: Encoding scheme of the given file is incorrect");
        }
    }
}
