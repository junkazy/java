package com.lgcns.test;

import java.util.Scanner;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletHandler;

import com.lgcns.test.routing.RoutingRule;

public class RunManager {

	public static void main(String[] args) throws Exception {
		
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        sc.close();
        
        RoutingRule routingRule = new RoutingRule();
        String result = routingRule.routing(input);

        System.out.println(result);
		
//		new RunManager().start();
	}

//	public void start() throws Exception {
//		Server server = new Server();
//		ServerConnector http = new ServerConnector(server);
//		http.setHost("127.0.0.1");
//		http.setPort(8080);
//		server.addConnector(http);
//		ServletHandler servletHandler = new ServletHandler();
//		servletHandler.addServletWithMapping(Proxy.class, "/helloworld");
//		server.setHandler(servletHandler);
//		server.start();
//		server.join();
//	}

}
