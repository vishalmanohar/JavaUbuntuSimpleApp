/*
  $Id: $
  @file SimpleServlet.java
  @brief Contains the SimpleServlet.java class

  All Rights Reserved.

  @author Rahul Singh [rsingh]
*/
package com.example.app;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;

public class SimpleServlet extends HttpServlet
{
    private long startTime = 0;
    public SimpleServlet()
    {
        startTime = System.currentTimeMillis();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        response.setContentType("text/html");
        long curTime = System.currentTimeMillis();
        long elapsed = curTime - startTime;

        int seconds = (int) (elapsed / 1000) % 60 ;
        int minutes = (int) ((elapsed / (1000*60)) % 60);
        int hours   = (int) ((elapsed / (1000*60*60)) % 24);

        response.getWriter().println("<h1>Hello World from Distelli. You have successfully deployed the Java App.</h1><i>Uptime: "+String.format("%02d:%02d:%02d", hours, minutes, seconds));
    }
}
