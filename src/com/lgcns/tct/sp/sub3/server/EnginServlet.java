package com.lgcns.tct.sp.sub3.server;

import com.lgcns.tct.sp.sub3.StateManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EnginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestURL = request.getRequestURL().toString();
        String path = requestURL.substring(requestURL.lastIndexOf("/") + 1);

        System.out.println(">> EnginServlet.doGet - path : " + path);

        try {
            StateManager.get(path).run();
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
