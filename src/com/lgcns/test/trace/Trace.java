package com.lgcns.test.trace;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lgcns.test.logging.Logging;

public class Trace {

	public void doService(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("$$$$$$$$$$$$$$$$$$ Trace $$$$$$$$$$$$$$$$$$");
		String URI = req.getRequestURI();
		String requestId = URI.substring(URI.lastIndexOf("/")+1);
		System.out.println("$$$$$$$$$$$$$$$$$$ " + URI);
		System.out.println("$$$$$$$$$$$$$$$$$$ " + requestId);
		
		ArrayList<String> list = Logging.traceMap.get(requestId);
		for (String item : list) {
			System.out.println(item);
		}
		System.out.println("$$$$$$$$$$$$$$$$$$ Trace $$$$$$$$$$$$$$$$$$");
	}


}
