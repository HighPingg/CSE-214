package Homework_6;

public class Auction {

    /**
     * The total amount of time left on this <code>Auction</code> (in hours).
     */
    private int timeRemaining;

    /**
     * The current bid amount of this <code>Auction</code> item.
     */
    private double currentBid;

    /**
     * The ID of this <code>Auction</code> object.
     */
    private String auctionID;

    /**
     * The name of the seller of this <code>Auction</code> object.
     */
    private String sellerName;

    /**
     * The name of the buyer of this <code>Auction</code> object.
     */
    private String buyerName;

    /**
     * The information of the <code>Auction</code> object.
     */
    private String itemInfo;

    /**
     * Creates a new Auction object with default parameters:
     * <ul>
     * <li><code>timeRemaining = 0</code></li>
     * <li><code>currentBid = 0</code></li>
     * <li><code>auctionID = ""</code></li>
     * <li><code>sellerName = ""</code></li>
     * <li><code>buyerName = ""</code></li>
     * <li><code>itemInfo = ""</code></li>
     * </ul>
     */
    public Auction() {
        this.timeRemaining = 0;
        this.currentBid = 0;
        this.auctionID = "";
        this.sellerName = "";
        this.buyerName = "";
        this.itemInfo = "";
    }

    /**
     * Creates a new <code>Auction</code> object with the given parameters.
     * 
     * @param timeRemaining The value to set <code>timeRemaining</code> to.
     * 
     * @param currentBid    The value to set <code>currentBid</code> to.
     * 
     * @param auctionID     The value to set <code>auctionID</code> to.
     * 
     * @param sellerName    The value to set <code>sellerName</code> to.
     * 
     * @param buyerName     The value to set <code>buyerName</code> to.
     * 
     * @param itemInfo      The value to set <code>itemInfo</code> to.
     */
    public Auction(int timeRemaining, double currentBid, String auctionID, String sellerName, String buyerName,
            String itemInfo) {
        this.timeRemaining = timeRemaining;
        this.currentBid = currentBid;
        this.auctionID = auctionID;
        this.sellerName = sellerName;
        this.buyerName = buyerName;
        this.itemInfo = itemInfo;
    }

    /**
     * Return the current value of <code>timeRemaining</code> stored in this object.
     * 
     * @return The current value of <code>timeRemaining</code>.
     */
    public int getTimeRemaining() {
        return timeRemaining;
    }

    /**
     * Return the current value of <code>currentBid</code> stored in this object.
     * 
     * @return The current value of <code>currentBid</code>.
     */
    public double getCurrentBid() {
        return currentBid;
    }

    /**
     * Return the current value of <code>auctionID</code> stored in this object.
     * 
     * @return The current value of <code>auctionID</code>.
     */
    public String getAuctionID() {
        return auctionID;
    }

    /**
     * Return the current value of <code>sellerName</code> stored in this object.
     * 
     * @return The current value of <code>sellerName</code>.
     */
    public String getSellerName() {
        return sellerName;
    }

    /**
     * Return the current value of <code>buyerName</code> stored in this object.
     * 
     * @return The current value of <code>buyerName</code>.
     */
    public String getBuyerName() {
        return buyerName;
    }

    /**
     * Return the current value of <code>itemInfo</code> stored in this object.
     * 
     * @return The current value of <code>itemInfo</code>.
     */
    public String getItemInfo() {
        return itemInfo;
    }

    /**
     * Decreases the time remaining for this auction by the specified amount. If
     * <code>time</code> is greater than the current remaining time for the auction,
     * then the time remaining is set to 0.
     * 
     * @param time The amount of time to decrease <code>timeRemaining</code> by.
     */
    public void decrementTimeRemaining(int time) {
        timeRemaining = Math.max(timeRemaining - time, 0);
    }

    /**
     * Makes a new bid on this auction. If <code>bidAmt</code> is larger than
     * <code>currentBid</code>, then the value of <code>currentBid</code> is
     * replaced by <code>bidAmt</code> and <code>buyerName</code> is is replaced by
     * <code>bidderName</code>.
     * 
     * @param bidderName The name of the new bidder.
     * 
     * @param bidAmt     The new bidder's price.
     * 
     * @throws ClosedAuctionException Thrown if the auction is closed and no more
     *                                bids can be placed (i.e.
     *                                <code>timeRemaining == 0</code>).
     * 
     */
    public void newBid(String bidderName, double bidAmt) throws ClosedAuctionException {
        if (timeRemaining == 0) {
            throw new ClosedAuctionException("Bid Rejected.");
        }

        if (currentBid < bidAmt) {
            buyerName = bidderName;
            currentBid = bidAmt;
        }

        System.out.println("Bid Accepted.");
    }

    /**
     * Returns string of data members in tabular form.
     * 
     * @return The tabular form of this <code>Auction</code> object.
     */
    @Override
    public String toString() {
        return String.format("  %9s | %10s | %-21s | %-23s | %3d hours | %s", auctionID,
                currentBid == 0.0 ? "" : String.format("%,.2f", currentBid), sellerName, buyerName, timeRemaining,
                itemInfo.substring(0, Math.min(42, itemInfo.length())));
    }

}