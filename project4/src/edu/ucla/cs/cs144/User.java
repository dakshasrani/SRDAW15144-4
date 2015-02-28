package edu.ucla.cs.cs144;

public class User {
    private String userId;
    private int rating;
    
    User() {
    }
    
    User(String userId,int rating) {
        this.userId = userId;
        this.rating = rating;
    }
    
    public String getUserId() {
        return this.userId;
    }
    
    public int getRating() {
        return this.rating;
    }
}