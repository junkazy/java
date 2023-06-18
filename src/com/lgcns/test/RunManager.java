package com.lgcns.test;

import com.lgcns.test.routing.RoutingRule;

public class RunManager {

	public static void main(String[] args) throws Exception {
		RoutingRule routingRule = new RoutingRule();
		routingRule.routing(args[0]);
	}
}
