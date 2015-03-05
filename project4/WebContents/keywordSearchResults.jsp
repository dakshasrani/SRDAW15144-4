<%@page import="edu.ucla.cs.cs144.SearchResult" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>

<html>
  <head>
    <title>Keyword Search Results</title>
    <script type="text/javascript" src="autosuggest2.js"></script>
    <script type="text/javascript" src="suggestions2.js"></script>
    <link rel="stylesheet" type="text/css" href="css/autosuggest.css" />
    <link rel="stylesheet" href="css/main.css" type="text/css" />
    <script type="text/javascript">
        window.onload = function () {
            var oTextbox = new AutoSuggestControl(document.getElementById("q"), new StateSuggestions());
        }
    </script>
  </head>

  <body>
    <div id="nav">
      <p class="title"><a href="/eBay/index.html">eBay</a></p>
      <ul id="navigation">
        <li><a href="/eBay/keywordSearch.html">Keyword Search</a></li>
        <li><a href="/eBay/getItem.html">Item Search</a></li>
      </ul>
    </div>
    <div id = "body">
    <div id="textbox" align="center">
      <form action="/eBay/search">
        <h2>Enter Keyword</h2>
        <input type="text" name="q" id="q">&nbsp;&nbsp;&nbsp;&nbsp;
        <input type="submit" value="Submit">
        <input type="hidden" name="numResultsToSkip" value="0">
        <input type="hidden" name="numResultsToReturn" value="20">
      </form>
    </div>
    <% String error = (String)request.getAttribute("error");
    if(error.equals("no")) {
    String q = (String)request.getAttribute("q");
    int numResultsToSkip = (Integer)request.getAttribute("numResultsToSkip");
    int numResultsToReturn = (Integer)request.getAttribute("numResultsToReturn");
    SearchResult[] resultItems = (SearchResult[])request.getAttribute("searchResult");
    int numResults = (Integer)request.getAttribute("numResult");
    int recordsPerPage = (Integer)request.getAttribute("recordsPerPage");
    int noOfPages = (int) Math.ceil(numResults * 1.0 / recordsPerPage);
    int currentPage = (Integer)request.getAttribute("currentPage");
    String itemId="";
    boolean check = currentPage < noOfPages;

    int startPageNo = (currentPage - 1)*recordsPerPage + 1;
    int endPageNo = 0;
    if((currentPage * recordsPerPage) > numResults)
        endPageNo = (currentPage - 1)*recordsPerPage + (numResults % recordsPerPage);
    else
        endPageNo = (currentPage*recordsPerPage);

    %>
    

<% if (resultItems.length!=0) {%>
  <div id="resultTable" align="center">
    <table class="results">
        <h3><%= startPageNo%> to <%= endPageNo%> Search Results for "<%=q%>"</h3>
          <tr class="columnHead">
            <td>Item ID</td>
            <td>Item Name</td>
          </tr>

        <% int loopVar = 0;
        if((currentPage * recordsPerPage) > numResults)
              loopVar = numResults % recordsPerPage;
        else
              loopVar = recordsPerPage;
          for(int item = 0; item < loopVar; item++){
            itemId = resultItems[(currentPage - 1)* recordsPerPage + item].getItemId();%>
            <tr>
              <td><a href="/eBay/item?id=<%=itemId%>"><%= resultItems[(currentPage - 1)* recordsPerPage + item].getItemId() %></a></td>
              <td><%= resultItems[(currentPage - 1)* recordsPerPage + item].getName() %></td>
            </tr>
          <% } %>

    </table>
  </div>

    <%-- Was working: for(int item = 0; item < resultItems.length; item++){ %>
    <tr>
      <td><%= resultItems[item].getItemId() %></td>
      <td><%= resultItems[item].getName() %></td>
    </tr>
    <%  } %> --%>
  <br />
  <%--For displaying Previous link except for the 1st page --%>
  <div id="previous" align="center">
    <c:if test="${currentPage != 1}">
        <td><a href="item.do?q=${q}&currentPage=${currentPage - 1}&numResultsToSkip=${numResultsToSkip}&numResultsToReturn=${numResultsToReturn}">Previous</a></td>
    </c:if>
  </div>

    <%--For displaying Page numbers.
  The when condition does not display a link for the current page--%>
  <div id="pageNo" align="center">
   <table class="pageNo">
       <tr>
           <c:forEach begin="1" end="<%= noOfPages%>" var="i">
               <c:choose>
                   <c:when test="${currentPage eq i}">
                       <td>${i}</td>
                   </c:when>
                   <c:otherwise>
                       <td><a href="item.do?q=${q}&currentPage=${i}&numResultsToSkip=${numResultsToSkip}&numResultsToReturn=${numResultsToReturn}">${i}</a></td>
                   </c:otherwise>
               </c:choose>
           </c:forEach>
       </tr>
    </table>
  </div>

   <%--For displaying Next link --%>
    <div id="next" align="center">
      <c:if test="<%= check%>">
          <td><a href="item.do?q=${q}&currentPage=${currentPage + 1}&numResultsToSkip=${numResultsToSkip}&numResultsToReturn=${numResultsToReturn}">Next</a></td>
      </c:if>
    </div>

 <% } else{ %>

    <h2 align="center">No Results Found for "<%=q%>" with given parameters</h2>
<% } %>
</div>
<% } else{ %>
<div class="noResults"> 
<h3 align="center">Please enter appropriate parameters</h2>
</div>
<% } %>
  </body>
</html>
