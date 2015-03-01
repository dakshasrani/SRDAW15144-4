package edu.ucla.cs.cs144;
import java.util.ArrayList;

import javax.xml.parsers.*;
import org.xml.sax.InputSource;
import org.w3c.dom.*;
import java.io.*;
import java.text.*;
import java.util.*;

public class Item {
    
    private int itemId, noOfBids;
    private String name, description;
    
    private float currently, buyPrice, firstBid;
    
    private String country;
    private String started;
    private String ends;
    
    private List<String> categories;
    private ArrayList<Bids> bids;
    
    private double latitude, longitude;
    private String itemLocation, itemCountry;
    
    private Seller seller;
    
    public Item() {
    }
    
    public int getItemId() {
        return this.itemId;
    }
    
    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public List<String> getCategories() {
        return this.categories;
    }
    
    public void setCategories(List<String> categories) {
        this.categories = categories;
    }
    
    public float getCurrently() {
        return this.currently;
    }
    
    public void setCurrently(float currently) {
        this.currently = currently;
    }
    
    public float getBuyPrice() {
        return this.buyPrice;
    }
    
    public void setBuyPrice(float buyPrice) {
        this.buyPrice = buyPrice;
    }
    
    public float getFirstBid() {
        return this.firstBid;
    }
    
    public void setFirstBid(float firstBid) {
        this.firstBid = firstBid;
    }
    
    public int getNoOfBids() {
        return this.noOfBids;
    }
    
    public void setNoOfBids(int noOfBids) {
        this.noOfBids = noOfBids;
    }
    
    public String getCountry() {
        return this.country;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }
    
    public String getStarted() {
        return this.started;
    }
    
    public void setStarted(String started) {
        this.started = started;
    }
    
    public String getEnds() {
        return this.ends;
    }
    
