package edu.ucla.cs.cs144;

public class Seller {
    private String userId;
    private int rating;
    
    public String getUserId(){
        return this.userId;
    }
    
    public int getRating(){
        return this.rating;
    }
    
    Seller(String userId, int rating) {
        this.userId = userId;
        this.rating = rating;
    }
}