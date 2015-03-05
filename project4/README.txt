Project 4
Team Name: The One With The Project
Team Members:
Daksha Asrani - 304432268
Sayali Santosh Rajwade - 704406328

We have used 1 grace day for this project.

In this project, we have implemented eBay search using keyword and Item ID.

For keyword search, the results are obtained based on parameters: q, numResultsToSkip and numResultsToReturn. Pagination is performed on the obtained results and maximum 10 records are displayed per page. Cases handled are:
1) No parameters given
2) Null q (keyword)
3) Alphabets instead of numbers for numResultsToSkip and numResultsToReturn.

Autosuggest is implemented for keyword search using Google Suggest. Suggestions are given in the dropdown and keyUp, keyDown and mouse over functionalities are implemented.

For item ID search, the case for null ID and no ID are handled.
The results obtained from the item ID search are arranged by item information, item location, auction information, bids information, seller information and item description.
The item location is displayed on map using geocoding for “Location, Country”. A information overlay having item name is displayed on the marker.

The bids table can be sorted using any column by clicking on that column name.