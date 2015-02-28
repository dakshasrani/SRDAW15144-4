package edu.ucla.cs.cs144;

import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ItemServlet extends HttpServlet implements Servlet {

    public ItemServlet() {}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
      String itemId = request.getParameter("itemId");
      String xml = AuctionSearchClient.getXMLDataForItemId(itemId);

        request.setAttribute("xml", xml);
        request.getRequestDispatcher("/index1.jsp").forward(request, response);
    }
}
