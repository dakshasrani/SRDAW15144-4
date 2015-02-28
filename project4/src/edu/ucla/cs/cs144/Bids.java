package edu.ucla.cs.cs144;

public class Bids {
    private Bidder bidder;
    private String time;
    private float amount;
    private String bidderID;
    
    public Bidder getBidder() {
        return this.bidder;
    }
    
    public void setBidder(Bidder bidder) {
        this.bidder = bidder;
    }
    
    public String getTime() {
        return this.time;
    }
    
    public void setTime(String time) {
        this.time = time;
    }
    
    public float getAmount() {
        return this.amount;
    }
    
    public void setAmount(float amount) {
        this.amount = amount;
    }
    
    public String getBidderId() {
        return this.bidderID;
    }
    
    public void setBidderId(String bidderID) {
        this.bidderID = bidderID;
    }
}