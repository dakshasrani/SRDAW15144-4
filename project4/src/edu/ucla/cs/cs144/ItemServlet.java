package edu.ucla.cs.cs144;

import java.io.*;
import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ucla.cs.cs144.Item;

public class ItemServlet extends HttpServlet implements Servlet {

    public ItemServlet() {}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String itemId = request.getParameter("itemId");
        String xml = AuctionSearchClient.getXMLDataForItemId(itemId);
        
        if (!xml.equals("")) {
            Item item = new Item(xml);
            request.setAttribute("item",item);
            request.setAttribute("categories", item.getCategories());
            request.setAttribute("seller",item.getSeller());
            request.setAttribute("bids",item.getBids());
            request.setAttribute("bl", item.getBids().size());
        }
        else {
            request.setAttribute("flag","on");
        }
        
        request.getRequestDispatcher("/itemResults.jsp").forward(request, response);
        
    }
    
}
