package objectserialization;

import java.io.*;
import java.util.*;
import java.lang.*;
import java.nio.file.*;

/**
 * Computer Science II
 * Week 6 Address Book with Object Serialization
 * @author Emmanuel Cruz
 * @since March 18, 2021
 * This program asks the user to enter first and last name
 * a telephone number and an email address when they're done entering as many contacts
 * and they like they can enter the escape sequence "EOF" Next they will be prompted
 * for the last name to search. All the data is stored as objects in a .ser file
 */


public class ObjectSerialization {
    private static ObjectOutputStream output;
    private static ObjectInputStream readInput;


    /**
     * openFileForWrite() opens the file so data can be written
     * writeToFile() writes data to the addresses.ser file as objects
     * closeWriteFile(); closes the file for later use
     * openFileForRead(); opens the file so the data can be read
     * readFile() reads the objects in the file, it is also surrounded by a try/catch file exception
     * closeReadFile() close the file for later use
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
     * This method creates the file addresses.ser or opens it if it already exists
     * it also catches three exceptions. If the user does not have permission
     * to open the file, if the file can not be found, or if there is an error opening the file
     */
    public static void openFileForWrite() {

        try {
            output = new ObjectOutputStream(Files.newOutputStream(Paths.get("addresses.ser")));
        } catch (SecurityException securityException) {
            System.err.println("Write Permission Denied. Exiting");
            System.exit(1);
        } catch (FileNotFoundException fileNotFoundException) {
            System.err.println("File Not Found. Exiting");
            System.exit(1);
        } catch (IOException ioException) {
            System.err.println("Error opening file. Exiting.");
            System.exit(1);
        }

    }//end of openFileForWrite

    /**
     * This method writes to the file
     * It prompts the user to enter the first name, last name,
     * telephone number, and email address. It also asks to enter the escape
     * sequence EOF once they're done. It also catches two exceptions and writes
     * the users data to the .ser file.
     */
    public static void writeToFile() {
        Scanner writeInput = new Scanner(System.in);
        System.out.printf("Enter First Name, Last Name, Telephone Number, Email Address%n");
        System.out.printf("Enter EOF When Finished Entering Data%n");

        String s = "EOF";

        // (writeInput.hasNext());

        while (writeInput.hasNext() != writeInput.hasNext(s)) {
            try {
                People addRecord = new People(writeInput.next(), writeInput.next(), writeInput.next(), writeInput.next());
                output.writeObject(addRecord);
            } catch (NoSuchElementException elementException) {
                System.err.println("Invalid input. Please try again.");

            } catch (IOException ioException) {
                System.err.println("Error writing to file. Exiting");
                break;
            }
        }
    }//end of writeToFile

    /**
     * The closeWriteFile method closes the file
     * so it can be accessed at later time
     */
    public static void closeWriteFile() {

        try {
            if (output != null)
                output.close();
        } catch (IOException ioException) {
            System.err.println("Error closing file. Exiting");
        }


    }//end of closeWriteFile

    /**
     * This method opens the file so it can be read
     * it also includes an execution to ensure that the file can be opened
     * it will display an error if it can not
     */
    public static void openFileForRead() {
        try {
            readInput = new ObjectInputStream(Files.newInputStream(Paths.get("addresses.ser")));
        } catch (IOException ioException) {
            System.out.println("Error Opening File");
            System.exit(1);
        }
    }//end of openFileForRead

    /**
     * This method reads the addresses.ser file and asks the user to enter
     * a last name to search for in the address book
     *
     * @throws NameException custom exception that throws an error message if a last name is not found in the address book.
     *                       <p>
     *                       Here I created a variable called goodNameMatches to keep track of the times a last name matches with the name user is searching for
     *                       if there is a match the customer exception will not be thrown.
     *                       <p>
     *                       This method all prints all of the matches in the lastname, firstname, phone, email address, format using StringBuilder.
     * @throws EOFException  is used to end the infinite while loop when there are no more objects (data) to be read in the
     *                       addresses.ser file.
     */
    public static void readFile() throws NameException {
        System.out.print("Please Enter a Last Name to Search: ");
        Scanner inputLastName = new Scanner(System.in);

        String lastName = inputLastName.next();
        //String dataFromFile;

        int goodNameMatches = 0;

        while (true) {
            try {
                People readRecord = (People) readInput.readObject();
                if (readRecord.getLastName().equalsIgnoreCase(lastName)) {
                    goodNameMatches++;
                    StringBuilder output = new StringBuilder();
                    output.append(readRecord.getLastName()).append(" ").append(readRecord.getFirstName()).append(" ").append(readRecord.getPhone()).append(" ").append(readRecord.getEmail());
                    System.out.printf("%s%n", output);
                }
            } catch (EOFException e) {
                // exit while loop: when there is no more objects to read
                break;
            } catch (IOException | ClassNotFoundException ioException) {
                ioException.printStackTrace();
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
        try {
            if (readInput != null)
                readInput.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}//end of closeReadFile


