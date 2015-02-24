<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
  <head><title>Keyword Search Results</title></head>
  <body>
    <%
    resultItems = request.getAttribute("searchResult");
    %>
    <table>
      <c:forEach items="${resultItems}" var="item">
        <tr>
          <td><c:out value="{$item.getItemId()}" /></td>
          <td><c:out value="{$item.getName()}" /></td>
        </tr>
      </c:forEach>
    </table>
  </body>
</html>
