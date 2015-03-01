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
    <link rel="stylesheet" type="text/css" href="autosuggest.css" />
    <script type="text/javascript">
        window.onload = function () {
            var oTextbox = new AutoSuggestControl(document.getElementById("query"), new StateSuggestions());
        }
    </script>
    <style>
  	input{
  		font-size: 100%
  	}
  	</style>
  </head>

  <body>
    <% String query = (String)request.getAttribute("query");
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
    <div id="textbox" align="center">
      <form action="search">
        <h3>Enter the keyword to be searched</h3>
        <input type="text" name="query" id="query"><br><br>
        <input type="submit" value="Submit">
      </form>
    </div>

<% if (resultItems.length!=0) {%>
  <div id="resultTable" align="center">
    <table border = 1 cellpadding = "5" cellspacing = "5" align="center" >
        <h3><%= startPageNo%> to <%= endPageNo%> Search Results for "<%=query%>"</h3>
          <tr align="center" bgcolor="#9acd32">
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
              <td><a href="item?itemId=<%=itemId%>"><%= resultItems[(currentPage - 1)* recordsPerPage + item].getItemId() %></a></td>
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

  <%--For displaying Previous link except for the 1st page --%>
  <div id="previous" align="center">
    <c:if test="${currentPage != 1}">
        <td><a href="item.do?query=${query}&currentPage=${currentPage - 1}">Previous</a></td>
    </c:if>
  </div>

    <%--For displaying Page numbers.
  The when condition does not display a link for the current page--%>
  <div id="pageNo" align="center">
   <table border=1 cellpadding="5" cellspacing="5">
       <tr>
           <c:forEach begin="1" end="<%= noOfPages%>" var="i">
               <c:choose>
                   <c:when test="${currentPage eq i}">
                       <td>${i}</td>
                   </c:when>
                   <c:otherwise>
                       <td><a href="item.do?query=${query}&currentPage=${i}">${i}</a></td>
                   </c:otherwise>
               </c:choose>
           </c:forEach>
       </tr>
    </table>
  </div>

   <%--For displaying Next link --%>
    <div id="next" align="center">
      <c:if test="<%= check%>">
          <td><a href="item.do?query=${query}&currentPage=${currentPage + 1}">Next</a></td>
      </c:if>
    </div>

 <% } else{ %>

    <h2>No Results Found for "<%=query%>"</h2>
<% } %>
  </body>
</html>
