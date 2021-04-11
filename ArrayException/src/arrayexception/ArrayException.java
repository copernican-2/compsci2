package arrayexception;

import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * Computer Science II Programming Assignment #2
 * @author Emmanuel Cruz
 * @since 1-24-2021
 */

public class ArrayException {

    /**
     * @throws NumberNotFound exception when a value in the array in not found
     */
    public static void main(String[] args) throws NumberNotFound {
        Scanner input = new Scanner(System.in);

        // create an array to search
        int[] array = new int[10];

        int response;
        int index;
        int value;
        boolean validInteger = true;
        boolean validIndex = true;
        boolean valueFound = true;

        // Prompt the user to input 10 integers and store them in the array
        System.out.println("Enter 10 integers");
        // Fill the array with user input.
        /**
         * for loop that will ask the user for an input
         * Inside there is a try and catch that will check for InputMismatchExceptions
         * This part of the code will keep executing until all elements of the array are entered correctly
         */
        for (int i = 0; i < array.length; i++) {
            do {
                try {

                    System.out.printf("Enter an integer for index# %d: ", i);
                    array[i] = input.nextInt();
                    validInteger = false;

                    /**
                     * When a user enters an invalid integer they will receive an exception error.
                     * In order to ask for the element in the same position on the array i had to be subtracted.
                     */
                } catch (InputMismatchException inputMismatchException) {
                    System.out.println("You did not enter a valid integer. Please try again.");
                    i--;
                    input.nextLine();
                }
            } while (validInteger);
        }

        /**
         *Prompts the user if they would like to search the array by index or a value
         */
        // Prompt the user to choose between searching by value or searching by index.
        System.out.printf("%n1)Search by index \n2)Search by value\n");
        response = input.nextInt();

        /**
         * Searching the array by index checks for two exceptions.
         * 1. ArrayIndexOutOfBoundsException, if the user selected an index that not within the bounds of the array.
         * 2. If the user does not enter a valid integer.
         */
        if (response == 1) {
            do {
                try {
                    //Search by index
                    //user inputs index
                    System.out.print("Enter an Index: ");
                    index = input.nextInt();

                    //Output the value stored at that index
                    System.out.printf("Integer at index %d: %d", index, array[index]);
                    validIndex = false;

                    /**
                     * When a value is entered not within the bounds of the array an arrayIndexOutOfBoundsException is displayed
                     */
                } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
                    System.out.println("You did not enter a valid index therefore out of bounds. Please try again.");
                    input.nextLine();
                    /**
                     * When an non-integer value is entered a inputMismatchException error is thrown and will prompt the user for another value.
                     */
                } catch (InputMismatchException inputMismatchException) {
                    System.out.println("You did not enter an integer. Please try again.");
                    input.nextLine();
                }
            } while (validIndex);
        }

        /**
         * Searching by Value
         * If a user would like to search by value, and they enter a non-integer value, they will see a inputMismatchException
         * exception and will be asked to enter a valid integer.
         * If the number they entered is not found, they will see a custom NumberNotFound exception and the program will exit
         */
        if (response == 2) {
            //Search by value
            do {
                try {
                    //user inputs value
                    System.out.print("Enter a value: ");
                    value = input.nextInt();

                    //for loop searches for value in the array
                    /**
                     * for loop that will check all the elements in the array to see if the value entered can be found
                     */
                    for (int i = 0; i < array.length; i++) {
                        //If the value is found, index and value will be displayed.
                        if (array[i] == value) {
                            System.out.printf("%nValue %d is found at index %d", value, i);
                            valueFound = false;
                        }
                    }
                    /**
                     * NumberNotFound exception is thrown when the value can not be found
                     */
                    if (valueFound) {
                        throw new NumberNotFound("Number not found.");
                    }
                    /**
                     * When an non-integer value is entered a inputMismatchException error is thrown and will prompt the user for another value.
                     */
                } catch (InputMismatchException inputMismatchException) {
                    System.out.println("You did not enter an a number, please try again.");
                    input.nextLine();
                }
            } while (valueFound);
        }
        input.close();
    }
}
