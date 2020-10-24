package Homework_5;

/**
 * Exception class used when a <code>DirectoryNode</code> has filled all of its
 * <code>children</code> and cannot add another child.
 * 
 * @author <b>Name</b>: Vincent Zheng
 *         <li><b>Solar_ID:</b> 113469839</li>
 *         <li><b>Email:</b> vincent.zheng@stonybrook.edu</li>
 *         <li><b>Assignment:</b> 5</li>
 *         <li><b>Course</b>: CSE 214</li>
 *         <li><b>Recitation</b>: R02</li>
 *         <li><b>TA</b>: William Simunek</li>
 */
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