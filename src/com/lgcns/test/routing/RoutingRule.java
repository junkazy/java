package com.lgcns.test.routing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletHandler;

import com.google.gson.Gson;
import com.lgcns.test.proxy.Proxy;
import com.lgcns.test.proxy.ProxyDTO;
import com.lgcns.test.proxy.RouteDTO;

public class RoutingRule {

	public static List<RouteDTO> RouteList = new ArrayList<RouteDTO>();

	public void routing(String ServiceProxy) throws Exception {

		// 1) Gson �ν��Ͻ� ����
		Gson gson = new Gson();

		// 2) ��ü�� �ٲ� ���ڿ� ������ ����
		String json = readJson(ServiceProxy);

		// 3) ���� ���ϴ� ��ü�� fromJson(���ڿ�, �ٲܰ�ü.class)
		ProxyDTO proxyDTO = gson.fromJson(json, ProxyDTO.class);
		for (RouteDTO routeDTO : proxyDTO.getRoutes()) {
			RouteList.add(routeDTO);
		}

		Server server = new Server();
		ServerConnector http = new ServerConnector(server);
		http.setHost("127.0.0.1");
		http.setPort(proxyDTO.getPort());
		server.addConnector(http);
		ServletHandler servletHandler = new ServletHandler();
		servletHandler.addServletWithMapping(Proxy.class, "/*");
		server.setHandler(servletHandler);
		server.start();
		server.join();

	}

	private String readJson(String path) {
		StringBuilder sb = new StringBuilder();

		try (BufferedReader br = new BufferedReader(new FileReader(new File(path)))) {
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return sb.toString();
	}

}
