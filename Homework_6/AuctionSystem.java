package Homework_6;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class AuctionSystem {

    /**
     * The <code>AuctionTable</code> that the <code>AuctionSystem</code> will be
     * using.
     */
    private AuctionTable auctionTable;

    /**
     * The <code>username</code> of the current user of the
     * <code>AuctionSystem</code>.
     */
    private String username;

    /**
     * Initializes a new <code>AuctionSystem</code> object given the
     * <code>username</code> and <code>auctionTable</code>.
     * 
     * @param username The <code>username</code> of the user.
     */
    public AuctionSystem(String username, AuctionTable auctionTable) {
        this.username = username;
        this.auctionTable = auctionTable;
    }

    /**
     * Takes the next input as the URL and calls the
     * <code>AuctionTable.buildFromURL()</code> method.
     * 
     * @param inputStream The input stream to take the URL from <code>String</code>.
     * 
     * @throws IllegalArgumentException If the input is an empty .
     */
    public void setToURL(Scanner inputStream) throws IllegalArgumentException {
        System.out.print("Please enter a URL: ");
        String URL = inputStream.nextLine();

        if (URL.length() == 0)
            throw new IllegalArgumentException("The URL Cannot Be Empty!");

        auctionTable = AuctionTable.buildFromURL(URL);
    }

    /**
     * Takes inputs for auction ID, auction time, and item info and creates a new
     * Auction and puts it into <code>auctionTable</code>.
     * 
     * @param inputStream The input stream to take the inputs from.
     * 
     * @throws IllegalArgumentException If any of the inputs are invalid or if the
     *                                  same auction ID already has an item.
     */
    public void newAuction(Scanner inputStream) throws IllegalArgumentException {
        // Getting and checking username
        System.out.println("\nCreating new Auction as " + username + ".");
        System.out.print("Please enter an Auction ID: ");

        String auctionID = inputStream.nextLine();

        if (auctionID.equals(""))
            throw new IllegalArgumentException("Auction ID cannot be empty!");

        // Getting and checking auction time
        System.out.print("Please enter an Auction time (hours): ");

        int auctionTime;
        try {
            auctionTime = Integer.parseInt(inputStream.nextLine());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Auction Time Must Be a Number!");
        }
        if (auctionTime <= 0)
            throw new IllegalArgumentException("Auction Time Must Be Positive!");

        // Getting and checking auction info
        System.out.print("Please enter some Item Info: ");

        String itemInfo = inputStream.nextLine();

        if (itemInfo.equals(""))
            System.out.println("Item Info cannot be empty!");

        // Putting all the entered information into the table.
        auctionTable.putAuction(auctionID, new Auction(auctionTime, 0.0, auctionID, username, "", itemInfo));
    }

    /**
     * Takes an <code>auctionID</code> and puts a bid onto an <code>Auction</code>
     * with that <code>auctionID</code>.
     * 
     * @param inputStream The input stream to take the <code>auctionID</code> from.
     * 
     * @throws IllegalArgumentException If the <code>auctionID</code> is invalid or
     *                                  doesn't match with an existing
     *                                  <code>Auction</code>.
     * 
     * @throws ClosedAuctionException   If the <code>Auction</code> is already
     *                                  closed.
     */
    public void putBid(Scanner inputStream) throws IllegalArgumentException, ClosedAuctionException {

        // Taking and checking the auctionID.
        System.out.print("Please enter an Auction ID: ");
        String auctionID = inputStream.nextLine();
        if (auctionID.equals(""))
            throw new IllegalArgumentException("Auciton ID Cannot Be Empty!");

        Auction auction = auctionTable.getAuction(auctionID);
        if (auction == null)
            throw new IllegalArgumentException("Auction Not Found In The Table!");

        // Outputs whether the auction is closed or open and get the current bid.
        System.out.printf("Auction %s is %s\n", auctionID, auction.getTimeRemaining() == 0 ? "CLOSED" : "OPEN");
        System.out.printf("\tCurrent Bid: %s\n\n",
                auction.getCurrentBid() == 0.0 ? "None" : String.format("$%,9.2f", auction.getCurrentBid()));

        // If there is no time remaining tells the user they can't bid.
        if (auction.getTimeRemaining() == 0.0) {
            System.out.println("You can no longer bid on this item.");
        } else {
            // Else takes a bid amount, and tries to put a new bid.
            System.out.print("What would you like to bid?: ");

            int bidAmt;
            try {
                bidAmt = Integer.parseInt(inputStream.nextLine());
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Bid Amount Must Be a Number!");
            }

            auction.newBid(username, bidAmt);
        }
    }

    /**
     * Gets a <code>aucitonID</code> and outputs the according information.
     * 
     * @param inputStream The <code>inputStream</code> to get the
     *                    <code>auctionID</code> from.
     */
    public void getAuctionInfo(Scanner inputStream) {

        // Getting the aucitonID annd checking it.
        System.out.print("Please enter an Auction ID: ");
        String auctionID = inputStream.nextLine();
        if (auctionID.equals(""))
            throw new IllegalArgumentException("Auciton ID Cannot Be Empty!");

        Auction auction = auctionTable.getAuction(auctionID);
        if (auction == null)
            throw new IllegalArgumentException("Auction Not Found In The Table!");

        System.out.println("\nAuction " + auctionID + ":");
        System.out.println("\tSeller: " + auction.getSellerName());
        System.out.println("\tBuyer: " + auction.getBuyerName());
        System.out.println("\tTime: " + auction.getTimeRemaining() + " hours");
        System.out.println("\tInfo: " + auction.getItemInfo());
    }

    /**
     * Calls <code>printTable()</code> on <code>auctionTable</code>.
     */
    public void printTable() {
        auctionTable.printTable();
    }

    /**
     * Calls <code>removeExpiredAuctions()</code> on <code>auctionTable</code>.
     */
    public void removeExpiredAuctions() {
        System.out.println("Removing expired auctions...");
        auctionTable.removeExpiredAuctions();
        System.out.println("All expired auctions removed.");
    }

    /**
     * Takes in an <code>int</code> time, checks it, and calls
     * <code>lettimePass()</code> based on the input on <code>auctionTable</code>.
     * 
     * @param inputStream The input stream to take the time from.
     * 
     * @throws IllegalArgumentException Thrown if the entered time isn't an integer
     *                                  or is non-positive.
     */
    public void letTimePass(Scanner inputStream) throws IllegalArgumentException {
        System.out.print("How many hours should pass: ");
        int timePass;
        try {
            timePass = Integer.parseInt(inputStream.nextLine());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Time Passed is Not a Number!");
        }

        System.out.println("Time passing...");
        auctionTable.letTimePass(timePass);
        System.out.println("Auction times updated.");
    }

    /**
     * Saves the current state of auctionTable into an object file auction.obj.
     * 
     * @throws IOException If the file exists but is a directory rather than a
     *                     regular file, does not exist but cannot be created, or
     *                     cannot be opened for any other reason (pulled out of the
     *                     Java API docs).
     */
    public void saveTable() throws IOException {
        FileOutputStream file = new FileOutputStream("auction.obj");
        ObjectOutputStream outStream = new ObjectOutputStream(file);

        outStream.writeObject(auctionTable);
        outStream.close();
    }

    /**
     * The method should first prompt the user for a username. This should be stored
     * in username The rest of the program will be executed on behalf of this user.
     * It would have an option to import a URL, create and Auction, etc...
     */
    public static void main(String[] args) {
        System.out.println("Starting...");

        // Importing the table from an auction.obj file. If this file is found, converts
        // it to an AuctionTable and stores it into table. If it's not found, then set
        // table to a new AuctionTable().
        AuctionTable table;
        try {
            FileInputStream file = new FileInputStream("auction.obj");
            ObjectInputStream inStream = new ObjectInputStream(file);
            System.out.println("Loading previous Auction Table...");
            table = (AuctionTable) inStream.readObject();
            inStream.close();
        } catch (Exception e) {
            System.out.println("No previous auction table detected.");
            System.out.println("Creating new table...\n");
            table = new AuctionTable();
        }

        Scanner input = new Scanner(System.in);

        // Keeps asking user for a username until a non-empty one is entered.
        String username;
        do {
            System.out.print("Please select a username: ");
            username = input.nextLine();

            if (username.equals(""))
                System.out.println("ERROR: Username Cannot Be Empty!");
        } while (username.equals(""));

        AuctionSystem system = new AuctionSystem(username, table);

        boolean userQuit = false;
        do {
            System.out.println("\n(D) - Import Data from URL");
            System.out.println("(A) - Create a New Auction");
            System.out.println("(B) - Bid on an Item");
            System.out.println("(I) - Get Info on Auction");
            System.out.println("(P) - Print All Auctions");
            System.out.println("(R) - Remove Expired Auctions");
            System.out.println("(T) - Let Time Pass");
            System.out.println("(Q) - Quit");
            System.out.print("\nPlease select an option: ");

            // Calls a different method according to the input. If input isn't matched, the
            // go to default case. Input ISN'T case-sensitive!
            try {
                switch (input.nextLine().toUpperCase().trim()) {
                    case "D":
                        system.setToURL(input);
                        break;

                    case "A":
                        system.newAuction(input);
                        break;

                    case "B":
                        system.putBid(input);
                        break;

                    case "I":
                        system.getAuctionInfo(input);
                        break;

                    case "P":
                        system.printTable();
                        break;

                    case "R":
                        system.removeExpiredAuctions();
                        break;

                    case "T":
                        system.letTimePass(input);
                        break;

                    case "Q":
                        userQuit = true;
                        break;

                    default:
                        System.out.println("ERROR: Unknown Argument Entered!");
                        break;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("ERROR: " + e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (!userQuit);

        // Saves the current AuctionTable to anh auction.obj file by calling
        // saveTable().
        System.out.println("\nWriting auction table to file...");
        try {
            system.saveTable();
            System.out.println("Done!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        input.close();
        System.out.println("\nGoodbye");
    }

}
