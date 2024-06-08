package com.lgcns.tct.sp.sub3.microservice;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateServer {

    public static void main(String[] args) throws Exception {
        CreateServer createServer = new CreateServer();
        createServer.start();
    }

    public void start() throws Exception {
        Server server = new Server(8011);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        context.addServlet(new ServletHolder(new CreateServlet()), "/*");

        server.setHandler(context);
        server.start();
        server.join();
    }

    public class CreateServlet extends HttpServlet {

        private static final long serialVersionUID = 1L;

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            String requestURL = request.getRequestURL().toString();
            String path = requestURL.substring(requestURL.lastIndexOf("/") + 1);

            System.out.println(">> CreateServlet.doGet - requestURL : " + request.getRequestURL().toString());
            System.out.println(">> CreateServlet.doGet - requestURI : " + request.getRequestURI().toString());
            System.out.println(">> CreateServlet.doGet - requestURI : " + request.getParameterMap().toString());
            System.out.println(">> CreateServlet.doGet - path : " + path);

            try {
                response.setStatus(HttpServletResponse.SC_OK);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("{\"key\":\"eH7bDVXX\"}");
                response.getWriter().close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
