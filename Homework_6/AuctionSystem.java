package Homework_6;

import java.util.Scanner;

public class AuctionSystem {

    private AuctionTable auctionTable;

    private String username;

    public AuctionSystem(String username) throws IllegalArgumentException {
        if (username.length() == 0)
            throw new IllegalArgumentException("username Cannot Be Empty!");

        this.username = username;
        this.auctionTable = new AuctionTable();
    }

    public void setToURL(Scanner inputStream) throws IllegalArgumentException {
        System.out.print("Please enter a URL: ");
        String URL = inputStream.nextLine();

        if (URL.length() == 0)
            throw new IllegalArgumentException("The URL Cannot Be Empty!");

        auctionTable = AuctionTable.buildFromURL(URL);
    }

    public void newAuction(Scanner inputStream) throws IllegalArgumentException {
        System.out.println("\nCreating new Auction as " + username + ".");
        System.out.print("Please enter an Auction ID: ");

        String auctionID = inputStream.nextLine();

        if (auctionID.equals(""))
            throw new IllegalArgumentException("Auction ID cannot be empty!");

        System.out.print("Please enter an Auction time (hours): ");

        int auctionTime;
        try {
            auctionTime = Integer.parseInt(inputStream.nextLine());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Auction Time Must Be a Number!");
        }
        if (auctionTime <= 0)
            throw new IllegalArgumentException("Auction Time Must Be Positive!");

        System.out.print("Please enter some Item Info: ");

        String itemInfo = inputStream.nextLine();

        if (itemInfo.equals(""))
            System.out.println("Item Info cannot be empty!");

        auctionTable.putAuction(auctionID, new Auction(auctionTime, 0.0, auctionID, username, "", itemInfo));
    }

    public void putBid(Scanner inputStream) throws IllegalArgumentException, ClosedAuctionException {
        System.out.print("Please enter an Auction ID: ");
        String auctionID = inputStream.nextLine();
        if (auctionID.equals(""))
            throw new IllegalArgumentException("Auciton ID Cannot Be Empty!");

        Auction auction = auctionTable.getAuction(auctionID);
        if (auction == null)
            throw new IllegalArgumentException("Auction Not Found In The Table!");

        System.out.printf("Auction %s is %s\n", auctionID, auction.getTimeRemaining() == 0 ? "CLOSED" : "OPEN");
        System.out.printf("\tCurrent Bid: %s\n\n",
                auction.getCurrentBid() == 0.0 ? "None" : String.format("$%,9.2f", auction.getCurrentBid()));

        if (auction.getTimeRemaining() == 0.0) {
            System.out.println("You can no longer bid on this item.");
        } else {
            System.out.print("What would you like to bid?: ");

            int bidAmt;
            try {
                bidAmt = Integer.parseInt(inputStream.nextLine());
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Bid Amount Must Be a Number!");
            }

            if (auction.getCurrentBid() < bidAmt) {
                auction.newBid(username, bidAmt);
                System.out.println("Bid Accepted.");
            } else {
                System.out.println("Bid Rejected.");
            }
        }
    }

    public void getAuctionInfo(Scanner inputStream) {
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

    public void printTable() {
        auctionTable.printTable();
    }

    public void removeExpiredAuctions() {
        System.out.println("Removing expired auctions...");
        auctionTable.removeExpiredAuctions();
        System.out.println("All expired auctions removed.");
    }

    public void letTimePass(Scanner inputStream) throws IllegalArgumentException {
        System.out.println("How many hours should pass: ");
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

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String username;
        do {
            System.out.print("Please select a username: ");
            username = input.nextLine();

            if (username.equals(""))
                System.out.println("ERROR: Username Cannot Be Empty!");
        } while (username.equals(""));

        AuctionSystem system = new AuctionSystem(username);

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

        input.close();
        System.out.println("\nGoodbye");
    }

}
