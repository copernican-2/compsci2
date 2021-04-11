package addressbook;

import java.io.*;
import java.util.*;
import java.lang.*;
import java.nio.file.*;

/**
 * Computer Science II
 * Week 5 Address Book
 *
 * @author Emmanuel Cruz
 * @since March 10, 2021
 * @version 2
 */


public class AddressBook {
    /**
     * Formatter is used write to a file
     * Scanner is user to read from a file
     */
    private static Formatter output;
    private static Scanner readInput;


    /**
     * This program asks the user to enter first and last name
     * a telephone number and an email address
     * when they're done enter as many contacts and they like they
     * can enter the escape sequence "EOF" Next they will be prompted
     * for the last name to search.
     */
    public static void main(String[] args) {
        openFileForWrite();
        writeToFile();
        closeWriteFile();
        openFileForRead();
        try {
            readFile();
        } catch (NameException nameException) {
            System.err.print("The Last Name You Have Entered Does Not Exist");
        }
        closeReadFile();
    }//end of main

    /**
     * This method creates the file addresses.txt or opens it if it already exists
     * it also catches two exceptions. If the user does not have permission
     * to open the file or if the file can not be found
     */
    public static void openFileForWrite() {

        try {
            output = new Formatter("addresses.txt");
        } catch (SecurityException securityException) {
            System.err.println("Write Permission Denied.");
            System.exit(1);
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("File Not Found.");
            System.exit(1);
        }

    }//end of openFileForWrite

    /**
     * This method writes to the file
     * It prompts the user to enter the first name last name
     * telephone number and email address. it also asks to enter the escape
     * sequence EOF once they're done. It also catches two exceptions and writes
     * to the user inputted data to the text file.
     */
    public static void writeToFile() {
        Scanner writeInput = new Scanner(System.in);
        System.out.printf("Enter First Name, Last Name, Telephone Number, Email Address%n");
        System.out.printf("Enter EOF When Finished Entering Data%n");

        String s = "EOF";

        while (writeInput.hasNext() != writeInput.hasNext(s)) {
            try {
                output.format("%s %s %s %s%n", writeInput.next(), writeInput.next(), writeInput.next(), writeInput.next());
            } catch (NoSuchElementException elementException) {
                System.err.println("Invalid input. Please try again.");

            } catch (SecurityException | FormatterClosedException e) {
                e.printStackTrace();
            }
        }
    }//end of writeToFile

    /**
     * The closeWriteFile method closes the file
     * so it can be accessed at later time
     */
    public static void closeWriteFile() {
        if (output != null) output.close();

    }//end of closeWriteFile

    /**
     * This method opens the file so it can be read
     * it also includes an execution to ensure that the file can be opened
     * it will display an error if it can not
     */
    public static void openFileForRead() {
        try {
            readInput = new Scanner(Paths.get("addresses.txt"));
        } catch (IOException ioException) {
            System.out.println("Error Opening File");
            System.exit(1);
        }
    }//end of openFileForRead

    /**
     * This method reads the file and asks the user to enter
     * a last name to search for in the address book
     *
     * @throws NameException if a last name that is entered does not match any of the last name in the address book
     *                       I error checked this by adding a counter for whenever there is a match for a last name. If there isn't the
     *                       counter will be 0 and it will throw the exception. If the counter is higher that 0 it means it found a match
     *                       and the exception will not be through
     *                       <p>
     *                       The method takes each line by line splits it by the comma delimiter and searches for a match on the last name
     *                       only because I have created a variable lastNameOnly from the array at index 1 where the last name is stored
     *                       so the program doesn't have to search the entire string thus avoiding an error
     *                       such as a person with the firstname such as Johnson appearing in the results when searching for the lastname Johnson
     */
    public static void readFile() throws NameException {
        System.out.print("Please Enter a Last Name to Search: ");
        Scanner inputLastName = new Scanner(System.in);

        String lastName = inputLastName.next();
        String dataFromFile;


        int goodNameMatches = 0;
        while (readInput.hasNextLine()) {

            dataFromFile = readInput.nextLine();

            String[] splitData = dataFromFile.split(" ");

            String firstNameOnly = splitData[0];
            String lastNameOnly = splitData[1];
            String phoneOnly = splitData[2];
            String emailAddyOnly = splitData[3];


            if (lastName.equalsIgnoreCase(lastNameOnly)) {
                goodNameMatches++;
                StringBuilder output = new StringBuilder();
                output.append(lastNameOnly).append(" ").append(firstNameOnly).append(" ").append(phoneOnly).append(" ").append(emailAddyOnly);
                System.out.printf("%s%n", output);
            }
        }

        try {
            if (goodNameMatches == 0) {
                throw new NameException("The Last Name You Have Entered Does Not Exist.");
            }
        } catch (NameException nameException) {
            nameException.printStackTrace();
        }


    }//end of readFile

    /**
     * This method closes the file after it has been read
     */
    public static void closeReadFile() {
        if (readInput != null) {
            readInput.close();
        }
    }//end of closeReadFile
}

