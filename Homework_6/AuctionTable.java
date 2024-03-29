package Homework_6;

import big.data.*;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The <code>Auctions</code> will be stored in a class <code>AuctionTable</code>
 * that extends <code>HashMap</code>. It will also have a
 * <code>buildFromURL()</code> method that takes in a URL and takes the
 * information and pushes the listings onto the <code>HashMap</code>. It also
 * has various different methods to interact with the <code>HashMap</code>.
 * 
 * @author <b>Name</b>: Vincent Zheng
 *      <li><b>Solar_ID:</b> 113469839</li>
 *      <li><b>Email:</b> vincent.zheng@stonybrook.edu</li>
 *      <li><b>Assignment:</b> 5</li>
 *      <li><b>Course</b>: CSE 214</li>
 *      <li><b>Recitation</b>: R02</li>
 *      <li><b>TA</b>: William Simunek</li>
 */
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
     *                                  represent a valid datasource (can't
     *                                  connect or invalid syntax).
     */
    public static AuctionTable buildFromURL(String URL) throws
                                                        IllegalArgumentException
    {
        try {
            System.out.println("\nLoading...");
            DataSource ds = DataSource.connect(URL).load();

            String[] sellerName = ds.fetchStringArray(
                                            "listing/seller_info/seller_name");
            String[] IDNum = ds.fetchStringArray("listing/auction_info/id_num");
            String[] bidderName = ds.fetchStringArray(
                                "listing/auction_info/high_bidder/bidder_name");

            String[] itemMemory = ds.fetchStringArray(
                                "listing/item_info/memory");
            String[] itemHardDrive = ds.fetchStringArray(
                                                "listing/item_info/hard_drive");
            String[] itemCPU = ds.fetchStringArray("listing/item_info/cpu");

            /*
            * Getting the current bids and removing the '$' and ',' signs from
            * them so they don't interfere with the conversion to Double.
            */
            String[] currentBid = ds.fetchStringArray(
                                            "listing/auction_info/current_bid");
            for (int i = 0; i < currentBid.length; i++) {
                String finalString = "";
                for (int j = 0; j < currentBid[i].length(); j++) {
                    if (!(currentBid[i].charAt(j) == '$' ||
                                                currentBid[i].charAt(j) == ','))
                        finalString += currentBid[i].charAt(j);
                }

                currentBid[i] = finalString;
            }

            /*
            * This chunk of code converts the time_left nodes in the XML from
            * day/hour format to just hours. This is dont by finding the index
            * of the first match for "day" and "hour" or "hr" and looping
            * backwards to get all of the numbers. The backwards loop will stop
            * when either the end of the String is reached or it encounters a
            * non-number character.
            */
            String[] timeLeftRaw = ds.fetchStringArray(
                                            "listing/auction_info/time_left");
            int[] timeLeft = new int[timeLeftRaw.length];
            for (int i = 0; i < timeLeft.length; i++) {
                int totalTime = 0;

                // Converting the days to hours and adding to the final number.
                if (timeLeftRaw[i].contains("day")) {
                    int indexOfDay = timeLeftRaw[i].indexOf("day");
                    int timeDays = 0;

                    for (int j = indexOfDay - 2; j >= 0 &&
                        timeLeftRaw[i].charAt(j) >= (int) '0' &&
                        timeLeftRaw[i].charAt(j) <= (int) '9'; j--)
                    {
                        timeDays += Integer.parseInt(
                                Character.toString(timeLeftRaw[i].charAt(j)))
                                * (int) Math.pow(10, (indexOfDay - 2 - j));
                    }

                    totalTime += 24 * timeDays;
                }

                // Converting the hours to Integers and adding to the final
                // number.
            if (timeLeftRaw[i].contains("hour") ||
                                            timeLeftRaw[i].contains("hr"))
            {
                    int indexOfHour = timeLeftRaw[i].indexOf("hour") != -1
                                        ? timeLeftRaw[i].indexOf("hour")
                                        : timeLeftRaw[i].indexOf("hr");
                    int timeHours = 0;

                    for (int j = indexOfHour - 2; j >= 0 &&
                        timeLeftRaw[i].charAt(j) >= (int) '0' &&
                        timeLeftRaw[i].charAt(j) <= (int) '9'; j--)
                    {
                        timeHours += Integer.parseInt(
                                Character.toString(timeLeftRaw[i].charAt(j)))
                                * (int) Math.pow(10, (indexOfHour - 2 - j));
                    }

                    totalTime += timeHours;
                }

                // Setting timeLeft of this index to the converted time in
                // hours.
                timeLeft[i] = totalTime;
            }

            /*
            * Going through each sellerName and creating a new Auction object
            * based on all the information at that index.
            */
            AuctionTable newTable = new AuctionTable();
            for (int i = 0; i < sellerName.length; i++) {
                newTable.putAuction(IDNum[i],
                        new Auction(timeLeft[i], Double.parseDouble(
                                    currentBid[i]), IDNum[i], sellerName[i],
                                    bidderName[i],
                                    String.format("%s - %s - %s", itemCPU[i],
                                    itemMemory[i], itemHardDrive[i])));
            }

            System.out.println("Auction data loaded successfully!");
            return newTable;
        } catch (Exception e) {
            throw new IllegalArgumentException(
                                "Unable to Connect or Read the Given Link!");
        }
    }

    /**
     * Manually posts an <code>Auction</code>, and add it into the table.
     * 
     * @param auctionID The unique <code>key</code> for this object.
     * 
     * @param auction   The <code>Auction</code> to insert into the table with
     *                  the corresponding <code>auctionID</code>.
     * 
     * @throws IllegalArgumentException If the given auctionID is already stored
     *                                  in the table.
     */
    public void putAuction(String auctionID, Auction auction) throws
                                                        IllegalArgumentException
    {
        if (containsKey(auctionID))
            throw new IllegalArgumentException("The auctionID already exists!");

        put(auctionID, auction);
    }

    /**
     * Get the information of an <code>Auction</code> that contains the given ID
     * as key.
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
     * Simulates the passing of time. Decrease the <code>timeRemaining</code> of
     * all <code>Auction</code> objects by the amount specified. The value
     * cannot go below <code>0</code>.
     * 
     * @param numHours The number of hours to decrease the
     *                 <code>timeRemaining</code> value by.
     * 
     * @throws IllegalArgumentException If the given <code>numHours</code> is
     *                                  non positive.
     */
    public void letTimePass(int numHours) throws IllegalArgumentException {
        if (numHours <= 0)
            throw new IllegalArgumentException("Time Entered Is Not Positive!");

        for (Auction item : values()) {
            item.decrementTimeRemaining(numHours);
        }
    }

    /**
     * Iterates over all <code>Auction</code> objects in the table and removes
     * them if they are expired (<code>timeRemaining == 0</code>).
     */
    public void removeExpiredAuctions() {
        ArrayList<String> keys = new ArrayList<>();
        for (String key : keySet()) {
            keys.add(key);
        }

        for (String auctionID : keys) {
            if (get(auctionID).getTimeRemaining() == 0) {
                remove(auctionID);
            }
        }
    }

    /**
     * Prints the <code>AuctionTable</code> in tabular form.
     */
    public void printTable() {
        System.out.println(
                  "\n Auction ID |     Bid    |        Seller         | "
                + "         Buyer          |    Time   | Item Info");

        System.out.println("==================================================="
                        + "=================================================" +
                        "===============================");

        for (Auction auction : values()) {
            System.out.println(auction.toString());
        }
    }

}