    public void setEnds(String ends) {
        this.ends = ends;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Seller getSeller() {
        return this.seller;
    }
    
    public void setSeller(Seller seller) {
        this.seller = seller;
    }
    
    public ArrayList<Bids> getBids() {
        return this.bids;
    }
    
    public void setBids(ArrayList<Bids> bids) {
        this.bids = bids;
    }
    
    public double getLatitude() {
        return this.latitude;
    }
    
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    
    public double getLongitude() {
        return this.longitude;
    }
    
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    
    public String getItemLocation() {
        return this.itemLocation;
    }
    
    public void setItemLocation(String itemLocation) {
        this.itemLocation = itemLocation;
    }
    
    public String getItemCountry() {
        return this.itemCountry;
    }
    
    public void setItemCountry(String itemCountry) {
        this.itemCountry = itemCountry;
    }
    
    public Item(String xml) {
        
        this.setItemId(itemId);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = null;
        
        try {
            db = dbf.newDocumentBuilder();
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(xml));
            try {
                Document doc = db.parse(is);
                NodeList nodes = doc.getElementsByTagName("Item");
                Element root = (Element)nodes.item(0);
                
                this.setItemId(Integer.parseInt(root.getAttribute("ItemID")));
                
                NodeList name = root.getElementsByTagName("Name");
                this.setName(getCharacterDataFromElement((Element)name.item(0)));
                
                NodeList categoryList = root.getElementsByTagName("Category");
                List<String> categories = new ArrayList<String>();
                for (int k=0; k<categoryList.getLength(); k++){
                    Element category = (Element)categoryList.item(k);
                    categories.add(getCharacterDataFromElement((Element)categoryList.item(k)));
                }
                this.setCategories(categories);
                
                NodeList currently = root.getElementsByTagName("Currently");
                this.setCurrently(Float.parseFloat(strip(getCharacterDataFromElement((Element)currently.item(0)))));
                
                NodeList buyPrice = root.getElementsByTagName("Buy_Price");
                if (buyPrice.getLength()==0)
                    this.setBuyPrice(0.0f);
                else
                    this.setBuyPrice(Float.parseFloat(strip(getCharacterDataFromElement((Element)buyPrice.item(0)))));
                
                NodeList firstBid = root.getElementsByTagName("First_Bid");
                this.setFirstBid(Float.parseFloat(strip(getCharacterDataFromElement((Element)firstBid.item(0)))));
                
                NodeList noOfBids = root.getElementsByTagName("Number_of_Bids");
                this.setNoOfBids(Integer.parseInt(getCharacterDataFromElement((Element)noOfBids.item(0))));
                
                NodeList started = root.getElementsByTagName("Started");
                this.setStarted(getCharacterDataFromElement((Element)started.item(0)));
                
                NodeList ends = root.getElementsByTagName("Ends");
                this.setEnds(getCharacterDataFromElement((Element)ends.item(0)));
                
                NodeList description = root.getElementsByTagName("Description");
                this.setDescription(getCharacterDataFromElement((Element)description.item(0)));
                
                NodeList locationNL = root.getElementsByTagName("Location");
                Element locationElt = (Element)locationNL.item(locationNL.getLength()-1);
                this.setItemLocation(getCharacterDataFromElement(locationElt));
                
                NodeList countryNL = root.getElementsByTagName("Country");
                this.setItemCountry(getCharacterDataFromElement((Element)countryNL.item(countryNL.getLength()-1)));
                
                NodeList seller = root.getElementsByTagName("Seller");
                Element sellerElt = (Element)seller.item(0);
                //Seller s = new Seller("Seller dude",1000);
                this.setSeller(new Seller(sellerElt.getAttribute("UserID"),Integer.parseInt(sellerElt.getAttribute("Rating"))));
                //this.setSeller(s);
                
                NodeList bidsNL = root.getElementsByTagName("Bids");
                NodeList bidsList = ((Element)bidsNL.item(0)).getElementsByTagName("Bid");
                ArrayList<Bids> bidsL = new ArrayList<Bids>();
                for (int k=0; k<bidsList.getLength(); k++) {
                    Bids bid = new Bids();
                    
                    NodeList bidder = ((Element)(bidsList.item(k))).getElementsByTagName("Bidder");
                    
                    String bidderId = ((Element)(bidder.item(0))).getAttribute("UserID");
                    bid.setBidderId(bidderId);
                    /*int bidderRating = Integer.parseInt(((Element)(bidder.item(0))).getAttribute("Rating"));
                    
                    NodeList location = ((Element)(bidder.item(0))).getElementsByTagName("Location");
                    String bidderLocation = "";
                    if (location.getLength()!=0)
                        bidderLocation = getCharacterDataFromElement((Element)location.item(0));
                    
                    NodeList country = ((Element)(bidder.item(0))).getElementsByTagName("Country");
                    String bidderCountry = "";
                    if (country.getLength()!=0)
                        bidderCountry = getCharacterDataFromElement((Element)country.item(0));
                    
                    bid.setBidder(new Bidder(bidderId,bidderRating,bidderLocation,bidderCountry));
                    */
                    NodeList time = ((Element)(bidsList.item(k))).getElementsByTagName("Time");
                    bid.setTime(getCharacterDataFromElement((Element)time.item(0)));
                    
                    NodeList amount = ((Element)(bidsList.item(k))).getElementsByTagName("Amount");
                    bid.setAmount(Float.parseFloat(strip(getCharacterDataFromElement((Element)amount.item(0)))));
                    
                    bidsL.add(bid);
                }
                this.bids = bidsL;
                
                //location name lat and long
                if (Double.parseDouble(locationElt.getAttribute("Latitude"))!=0.00)
                    this.setLatitude(Double.parseDouble(locationElt.getAttribute("Latitude")));
                else
                    this.setLatitude(0.00);
                
                if(Double.parseDouble(locationElt.getAttribute("Longitude"))!=0.00)
                    this.setLongitude(Double.parseDouble(locationElt.getAttribute("Longitude")));
                else
                    this.setLongitude(0.00);
                
                
            } catch (Exception e) {
                // handle IOException
            }
        } catch (ParserConfigurationException e1) {
            // handle ParserConfigurationException
        }
    }
    
    static String getCharacterDataFromElement(Element e) {
        Node child = e.getFirstChild();
        if (child instanceof CharacterData) {
            CharacterData cd = (CharacterData) child;
            return cd.getData();
        }
        return "?";
    }
    
    static String strip(String money) {
        if (money.equals(""))
            return money;
        else {
            double am = 0.0;
            NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
            try { am = nf.parse(money).doubleValue(); }
            catch (ParseException e) {
                System.out.println("This method should work for all " +
                                   "money values you find in our data.");
                System.exit(20);
            }
            nf.setGroupingUsed(false);
            return nf.format(am).substring(1);
        }
    }
}