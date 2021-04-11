package objectserialization;

import java.io.Serializable;
/**
 * Computer Science II
 * Week 6 Object Serialization
 * @author Emmanuel Cruz
 * @since March 18, 2021
 */

/**
 *Class that creates the People object/constructor.
 * firstName collects the first name inclues getters and setters
 * lastName collects the last name includes getters and setters
 * phone collects the phone number includes getters and setters
 * email collects the email addresses includes getters and setters
 */
public class People implements Serializable {
    private String firstName;
    private String lastName;
    private String phone;
    private String email;

    /**
     * People Constructor
     * @param firstName for the first name of the person
     * @param lastName for the last name of the person
     * @param phone the persons phone number
     * @param email the perons email address
     */
    public People(String firstName, String lastName, String phone, String email){
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
    }

    /**
     * getFristName
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * setFirstName
     * @param firstName sets the first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * getLastName
     * @return returns the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * setLastName
     * @param lastName sets the lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * getPhone
     * @return returns the phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * setPhone
     * @param phone sets the phone number
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * getEmail
     * @return returns the email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * setEmail
     * @param email sets the email address
     */
    public void setEmail(String email) {
        this.email = email;
    }
}