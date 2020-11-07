package Homework_6;

import java.util.Scanner;

public class AuctionSystem {

    private AuctionTable auctionTable;

    private String username;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        boolean userQuit = false;
        do {
            System.out.println("(D) - Import Data from URL");
            System.out.println("(A) - Create a New Auction");
            System.out.println("(B) - Bid on an Item");
            System.out.println("(I) - Get Info on Auction");
            System.out.println("(P) - Print All Auctions");
            System.out.println("(R) - Remove Expired Auctions");
            System.out.println("(T) - Let Time Pass");
            System.out.println("(Q) - Quit");

            try {
                switch (input.nextLine().toUpperCase().trim()) {
                    case "D":
                        // TODO implement D
                        break;

                    case "A":
                        // TODO implement A
                        break;

                    case "B":
                        // TODO implement B
                        break;

                    case "I":
                        // TODO implement I
                        break;

                    case "P":
                        // TODO implement P
                        break;

                    case "R":
                        // TODO implement R
                        break;

                    case "T":
                        // TODO implement T
                        break;

                    case "Q":
                        // TODO implement Q
                        break;

                    default:
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (!userQuit);

        input.close();
        System.out.println();
    }

}
