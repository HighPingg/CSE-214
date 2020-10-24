package Homework_5;

/**
 * Exception class used for when a path to a directory leads to a file or when
 * the directory cannot be located in the tree.
 * 
 * @author <b>Name</b>: Vincent Zheng
 *         <li><b>Solar_ID:</b> 113469839</li>
 *         <li><b>Email:</b> vincent.zheng@stonybrook.edu</li>
 *         <li><b>Assignment:</b> 5</li>
 *         <li><b>Course</b>: CSE 214</li>
 *         <li><b>Recitation</b>: R02</li>
 *         <li><b>TA</b>: William Simunek</li>
 */
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