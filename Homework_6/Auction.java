package Homework_6;

import big.data.*;

public class Auction {

    private int timeRemaining;

    private double currentBid;

    private String auctionID;

    private String sellerName;

    private String buyerName;

    private String itemInfo;

    public Auction() {

    }

    public int getTimeRemaining() {
        return timeRemaining;
    }

    public double getCurrentBid() {
        return currentBid;
    }

    public String getAuctionID() {
        return auctionID;
    }

    public String getSellerName() {
        return sellerName;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public String getItemInfo() {
        return itemInfo;
    }

    public void decrementTimeRemaining(int time) {
        // TODO implement decrementTimeRemaining()
    }

    public void newBid(String bidderName, double bidAmt) throws ClosedAuctionException {
        // TODO implement newBid()
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub toString()
        return super.toString();
    }

}