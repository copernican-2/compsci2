package addressbook;

/**
 * Computer Science II
 * Week 5 Address Book
 * @author Emmanuel Cruz
 * @since March 10, 2021
 */
public class NameException extends Exception{
    /**
     * This is a custom exception so that it can display
     * a message with there is no match when searching
     * for a last name
     * @param message displays a message when there isn't a match for the last name
     */
    public NameException(String message){

        super(message);
    }

}
