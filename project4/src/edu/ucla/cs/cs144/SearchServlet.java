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
        String q = new String();
        request.setAttribute("error","no");
        int numResultsToSkip=0, numResultsToReturn=10;
        try{
            q = request.getParameter("q");
            if(q==null || q.equals(""))
                request.setAttribute("error","yes");
            if(request.getParameter("numResultsToSkip")!=null)
                numResultsToSkip = Integer.parseInt(request.getParameter("numResultsToSkip"));
            if(request.getParameter("numResultsToReturn")!=null)
                numResultsToReturn = Integer.parseInt(request.getParameter("numResultsToReturn"));
        }
        catch(NumberFormatException e)
        {
            request.setAttribute("error","yes");
        }
        
        SearchResult[] searchResult = AuctionSearchClient.basicSearch(q,numResultsToSkip,numResultsToReturn);

        request.setAttribute("q", q);
        request.setAttribute("numResultsToSkip",numResultsToSkip);
        request.setAttribute("numResultsToReturn",numResultsToReturn);
        request.setAttribute("searchResult", searchResult);
        request.setAttribute("numResult",searchResult.length);

        int recordsPerPage = 10;
        int page = 1;
        if(request.getParameter("currentPage") != null)
            page = Integer.parseInt(request.getParameter("currentPage"));

        request.setAttribute("recordsPerPage", recordsPerPage);
        request.setAttribute("currentPage", page);
        request.getRequestDispatcher("/keywordSearchResults.jsp").forward(request, response);
    }
}
