package com.lgcns.test.logging;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Logging {

	public static HashMap<String, ArrayList<String>> traceMap = new HashMap<String, ArrayList<String>>();

	public void doService(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!! Logging : " + req.getRequestURI());
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!! Logging : " + req.getQueryString());
		String URI = req.getRequestURI();
		String[] querys = req.getQueryString().split("&");

		HashMap<String, String> queryMap = new HashMap<String, String>();
		for (String query : querys) {
			String[] keyValue = query.split("=");
			queryMap.put(keyValue[0], keyValue[1]);
		}

		String requestId = URI.substring(URI.lastIndexOf("/")+1);
		if (traceMap.containsKey(requestId)) {
			System.out.println("YYY!!!!!!!!!!!!!    " + requestId);
			ArrayList<String> list = traceMap.get(requestId);
			list.add(req.getQueryString());
		} else {
			System.out.println("NNN!!!!!!!!!!!!!    " + requestId);
			ArrayList<String> list = new ArrayList<String>();
			list.add(req.getQueryString());
			traceMap.put(requestId, list);
		}

	}

}
