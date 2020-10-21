package Homework_5;

public class NotADirectoryException extends Exception {

    /**
     * Default serial version UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor that creates a NotADirectoryException using s as the message
     * 
     * @param s the message to add to the Exception
     * 
     *          postCondition: This NotADirectoryException is instatiated with a
     *          valid message.
     */

    public NotADirectoryException(String s) {
        super(s);
    }
}