package Homework_4;

public class IllegalInputException extends Exception {

    /**
     * Default serial version UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor that creates a IllegalInputException using s as the message
     * 
     * @param s the message to add to the Exception
     * 
     *          postCondition: This IllegalInputException is instatiated with a
     *          valid message.
     */

    public IllegalInputException(String s) {
        super(s);
    }
}
