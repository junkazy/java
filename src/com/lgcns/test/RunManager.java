package com.lgcns.test;

import java.util.Scanner;

import com.lgcns.test.routing.RoutingRule;

public class RunManager {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		String proxy = sc.next();
		String ServicePath = sc.next();
		sc.close();

		RoutingRule routingRule = new RoutingRule();
		String result = routingRule.routing(proxy + ".txt", ServicePath);

		System.out.println(result);

	}

}
