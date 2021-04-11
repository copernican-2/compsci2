package averagecalculator;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;


/**
 * Computer Science II Midterm
 * @author Emmanuel Cruz
 * @since March 16 2021
 */

/**
 * This is a java program that will ask for 10 integers
 * then it will calculate the average.
 */
public class AverageCalculator {

    /**
     * Formatter to write and output date to a text file
     * Scanner to read the data from the text file
     */
    private static Formatter output;
    private static Scanner readInput;

    /**
     * openFileForWrite() opens the file to write data
     * writeToFile() writes data to the text file
     * closeWriteFile() close the file so it can be used at a later time
     * openFileForRead() opens the file so it can be read
     * readFile() reads and processes the data
     * closeReadFile() closes the file so it can be used at a later time.
     */
    public static void main(String[] args) {
        openFileForWrite();
        writeToFile();
        closeWriteFile();
        openFileForRead();
        readFile();
        closeReadFile();
    }//end of main

    /**
     * Creates a text file called numbers.txt
     * @throws SecurityException     if the file is not open or does not have permission
     * @throws FileNotFoundException if the file is not found
     */
    public static void openFileForWrite() {

        try {
            output = new Formatter("numbers.txt");
        } catch (SecurityException securityException) {
            System.err.println("Write Permission Denied. Exiting");
            System.exit(1);
        } catch (FileNotFoundException fileNotFoundException) {
            System.err.println("File Not Found. Exiting");
            System.exit(1);
        }

    }//end of openFileForWrite

    /**
     * Asks the user to input 10 integers
     * Includes error checking to ensure the user only enter integers
     *
     * @throws InputMismatchException   if the integer is invalid
     * @throws FormatterClosedException if there is an error writing to the file
     * The integers are place in an array and then written into a text file separated by commas
     *I didn't utilize the NoSuchElementException try/catch since I already utilized the InputMismatchException
     */
    public static void writeToFile() {
        int[] array = new int[10];
        boolean validInteger = true;
        Scanner input = new Scanner(System.in);

        System.out.println("Enter 10 integers");
        for (int i = 0; i < array.length; i++) {
            do {
                try {
                    array[i] = input.nextInt();
                    validInteger = false;

                } catch (InputMismatchException inputMismatchException) {
                    System.out.println("You did not enter a valid integer. Please try again.");
                    i--;
                    input.nextLine();
                }
            } while (validInteger);
        }
        for (int i = 0; i < array.length; i++) {
            try {
                output.format("%d%s", array[i], ",");
            } catch (FormatterClosedException formatterClosedException) {
                System.err.println("Error writing to file. Exiting");
            }

        }

    }//end of writeToFile

    /**
     * This closes the file after is has writen for later use.
     */
    public static void closeWriteFile() {
        if (output != null)
            output.close();

    }//end of closeWriteFile

    /**
     *Opens the file numbers.txt so it can be read
     * @throws IOException if there is an error opening the file
     */
    public static void openFileForRead() {
        try {
            readInput = new Scanner(Paths.get("numbers.txt"));
        } catch (IOException ioException) {
            System.err.println("Error Opening File. Exiting.");
            System.exit(1);
        }
    }//end of openFileForRead

    /**
     *Here the file is read and the data is processed
     * @throws NoSuchElementException if the file does not exist
     * @throws IllegalStateException if the file can not be read. It could be corrupted or in use.
     * Here the 10 integers which are in a string format are split by the comma delimiter.
     * The numbers are then parsed (converted) into integers.
     * All of the numbers are added together and the divided by 10.
     * The average is then outputted
     */
    public static void readFile() {
        System.out.print("The Average of your 10 Integers is: ");
        String numbersFromFile;

        try {
            while (readInput.hasNextLine()) {
                int totalSum = 0;
                numbersFromFile = readInput.nextLine();
                String[] splitData = numbersFromFile.split(",");
                for (String converter : splitData) {
                    int convertedNumber = Integer.parseInt(converter);
                    totalSum += convertedNumber;
                }
                int average = totalSum / 10;
                System.out.print(average);
            }
        }catch(NoSuchElementException noSuchElementException){
            System.err.println("File improperly formed. Exiting");
            System.exit(1);
        }catch(IllegalStateException stateException){
            System.err.println("Error reading from file. Exiting");
            System.exit(1);
        }

    }//end of readFile

    /**
     *This closes the file after it is read for later use
     */
    public static void closeReadFile() {
        if (readInput != null)
            readInput.close();

    }//end of closeReadFile

}//end of AverageCalculator
