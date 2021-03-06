package edu.ucla.cs.cs144;

import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SearchServlet extends HttpServlet implements Servlet {

    public SearchServlet() {}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String query = request.getParameter("query");
        SearchResult[] searchResult = AuctionSearchClient.basicSearch(query,0,20);
        request.setAttribute("searchResult", searchResult);
        request.setAttribute("numResult",searchResult.length);
        request.getRequestDispatcher("/keywordSearchResults.jsp").forward(request, response);
    }
}
