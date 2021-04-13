/**
 * @author Emmanuel Cruz
 * @since Apr 12, 2021
 * Assignment 7 Comp Sci 2
 */

package lists;

import java.util.*;
import java.util.stream.Collectors;

/**
 * This program asks the user to create a shopping list
 * by adding items one by one to a list. The user will be prompted
 * if they would like to continue by Y/N.
 * After entering N they will be asked to enter an item to remove from the list.
 * The item would be remove and they would be asked if they would like to continue
 * removing items from the shopping list.
 */
public class Lists {

    public static void main(String[] args) {

        /**
         * Creates a list array and asks the user to enter an item to the shopping list
         */
        System.out.println("Welcome to My Shopping List!");
        System.out.print("Please enter an item: ");
        Scanner input = new Scanner(System.in);
        List<String> items = new ArrayList<String>();
        items.add(input.next());

        /**
         * Asks the user if they would like to enter more items to the shopping cart
         */
        System.out.print("Enter another Item Y/N?: ");
        String yesNo = input.next();

        /**
         * While the answer is Yes the program will keep asking to enter more items
         * It is also set to ignore the case for the Y/N
         */
        while (yesNo.equalsIgnoreCase("Y")) {
            System.out.print("Please enter an item: ");
            items.add(input.next());
            System.out.print("Enter another Item Y/N?: ");
            yesNo = input.next();
        }


        /**
         * printShoppingList method is invoked with parameter items
         * to print the items currently on the list
         */
        printShoppingList(items);

        /**
         * Creates a list of items to be removed from the shopping list.
         */
        List<String> removeItem = new ArrayList<String>();

        /**
         * enterItemToRemove method is invoked with parameters input, items, removeItem
         * to remove an item from the shopping list
         */
        enterItemToRemove(input, items, removeItem);

        /**
         * Prints the item that was removed from the list
         * Removes item from the main shopping list
         * Resets/clears the removeItems list so the previous item does not get used again
         * printShoppingList method is invoked to print the remaining items on the list
         */
        System.out.print(Collections.singletonList(removeItem).toString().replace("[", "").replace("]", "") + " has been removed \n");
        removeItems(items, removeItem);
        removeItem.clear();
        printShoppingList(items);


        /**
         * Error handling, if the size of items list is 0 it will display a messages stating
         * that there are no more items on the list and it will exit the program
         */
        if (items.size() == 0) {
            System.out.print("They're no more items to remove from your Shopping List. Goodbye!");
            System.exit(0);
        }


        /**
         * Prompts the user if the would like to remove more items.
         * While the answers is Yes
         * enterItemToRemove method is invoked to remove items from the shopping list
         * The item that is being remove is printed from the removeItems list
         * The removeItems list is cleared so that item is not reused
         * the printShoppingList method is invoked to print the updated shopping list
         */
        System.out.print("Would you like to remove another item Y/N?: ");
        String noYes = input.next();
        while (noYes.equalsIgnoreCase("Y")) {
            enterItemToRemove(input, items, removeItem);

            System.out.print(removeItem.toString().replace("[", "").replace("]", "") + " has been removed \n");
            removeItems(items, removeItem);
            removeItem.clear();
            printShoppingList(items);

            /**
             * Error handling, if the size of items list is 0 it will display a messages stating
             * that there are no more items on the list and it will exit the program.
             * Else, it will continue to ask if the user would like to keep removing items from the list until
             * They're no more items on the list or until the user enters N for no.
             */
            if (items.size() == 0) {
                System.out.print("They're no more items to remove from your Shopping List. Goodbye!");
                noYes = "N";
            } else {
                System.out.print("Would you like to remove another item Y/N?: ");
                noYes = input.next();
            }
        }
    }

    /**
     * Method that handles an input mismatch from the user. If the item is not found on the list
     * for it to be removed it will prompt the user that the item is not found
     * to please try again.
     * @param input      asks to enter them item to remove
     * @param items      the list of items in the shopping list of type list
     * @param removeItem the list of items to remove of type list
     */
    private static void enterItemToRemove(Scanner input, List<String> items, List<String> removeItem) {
        System.out.print("Enter an item to remove from the list: ");
        removeItem.add(input.next());
        while (!contains(items, removeItem)) {
            System.out.println("You did not enter an item on the list. Please try again.");
            System.out.print("Enter an item to remove from the list: ");
            removeItem.clear();
            removeItem.add(input.next());
        }
    }

    /**
     * Method that prints the shopping list at the type it is invoked.
     * @param list the list of items in the shopping list of type list
     */
    private static void printShoppingList(Collection<String> list) {
        System.out.println("Here is your Shopping List:");
        for (String item : list)
            System.out.println(item);
    }

    /**
     * Method to remove items from the shopping list. This method uses a lambda to ignore the case.
     * @param addedItems list of items that were added to the shopping list of type list
     * @param removedItems list of items that are to be removed from the shopping list of type list
     */
    private static void removeItems(Collection<String> addedItems, Collection<String> removedItems) {
        removedItems.forEach(removeItem -> addedItems.removeIf(removeItem::equalsIgnoreCase));

    }

    /**
     * Boolean method to return true or false if the item to be removed is contained
     * within the shopping list when the words for each list are set to lowercase.
     * @param addedItems list of items that were added to the shopping list of type list
     * @param removedItems list of items that are to be removed from the shopping list of type list
     * @return true if the item is contained on both addedItems and removedItems list.
     */
    private static boolean contains(Collection<String> addedItems, Collection<String> removedItems) {
        List<String> addedLowerItems = addedItems.stream().map(String::toLowerCase).collect(Collectors.toList());
        return removedItems.stream().anyMatch(removedItem -> addedLowerItems.contains(removedItem.toLowerCase()));
    }


}