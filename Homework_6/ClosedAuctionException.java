package Homework_6;

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
