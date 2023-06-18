package com.lgcns.test.proxy;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.api.Request;

import com.google.gson.Gson;
import com.lgcns.test.logging.Logging;
import com.lgcns.test.routing.RoutingRule;
import com.lgcns.test.trace.Trace;

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
				
				logging(req.getHeader("x-requestId"), req.getRequestURL().toString(), routeDTO.getUrl() + URI, 200);

				HttpClient httpClient = new HttpClient();
				try {
					httpClient.start();
					Request request = httpClient
							.newRequest(routeDTO.getUrl() + URI + (query != null ? "?" + query : ""));
					request.method(method.toUpperCase());
					Enumeration<String> headerNames = req.getHeaderNames();
					while (headerNames.hasMoreElements()) {
						String headerName = headerNames.nextElement();
						String headerValue = req.getHeader(headerName);
						if (headerName.startsWith("x-")) {
							System.out.println("## " + headerName + " :: " + headerValue);
							request.header(headerName, headerValue);
						}
					}
					request.header("x-callService", req.getRequestURL().toString());
					ContentResponse contentRes = request.send();

					res.setStatus(contentRes.getStatus());
					res.getWriter().write(contentRes.getContentAsString());
					
					logging(req.getHeader("x-requestId"), routeDTO.getUrl() + URI, routeDTO.getUrl() + URI, contentRes.getStatus());

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}

		if (URI.startsWith("/logging")) {
			Logging logging = new Logging();
			logging.doService(req, res);
			
		} else if (URI.startsWith("/trace")) {
			gettrace(URI);
			
		} else if (URI.startsWith("/gettrace")) {
			Trace trace = new Trace();
			trace.doService(req, res);
		}
	}

	private void logging(String requestId, String url, String target, int status) {

		System.out.println("##logging##########################################");
		System.out.println("## " + requestId);
		System.out.println("## " + url);
		System.out.println("## " + target);
		System.out.println("## " + status);
		System.out.println("##logging##########################################");
		String method = "get";
		String URI = "/logging";
		String query = "url="+url+"&target="+target+"&status="+status;
		
		HttpClient httpClient = new HttpClient();
		try {
			httpClient.start();
			Request request = httpClient
					.newRequest("http://127.0.0.1:5000" + URI + "/" + requestId + (query != null ? "?" + query : ""));
			request.method(method.toUpperCase());
			ContentResponse contentRes = request.send();
			
			System.out.println("##logging##########################################" + contentRes.getContentAsString());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void gettrace(String URI) {

		System.out.println("##trace##########################################");
		System.out.println("## " + URI);
		System.out.println("##trace##########################################");
		String method = "get";
		String getURI = "/gettrace";
		
		HttpClient httpClient = new HttpClient();
		try {
			httpClient.start();
			Request request = httpClient
					.newRequest("http://127.0.0.1:5000" + getURI + "/" + URI);
			request.method(method.toUpperCase());
			ContentResponse contentRes = request.send();
			
			System.out.println("##trace##########################################" + contentRes.getContentAsString());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
