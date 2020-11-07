package Homework_6;

import big.data.*;
import java.util.HashMap;

public class AuctionTable extends HashMap<String, Auction> {

    /**
     * Default serial version UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Uses the <code>BigData</code> library to construct an
     * <code>AuctionTable</code> from a remote data source.
     * 
     * @param URL <code>String</code> representing the <code>URL</code> fo the
     *            remote data source.
     * 
     * @return The <code>AuctionTable</code> constructed from the remote data
     *         source.
     * 
     * @throws IllegalArgumentException Thrown if the <code>URL</code> does not
     *                                  represent a valid datasource (can't connect
     *                                  or invalid syntax).
     */
    public static AuctionTable buildFromURL(String URL) throws IllegalArgumentException {
        
    }

    /**
     * Manually posts an <code>Auction</code>, and add it into the table.
     * 
     * @param auctionID The unique <code>key</code> for this object.
     * 
     * @param auction   The <code>Auction</code> to insert into the table with the
     *                  corresponding <code>auctionID</code>.
     * 
     * @throws IllegalArgumentException If the given auctionID is already stored in
     *                                  the table.
     */
    public void putAuction(String auctionID, Auction auction) throws IllegalArgumentException {
        if (containsKey(auctionID))
            throw new IllegalArgumentException("The auctionID already exists!");

        put(auctionID, auction);
    }

    /**
     * Get the information of an <code>Auction</code> that contains the given ID as
     * key.
     * 
     * @param auctionID The unique <code>key</code> for this object
     * 
     * @return An <code>Auction</code> object with the given <code>key</code>,
     *         <code>null</code> otherwise.
     */
    public Auction getAuction(String auctionID) {
        return get(auctionID);
    }

    /**
     * Simulates the passing of time. Decrease the <code>timeRemaining</code> of all
     * <code>Auction</code> objects by the amount specified. The value cannot go
     * below <code>0</code>.
     * 
     * @param numHours The number of hours to decrease the
     *                 <code>timeRemaining</code> value by.
     * 
     * @throws IllegalArgumentException If the given <code>numHours</code> is non
     *                                  positive.
     */
    public void letTimePass(int numHours) throws IllegalArgumentException {
        if (numHours <= 0)
            throw new IllegalArgumentException("Time Entered Is Not Positive!");

        for (Auction item : values()) {
            item.decrementTimeRemaining(numHours);
        }
    }

    /**
     * Iterates over all <code>Auction</code> objects in the table and removes them
     * if they are expired (<code>timeRemaining == 0</code>).
     */
    public void removeExpiredAuctions() {
        for (String auctionID : keySet()) {
            if (get(auctionID).getTimeRemaining() == 0) {
                remove(auctionID);
            }
        }
    }

    /**
     * Prints the <code>AuctionTable</code> in tabular form.
     */
    public void printTable() {
        System.out.println(" Auction ID |     Bid    |        Seller         | "
                + "         Buyer          |    Time   | Item Info");
        System.out.println("==================================================="
                + "=================================================" + "===============================");

        for (Auction auction : values()) {
            System.out.println(auction.toString());
        }
    }

}
