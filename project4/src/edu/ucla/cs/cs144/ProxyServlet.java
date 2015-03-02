package edu.ucla.cs.cs144;

import java.io.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProxyServlet extends HttpServlet implements Servlet {

    public ProxyServlet() {}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/xml");
        String query = request.getParameter("query");
        HttpURLConnection httpConnection = (HttpURLConnection) new URL("http://google.com/complete/search?output=toolbar&q="+query).openConnection();
        InputStream input = httpConnection.getInputStream();

        byte[] buffer = new byte[1024];
           int bytesRead;
           while ((bytesRead = input.read(buffer)) != -1)
           {
               response.getOutputStream().write(buffer, 0, bytesRead);
           }
    }
}
