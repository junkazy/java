package com.lgcns.tct.sp.sub3.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lgcns.tct.sp.sub3.StateManager;
import com.lgcns.tct.sp.sub3.ValiableManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class EnginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestURL = request.getRequestURL().toString();
        String path = requestURL.substring(requestURL.lastIndexOf("/") + 1);

        System.out.println(">> EnginServlet.doGet - path : " + path);

        try {
            String result = StateManager.get(path).run();
            //System.out.println(">> EnginServlet.doGet - result : " + result);
            Map<String, String> map = ValiableManager.get();

            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("application/json");
            //response.getWriter().write(new GsonBuilder().setPrettyPrinting().create().toJson(map));
            response.getWriter().write(result);
            response.getWriter().close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
