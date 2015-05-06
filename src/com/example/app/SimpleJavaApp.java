/*
  $Id: $
  @file SimpleJavaApp.java
  @brief Contains the SimpleJavaApp.java class

  All Rights Reserved.

  @author Rahul Singh [rsingh]
*/
package com.example.app;

import javax.servlet.http.HttpServlet;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class SimpleJavaApp
{
    private Thread webServerThread = null;
    private Server server = null;
    private static final String LOGGING_LAYOUT = "%d{yyyy-MM-dd HH:mm:ss.S z}:[%p]:[%t]:%c:%m%n";
    public SimpleJavaApp(int port)
    {
        try
        {
            this.server = new Server(port);

            ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
            context.setContextPath("/");
            this.server.setHandler(context);
            HttpServlet servlet = new SimpleServlet();

            ServletHolder servletHolder = new ServletHolder(servlet);
            context.addServlet(servletHolder, "/*");
        }
        catch(Exception e)
        {
            throw(new RuntimeException(e));
        }
    }

    public void run()
    {
        try
        {
            this.server.start();
            this.server.join();
        }
        catch(Exception e)
        {
            throw(new RuntimeException(e));
        }
    }

    public static void main(String[] args)
    {
        Logger rootLogger = Logger.getRootLogger();

        if(args.length < 1)
            throw(new RuntimeException("Usage: SimpleJavaApp <port>"));
        rootLogger.addAppender(new ConsoleAppender(new PatternLayout(LOGGING_LAYOUT)));
        rootLogger.setLevel(Level.ERROR);

        int port = 0;
        try
        {
            port = Integer.parseInt(args[0]);
        }
        catch(NumberFormatException nfe)
        {
            throw(new RuntimeException(nfe));
        }

        SimpleJavaApp app = new SimpleJavaApp(port);
        app.run();
    }
}
