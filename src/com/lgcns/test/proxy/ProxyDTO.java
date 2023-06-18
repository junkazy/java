package com.lgcns.test.proxy;

public class ProxyDTO {
	
	private int port;
	private RouteDTO[] routes;
	
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public RouteDTO[] getRoutes() {
		return routes;
	}
	public void setRoutes(RouteDTO[] routes) {
		this.routes = routes;
	}
	
}
