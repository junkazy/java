package com.lgcns.test.proxy;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;

import com.lgcns.test.routing.RoutingRule;

public class Proxy extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doService(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doService(req, res);
	}

	private void doService(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String method = req.getMethod();
		String URI = req.getRequestURI();
		String query = req.getQueryString();

		List<RouteDTO> RouteList = RoutingRule.RouteList;

		for (RouteDTO routeDTO : RouteList) {
			if (URI.startsWith(routeDTO.getPathPrefix())) {

				HttpClient httpClient = new HttpClient();
				try {
					httpClient.start();
					ContentResponse contentRes = httpClient
							.newRequest(routeDTO.getUrl() + URI + (query != null ? "?" + query : ""))
							.method(method.toUpperCase()).send();
					
					res.setStatus(contentRes.getStatus());
					res.getWriter().write(contentRes.getContentAsString());

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

}
