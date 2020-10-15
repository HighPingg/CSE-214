package Homework_4;

/**
 * Custom Exception class used for illegal user inputs. Takes in a message and
 * pushes it to the Exception constructor.
 * 
 * @author <b>Name</b>: Vincent Zheng
 *      <li><b>Solar_ID:</b> 113469839</li>
 *      <li><b>Email:</b> vincent.zheng@stonybrook.edu</li>
 *      <li><b>Assignment:</b> 4</li>
 *      <li><b>Course</b>: CSE 214</li>
 *      <li><b>Recitation</b>: R02</li>
 *      <li><b>TA</b>: William Simunek</li>
 */
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
