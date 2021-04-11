package arrayexception;

/**
 * Computer Science - Assignment 2
 * @author Emmanuel Cruz
 * @since 1-24-2021
 */

/**
 * NumberNotFound exception that will be thrown when a value can not be found
 */
public class NumberNotFound extends Exception{
    /**
     * @param errorMessage the message that will displayed when the value is not found
     */
    NumberNotFound(String errorMessage){
        System.out.println(errorMessage);
    }
}
