package edu.ucla.cs.cs144;

public class Bidder extends User {
    String location, country;
    
    Bidder(String userId, int rating, String location, String country) {
        super(userId, rating);
        this.location = location;
        this.country = country;
    }
}