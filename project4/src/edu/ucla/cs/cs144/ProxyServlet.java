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
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.Node;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class ProxyServlet extends HttpServlet implements Servlet {

    public ProxyServlet() {}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/xml");
       // String xmlString = "";
        String query = request.getParameter("query");
        PrintWriter out = response.getWriter();
        HttpURLConnection httpConnection = (HttpURLConnection) new URL("http://google.com/complete/search?output=toolbar&q="+query).openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
        String responseLine;
        StringBuffer appendedResponses = new StringBuffer();
        while ((responseLine = in.readLine()) != null){
            
            //out.println(responseLine);
            appendedResponses.append(responseLine);
        }
        out.println(appendedResponses);
        out.close();
    }
    
   /*public static String getCharacterDataFromElement(Element e) {
    Node child = (Node) e.getFirstChild();
    if (child instanceof CharacterData) {
      CharacterData cd = (CharacterData) child;
      return cd.getData();
    }
    return "";
  }*/
}
