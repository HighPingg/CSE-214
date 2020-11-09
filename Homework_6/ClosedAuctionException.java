package Homework_6;

/**
 * <code>ClosedAuctionException</code> is thrown when the user tries to make a
 * bid on an expired <code>Auction</code>.
 * 
 * @author <b>Name</b>: Vincent Zheng
 *         <li><b>Solar_ID:</b> 113469839</li>
 *         <li><b>Email:</b> vincent.zheng@stonybrook.edu</li>
 *         <li><b>Assignment:</b> 5</li>
 *         <li><b>Course</b>: CSE 214</li>
 *         <li><b>Recitation</b>: R02</li>
 *         <li><b>TA</b>: William Simunek</li>
 */
public class ClosedAuctionException extends Exception {
    /**
     * Default serial version UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor that creates a ClosedAuctionException using s as the message
     * 
     * @param s the message to add to the Exception
     * 
     *          <li><b>PostCondition:</b> This ClosedAuctionException is
     *          instatiated with a valid message.</li>
     */

    public ClosedAuctionException(String s) {
        super(s);
    }
}
