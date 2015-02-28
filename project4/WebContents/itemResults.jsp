<%@ page import="edu.ucla.cs.cs144.*" %>
<%@ page import="java.util.*" %>

<%
    Item item = (Item)request.getAttribute("item");
    List<String> categories = item.getCategories();
    Iterator category = categories.iterator();
    Seller seller = (Seller)request.getAttribute("seller");
    List<Bids> bids = (List<Bids>)request.getAttribute("bids");
    Iterator bid = bids.iterator();
%>

<!DOCTYPE html>
<html>
    <head>
      <script src="sorttable.js"></script>
      <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false"></script>
      <script type="text/javascript">
        var geocoder;
            function initialize() {
                geocoder = new google.maps.Geocoder();
                var latlng = new google.maps.LatLng(0.0, 0.0);
                var myOptions = { 
                                    zoom: 4,  
                                    center: latlng, 
                                    mapTypeId: google.maps.MapTypeId.ROADMAP 
                                  }; 
                var map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);
        
                var address = "<%= item.getItemLocation() %>, <%= item.getItemCountry() %>";
                geocoder.geocode(
                    {'address': address},
                    function(results, status) {
                        if (status == google.maps.GeocoderStatus.OK) {
                            map.setCenter(results[0].geometry.location);
                            map.fitBounds(results[0].geometry.viewport);
                            map.setZoom(8);
                            var marker = new google.maps.Marker({
                                map: map,
                                position: results[0].geometry.location});
                        } else {
                            geocoder.geocode( { 'address': "<%= "USA" %>"},
                            function(results, status) {
                                if (status == google.maps.GeocoderStatus.OK)
                                {
                                    map.setCenter(results[0].geometry.location);
                                    map.fitBounds(results[0].geometry.viewport);
                                    var marker = new google.maps.Marker({
                                        map: map,
                                        position: results[0].geometry.location});
                                }
                            })
                        }
                    }
                );
            }
      </script>
      <style>
      table, th, td {
        border: 1px solid black;
      }
      th, td {
        text-align: center;
        vertical-align: bottom;
      }
      th {
        background-color: green;
        color: white;
      }
      </style>
    </head>
    <body onload="initialize()">
      <div id="textbox" align="center">
    <form action="item">
      <h3>Enter the item ID to be searched</h3>
      <input type="text" name="itemId"><br><br>
  		<input type="submit" value="Submit">
  	</form>
  </div>
        <h1><%= item.getName() %></h1>
        <h3>Item ID : <%= item.getItemId() %></h3>
        <br/>
        Categories: <br/>
        <ul>
          <% while (category.hasNext()) { %>
            <li><%= category.next() %></li>
          <% } %>
        </ul>
        <br/>
        <b>Item Location: </b>
        <%= (item.getItemLocation() +", "+item.getItemCountry()) %><br/>
        <br/>
        <div id="map_canvas" style="width: 100%; height: 400px"></div>
        <br/>
        <b>Auction Information</b><br/>
        Currently : <%= item.getCurrently() %><br/>
        First Bid : <%= item.getFirstBid() %><br/>
        Total Bids: <%= item.getNoOfBids() %><br/>
        <% if(item.getBuyPrice()!=0.0) { %>
          Buy Price : <%= item.getBuyPrice() %><br/>
        <% } %>
        Started : <%= item.getStarted() %><br/>
        Ends : <%= item.getEnds() %><br/>

        <br/>
        <b>Seller Information: </b><br/>
        User ID : <%= seller.getUserId() %><br/>
        Rating : <%= seller.getRating() %><br/>

        <br/>
        <b>Bids</b><br/>
        <% if(item.getNoOfBids()!=0) { %>
            <table class="sortable">
              <tr>
                <th>#</th>
                <th>Bidder</th>
                <th>Time</th>
                <th>Amount</th>
              </tr>
              <% int i=0;
                while(bid.hasNext()) {
                  Bids b = (Bids)bid.next();
                  i++; %>
                  <tr>
                    <td><%= i %></td>
                    <td><%= b.getBidderId() %></td>
                    <td><%= b.getTime() %></td>
                    <td><%= b.getAmount() %></td>
                  </tr>
                <% } %>

        <% } else { %>
          No bids yet.
        <% } %>
        </table>
        <br/>
        <b>Item Description</b><br/>
        <%= item.getDescription() %>
    </body>
</html>
