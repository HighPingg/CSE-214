package Homework_5;

public class FullDirectoryException extends Exception {

    /**
     * Default serial version UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor that creates a FullDirectoryException using s as the message
     * 
     * @param s the message to add to the Exception
     * 
     *          postCondition: This FullDirectoryException is instatiated with a
     *          valid message.
     */

    public FullDirectoryException(String s) {
        super(s);
    }
}