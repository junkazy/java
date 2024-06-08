package com.lgcns.tct.sp.sub3.microservice;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddServer {

    public static void main(String[] args) throws Exception {
        AddServer addServer = new AddServer();
        addServer.start();
    }

    public void start() throws Exception {
        Server server = new Server(8012);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        context.addServlet(new ServletHolder(new AddServlet()), "/*");

        server.setHandler(context);
        server.start();
        server.join();
    }

    public class AddServlet extends HttpServlet {

        private static final long serialVersionUID = 1L;

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            String requestURL = request.getRequestURL().toString();
            String path = requestURL.substring(requestURL.lastIndexOf("/") + 1);

            long id = Thread.currentThread().getId();
            System.out.println("[" + id + "] >> AddServlet.doGet - requestURL : " + request.getRequestURL().toString());
            System.out.println("[" + id + "] >> AddServlet.doGet - requestURI : " + request.getRequestURI().toString());
            System.out.println("[" + id + "] >> AddServlet.doGet - requestURI : " + request.getParameterMap().toString());
            System.out.println("[" + id + "] >> AddServlet.doGet - path : " + path);

            try {
                for (int i = 0; i < 3; i++) {
                    System.out.println("[" + id + "] >> AddServlet.doGet - Thread.sleep : " + i + "sec...");
                    Thread.sleep(1000);
                }

                response.setStatus(HttpServletResponse.SC_OK);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("{\"id\":\"ID_"+(id)+"\",\"total\":\""+(id)+"\"}");
                response.getWriter().close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
